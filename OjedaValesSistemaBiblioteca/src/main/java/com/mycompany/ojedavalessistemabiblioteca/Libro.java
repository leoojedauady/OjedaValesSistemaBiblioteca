package com.mycompany.ojedavalessistemabiblioteca;

/**
 *
 * @author leojeda
 */
public class Libro {
    private String titulo;
    private String autor;
    private int id; // la forma de identificar al libro

    public Libro(int id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
    }
    
    public int getId() {
        return id; }
    public String getTitulo() {
        return titulo; }
    public String getAutor() { 
        return autor; }
    
    @Override
    public String toString() {
        return "[" + id + "] " + titulo + " - " + autor;
    }
}
