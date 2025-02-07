# Pricing Service - Microservicio de Precios en Spring Boot

Este proyecto es un **microservicio REST** desarrollado en **Spring Boot**, que permite consultar los precios de productos en función de una fecha de aplicación, un identificador de producto y una marca.  
El sistema usa una **base de datos en memoria (H2)** y sigue una **arquitectura hexagonal**, respetando principios **SOLID** y buenas prácticas de desarrollo.

---

## Características del Proyecto

✔ **Spring Boot 3.4.2** con **Java 17**  
✔ **Arquitectura Hexagonal** (Separación de dominio, aplicación e infraestructura)  
✔ **Base de datos en memoria (H2)** con carga automática de datos  
✔ **API REST con Spring Web**  
✔ **Manejo de excepciones centralizado** con `@RestControllerAdvice`  
✔ **Pruebas unitarias** con **JUnit y Mockito**  
✔ **Pruebas de integración** con **Spring Boot Test y TestRestTemplate**  
✔ **Código limpio y desacoplado**, siguiendo principios SOLID

---

## Cómo Ejecutar la Aplicación

### **Requisitos Previos**
- **Java 17** o superior
- **Maven 3.6+**
- (Opcional) Postman o `cURL` para probar la API

### ▶ **Ejecutar desde Maven**
```bash
mvn clean install
mvn spring-boot:run
```

### ▶ **Ejecutar desde Docker**
Si deseas ejecutar la aplicación con **Docker**, puedes construir y correr la imagen con:

```bash
docker build -t pricing-service .
docker run -p 8080:8080 pricing-service
```

---

## API REST - Documentación

### **Endpoint Principal**
**URL Base**: `http://localhost:8080/api/prices`  
Método: **GET**  
Consulta precios con diferentes filtros opcionales.

### **Ejemplo de Consulta (Con Filtros)**
```bash
curl -X GET "http://localhost:8080/api/prices?date=2020-06-14T16:00:00&productId=35455&brandId=1"
```

**Respuesta esperada (`200 OK`)**
```json
[
  {
    "productId": 35455,
    "brandId": 1,
    "priceList": 2,
    "startDate": "2020-06-14T15:00:00",
    "endDate": "2020-06-14T18:30:00",
    "price": 25.45
  }
]
```

### **Ejemplo de Consulta (Sin Filtros)**
```bash
curl -X GET "http://localhost:8080/api/prices"
```
**Respuesta esperada (`200 OK`)**: Listado de todos los precios.

### **Caso de Precio No Encontrado**
```bash
curl -X GET "http://localhost:8080/api/prices?date=2023-01-01T10:00:00&productId=35455&brandId=1"
```
**Respuesta esperada (`200 OK`, lista vacía)**:
```json
[]
```

---

## Base de Datos H2
La base de datos es inicializada automáticamente al arrancar la aplicación.

### **Acceso a la Consola H2**
URL: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)  
**Credenciales:**
- **JDBC URL:** `jdbc:h2:mem:pricingdb`
- **User:** `sa`
- **Password:** *(vacío)*

**Consulta rápida:**
```sql
SELECT * FROM prices;
```

---

## **Tests Realizados**

El proyecto incluye pruebas unitarias e integración que validan el correcto funcionamiento del servicio.

### ▶ **Ejecutar los tests**
```bash
mvn clean test
```

### **Pruebas Incluidas**
✔ **Test Unitario** de `FindPriceUseCase` con **Mockito**  
✔ **Test de Integración** para el **endpoint REST**  
✔ **Validación de los 5 casos del enunciado**

---

## **Estructura del Proyecto**
El código sigue una **arquitectura hexagonal**, separando la lógica de negocio del acceso a datos y la API.

```
pricing-service
│── src/main/java/com/store/pricing_service
│   ├── application
│   │   ├── usecase (Casos de uso - lógica de negocio)
│   ├── domain
│   │   ├── model (Entidades del dominio)
│   │   ├── repository (Interfaces de persistencia)
│   ├── infrastructure
│   │   ├── controller (Controladores REST)
│   │   ├── persistence (Implementaciones de repositorios)
│   │   ├── exception (Manejo de errores)
│   │   ├── dto (Objetos de transferencia de datos)
│   ├── PricingServiceApplication.java (Clase principal)
│
└── src/test/java/com/store/pricing_service
    ├── application (Tests de lógica de negocio)
    ├── integration (Tests de integración)
```

---

## **Tecnologías Utilizadas**
- **Java 17**
- **Spring Boot 3.4.2 (Web, Data JPA, Validation)**
- **H2 Database** (Base de datos en memoria)
- **Lombok** (Reducción de boilerplate)
- **JUnit 5 y Mockito** (Pruebas unitarias)
- **TestRestTemplate** (Pruebas de integración)