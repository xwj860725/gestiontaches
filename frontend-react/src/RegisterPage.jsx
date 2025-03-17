import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './AuthPage.css'; 

function RegisterPage() {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('http://localhost:8080/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, email, password }),
      });

      if (response.ok) {
        alert('Inscription réussi ! Veuillez vous connecter');
        navigate('/'); // jump to connection page
      } else {
        const errorMsg = await response.text();
        alert(errorMsg);
      }
    } catch (error) {
      console.error('Inscription erronée:', error);
      alert('Inscription erronée,veuillez réessayer plus tard');
    }
  };

  return (
    <div className="container">
      <h2>Créer un compte</h2>
      <form onSubmit={handleRegister} className="form">
        <input
          type="text"
          placeholder="username"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
          className="input"
        />
        <input
          type="email"
          placeholder="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
          className="input"
        />
        <input
          type="password"
          placeholder="mot de passe"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
          className="input"
        />
        <button type="submit" className="registerButton">Créer un compte</button>
      </form>
      <p>Déjà un compte？ <a href="/">Se connecter</a></p>
    </div>
  );
}

export default RegisterPage;
