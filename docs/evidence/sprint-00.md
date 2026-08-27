# Sprint 0 Evidence

## Sprint Goal
Resolver los bloqueos de entorno de laboratorio mediante la integración de Maven Wrapper (mvnw) y establecer la estrategia inicial de branching (feature/* + PR) para garantizar un flujo de trabajo ordenado

## Repository baseline
- Repo: https://github.com/vikvillalba/orderflow-devops.git
- Initial commit: f557214 (Creación del repositorio)
- Fresh clone verified by: Todos los integrantes del equipo

## Baseline execution
- Tests: ./mvnw test
- Artifact: ./mvnw package
- Endpoint: http://localhost:8080/actuator/health

## Value Stream
- AS-IS: `docs/value-stream/as-is.md`
- TO-BE: `docs/value-stream/to-be.md`

## Incremento demostrable
Proyecto base en Java/Maven configurado con los archivos del wrapper (mvnw, .mvn/) que permiten clonar, compilar y probar el sistema en cualquier máquina sin requerir instalación previa de Maven global.

## PR / pipeline / deployment / infraestructura
N/A — todavía no corresponde a este Sprint.

## Decisión y trade-off
- **Decisión:** Incluir Maven Wrapper (`mvnw`) en el repositorio e implementar la estrategia de ramas `feature/...` con Pull Requests.
- **Trade-off:** 
  - Ventaja: Elimina el bloqueo por falta de software preinstalado en el laboratorio.
  - Costo: Exige disciplina del equipo para revisar PRs y no subir cambios directo a la rama `main`.

## Demo mínima reproducible
### 1. Clonado e inspección inicial

**En Windows:**
   ```bash
   git clone https://github.com/vikvillalba/orderflow-devops.git
   cd orderflow-devops
   git log --oneline -1
   mvnw.cmd clean test
   mvnw.cmd package
   ```

**En Linux/macOS:**
```bash
git clone https://github.com/vikvillalba/orderflow-devops.git
cd orderflow-devops
git log --oneline -1
./mvnw clean test
./mvnw package
```
### 2. Baseline
**En Windows:**
```bash
mvnw.cmd clean test
mvnw.cmd package
mvnw.cmd -pl orders-api spring-boot:run
```
**En Linux/macOS:**
```bash
./mvnw clean test
./mvnw package
./mvnw -pl orders-api spring-boot:run
```
### 3. Pruebas
```bash
#Salud del sistema
curl http://localhost:8080/actuator/health

#Crear una orden
Invoke-RestMethod -Uri "http://localhost:8080/api/orders" -Method Post -ContentType "application/json" -Body '{"customerId":"team-demo","total":150.00}'

#Consultar órdenes
curl http://localhost:8080/api/orders
```


## Contribuciones del equipo
- Integrante 1 (Juan Pablo Heras) — Documentación y apoyo en el Value Stream (as-is.md y to-be.md).
- Integrante 2 (Victoria Villalba) — Creación de repositorio, documentación de Value Stream (as-is.md) y del Working Agreement.
- Integrante 3 (Pedro Esquer) — Configuración e integración de Maven Wrapper (mvnw) y actualización de las instrucciones en README.md.
- Integrante 4 (Pablo Zamora) — Documentación de sprint-00.md y apoyo en la documentación de Value Stream (as-is.md)

## Mini Definition of Done
- [X] Repo y commit baseline identificables
- [X] Fresh clone verificado
- [X] Build/tests, artifact y endpoint reproducibles
- [X] AS-IS, TO-BE y Working Agreement completos
- [X] Decisión/trade-off defendible

## Retro: Keep / Change / Next experiment
- **Keep**: El diagnóstico rápido del cuello de botella en el laboratorio y la buena comunicación para solucionarlo.
- **Change**: Hacer commits directos sobre la rama main.
- **Next experiment**: Habilitar la regla de protección de rama en GitHub para exigir la aprobación de 1 Pull Request antes de hacer merge a main.

## Uso de IA
- **Herramienta**: Claude
- **Prompt relevante**:
- **Qué verificamos/cambiamos**:

