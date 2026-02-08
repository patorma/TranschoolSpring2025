# Transchool 2025 - Bakend API
![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.1-brightgreen?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=for-the-badge&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Ready-blue?logo=docker)




##  Descripción del Proyecto

**Transchool 2025** es una solución robusta para la gestión de transporte escolar, desarrollada sobre una arquitectura moderna. Este proyecto representa la evolución técnica de una versión original de 2016, migrada a **Spring Boot 3.4.1** para garantizar escalabilidad, seguridad,un rendimiento óptimo y arquitectura preparada para despliegue con Docker..

La API gestiona la lógica de negocio para administradores, transportistas y apoderados, permitiendo el control de furgones, asignaciones de estudiantes y seguimiento de mensualidades.
---

##  Stack Tecnológico
* **Core:** Java 21 (LTS) & Spring Boot 3.4.1.
* **Seguridad:** Spring Security con autenticación basada en **JWT (JSON Web Tokens)**.
* **Persistencia:** Spring Data JPA con **PostgreSQL**.
* **Maven.**
* *Docker & Docker Compose
* **Documentación:** Springdoc-OpenAPI (Swagger UI) 2.8.5.
* **Mapeo y Utilidades:** ModelMapper & Lombok.

---

##  Documentación Interactiva (Swagger)

El proyecto integra **Swagger UI**, lo que permite visualizar y probar los endpoints sin necesidad de herramientas externas como Postman.

### Acceso
La interfaz está disponible en la siguiente URL una vez iniciada la aplicación:
`http://localhost:8080/api/v1/swagger-ui/index.html` 

### Manejo de Paginación (Pageable)
Los endpoints de listado (como `/admin/furgones/page`) están optimizados para recibir parámetros de paginación estándar de Spring Data:
* `page`: Número de página (ej. `0`).
* `size`: Cantidad de registros por página (ej. `5`).
* `sort`: Criterio de ordenación (ej. `patente,asc`).

---

##  Seguridad y Roles
La aplicación implementa un sistema de control de acceso basado en roles (RBAC). Las rutas están protegidas según el perfil del usuario:

| Rol | Alcance de Acceso |
| :--- | :--- |
| **ADMIN** | Gestión total de usuarios, furgones ,asignaciones y registro de las mensualidades y pagos de estas últimas. |
| **TRANSPORTISTA** | Gestión de recorridos y visualización de furgones asignados. |
| **APODERADO** | Gestión de estudiantes y revisión de mensualidades. |

> **Nota:** Todos los recursos, excepto los endpoints de `/auth/**` y la documentación de Swagger, requieren un token JWT válido en el encabezado de la petición (`Authorization: Bearer <token>`).

---

##  Instalación y Ejecución

- Docker
- Docker Compose

>  No es necesario tener Java ni Maven instalados localmente.
> Tampoco se necesita tener instalado PostgreSQL
> 
##  Pruebas unitarias
El proyecto cuenta con pruebas unitarias básicas utilizando **JUnit 5** y **Spring Boot Test**.
Las pruebas están enfocadas en:
- Validar la lógica de negocio
- Verificar el comportamiento de servicios sin levantar el servidor completo
###  Ejecutar pruebas

Para ejecutar las pruebas de forma local:

```bash
mvn test
O usando Docker (opcional):

docker compose run --rm api mvn test

### 2. Configuración
Ajusta las credenciales de tu base de datos en el archivo:
`src/main/resources/application.properties`

### 3. Compilación y Despliegue
Debido a la estricta validación del contexto de Spring 3.4, se recomienda compilar omitiendo los tests en la primera ejecución:

```bash
# Limpiar y compilar el proyecto
mvn clean install -DskipTests

# Ejecutar la aplicación
mvn spring-boot:run


##  Arquitectura

- Basado en arquitectura en tres capas: `Capa de presentación(controller,dto) → Capa de Lógica de negocio(service,domain,mapper,exeption) → Capa de Persistencia(repository) → Capas Tranversales/Infreaestructura(config,security)`
- Validación con anotaciones como `@Valid`, `@NotBlank`, `@Size`,  `@NotNull`
- Seguridad con JWT y roles (`ADMIN`, `TRANSPORTISTA`, `APODERADO`)
- Acceso a rutas restringido mediante `@PreAuthorize`

---




##  Ejecución local

El proyecto se ejecuta completamente con **Docker Compose**, levantando tanto la API como la base de datos.

###  Clonar el repositorio

```bash
git clone https://github.com/patorma/TranschoolSpring2025.git
cd TranschoolSpring2025


2. Comando para ejecutar el proyecto
Una vez que rse realiza lo anteriormente explicado se debe ejecutar en una consola que este ubicada en la raiz del proyecto el siguiente comando:
docker compose up --build 

-
