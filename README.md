1. Descripción General
Este sistema es una aplicación robusta basada en microservicios (Spring Boot) que permite la generación dinámica de códigos QR a partir de texto o URLs, asegurando la persistencia de los datos en una base de datos relacional en memoria.
2. Arquitectura Tecnológica (Stack)
•	Lenguaje: Java 17+
•	Framework: Spring Boot 3.x (Ecosistema Spring)
•	Persistencia: Spring Data JPA con base de datos H2.
•	Generación de Imagen: Librería Google ZXing (Zebra Crossing).
•	Documentación de API: Swagger / OpenAPI 3.0.
3. Flujo de Datos
El proceso sigue el patrón de diseño MVC (Modelo-Vista-Controlador):
1.	Vista: El usuario envía una petición desde el navegador o Swagger.
2.	Controlador (QrController): Recibe el texto y el nombre.
3.	Servicio/Repo: El controlador utiliza el QrRepository para guardar la entidad en H2 y el QrService para convertir el texto en una matriz de bits (Imagen PNG).
4.	Respuesta: Se retorna la imagen al cliente y el registro queda almacenado para futuras consultas.
