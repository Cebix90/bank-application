import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function RegisterPage() {
const navigate = useNavigate();
const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const handleRegistration = async (data) => {
    try {
        console.log(data);
        const postalCodeAsInt = parseInt(data.zip, 10);

        if (isNaN(postalCodeAsInt)) {
            throw new Error("Invalid postal code: must be a number");
        }
      const requestBody = {
        user: {
          name: data.name,
          surname: data.surname,
          email: data.email,
          password: data.password,
        },
        address: {
          street: data.address1,
          houseNumber: data.address2,
          city: data.city,
          postalCode: postalCodeAsInt,
          country: data.country,
        },
      };
      console.log(requestBody);
      const response = await axios.post(
        "http://localhost:8080/api/auth/register", 
        requestBody,
        {
        headers: { "Content-Type": "application/json" }
        }

      );
      
      console.log("Rejestracja zakończona sukcesem:", response.data);
      alert("Zarejestrowano użytkownika. Informacja z serwera: " + response.data);
       navigate("/login");
    } catch (error) {
      if (error.response) {
        console.error("Błąd serwera:", error.response.data);
        alert("Błąd podczas rejestracji: " + error.response.data);
      } else {
        console.error("Błąd po stronie klienta:", error);
        alert("Wystąpił problem z połączeniem.");
      }
    }
  };
    
    const countriesTable = [
        {
            value:"PL",
            name:"Polska"
        },
        {
            value:"USA",
            name:"USA"
        }
    ]
    return (
      <div className="container mt-5">
          <h2 className="mb-4 text-center">Utwórz konto </h2>
          <Form onSubmit={handleSubmit(handleRegistration)} className="shadow p-4 rounded bg-light">
              <Row className="mb-4">
                  <Form.Group as={Col} controlId="formGridName" lg={6} sm={12}>
                      <Form.Label>Imię</Form.Label>
                      <Form.Control
                          placeholder="Imię"
                          {...register('name', { required: 'Imię jest wymagane' })}
                          isInvalid={!!errors.name}
                      />
                      <Form.Control.Feedback type="invalid">
                          {errors.name?.message}
                      </Form.Control.Feedback>
                  </Form.Group>

                  <Form.Group as={Col} controlId="formGridSurname" lg={6} sm={12}>
                      <Form.Label>Nazwisko</Form.Label>
                      <Form.Control
                          placeholder="Nazwisko"
                          {...register('surname', { required: 'Nazwisko jest wymagane' })}
                          isInvalid={!!errors.surname}
                      />
                      <Form.Control.Feedback type="invalid">
                          {errors.surname?.message}
                      </Form.Control.Feedback>
                  </Form.Group>
              </Row>

              <Row className="mb-4">
                  <Form.Group as={Col} controlId="formGridEmail" lg={6} sm={12}>
                      <Form.Label>Email</Form.Label>
                      <Form.Control
                          type="email"
                          placeholder="Wprowadź email"
                          {...register('email', {
                              required: 'Email jest wymagany',
                              pattern: {
                                  value: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
                                  message: 'Nieprawidłowy format email'
                              }
                          })}
                          isInvalid={!!errors.email}
                      />
                      <Form.Control.Feedback type="invalid">
                          {errors.email?.message}
                      </Form.Control.Feedback>
                  </Form.Group>

                  <Form.Group as={Col} controlId="formGridPassword" lg={6} sm={12}>
                      <Form.Label>Hasło</Form.Label>
                      <Form.Control
                          type="password"
                          placeholder="Wprowadź hasło"
                          {...register('password', {
                              required: 'Hasło jest wymagane',
                              minLength: { value: 6, message: 'Hasło musi mieć co najmniej 6 znaków' }
                          })}
                          isInvalid={!!errors.password}
                      />
                      <Form.Control.Feedback type="invalid">
                          {errors.password?.message}
                      </Form.Control.Feedback>
                  </Form.Group>
              </Row>

              <Form.Group className="mb-4" controlId="formGridAddress1">
                  <Form.Label>Adres</Form.Label>
                  <Form.Control
                      placeholder="1234 Główna Ulica"
                      {...register('address1', { required: 'Adres jest wymagany' })}
                      isInvalid={!!errors.address1}
                  />
                  <Form.Control.Feedback type="invalid">
                      {errors.address1?.message}
                  </Form.Control.Feedback>
              </Form.Group>

              <Form.Group className="mb-4" controlId="formGridAddress2">
                  <Form.Label>Adres 2</Form.Label>
                  <Form.Control
                      placeholder="Mieszkanie, studio lub piętro"
                      {...register('address2')}
                  />
              </Form.Group>

              <Row className="mb-4">
                  <Form.Group as={Col} controlId="formGridCity" lg={6} sm={12}>
                      <Form.Label>Miasto</Form.Label>
                      <Form.Control
                          {...register('city', { required: 'Miasto jest wymagane' })}
                          isInvalid={!!errors.city}
                      />
                      <Form.Control.Feedback type="invalid">
                          {errors.city?.message}
                      </Form.Control.Feedback>
                  </Form.Group>

                  <Form.Group as={Col} controlId="formGridState" lg={6} sm={12}>
                      <Form.Label>Kraj</Form.Label>
                      <Form.Select
                          {...register('country', { required: 'Kraj jest wymagany' })}
                          isInvalid={!!errors.country}
                      >
                          {countriesTable.map((country) => (
                              <option key={country.value} value={country.value}>{country.name}</option>
                          ))}
                      </Form.Select>
                      <Form.Control.Feedback type="invalid">
                          {errors.country?.message}
                      </Form.Control.Feedback>
                  </Form.Group>

                  <Form.Group as={Col} controlId="formGridZip" lg={6} sm={12}>
                      <Form.Label>Kod pocztowy</Form.Label>
                      <Form.Control
                          {...register('zip', { required: 'Kod pocztowy jest wymagany' })}
                          isInvalid={!!errors.zip}
                      />
                      <Form.Control.Feedback type="invalid">
                          {errors.zip?.message}
                      </Form.Control.Feedback>
                  </Form.Group>
              </Row>

              <Button variant="danger" type="submit" className="w-100 mt-3">
                  Zarejestruj się
              </Button>
          </Form>
      </div>
  );
}

export default RegisterPage;