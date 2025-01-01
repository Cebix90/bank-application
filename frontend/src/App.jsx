// import './App.css'
import { Routes, Route} from 'react-router-dom';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import AccountPage from './pages/AccountPage'

function App() {

  return (
      <Routes>
        <Route path="/" element={<HomePage/>}/>
        <Route path='/AccountPage' element={<AccountPage/>}/>
        <Route path='/login' element={<LoginPage/>}></Route>
        <Route path='/register' element={<RegisterPage/>}></Route>

      </Routes>
  )
}

export default App
