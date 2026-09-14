package mx;

public class ServiceExtra {
    public String evaluarCodigo(int opcion) {
        if (opcion == 1) {
            return "Opción 1 ejecutada";
        } else if (opcion == 2) {
            return "Opción 2 ejecutada";
        } else if (opcion == 3) {
            return "Opción 3 ejecutada";
        }
        return "Opción por defecto";
    }
}
