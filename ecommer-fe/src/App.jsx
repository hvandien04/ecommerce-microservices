import React from 'react';
import { BrowserRouter as Router, Routes, Route, useLocation, Navigate } from 'react-router-dom';
import './App.css'
import Header from './components/layout/header';
import Footer from './components/layout/footer';

function AppContent() {

  return (
    <>
      <div className='app'>
        <Header/>
      </div>
    </>
  )
}

function App() {
  return (
          <Router>
            <AppContent/>
          </Router>
  );
}

export default App
