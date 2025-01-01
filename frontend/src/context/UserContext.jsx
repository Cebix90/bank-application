import  { createContext, useState, useContext } from "react";

// Tworzymy kontekst
const UserContext = createContext();

// Custom hook do łatwego dostępu do kontekstu
export const useUser = () => useContext(UserContext);

export const UserProvider = ({ children }) => {
  const [userName, setUserName] = useState(null);

  // Funkcja logowania - ustawia nazwę użytkownika
  const login = (name) => setUserName(name);

  // Funkcja wylogowania - resetuje stan użytkownika
  const logout = () => setUserName(null);

  return (
    <UserContext.Provider value={{ userName, login, logout }}>
      {children}
    </UserContext.Provider>
  );
};
