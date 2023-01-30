import React from 'react';
import { Route, Routes } from 'react-router-dom';
import UsersPage from './pages/UsersPage/UsersPage';
import LoginPage from "./pages/LoginPage/LoginPage";
import HomePage from "./pages/HomePage/HomePage";
import Registry from "./pages/RegistrationPage/RegistryPage";




function App() {
  return (
      <Routes>
          <Route path={'/login'} element={<LoginPage/>} />
          <Route path={'/users'} element={<UsersPage/>} />
          <Route path={'/'} element={<HomePage/>} />
          <Route path={'/registration'} element={<Registry/>} />

      </Routes>
  );
}

export default App;
