# Transchool 2025 - Bakend API
![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.1-brightgreen?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=for-the-badge&logo=postgresql)



##  Descripción del Proyecto

**Transchool 2025** es una solución robusta para la gestión de transporte escolar, desarrollada sobre una arquitectura moderna. Este proyecto representa la evolución técnica de una versión original de 2016, migrada a **Spring Boot 3.4.1** para garantizar escalabilidad, seguridad y un rendimiento óptimo.

La API gestiona la lógica de negocio para administradores, transportistas y apoderados, permitiendo el control de furgones, asignaciones de estudiantes y seguimiento de mensualidades.
---

##  Stack Tecnológico
* **Core:** Java 21 (LTS) & Spring Boot 3.4.1.
* **Seguridad:** Spring Security con autenticación basada en **JWT (JSON Web Tokens)**.
* **Persistencia:** Spring Data JPA con **PostgreSQL**.
* **Documentación:** Springdoc-OpenAPI (Swagger UI) 2.8.5.
* **Mapeo y Utilidades:** ModelMapper & Lombok.

---

##  Arquitectura

- Basado en arquitectura en tres capas: `Capa de presentación(controller,dto) → Capa de Lógica de negocio(service,domain,mapper,exeption) → Capa de Persistencia(repository) → Capas Tranversales/Infreaestructura(config,security)`
- Validación con anotaciones como `@Valid`, `@NotBlank`, `@Size`,  `@NotNull`
- Seguridad con JWT y roles (`ADMIN`, `TRANSPORTISTA`, `APODERADO`)
- Acceso a rutas restringido mediante `@PreAuthorize`

---

##  Módulos principales

| Módulo     | Descripción                                      |
| ---------- | ------------------------------------------------ |
| `auth`     | Registro, login,autenticación con JWT y permios  |
| `admin`    | Gestión de usuarios, pagos, furgones             |
| `furgones` | Asignación de furgón único a un transportista    |
| `pagos`    | Registro de pagos y cálculo de mora automática   |
|`Estudiantes`| Registro de Estudiantes por parte de los apóderados|

---

##  Roles y permisos

| Rol             | Acciones                                                           |
| --------------- | ------------------------------------------------------------------ |
| `ADMIN`         | Ver todos los usuarios, asignar furgones, registrar transportistas,regsitrar pagos de apoderados, registrar furgones |
| `TRANSPORTISTA` | Ver su furgón asignado y recorridos de los estudiantes                                             |
| `APODERADO`     | Ver sus propios pagos y estudiantes que ingresó                                              |

---

## 🚀 Ejecución local

1. Clonar repositorio:
git clone https://github.com/patorma/TranschoolSpring2025.git
cd transchool2025


2. Cambiar a última rama del proyecto:

- Una vez que clonaste el proyecto revisa el archivo Transchool.postman_collection.json e importalo en postman para tener las rutas del sistema . De lo anterior no olvides ocupar postgresql y crear la base de datos: transchool_db.


🚀 Pendiente por implementar
Integración con Docker

Pruebas unitarias con JUnit y Mockito

CI/CD con Jenkins o GitHub Actions

Documentación Swagger







