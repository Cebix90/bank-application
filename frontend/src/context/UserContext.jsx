// /* eslint-disable react/prop-types */
// import  { createContext, useState, useContext } from "react";

// // Tworzymy kontekst
// const UserContext = createContext();

// // Custom hook do łatwego dostępu do kontekstu
// export const useUser = () => useContext(UserContext);

// export const UserProvider = ({ children }) => {
//   const [userName, setUserName] = useState(null);
//   const login = (name) => setUserName(name);


//   return (
//     <UserContext.Provider value={{ userName, login, logout }}>
//       {children}
//     </UserContext.Provider>
//   );
// };
  