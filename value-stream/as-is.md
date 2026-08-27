# Value Stream AS-IS — Sprint 0

## Descripción

Se creó el repositorio junto con el proyecto inicial durante la sesión.
Sin embargo, hubo complicaciones al momento de ejecutar y probar el sistema, ya que
en ninguna de las computadoras del laboratorio estaba instalado Maven (mvn), lo cual
impidió compilar y correr el proyecto localmente en ese entorno.

Cada integrante del equipo pudo clonar y ejecutar el sistema en sus computadoras
personales, donde se comprobó que funciona correctamente.

Adicionalmente, no se identificaron conflictos de código durante este sprint, pero
tampoco se tiene una estrategia de branching definida: todo el trabajo
se realiza directo sobre `main`, sin ramas ni revisión previa mediante
pull requests, por lo que al momento de realizar cualquier cambio se tienen que tomar turnos.

## Flujo de valor (etapas)

| Etapa                                  | Descripción                                                  | Estado / Tiempo                         |
|----------------------------------------|--------------------------------------------------------------|-----------------------------------------|
| Setup de repo                          | Creación del repositorio y proyecto inicial                  | Completado en sesión                    |
| Configuración de entorno (laboratorio) | Intento de ejecutar el proyecto en las PCs del laboratorio   | Bloqueado — Maven (mvn) no instalado    |
| Configuración de entorno (personal)    | Clonado y ejecución en equipos personales de cada integrante | Completado, funcional                   |
| Validación                             | Verificación de que el sistema corre correctamente           | Exitosa (solo en entornos personales)   |
| Control de versiones                   | Subida de cambios al repositorio                             | Directo a `main`, sin ramas ni revisión |

## Cuellos de botella identificados

- Falta de Maven preinstalado en las computadoras del laboratorio, lo que impide
  ejecutar el proyecto en ese entorno compartido.
- Dependencia del entorno personal de cada integrante para poder trabajar, lo cual
  no garantiza consistencia entre máquinas.
- Ausencia de una estrategia de branching: el equipo ya trabaja cada quién en su máquina,
  condición típica bajo la cual ocurren conflictos de merge y regresiones en `main` si no se controla desde ahora.