 import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que representa una tarea individual.
 */
class Tarea {
    private String descripcion;
    private boolean completada;

    public Tarea(String descripcion) {
        this.descripcion = descripcion;
        this.completada = false; // Por defecto, la tarea no está completada
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean estaCompletada() {
        return completada;
    }

    public void marcarComoCompletada() {
        if (!completada) {
            completada = true;
            System.out.println("✅ Tarea marcada como completada: " + descripcion);
        } else {
            System.out.println("⚠️ La tarea ya estaba completada.");
        }
    }

    @Override
    public String toString() {
        String estado = completada ? "[✓]" : "[ ]";
        return estado + " " + descripcion;
    }
}

/**
 * Clase principal que gestiona la lista de tareas mediante un menú.
 */
public class ListaDeTareas {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ArrayList<Tarea> tareas = new ArrayList<>();

        int opcion;
        do {
            // Mostrar menú
            System.out.println("\n===== MENÚ DE LISTA DE TAREAS =====");
            System.out.println("1. Agregar nueva tarea");
            System.out.println("2. Marcar tarea como completada");
            System.out.println("3. Ver todas las tareas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            // Validar que sea un número entero
            while (!entrada.hasNextInt()) {
                System.out.print("Entrada inválida. Ingrese un número del 1 al 4: ");
                entrada.next();
            }
            opcion = entrada.nextInt();
            entrada.nextLine(); // Limpiar el buffer del salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la descripción de la tarea: ");
                    String descripcion = entrada.nextLine().trim();
                    if (descripcion.isEmpty()) {
                        System.out.println("Error: La descripción no puede estar vacía.");
                    } else {
                        tareas.add(new Tarea(descripcion));
                        System.out.println("Tarea agregada con éxito.");
                    }
                    break;

                case 2:
                    if (tareas.isEmpty()) {
                        System.out.println("No hay tareas disponibles. Agregue una primero.");
                        break;
                    }
                    // Mostrar tareas numeradas para elegir cuál completar
                    System.out.println("--- Tareas pendientes ---");
                    for (int i = 0; i < tareas.size(); i++) {
                        // Solo mostramos las que no están completadas, pero podemos mostrar todas
                        System.out.println((i + 1) + ". " + tareas.get(i));
                    }
                    System.out.print("Número de la tarea a completar: ");
                    int indice = leerIndiceValido(entrada, tareas.size());
                    if (indice != -1) {
                        tareas.get(indice).marcarComoCompletada();
                    }
                    break;

                case 3:
                    if (tareas.isEmpty()) {
                        System.out.println("La lista de tareas está vacía.");
                    } else {
                        System.out.println("--- LISTA DE TAREAS ---");
                        for (int i = 0; i < tareas.size(); i++) {
                            System.out.println((i + 1) + ". " + tareas.get(i));
                        }
                    }
                    break;

                case 4:
                    System.out.println("¡Hasta luego! Que tengas un día productivo.");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);

        entrada.close();
    }

    /**
     * Lee un número de índice válido (basado en 1) y lo convierte a índice de lista (basado en 0).
     * @param sc Scanner activo
     * @param max Número máximo de tareas
     * @return índice válido (0 a max-1) o -1 si el usuario cancela o hay error
     */
    private static int leerIndiceValido(Scanner sc, int max) {
        while (!sc.hasNextInt()) {
            System.out.print("Valor inválido. Ingrese un número de tarea: ");
            sc.next();
        }
        int num = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
        if (num < 1 || num > max) {
            System.out.println("Error: Número fuera de rango. Debe ser entre 1 y " + max + ".");
            return -1;
        }
        return num - 1; // Convertir a índice base 0
    }
} 
    

