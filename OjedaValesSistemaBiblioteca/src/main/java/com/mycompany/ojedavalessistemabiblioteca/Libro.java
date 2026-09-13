package com.mycompany.ojedavalessistemabiblioteca;

/**
 *
 * @author leojeda
 */
public class Libro {
    String nombre;
    String autor;
    String url; // url al libro (opcional)
    long isbn; // identificador único de un libro (opcional)

    public Libro(String nombre, String autor, String url, long isbn) {
        this.nombre = nombre;
        this.autor = autor;
        this.url = url;
        this.isbn = isbn;
    }

    public Libro(String nombre, String autor) {
        this(nombre,autor,"example.com",0000000000000); //cuando no nos dan la url o el isbn
    }
    
    
}
