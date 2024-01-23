## Documentación oficial de la API web de CHapi
En el presente documento se describe las funciones y objetivos de las API web de CHapi. 
Estas API es la que se usa para la comunicación entre el servidor y la aplicación, donde se
realizan todas las peticiones correspondientes que CHapi necesita para su funcionamiento. 

La API de nuestro servidor response a partir del siguiente link:

- Página web promocional: https://apichapi.me

## Ejecutar la API de manera local

### Requerimientos
Para poder crear una instancia local se necesitan las siguientes tecnologias:

- MongoDB version 5
- NPM version 8
- Docker compose 

### Ejecución
Para levantar la instancia local seguimos los siguientes pasos:

- Tener Docker abierto 
- Abrir terminal en la carpeta /CHapi_API
- Ejecutar "docker compose build"
- Ejecutar "docker compose up"

Con esto se nos estaria creando un contenedor de nuestra API en Docker

## Tecnologias Ocupadas
La API ocupa las siguientes tecnologias dentro de las cuales tenemos lenguajes, dependecias y librerias

- nodejs
- express
- npm
- docker
- nodemailer (como libreria para el envio de correos)
- nodemon
- mongoose
- passport
