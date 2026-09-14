# Evidencia Unidad 1 - GitHub Actions y SonarQube

## 1. Identificación 

- Equipo: Programitos
- Integrantes: 
    - Juan Pablo Heras Carrazco
    - Pedro Morales Esquer
    - Victoria Isabel Villalba Beltrán
    - Pablo Emilio Zamora Gámez
- Proyecto: Orderflow
- Repositorio: https://github.com/vikvillalba/orderflow-devops


## 2. Flujo de integración

El proyecto utilizará GitHub Actions para ejecutar el build, las pruebas, generar el reporte de las mismas con JaCoCo y ejecutar el análisis de SonarQube.

Flujo:

Push hacia main / Pull Request -> GitHub Actions -> Maven + Tests -> JaCoCo -> SonnarScanner -> SonnarQube -> Quality Gate


Cómo el equipo decidió trabajar con la versión gratuita de SonarQube se utilizará el Quality Gate que viene incluido por default con el plan gratuito `Sonar Way`.

## 3. Ejecución base

Antes de provocar el fallo controlado se verificó que el proyecto pudiera completar correctamente el pipeline:

- Commit: aef86e6
- Run de GitHub Actions: https://github.com/vikvillalba/orderflow-devops/actions/runs/34807503245
- Resultado: Pass
- Quality Gate: Passed

*Dentro del YAML para configurar GitHub Actions se configuró que si SonarQube fallaba, entonces también fallaría el workflow de GitHub Actions*

## 4. Predicción del fallo controlado

Se decidió introducir intencionalmente una comparación incorrecta de Strings mediante el operador `==` en lugar de con el método `.equals()`

Código introducido: 

```java
public synchronized boolean isValidStatus(String status) {
    return "ACTIVE" == status;
}
return status
```

Se esperaba que SonarQube identificara esta comparación como fallo / bug ya que `==` compara referencias de objetos y no el contenido.


## 5. Ejecución con fallo
- Commit del fallo: 39bf0dd
- Run de GitHub Actions: https://github.com/vikvillalba/orderflow-devops/actions/runs/34808567352
- Análisis SonarQube: https://sonarcloud.io/summary/new_code?id=vikvillalba_orderflow-devops&pullRequest=6
- Quality Gate y workflow GitHub Actions: FAILED

SonarQube mostró el error: 

    Strings and Boxed types should be compared using "equals()"

## 6. Correción
El problema se corrigió cambiando:

```java
"ACTIVE" == status
```

```java
"ACTIVE".equals(status)
```

Con la nueva opción se compara el contenido del String en lugar de su referencia. 

## 7. Ejecución después de la correción
- Commit: d6b49df
- Run de GitHub Actions: https://github.com/vikvillalba/orderflow-devops/actions/runs/34811223573
- Análisis de SonarQube: https://sonarcloud.io/project/issues?id=vikvillalba_orderflow-devops&pullRequest=6&s=IMPACT_RANK&sinceLeakPeriod=true&issueStatuses=OPEN%2CCONFIRMED
- QualityGate: Passed

## 8. Uso de IA