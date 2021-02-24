# Devops

Este proyecto es un ejemplo de una aplicación Java usando Spring Boot que utiliza Jenkins para contruir una imagen en Docker para desplegarse.

## Paso de Guía

### Prerequisitos

1. Instalar Maven en la máquina local:
   Para eso puede descargar y validar la documentación de Maven en su sitio oficial [aquí](https://maven.apache.org/).
2. Instalar Git en la máquina local: Seguir la guía en la documentación oficial de la herramienta [aquí](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git).

### Pasos:

1. Crear proyecto de Spring boot: Esto puede ser a través del sitio [Spring Initializr](https://start.spring.io/) o través del IDE donde ya se tenga un plugin previamente instalado para Spring Boot.
2. Inicializar repositorio git utilizando el comando: `git init` **_(Esto en la raíz del proyecto)_**
3. Agregar _tracking_ de la primera versión del proyecto utilizando el comando: `git add .`. Al usar `.` estamos indicando que se esta utilizando la ruta o contexto actual, entonces `git add .` va a agregar todos los archivos en ese contexto.
4. Hacer commit de la versión inicial del proyecto. Utilizando el comando: `git commit -m "Version inicial del proyecto"`.
5. Para validar el estado del repositorio y los archivos agregados/modificados usar el comando: `git status`.
6. Compilar y construir el proyecto por primera vez. En el caso de usar Maven (instalado previamente), se pueden utilizar los siguientes comandos: **_(Esto en la raíz del proyecto al mismo nivel del archivo pom.xml)_**

```
    mvn clean compile
    o
    mvn clean package
```

7. Para que actuator muestre todos los endpoints se agrega en el archivo **_aplication.properties_** lo siguiente: `management.endpoints.web.exposure.include=\*`
8. Se crea un nuevo repositorio en github, de preferencia público y se dejan los valores en default.
9. Se agrega el nuevo repositorio remoto al nuestro ya existente en la máquina local.

```
   git remote add origin https://github.com/USUARIO/REPO.git
   git branch -M main
   git push -u origin main
```

10. Se crea version inicial del _Jenkinsfile_ y _Dockerfile_
11. En nuestro servidor de Jenkins definir un job multibranch, se crean las credenciales a GitHub y se usa el url de nuestro repositorio.
12. En nuestro servidor de Jenkins instalamos y configuramos el plugin de [Sonar SonarQube scanner](https://docs.sonarqube.org/latest/analysis/scan/sonarscanner-for-jenkins/).
