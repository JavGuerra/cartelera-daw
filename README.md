![Logo Cartelera](src/main/resources/static/img/logo.svg)  

# Proyecto final DAW «Cartelera»

Junio 2024

## La práctica

Empleando **Java** y el framework **Spring**, se ha llevado a cabo la planificación e implementación de una aplicación web que permite a los usuarios listar cines, salas de cine y sus películas asociadas, y capacita a los usuarios registrados para modificar, mediante formularios, cada una de las entidades mencionadas.

En su desarrollo se ha empleado **Spring security**, **Tymeleaft** y **Bootstrap**. Se ha codificado usando **IntelliJ IDEA**.

La planificación y codificación se han llevado a cabo usando **Trello** y **GitHub**.

Además de los requisitos para el MVP, la app implementa:

- Sprint Security para securización de rutas
- Registro de Usuario
- Validaciones de formularios
- Automatizaciones
- Documentación JavaDoc
- Implementación de Logs

## Indicaciones de instalación

Tras clonar el repositorio, es necesario crear un usuario en la BBDD relacional llamado «**cartelera-daw**» con el password: «**cartelera-daw**» (sin comillas). Estos datos, así como el puerto por defecto de la app (**8082**), pueden ser alterados en el fichero «aplication.propieties», en resources.

Cada vez que se inicia la aplicación, esta recarga los datos en la BBDD.

Una vez iniciada la aplicación en local, se puede acceder a ella con en dirección: http://localhost:8082

El acceso al panel de administración requiere de un usuario y contraseña. Por defecto, la aplicación tiene un usuario «admin» y su contraseña es «admin».

ADVERTENCIA: Se recomienda encarecidamente que, tras el primer acceso, la contraseña de admin sea cambiada o se cree una nueva cuenta de usuario desde la que eliminar la cuenta «admin».

## Sobre el autor

* Desarrollado por [**Javier Guerra**](https://javguerra.github.io/)

## Licencia

Sobre el código fuente: [GNU GENERAL PUBLIC LICENSE Version 3](LICENSE)
