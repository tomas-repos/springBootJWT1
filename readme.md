# Spring Boot JWT Authentication

Este proyecto demuestra cómo implementar autenticación con **JSON Web Tokens (JWT)** en una aplicación Spring Boot.

## 🚀 Funcionalidades
- **Login**: el usuario se autentica con usuario y contraseña y recibe un JWT.
- **Refresh**: permite renovar el token antes de que expire.
- **Endpoints protegidos**: acceso solo con un token válido en el header `Authorization: Bearer <token>`.
- **Roles**: control de acceso según rol (`ROLE_USER`, `ROLE_ADMIN`).

## 🛠️ Tecnologías usadas
- Java 17
- Spring Boot
- Spring Security
- PostgreSQL
- JWT (jjwt)

## 📦 Configuración
En `application.properties` se definen las propiedades:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/authentication
spring.datasource.username=demo_user
spring.datasource.password=demo_pass
jwt.secret=demo-secret-key-1234567890
jwt.expiration=3600000


Configurar base de datos

Crear una base de datos PostgreSQL llamada authentication.

Usuario y contraseña de prueba:

properties
spring.datasource.username=demo_user
spring.datasource.password=demo_pass
Configurar JWT
En application.properties:

properties
jwt.secret=demo-secret-key-1234567890
jwt.expiration=3600000
Compilar y ejecutar

bash
mvn spring-boot:run
Probar con Postman

Login: POST http://localhost:8080/auth/login

json
{
  "username": "juan",
  "password": "1234"
}
Refresh: POST http://localhost:8080/auth/refresh

json
{
  "token": "<token_actual>"
}
Endpoint protegido: GET http://localhost:8080/hello  
Header:

Código
Authorization: Bearer <token>