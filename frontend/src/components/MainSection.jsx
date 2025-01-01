import { Container, Row, Col, Button, Card } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import { FaShieldAlt, FaPiggyBank, FaChartLine, FaUsers } from 'react-icons/fa';

function MainSection() {
  const navigate = useNavigate();

  return (
    <Container className="py-5">
      {/* Górny rząd z nagłówkiem */}
      <Row className="align-items-center">
        <Col lg={6} xs={12} className="mb-4 mb-lg-0">
          <h2 className="fw-bold">
            Otwórz konto i skorzystaj z naszej promocji zwrotu do
            <span className="text-danger"> 850 zł</span>
          </h2>
        </Col>
        {/* Prawa kolumna z grafiką */}
        <Col lg={6} xs={12} className="text-center">
          <img
            src="src/Images/cardVisa.jpg"
            alt="Promocja konta"
            className="img-fluid rounded"
          />
        </Col>
      </Row>

      {/* Środkowy rząd z dodatkowymi informacjami */}
      <Row className="mt-5">
        <Col>
          <h3 className="text-muted text-lg-start">
            i oszczędzaj nawet na <strong>7,5% w skali roku</strong> <br />
            na 3-miesięcznej lokacie
          </h3>
        </Col>
      </Row>

      {/* Sekcja z ikonami i korzyściami */}
      <Row className="mt-5">
        <Col lg={3} md={6} className="mb-4">
          <Card className="text-center p-3 shadow">
            <FaShieldAlt size={40} className="text-danger mb-3" />
            <Card.Body>
              <Card.Title>Bezpieczeństwo</Card.Title>
              <Card.Text>
                Pakiet ochrony Twoich finansów i porad dla użytkowników.
              </Card.Text>
            </Card.Body>
          </Card>
        </Col>

        <Col lg={3} md={6} className="mb-4">
          <Card className="text-center p-3 shadow">
            <FaPiggyBank size={40} className="text-danger mb-3" />
            <Card.Body>
              <Card.Title>Oszczędności</Card.Title>
              <Card.Text>
                Oprocentowanie nawet <strong>7,5% w skali roku</strong>!
              </Card.Text>
            </Card.Body>
          </Card>
        </Col>

        <Col lg={3} md={6} className="mb-4">
          <Card className="text-center p-3 shadow">
            <FaChartLine size={40} className="text-danger mb-3" />
            <Card.Body>
              <Card.Title>Stabilny wzrost</Card.Title>
              <Card.Text>
                Twoje oszczędności pracują dla Ciebie każdego dnia.
              </Card.Text>
            </Card.Body>
          </Card>
        </Col>

        <Col lg={3} md={6} className="mb-4">
          <Card className="text-center p-3 shadow">
            <FaUsers size={40} className="text-danger mb-3" />
            <Card.Body>
              <Card.Title>Wsparcie 24/7</Card.Title>
              <Card.Text>
                Zespół wsparcia dostępny przez całą dobę, 7 dni w tygodniu.
              </Card.Text>
            </Card.Body>
          </Card>
        </Col>
      </Row>

      {/* Dolny rząd z przyciskiem */}
      <Row className="mt-4">
        <Col lg={6} xs={12} className="d-flex flex-column align-items-start">
          <Button
            variant="danger"
            size="lg"
            className="mb-3"
            onClick={() => navigate('/register')}
          >
            Załóż konto przez aplikację
          </Button>
          <Button
            variant="outline-danger"
            size="lg"
            onClick={() => navigate('/offer')}
          >
            Dowiedz się więcej
          </Button>
        </Col>
      </Row>

      {/* Opinie klientów */}
      <Row className="mt-5">
        <Col>
          <h4 className="text-center mb-4 fw-bold">Co mówią nasi klienci?</h4>
          <Card className="border-0 shadow-sm p-3 mb-3">
            <Card.Body>
              <blockquote className="blockquote mb-0">
                <p>
                &quot; Dzięki promocji założyłem konto w kilka minut, a moje oszczędności
                  już zaczynają przynosić zyski!&quot;
                </p>
                <footer className="blockquote-footer">Jan Kowalski</footer>
              </blockquote>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
}

export default MainSection;
