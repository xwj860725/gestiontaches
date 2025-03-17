import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import TasksPage from './TasksPage';
import LoginPage from './LoginPage';
import RegisterPage from './RegisterPage';
import './App.css';

function App() {
  return (
    <Routes>
      {/* redirect to the login page when accessing the root path */}
      <Route path="/" element={<Navigate to="/login" replace />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/register" element={<RegisterPage />} />
      <Route path="/tasks" element={<TasksPage />} />
    </Routes>
  );
}

export default App;
