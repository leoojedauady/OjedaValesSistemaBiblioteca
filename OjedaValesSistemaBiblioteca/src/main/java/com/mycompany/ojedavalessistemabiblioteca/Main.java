package com.mycompany.ojedavalessistemabiblioteca;
import java.util.Scanner;
/**
 *
 * @author leojeda
 */
public class Main {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        GestorBiblioteca.cargarDatos(); // se cargan los datos del txt
        int opcion = 0;

        while (opcion != 3) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Registrar un libro");
            System.out.println("2. Buscar un libro");
            System.out.println("3. Salir");
            System.out.print("Elige una opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // arregla el bug de salto de linea

            switch (opcion) {
                case 1:
                    GestorBiblioteca.registrarLibro(scanner);
                    break;

                case 2:
                    GestorBiblioteca.buscarLibro(scanner);
                    break;

                case 3:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opcion no válida.");
                    break;
            }
        }
    }
}
