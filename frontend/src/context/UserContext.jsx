// <<<<<<< Frontend/feature/EditLoginRegisterPages
// // import  { createContext, useState, useContext } from "react";
// =======
// import  { createContext, useState, useContext } from "react";

// // Tworzymy kontekst
// const UserContext = createContext();
// >>>>>>> main

// // Custom hook do łatwego dostępu do kontekstu
// export const useUser = () => useContext(UserContext);

// export const UserProvider = ({ children }) => {
//   const [userName, setUserName] = useState(null);

// <<<<<<< Frontend/feature/EditLoginRegisterPages
// // export const UserProvider = ({ children }) => {
// //   const [userName, setUserName] = useState(null);
// =======
//   // Funkcja logowania - ustawia nazwę użytkownika
//   const login = (name) => setUserName(name);
// >>>>>>> main

//   // Funkcja wylogowania - resetuje stan użytkownika
//   const logout = () => setUserName(null);

// <<<<<<< Frontend/feature/EditLoginRegisterPages
// //   // Funkcja wylogowania - resetuje stan użytkownika
// //   const logout = () => setUserName(null);

// //   return (
// //     <UserContext.Provider value={{ userName, login, logout }}>
// //       {children}
// //     </UserContext.Provider>
// //   );
// // };
// =======
//   return (
//     <UserContext.Provider value={{ userName, login, logout }}>
//       {children}
//     </UserContext.Provider>
//   );
// };
// >>>>>>> main
