// import './App.css'
import { Routes, Route, Outlet} from 'react-router-dom';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import AccountPage from './pages/AccountPage'
import { UserProvider } from './context/UserContext';

function App() {

  return (
    <UserProvider>
      <Routes>
        <Route path="/" element={<HomePage/>}/>
        <Route path='/AccountPage' element={<AccountPage/>}/>
        <Route path='/login' element={<LoginPage/>}></Route>
        <Route path='/register' element={<RegisterPage/>}></Route>

      </Routes>
    </UserProvider>
  )
}

export default App
