![Logo Cartelera](src/main/resources/static/img/logo.svg)  

# Proyecto final DAW «Cartelera»

Junio 2024

## La práctica

Empleando **Java** y el framework **Spring**, se ha llevado a cabo la planificación e implementación de una aplicación web que permite a los usuarios listar cines, salas de cine y sus películas asociadas, y capacita a los usuarios registrados para modificar, mediante formularios, cada una de las entidades mencionadas.

En su desarrollo se ha empleado **Spring security**, **Hibernate**, **Tymeleaft** y **Bootstrap**. Se ha codificado usando **IntelliJ IDEA**. El despliegue se ha realizado en **Railway.app**.

La planificación y codificación se han llevado a cabo usando **Trello** y **GitHub**.

![Banner](src/main/resources/static/img/banner.png)

Además de los requisitos para el MVP, la app implementa:

- Segurización de rutas.
- Registro de usuarios.
- Validaciones de formularios
- Automatizaciones, como el selector inteligente de cines, y otros.
- Documentación con JavaDoc.
- Implementación de Logs.
- Notificaciones.
- Aviso de cookies.
- Spinner loader.

## Indicaciones de instalación

Tras clonar el repositorio, es necesario crear un usuario en la BBDD relacional llamado «**cartelera-daw**» con el password que deseemos. Estos datos, así como el puerto por defecto de la app (**8082**), pueden ser introducidos en el fichero «aplication.propieties», en la carpeta resources.

Cada vez que se inicia la aplicación, esta recarga los datos en la BBDD.

Una vez iniciada la aplicación en local, se puede acceder a ella en la dirección: http://localhost:8082

El acceso al panel de administración requiere de un usuario y contraseña. Por defecto, la aplicación tiene un usuario «admin» y su contraseña es «admin».

ADVERTENCIA: Se recomienda que, tras el primer acceso, la contraseña de admin sea cambiada o se cree una nueva cuenta de usuario desde la que eliminar la cuenta «admin».

## Acceso a la App

Ir a [Cartelera DAW](https://cartelera-daw.up.railway.app/)

## Sobre el autor

Desarrollado por [**Javier Guerra**](https://javguerra.github.io/)

## Licencia

Sobre el código fuente: [GNU GENERAL PUBLIC LICENSE Version 3](LICENSE)

## Saber más

Artículos informativos elaborados por el autor relacionados con esta aplicación:

- [Notificaciones con Thymeleaft](https://javguerra.github.io/2024-07-03-notificaciones-thymeleaft/)
- [Spinner loader en Thymeleaft](https://javguerra.github.io/2024-07-08-loader-thymeleaft/)