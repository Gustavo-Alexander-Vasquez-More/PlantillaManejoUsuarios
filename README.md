# PlantillaManejoUsuarios

**PlantillaManejoUsuarios** es una plantilla reutilizable para aplicaciones Java que implementa gestión y autenticación de usuarios utilizando buenas prácticas de seguridad y escalabilidad. El proyecto integra autenticación con tokens JWT (JJWT), Hibernate para el acceso a la base de datos MySQL y configuración avanzada de seguridad con Spring Security, incluyendo protección de contraseñas mediante algoritmos de hash y encriptación.

## Propósito

Esta plantilla está pensada para servir de base a sistemas Java que requieran administración de usuarios, autenticación robusta y acceso seguro a recursos protegidos. Permite ahorrar tiempo en la configuración inicial y asegura una arquitectura mantenible y segura para proyectos empresariales o personales.

## Tecnologías utilizadas

- **Java**
- **Spring Security**: Protección y filtrado de endpoints.
- **Hibernate**: ORM para interacción con MySQL.
- **MySQL**: Base de datos relacional.
- **JJWT**: Autenticación por tokens JWT.
- **Algoritmos de hash y encriptación**: Seguridad en contraseñas y datos sensibles.
- **Maven**: Gestión de dependencias y ciclo de vida.

## Estructura del proyecto

El repositorio incluye los siguientes archivos y carpetas principales:

```
PlantillaManejoUsuarios/
├── .gitattributes
├── .gitignore
├── .mvn/
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src/
```

- **.gitignore / .gitattributes:** Configuración de control de versiones.
- **.mvn/, mvnw, mvnw.cmd:** Herramientas de Maven Wrapper para la gestión y ejecución del proyecto.
- **pom.xml:** Archivo de configuración de dependencias Maven.
- **src/**: Contiene el código fuente Java, configuraciones, controladores, entidades, servicios y repositorios.

## Instalación y ejecución

1. Clona el repositorio:
   ```bash
   git clone https://github.com/Gustavo-Alexander-Vasquez-More/PlantillaManejoUsuarios.git
   ```
2. Accede al directorio del proyecto y configura tu base de datos MySQL.
3. Modifica las credenciales de conexión en los archivos de configuración dentro de `src/`.
4. Ejecuta el proyecto usando Maven Wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```
   o con Maven instalado:
   ```bash
   mvn spring-boot:run
   ```

## Características

- Registro y autenticación de usuarios mediante JWT.
- Gestión y protección de contraseñas con hash y encriptación.
- Integración directa con Hibernate y MySQL.
- Seguridad avanzada en endpoints gracias a Spring Security.
- Arquitectura preparada para escalar y mantener buenas prácticas de desarrollo.

## Autor

[Gustavo Alexander Vasquez More](https://github.com/Gustavo-Alexander-Vasquez-More)

---
