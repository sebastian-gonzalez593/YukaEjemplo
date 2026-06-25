import java.util.Scanner;

/**
 * Clase que representa una cuenta bancaria simple.
 */
class CuentaBancaria {
    private String numeroCuenta;
    private String titular;
    private double saldo;

    // Constructor
    public CuentaBancaria(String numeroCuenta, String titular, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        // Nos aseguramos de que el saldo inicial no sea negativo
        this.saldo = Math.max(saldoInicial, 0);
    }

    /**
     * Deposita una cantidad positiva en la cuenta.
     * @param cantidad el monto a depositar (debe ser > 0)
     * @return true si el depósito fue exitoso, false en caso contrario
     */
    public boolean depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            System.out.printf("Se depositaron $%.2f. Saldo actual: $%.2f%n", cantidad, saldo);
            return true;
        } else {
            System.out.println("Error: La cantidad a depositar debe ser mayor que 0.");
            return false;
        }
    }

    /**
     * Retira una cantidad de la cuenta si hay saldo suficiente.
     * @param cantidad el monto a retirar (debe ser > 0 y <= saldo)
     * @return true si el retiro fue exitoso, false en caso contrario
     */
    public boolean retirar(double cantidad) {
        if (cantidad <= 0) {
            System.out.println("Error: La cantidad a retirar debe ser mayor que 0.");
            return false;
        }
        if (cantidad > saldo) {
            System.out.println("Error: Saldo insuficiente. Saldo actual: $" + saldo);
            return false;
        }
        saldo -= cantidad;
        System.out.printf("Se retiraron $%.2f. Saldo actual: $%.2f%n", cantidad, saldo);
        return true;
    }

    /**
     * Muestra el saldo actual de la cuenta.
     */
    public void mostrarSaldo() {
        System.out.println("--- Información de la cuenta ---");
        System.out.println("Número de cuenta: " + numeroCuenta);
        System.out.println("Titular: " + titular);
        System.out.printf("Saldo actual: $%.2f%n", saldo);
    }
}

/**
 * Clase principal que ejecuta el programa de la cuenta bancaria.
 */
public class ProgramaBanco {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        // Creamos una cuenta de ejemplo
        CuentaBancaria cuenta = new CuentaBancaria("123-456-789", "María García", 1000.0);

        int opcion;
        do {
            // Mostrar menú
            System.out.println("\n===== MENÚ DE CUENTA BANCARIA =====");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar dinero");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            // Leer opción del usuario
            while (!entrada.hasNextInt()) {
                System.out.print("Entrada inválida. Ingrese un número del 1 al 4: ");
                entrada.next();
            }
            opcion = entrada.nextInt();

            // Ejecutar la opción elegida
            switch (opcion) {
                case 1:
                    cuenta.mostrarSaldo();
                    break;
                case 2:
                    System.out.print("Ingrese la cantidad a depositar: ");
                    double deposito = leerCantidad(entrada);
                    cuenta.depositar(deposito);
                    break;
                case 3:
                    System.out.print("Ingrese la cantidad a retirar: ");
                    double retiro = leerCantidad(entrada);
                    cuenta.retirar(retiro);
                    break;
                case 4:
                    System.out.println("Gracias por usar el sistema bancario. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);

        entrada.close();
    }

    /**
     * Método auxiliar para leer una cantidad double de forma segura.
     * @param sc Scanner activo
     * @return valor double ingresado por el usuario
     */
    private static double leerCantidad(Scanner sc) {
        while (!sc.hasNextDouble()) {
            System.out.print("Valor inválido. Ingrese un número válido: ");
            sc.next();
        }
        return sc.nextDouble();
    }
}