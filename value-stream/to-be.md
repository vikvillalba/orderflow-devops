# Value Stream TO-BE — Sprint 0

## Descripción

En el AS-IS se identificaron dos hallazgos principales: (1) el equipo no pudo
ejecutar el proyecto en el laboratorio por falta de Maven, obligando a depender
de equipos personales; y (2) no existe una estrategia de branching, ya que todo
el trabajo se realiza directo sobre `main`.

En el TO-BE se propone resolver ambos puntos: usar Maven Wrapper (`mvnw`) para
eliminar la dependencia de tener Maven instalado localmente, y adoptar un flujo
de branching basado en ramas de feature y pull requests, de forma que el equipo
empiece a trabajar de manera ordenada desde este mismo sprint, antes de que se
acumulen commits directos en `main`.

## Flujo de valor propuesto (etapas)

| Etapa                                  | Descripción                                        | Solución propuesta                                                                                   | Estado / Tiempo esperado                   |
|----------------------------------------|----------------------------------------------------|------------------------------------------------------------------------------------------------------|--------------------------------------------|
| Setup de repo                          | Creación del repositorio y proyecto inicial        | Sin cambios                                                                                          | Completado                                 |
| Configuración de entorno (laboratorio) | Ejecución del proyecto en las PCs del laboratorio  | Uso de Maven Wrapper (`mvnw`) incluido en el repo, o contenedor Docker con el entorno preconfigurado | Ejecutable sin instalación previa de Maven |
| Configuración de entorno (personal)    | Clonado y ejecución en equipos personales          | Mismo flujo estandarizado con `mvnw`                                                                 | Consistente con el entorno de laboratorio  |
| Desarrollo de cambios                  | Cada integrante modifica el código localmente      | Rama `feature/nombre-corto` por tarea                                                                | Cambios aislados por feature               |
| Revisión e integración                 | Subida de cambios al repositorio                   | Pull request revisado por al menos un integrante antes de fusionar a `main`                          | `main` siempre estable                     |
| Validación                             | Verificación de que el sistema corre correctamente | Prueba en laboratorio y personales antes del merge                                                   | Exitosa en ambos entornos                  |

## Cambios respecto al AS-IS

| Aspecto                | AS-IS                                                      | TO-BE                                                    |
|------------------------|------------------------------------------------------------|----------------------------------------------------------|
| Dependencia de Maven   | Requería tener Maven instalado manualmente en cada máquina | Se usa Maven Wrapper (`mvnw`), sin instalación previa    |
| Entorno de laboratorio | Bloqueado: no se podía ejecutar el proyecto                | Funcional: mismo procedimiento que en equipos personales |
| Control de versiones   | Trabajo directo sobre `main`, sin ramas                    | Ramas `feature/*` + pull request revisado                |
| Estabilidad de `main`  | En riesgo constante                                        | Protegida, solo se actualiza con código validado         |


## Herramientas/prácticas a implementar

- **Maven Wrapper (`mvnw`)** — permite compilar y ejecutar el proyecto sin necesidad de tener Maven instalado globalmente en la máquina.
- **Ramas de feature (`feature/*`)** — aíslan el trabajo de cada tarea o historia de usuario.
- **Pull requests** — permiten revisión de código antes de integrar a `main`.

## Beneficios esperados

- Se elimina el bloqueo por falta de instalación de herramientas en el laboratorio.
- Mayor consistencia: todos los integrantes trabajan bajo el mismo entorno, sin importar la máquina.
- Menor riesgo de conflictos entre integrantes y de romper `main`.
- Mayor trazabilidad: se puede ver claramente qué tarea corresponde a cada cambio.
- Se establece un flujo de pull requests definido.

## Riesgos o consideraciones

- Requiere configurar correctamente `mvnw` en el repositorio para que funcione igual en todos los entornos.
- Requiere que todo el equipo adopte la disciplina de no trabajar directo en `main`.
- Puede añadir un poco más de tiempo al flujo por la espera de revisión del pull request.

## Notas

Se recomienda documentar en el README del proyecto tanto el uso de `mvnw` como la
convención de nombres de ramas, para que todo el equipo la siga desde el inicio.
Docker queda descartado por temporalmente dado que agrega complejidad innecesaria para el problema actual; 
se podría reevaluar en sprints futuros a medida que el proyecto crezca en complejidad.