# Value Stream TO-BE — Sprint 0

## Descripción

En el AS-IS se identificó que el equipo no pudo ejecutar ni probar el proyecto en 
las computadoras del laboratorio debido a que Maven (mvn) no estaba instalado, 
obligando a cada integrante a depender de su computadora personal para trabajar.

En el TO-BE se busca eliminar esta dependencia del entorno local, de forma que 
cualquier integrante pueda clonar el repositorio y ejecutar el proyecto sin importar 
si Maven está o no instalado en la máquina, ya sea usando Maven Wrapper (mvnw) o 
un entorno en contenedor (Docker). Esto asegura consistencia entre todos los 
entornos de trabajo, incluyendo el laboratorio.

## Flujo de valor propuesto (etapas)

| Etapa | Descripción | Solución propuesta | Estado / Tiempo esperado |
|---|---|---|---|
| Setup de repo | Creación del repositorio y proyecto inicial | Sin cambios | Completado |
| Configuración de entorno (laboratorio) | Ejecución del proyecto en las PCs del laboratorio | Uso de Maven Wrapper (⁠ mvnw ⁠) incluido en el repo, o contenedor Docker con el entorno preconfigurado | Ejecutable sin instalación previa de Maven |
| Configuración de entorno (personal) | Clonado y ejecución en equipos personales | Mismo flujo estandarizado con ⁠ mvnw ⁠/Docker | Consistente con el entorno de laboratorio |
| Validación | Verificación de que el sistema corre correctamente | Prueba en laboratorio y en equipos personales bajo el mismo procedimiento | Exitosa en ambos entornos |

## Cambios respecto al AS-IS

| Aspecto | AS-IS | TO-BE |
|---|---|---|
| Dependencia de Maven | Requería tener Maven instalado manualmente en cada máquina | Se usa Maven Wrapper (⁠ mvnw ⁠), que no requiere instalación previa |
| Entorno de laboratorio | Bloqueado — no se podía ejecutar el proyecto | Funcional — mismo procedimiento que en equipos personales |
| Consistencia entre entornos | Solo funcionaba en computadoras personales | Funciona igual en laboratorio y en personales |

## Herramientas/prácticas a implementar

•⁠  ⁠*Maven Wrapper (⁠ mvnw ⁠)* — permite compilar y ejecutar el proyecto sin necesidad de tener Maven instalado globalmente en la máquina.
•⁠  ⁠(Alternativa) *Docker* — encapsula el entorno de ejecución completo (JDK, Maven, dependencias) en un contenedor, evitando cualquier instalación manual en el laboratorio.

## Beneficios esperados

•⁠  ⁠Se elimina el bloqueo por falta de instalación de herramientas en el laboratorio.
•⁠  ⁠Mayor consistencia: todos los integrantes trabajan bajo el mismo entorno, sin importar la máquina.
•⁠  ⁠Onboarding más rápido para nuevos integrantes o para trabajar en máquinas distintas.

## Riesgos o consideraciones

•⁠  ⁠Requiere configurar correctamente ⁠ mvnw ⁠ en el repositorio (o el ⁠ Dockerfile ⁠, si se opta por esa alternativa) para que funcione igual en todos los entornos.
•⁠  ⁠Si se elige Docker, el equipo necesita tener Docker instalado; hay que verificar que esto sí esté disponible en el laboratorio antes de adoptarlo.

## Notas

Se recomienda decidir entre ⁠ mvnw ⁠ (más simple, sin dependencias extra) o Docker 
(más completo, pero requiere que Docker esté disponible en el laboratorio) antes 
del siguiente sprint, y documentar el procedimiento elegido en el README del proyecto.