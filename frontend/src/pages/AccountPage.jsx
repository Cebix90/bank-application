import  { useEffect, useState } from 'react';
import { Container, Row, Col, Card,Spinner } from 'react-bootstrap';
import { FaUserCircle, FaWallet } from 'react-icons/fa';
import FooterSection from '../components/FooterSection';
import axios from 'axios';
import NavBar from '../components/NavBar';
function AccountPage() {
  const [user, setUser] = useState(null); 
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null); 
  const transactions = [];
  const fetchUserData = async () => {
    try {
      const userId = localStorage.getItem('userId'); 
      const token = localStorage.getItem('token');
  
      if (!userId || !token) throw new Error('Brak zalogowanego użytkownika lub tokenu');
  
      const response = await axios.get(
        `http://localhost:8080/api/custom-users/${userId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );
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
      <NavBar/>
      <div className="d-flex flex-column min-vh-100">
      <Container className="py-5 ">
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
        {/* Historia transakcji */}
      <Row className="justify-content-center mt-5">
        <Col md={8}>
          <h4 className="fw-bold text-center mb-4">Historia transakcji</h4>
          {transactions && transactions.length > 0 ? (
            <table className="table table-striped table-bordered">
              <thead className="table-light">
                <tr>
                  <th>#</th>
                  <th>Data</th>
                  <th>Opis</th>
                  <th>Kwota</th>
                  <th>Status</th>
                </tr>
              </thead>
              <tbody>
                {transactions.map((transaction, index) => (
                  <tr key={transaction.id}>
                    <td>{index + 1}</td>
                    <td>{new Date(transaction.date).toLocaleDateString('pl-PL')}</td>
                    <td>{transaction.description}</td>
                    <td
                      className={
                        transaction.amount > 0 ? 'text-success' : 'text-danger'
                      }
                    >
                      {transaction.amount.toLocaleString('pl-PL', {
                        style: 'currency',
                        currency: 'PLN',
                      })}
                    </td>
                    <td>{transaction.status}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          ) : (
            <p className="text-center text-muted">Brak historii transakcji.</p>
          )}
        </Col>
      </Row>
      </Container>
      </div>
      <FooterSection />
    </>
  );
}

export default AccountPage;
