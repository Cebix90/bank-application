import { useState } from "react";
import { useForm } from "react-hook-form";
import { Form, Button, Row, Col, Container } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function LoginPage() {
  const navigate = useNavigate();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();
  const [loginError, setLoginError] = useState("");

  const handleLogin = async (data) => {
    try {
      const response = await axios.post(
        "http://localhost:8080/api/auth/login",
        data
      );
      console.log(data);
      localStorage.setItem("token", response.data.token); // Zapisz token JWT
      localStorage.setItem("userId", response.data.userId); // Zapisz ID użytkownika
      localStorage.setItem("email", data.email); // Zapisz ID użytkownika

      // console.log(response.data.name);
      //   localStorage.setItem("name",response.data.name);

      console.log(response.data);
      navigate("/");
      alert("Zalogowano pomyślnie!");
    } catch (error) {
      console.error("Błąd logowania:", error.response?.data || error.message);
      setLoginError("Nieprawidłowy email lub hasło");
    }
  };
  return (
    <Container
      className="d-flex justify-content-center align-items-center"
      style={{ minHeight: "100vh" }}
    >
      <Form
        onSubmit={handleSubmit(handleLogin)}
        className="shadow p-4 rounded bg-light w-100 w-md-50"
        style={{ maxWidth: "500px" }}
      >
        <h2 className="mb-4 text-center">Logowanie</h2>

        <Form.Group as={Row} className="mb-3" controlId="formGridEmail">
          <Form.Label column sm={2}>
            Email
          </Form.Label>
          <Col sm={10}>
            <Form.Control
              type="email"
              placeholder="Wprowadź email"
              {...register("email", {
                required: "Email jest wymagany",
                pattern: {
                  value: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
                  message: "Nieprawidłowy format email",
                },
              })}
              isInvalid={!!errors.email}
            />
            <Form.Control.Feedback type="invalid">
              {errors.email?.message}
            </Form.Control.Feedback>
          </Col>
        </Form.Group>

        <Form.Group as={Row} className="mb-3" controlId="formGridPassword">
          <Form.Label column sm={2}>
            Hasło
          </Form.Label>
          <Col sm={10}>
            <Form.Control
              type="password"
              placeholder="Wprowadź hasło"
              {...register("password", {
                required: "Hasło jest wymagane",
              })}
              isInvalid={!!errors.password}
            />
            <Form.Control.Feedback type="invalid">
              {errors.password?.message}
            </Form.Control.Feedback>
          </Col>
        </Form.Group>
        {loginError && <p className="text-danger">{loginError}</p>}
        <Button variant="danger" type="submit" className="w-100 mt-3">
          Zaloguj się
        </Button>
      </Form>
    </Container>
  );
}

export default LoginPage;
