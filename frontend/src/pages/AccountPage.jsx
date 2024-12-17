import React from 'react';
import { Container, Row, Col, Card } from 'react-bootstrap';
import { FaUserCircle, FaWallet } from 'react-icons/fa';
import FooterSection from '../components/FooterSection';
function AccountPage() {
  // Przykładowe dane użytkownika
  const user = {
    firstName: 'Jan',
    lastName: 'Kowalski',
    balance: 12500.75, // saldo w PLN
  };

  return (
    <>
    <Container className="py-5">
      {/* Nagłówek strony */}
      <Row className="mb-4">
        <Col>
          <h2 className="fw-bold text-center">Twoje konto</h2>
          <p className="text-muted text-center">
            Sprawdź swoje dane i aktualne saldo.
          </p>
        </Col>
      </Row>

      {/* Sekcja z kartą użytkownika */}
      <Row className="justify-content-center">
        <Col md={6}>
          <Card className="shadow-lg">
            <Card.Body>
              {/* Sekcja z ikoną i danymi użytkownika */}
              <div className="d-flex align-items-center mb-4">
                <FaUserCircle size={50} className="text-danger me-3" />
                <div>
                  <h4 className="mb-0">{`${user.firstName} ${user.lastName}`}</h4>
                  <small className="text-muted">Klient banku</small>
                </div>
              </div>

              {/* Sekcja salda */}
              <div className="d-flex align-items-center justify-content-between mb-3">
                <h5 className="fw-bold">Saldo:</h5>
                <div className="d-flex align-items-center">
                  <FaWallet size={30} className="text-success me-2" />
                  <h4 className="mb-0 text-success">
                    {user.balance.toLocaleString('pl-PL', {
                      style: 'currency',
                      currency: 'PLN',
                    })}
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
    <FooterSection/>
    </>
  );
}

export default AccountPage;
