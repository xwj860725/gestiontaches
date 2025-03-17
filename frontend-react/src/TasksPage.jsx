import React, { useState, useEffect } from 'react';
import './TasksPage.css'; 

const TasksPage = () => {
  const [tasks, setTasks] = useState([]);           // Storing task lists
  const [showAddModal, setShowAddModal] = useState(false);     // Control the display of the "add task" modal box
  const [newTitle, setNewTitle] = useState('');      // New task's name
  const [newDescription, setNewDescription] = useState(''); // New task's description
  
  const [showSearchModal, setShowSearchModal] = useState(false); // Control the "search task" modal box display
  const [searchKeyword, setSearchKeyword] = useState('');        // Search keyword
  const [isSearchMode, setIsSearchMode] = useState(false);         // Whether or not  in the search status

  // Load all tasks
  const loadTasks = () => {
    fetch('http://localhost:8080/tasks', { mode: 'cors' })
      .then(response => {
        if (!response.ok) {
          throw new Error(`Failed to load task, status code: ${response.status}`);
        }
        return response.json();
      })
      .then(data => {
        setTasks(data);
        setIsSearchMode(false);
      })
      .catch(error => console.error('Error fetching tasks:', error));
  };

  useEffect(() => {
    loadTasks();
  }, []);

  // Delete tasks
  const handleDelete = (id) => {
    fetch(`http://localhost:8080/tasks/${id}`, { method: 'DELETE', mode: 'cors' })
      .then(response => {
        if (!response.ok) {
          throw new Error(`Delete task failed, status code: ${response.status}`);
        }
        // Assume that no content is returned after a successful deletion
        setTasks(tasks.filter(task => task.id !== id));
      })
      .catch(error => {
        console.error('Error deleting task:', error);
        alert('Failed to delete tasks');
      });
  };

  // Add tasks
  const handleAddTask = (e) => {
    e.preventDefault();
    if (!newTitle.trim()) {
      alert('Task name cannot be empty！');
      return;
    }
    fetch('http://localhost:8080/tasks', {
      method: 'POST',
      mode: 'cors',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ title: newTitle, description: newDescription, completed: false })
    })
      .then(response => {
        if (!response.ok) {
          return response.text().then(text => { throw new Error(text) });
        }
        return response.json();
      })
      .then(newTask => {
        setTasks([...tasks, newTask]);
        setShowAddModal(false);
        setNewTitle('');
        setNewDescription('');
      })
      .catch(error => {
        console.error('Error adding task:', error);
        alert(error.message);
      });
  };

  // Search tasks
  const handleSearchTask = (e) => {
    e.preventDefault();
    fetch(`http://localhost:8080/tasks/search?keyword=${encodeURIComponent(searchKeyword)}`, { mode: 'cors' })
      .then(response => {
        if (!response.ok) {
          return response.text().then(text => { throw new Error(text) });
        }
        return response.json();
      })
      .then(data => {
        setTasks(data);
        setIsSearchMode(true);
        setShowSearchModal(false);
      })
      .catch(error => {
        console.error('Error searching tasks:', error);
        alert('Search task failed: ' + error.message);
      });
  };

  // Reset search and reload all tasks
  const handleResetSearch = () => {
    loadTasks();
    setSearchKeyword('');
  };

  // Log out and jump back to login page
  const handleLogout = () => {
    window.location.href = '/login';
  };

  return (
    <div className="container1">
      {/* Top area: add, search, reset button and bottom area: logout button  */}
      <div className="topBar">
        <button className="addTaskButton" onClick={() => setShowAddModal(true)}>
          + add task
        </button>
        <button className="searchTaskButton" onClick={() => setShowSearchModal(true)}>
          search task
        </button>
        {isSearchMode && (
          <button className="resetSearchButton" onClick={handleResetSearch}>
            Back to all the tasks
          </button>
        )}
        <button className="logoutButton" onClick={handleLogout}>
          Logout
        </button>
      </div>

      {/* Show task card list*/}
      {tasks.map((task) => (
        <div key={task.id} className="task-card">
          <span className="name">{task.title} - {task.description}</span>
          <button className="deleteButton" onClick={() => handleDelete(task.id)}>
            delete
          </button>
        </div>
      ))}

      {/* Add task modal box */}
      {showAddModal && (
        <div className="modalOverlay">
          <div className="modalContent">
            <h3>Add new task</h3>
            <form onSubmit={handleAddTask} className="modalForm">
              <input 
                type="text" 
                placeholder="task name" 
                value={newTitle} 
                onChange={(e) => setNewTitle(e.target.value)} 
                required 
                className="input"
              />
              <textarea 
                placeholder="task description" 
                value={newDescription} 
                onChange={(e) => setNewDescription(e.target.value)} 
                className="input"
              />
              <div className="modalButtons">
                <button type="submit" className="addTaskButton">add</button>
                <button type="button" className="cancelButton" onClick={() => setShowAddModal(false)}>cancel</button>
              </div>
            </form>
          </div>
        </div>
      )}

      {/* Search task modal box */}
      {showSearchModal && (
        <div className="modalOverlay">
          <div className="modalContent">
            <h3>Search task</h3>
            <form onSubmit={handleSearchTask} className="modalForm">
              <input 
                type="text" 
                placeholder="input keyword" 
                value={searchKeyword} 
                onChange={(e) => setSearchKeyword(e.target.value)} 
                required 
                className="input"
              />
              <div className="modalButtons">
                <button type="submit" className="searchTaskButton">search</button>
                <button type="button" className="cancelButton" onClick={() => setShowSearchModal(false)}>cancel</button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default TasksPage;
