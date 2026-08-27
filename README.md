# OrderFlow — Student Starter

OrderFlow funciona localmente, pero deliberadamente NO contiene el delivery system del curso.

## ¿De qué trata el sistema?

OrderFlow simula el procesamiento de pedidos de una empresa. Su API permite registrar pedidos asociados con un cliente, consultar los pedidos existentes y verificar el estado de salud del servicio. También incluye un componente de notificaciones que, más adelante, se preparará para ejecutarse como una función AWS Lambda.

La funcionalidad de negocio inicial es deliberadamente pequeña porque el propósito del proyecto no es construir una tienda completa. Durante el semestre, el equipo transformará la manera en que OrderFlow se integra, prueba, empaqueta, entrega, despliega, aprovisiona y observa mediante prácticas DevOps reproducibles.

## Requisitos

- Java 21
- Git
- Maven Wrapper incluido en el repositorio

El proyecto incluye Maven Wrapper (`mvnw` y `mvnw.cmd`), por lo que no es necesario instalar Maven manualmente. El Wrapper utiliza la versión de Maven configurada por el proyecto.

Más adelante: Docker, AWS CLI, Terraform, Minikube, kubectl y Kompose.

## Bootstrap del repositorio del equipo

Cada equipo crea en la Sesión 1 su propio repositorio GitHub, por ejemplo `orderflow-equipo-03`. Ese repositorio será la **source of truth** durante todo el semestre.

Importen el starter y creen el baseline:

    git init
    git add .
    git commit -m "chore: import OrderFlow baseline"
    git branch -M main
    git remote add origin <URL>
    git push -u origin main

Otro integrante verifica la reproducibilidad desde un fresh clone:

En Windows:

    git clone <URL>
    cd <repo>
    git log --oneline -1
    mvnw.cmd clean test
    mvnw.cmd package

En Linux/macOS:

    git clone <URL>
    cd <repo>
    git log --oneline -1
    ./mvnw clean test
    ./mvnw package

## Baseline

En Windows:

    mvnw.cmd clean test
    mvnw.cmd package
    mvnw.cmd -pl orders-api spring-boot:run

En Linux/macOS:

    ./mvnw clean test
    ./mvnw package
    ./mvnw -pl orders-api spring-boot:run

Prueba:

    curl http://localhost:8080/actuator/health
    Invoke-RestMethod -Uri "http://localhost:8080/api/orders" -Method Post -ContentType "application/json" -Body '{"customerId":"team-demo","total":150.00}'
    curl http://localhost:8080/api/orders

## Evidencia acumulativa

No sobrescriban evidencias anteriores. Cada Sprint conserva su propio archivo:

    docs/evidence/
    ├── sprint-00.md
    ├── sprint-01.md
    ├── sprint-02.md
    ├── sprint-03.md
    ├── sprint-04.md
    ├── sprint-05.md
    ├── sprint-06.md
    └── sprint-07.md

Usen PR, pipeline, deployment o infraestructura sólo cuando ya correspondan al Sprint. Antes de eso registren: `N/A — todavía no corresponde a este Sprint.`

## Regla del semestre

No implementen por adelantado `.github/workflows`, `delivery`, `infra`, `k8s` u `observability`. Esas carpetas se desarrollan progresivamente como evidencia de aprendizaje.
