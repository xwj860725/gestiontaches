import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './AuthPage.css';

function LoginPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

    // handle regular logins
  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password }),
      });

      if (response.ok) {
        alert('Login success！');
        navigate('/tasks'); // sauter à la page des taches
      } else {
        const errorMsg = await response.text();
        alert(errorMsg);
      }
    } catch (error) {
      console.error('login erronée:', error);
      alert('Login erronée,veuillez réessayer plus tard');
    }
  };

  // handle google logins, redirect directly to the backend OAuth2 authorization portal
  const handleGoogleLogin = () => {
    window.location.href = 'http://localhost:8080/oauth2/authorization/google';
  };

  return (
    <div className="container">
      <h2>Se connecter</h2>
      <form onSubmit={handleLogin} className="form">
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
        <button type="submit" className="loginButton">Se connecter</button>
      </form>

      {/* third-party login */}
      <div className="socialLogin">
        <button className="socialButton" onClick={handleGoogleLogin}>
        Se connecter avec Google 
        </button>
      </div>

      <p>Pas de compte？ <a href="/register">Créer un compte</a></p>
    </div>
  );
}

export default LoginPage;
