
package Parcial2;

// Clase principal para el menú
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Garaje garaje = new Garaje();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú de gestión del Garaje:");
            System.out.println("1. Alquilar un espacio");
            System.out.println("2. Retirar vehículo");
            System.out.println("3. Consulta de ingresos mensuales");
            System.out.println("4. Consulta proporción autos/motos");
            System.out.println("5. Listado de matrículas y cuota mensual y tipo vehículo");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la línea

            switch (opcion) {
                case 1:
                    System.out.println("¿Es un Auto o una Moto? (auto/moto): ");
                    String tipo = scanner.nextLine().toLowerCase();
                    System.out.print("Marca: ");
                    String marca = scanner.nextLine();
                    System.out.print("Precio: ");
                    double precio = scanner.nextDouble();
                    System.out.print("Cilindraje: ");
                    int cilindraje = scanner.nextInt();
                    scanner.nextLine(); // Consumir la línea

                    if (tipo.equals("moto")) {
                        System.out.print("¿Tiene sidecar? (true/false): ");
                        boolean sidecar = scanner.nextBoolean();
                        scanner.nextLine(); // Consumir la línea
                        Moto moto = new Moto(marca, precio, cilindraje, sidecar);
                        System.out.print("Introduce la matrícula (6 caracteres): ");
                        String matricula = scanner.nextLine();
                        if (moto.matricular(matricula) && garaje.alquilarEspacio(moto)) {
                            System.out.println("Moto alquilada exitosamente.");
                        } else {
                            System.out.println("No se pudo alquilar el espacio para la moto.");
                        }
                    } else if (tipo.equals("auto")) {
                        System.out.print("¿Tiene radio? (true/false): ");
                        boolean radio = scanner.nextBoolean();
                        System.out.print("¿Tiene navegador? (true/false): ");
                        boolean navegador = scanner.nextBoolean();
                        scanner.nextLine(); // Consumir la línea
                        Auto auto = new Auto(marca, precio, cilindraje, radio, navegador);
                        System.out.print("Introduce la matrícula (6 caracteres): ");
                        String matricula = scanner.nextLine();
                        if (auto.matricular(matricula) && garaje.alquilarEspacio(auto)) {
                            System.out.println("Auto alquilado exitosamente.");
                        } else {
                            System.out.println("No se pudo alquilar el espacio para el auto.");
                        }
                    } else {
                        System.out.println("Tipo de vehículo no válido.");
                    }
                    break;
                case 2:
                    System.out.print("Introduce la matrícula del vehículo a retirar: ");
                    String placa = scanner.nextLine();
                    if (garaje.retirarVehiculo(placa)) {
                        System.out.println("Vehículo retirado exitosamente.");
                    } else {
                        System.out.println("No se encontró el vehículo con esa matrícula.");
                    }
                    break;
                case 3:
                    System.out.println("Ingresos mensuales: " + garaje.calcularIngresos());
                    break;
                case 4:
                    System.out.println("Proporción autos/motos: " + garaje.calcularProporcionAutosMotos());
                    break;
                case 5:
                    garaje.listarVehiculos();
                    break;
                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 6);

        scanner.close();
    }
}
