import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import HomePage from '../pages/HomePage';
import LoginPage from '../pages/LoginPage';
import AccountPage from '../pages/AccountPage';
import Button from 'react-bootstrap/Button';
import { useState, useEffect } from 'react';  
import { useNavigate } from "react-router-dom";

function NavBar() {
  const [userName, setUserName] = useState(null); 
  const navigate = useNavigate();

  const logout = () => {
    localStorage.removeItem('userId');
    localStorage.removeItem('token');
    localStorage.removeItem('email');
    setUserName(null); 
    navigate("/"); 
  };

  useEffect(() => {
    const storedUserName = localStorage.getItem('email');
    if (storedUserName) {
    setUserName(storedUserName);
    }
  }, []);

  const menuItems = [
    {
      id: 1,
      label: "Home",
      url: "/",
      element: <HomePage />
    },
    {
      id: 2,
      label: "Accounts",
      url: "/AccountPage",
      element: <AccountPage></AccountPage>
    },
    {
      id: 3,
      label: "Credits",
      url: "/credit",
      element: <></>
    },
  ];

  const registerMenuItems = [
    {
      id: 1,
      label: "Login",
      url: "/login",
      element: <LoginPage></LoginPage>
    },
    {
      id: 2,
      label: "Register",
      url: "/register",
      element: <></>
    }
  ];

  return (
    <Navbar className="bg-body-tertiary">
      <Container>
        <Navbar.Brand href="#home">BankApplication</Navbar.Brand>
        <Navbar.Toggle />
        <Navbar.Collapse className="justify-content-start">
          <Nav className="me-auto">
            {menuItems.map(item => (
              <Nav.Link key={item.id} href={item.url}>
                {item.label}
              </Nav.Link>
            ))}
          </Nav>
        </Navbar.Collapse>

        <Navbar.Collapse className="justify-content-end">
          <Nav>
            {userName ? (
              <>
                <Nav.Link>{`Hello, ${userName}!`}</Nav.Link>
                <Button variant="outline-danger" onClick={logout}>Logout</Button>  
              </>
            ) : (
              registerMenuItems.map(item => (
                <Button key={item.id} href={item.url} variant="outline-success">
                  {item.label}
                </Button>
              ))
            )}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default NavBar;
