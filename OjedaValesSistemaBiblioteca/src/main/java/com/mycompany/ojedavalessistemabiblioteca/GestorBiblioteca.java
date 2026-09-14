package com.mycompany.ojedavalessistemabiblioteca;

/**
 *
 * @author leojeda
 */
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorBiblioteca {
    
    private static final String ARCHIVO = "libros.txt";
    private static List<Libro> listaLibros = new ArrayList<>();
    private static int siguienteId = 1;

    // Método para cargar datos
    public static void cargarDatos() {
        File archivo = new File(ARCHIVO);
        
        
        System.out.println("Buscando base de datos en: " + archivo.getAbsolutePath());
        
        if (!archivo.exists()) {
            System.out.println("Se creara el archivo al registrar un libro.");
            return; 
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                // ignora líneas en blanco
                if (linea.trim().isEmpty()) {
                    continue;
                }

                if (primeraLinea && linea.toLowerCase().startsWith("id")) {
                    primeraLinea = false;
                    continue;
                }

                String[] datos = linea.split(",");
                
                // checa las comas que haya 3 datos nomás
                if (datos.length >= 3) {
                    try {
                        int id = Integer.parseInt(datos[0].trim());
                        String titulo = datos[1].trim();
                        String autor = datos[2].trim();

                        Libro libroExistente = new Libro(id, titulo, autor);
                        listaLibros.add(libroExistente);
                        
                        if (id >= siguienteId) {
                            siguienteId = id + 1;
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("No se pudo leer el ID en la linea -> " + linea);
                    }
                } else {
                    System.out.println("Linea con formato incorrecto ignorada -> " + linea);
                }
            }
            System.out.println("Se cargaron " + listaLibros.size() + " libros del archivo.");
            
        } catch (Exception e) {
            // 4. ERROR REAL: Si algo explota, ahora sí nos dirá exactamente por qué
            System.out.println("Error al leer el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void registrarLibro(Scanner scanner) {
        System.out.print("Ingresa el titulo del libro: ");
        String titulo = scanner.nextLine();
        
        System.out.print("Ingresa el autor: ");
        String autor = scanner.nextLine();

        Libro nuevoLibro = new Libro(siguienteId, titulo, autor);
        listaLibros.add(nuevoLibro);
        siguienteId++; 

        guardarEnArchivo(nuevoLibro);
        System.out.println("Libro registrado con el ID: " + nuevoLibro.getId());
    }
    
    public static void mostrarLibros() {
        if (listaLibros.isEmpty()) {
            System.out.println("La biblioteca esta vacia.");
            return;
        }
        System.out.println("\n--- LISTA DE LIBROS ---");
        for (Libro libro : listaLibros) {
            System.out.println(libro.toString());
        }
    }
    
    private static void guardarEnArchivo(Libro libro) {
        File archivo = new File(ARCHIVO);
        boolean esNuevo = !archivo.exists();

        try (FileWriter fw = new FileWriter(archivo, true);
             PrintWriter pw = new PrintWriter(fw)) {
            
            if (esNuevo) pw.println("id,titulo,autor");
            pw.println(libro.getId() + "," + libro.getTitulo() + "," + libro.getAutor());
            
        } catch (IOException e) {
            System.out.println("Error al guardar.");
        }
    }
    
    // Método para buscar libros
    public static void buscarLibro(Scanner scanner) {
        if (listaLibros.isEmpty()) {
            System.out.println("La biblioteca estas vacia. No hay nada que buscar.");
            return;
        }

        System.out.print("Ingresa el titulo o autor a buscar: ");
        String termino = scanner.nextLine().toLowerCase().trim(); 

        boolean encontrado = false;
        System.out.println("\n--- RESULTADOS DE BUSQUEDA ---");

        for (Libro libro : listaLibros) {
            String tituloMin = libro.getTitulo().toLowerCase();
            String autorMin = libro.getAutor().toLowerCase();

            if (tituloMin.contains(termino) || autorMin.contains(termino)) {
                System.out.println(libro.toString());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron libros que coincidan con: '" + termino + "'");
        }
    }
}
