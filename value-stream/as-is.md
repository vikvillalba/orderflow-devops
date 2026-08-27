# Value Stream AS-IS — Sprint 0

## Descripción

Se creó el repositorio junto con el proyecto inicial durante la sesión.
Sin embargo, hubo complicaciones al momento de ejecutar y probar el sistema, ya que
en ninguna de las computadoras del laboratorio estaba instalado Maven (mvn), lo cual
impidió compilar y correr el proyecto localmente en ese entorno.

Cada integrante del equipo pudo clonar y ejecutar el sistema en sus computadoras
personales, donde se comprobó que funciona correctamente.

## Flujo de valor (etapas)

| Etapa | Descripción | Estado / Tiempo |
|---|---|---|
| Setup de repo | Creación del repositorio y proyecto inicial | Completado en sesión |
| Configuración de entorno (laboratorio) | Intento de ejecutar el proyecto en las PCs del laboratorio | Bloqueado — Maven (mvn) no instalado |
| Configuración de entorno (personal) | Clonado y ejecución en equipos personales de cada integrante | Completado, funcional |
| Validación | Verificación de que el sistema corre correctamente | Exitosa (solo en entornos personales) |

## Cuellos de botella identificados

- Falta de Maven preinstalado en las computadoras del laboratorio, lo que impide
  ejecutar el proyecto en ese entorno compartido.
- Dependencia del entorno personal de cada integrante para poder trabajar, lo cual
  no garantiza consistencia entre máquinas.