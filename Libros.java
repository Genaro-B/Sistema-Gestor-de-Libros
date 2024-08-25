package SistemaGestorBiblioteca;

import java.util.Scanner;

public class Libros {
    Scanner leer=new Scanner(System.in);
    //Comenzamos creando los atributos fundamentales de cada libro;
    private String autor;
    private String titulo;
    private int id;
    private boolean estado;
    private Usuario pertenencia;
    private String[] comentarios=new String[10];
    //Constructores:
    public Libros() {
    }

    public Libros(String autor, String titulo, int id) {
        this.autor = autor;
        this.titulo = titulo;
        this.id = id;
    }
    //Getters:
    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getId() {
        return id;
    }

    public boolean isEstado() {
        return estado;
    }

    public Usuario getPertenencia() {
        return pertenencia;
    }

    public String[] getComentarios() {
        return comentarios;
    }
    //Metodos Generales:

    //Crear el metodo set del atributo pertenencia para que nos permita manipular quien es el tutor actual de el libro;
    public void setPertenencia(Usuario pertenencia) {
        this.pertenencia = pertenencia;
    }
    //Metodo que permita agregar un comentario al libro
    public void agregarComentario(String Comentario){
        for (int i=0;i<this.comentarios.length;i++){
            if(this.comentarios[i]==null){
                this.comentarios[i]=Comentario;
                break;
            }
        }
    }
    //Metodo que permita ver comentarios:
    public void verComentarios() {
        System.out.println("<--Comentarios-->");
        for (int i = 0; i < this.comentarios.length; i++) {
            System.out.print((i + 1) + " ");
            if (this.comentarios[i]==null) {
                System.out.println("vacio");
            } else {
                System.out.println(this.comentarios[i]);
            }
        }
    }
    //Metodo para ver la informacion del libro:
    public void verInformacion(){
        int opcion=0;
        System.out.println("<--Libro-->");
        System.out.println("Nombre :"+this.getTitulo());
        System.out.println("Autor:"+this.autor);
        System.out.println("Id:"+this.getId());
        System.out.println("Desea ver comentarios del libro? 1)si 2)no");
        opcion=leer.nextInt();
        if(opcion==1){
            verComentarios();
        }
    }
}
