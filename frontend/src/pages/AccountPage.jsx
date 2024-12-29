import  { useEffect, useState } from 'react';
import { Container, Row, Col, Card,Spinner } from 'react-bootstrap';
import { FaUserCircle, FaWallet } from 'react-icons/fa';
import FooterSection from '../components/FooterSection';
import axios from 'axios';

function AccountPage() {
  const [user, setUser] = useState(null); 
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null); 
  const fetchUserData = async () => {
    try {
      
       const userId = localStorage.getItem('userId'); 
      console.log('User ID:', userId);
      if (!userId) throw new Error('Brak zalogowanego użytkownika');
      const response = await axios.get(`http://localhost:8080/api/custom-users/${userId}`);
      setUser(response.data);
    } catch (err) {
      setError(err.message || 'Wystąpił błąd podczas ładowania danych');
    } finally {
      setLoading(false); 
    }
  };

  useEffect(() => {
    fetchUserData();
  }, []);

  if (loading) {
    return (
      <Container className="py-5 text-center">
        <Spinner animation="border" variant="primary" />
      </Container>
    );
  }

  // Obsługa błędu
  if (error) {
    return (
      <Container className="py-5 text-center">
        <p className="text-danger">{error}</p>
      </Container>
    );
  }

  return (
    <>
      <Container className="py-5">
        <Row className="mb-4">
          <Col>
            <h2 className="fw-bold text-center">Twoje konto</h2>
            <p className="text-muted text-center">
              Sprawdź swoje dane i aktualne saldo.
            </p>
          </Col>
        </Row>

        {/* Karta użytkownika */}
        <Row className="justify-content-center">
          <Col md={6}>
            <Card className="shadow-lg">
              <Card.Body>
                {/* Dane użytkownika */}
                <div className="d-flex align-items-center mb-4">
                  <FaUserCircle size={50} className="text-danger me-3" />
                  <div>
                    <h4 className="mb-0">{`${user.name} ${user.surname}`}</h4>
                    <small className="text-muted">{user.email}</small>
                  </div>
                </div>

                {/* Sekcja salda */}
                <div className="d-flex align-items-center justify-content-between mb-3">
                  <h5 className="fw-bold">Saldo:</h5>
                  <div className="d-flex align-items-center">
                    <FaWallet size={30} className="text-success me-2" />
                    <h4 className="mb-0 text-success">
                      {user.balance ? user.balance.toLocaleString('pl-PL', {
                        style: 'currency',
                        currency: 'PLN',
                      }) : '0.00 PLN'}
                    </h4>
                  </div>
                </div>

                {/* Dodatkowa informacja */}
                <p className="text-muted text-center">
                  Twoje środki są bezpieczne. Dziękujemy za zaufanie!
                </p>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
      <FooterSection />
    </>
  );
}
//   return (
//     <>
//     <Container className="py-5">
//       {/* Nagłówek strony */}
//       <Row className="mb-4">
//         <Col>
//           <h2 className="fw-bold text-center">Twoje konto</h2>
//           <p className="text-muted text-center">
//             Sprawdź swoje dane i aktualne saldo.
//           </p>
//         </Col>
//       </Row>

//       {/* Sekcja z kartą użytkownika */}
//       <Row className="justify-content-center">
//         <Col md={6}>
//           <Card className="shadow-lg">
//             <Card.Body>
//               {/* Sekcja z ikoną i danymi użytkownika */}
//               <div className="d-flex align-items-center mb-4">
//                 <FaUserCircle size={50} className="text-danger me-3" />
//                 <div>
//                   <h4 className="mb-0">{`${user.firstName} ${user.lastName}`}</h4>
//                   <small className="text-muted">Klient banku</small>
//                 </div>
//               </div>

//               {/* Sekcja salda */}
//               <div className="d-flex align-items-center justify-content-between mb-3">
//                 <h5 className="fw-bold">Saldo:</h5>
//                 <div className="d-flex align-items-center">
//                   <FaWallet size={30} className="text-success me-2" />
//                   <h4 className="mb-0 text-success">
//                     {user.balance.toLocaleString('pl-PL', {
//                       style: 'currency',
//                       currency: 'PLN',
//                     })}
//                   </h4>
//                 </div>
//               </div>

//               {/* Dodatkowa informacja */}
//               <p className="text-muted text-center">
//                 Twoje środki są bezpieczne. Dziękujemy za zaufanie!
//               </p>
//             </Card.Body>
//           </Card>
//         </Col>
//       </Row>
//     </Container>
//     <FooterSection/>
//     </>
//   );
// }

export default AccountPage;
