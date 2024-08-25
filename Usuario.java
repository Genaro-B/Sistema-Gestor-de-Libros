package SistemaGestorBiblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class Usuario{
    Scanner leer=new Scanner(System.in);
    Scanner leer2=new Scanner(System.in);
    private  String nombre="";
    private String apellido;
    private  int edad;
    private int numeroDeCliente;
    private int deuda;
    private ArrayList<Libros> misLibros=new ArrayList<>();
    //Constructores:
    public Usuario() {
    }
    public Usuario(String nombre, String apellido, int edad, int numeroDeCliente, int deuda, ArrayList<Libros> misLibros) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.numeroDeCliente = numeroDeCliente;
        this.deuda = deuda;
        this.misLibros = misLibros;
    }
    //Getters:
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public int getNumeroDeCliente() {
        return numeroDeCliente;
    }

    public int getDeuda() {
        return deuda;
    }

    public void setDeuda(int deuda) {
        this.deuda +=deuda;
    }

    public ArrayList<Libros> getMisLibros() {
        return misLibros;
    }
    //Setters:
    public void setMisLibros(ArrayList<Libros> misLibros) {
        this.misLibros = misLibros;
    }

    //Metodos:
    //Creamos un metodo que nos permita determinar si el usuario esta apto para tomar un libro
    //Su deuda no debe pasar o igualar los 5000 pesos;
    public boolean evaluarEstado(){
        boolean estado=true;
        if(this.deuda>=5000){
            estado=false;
        }
        return estado;
    }
    //Ahora vamos a crear un metodo que permita al usuario pagar lo que debe para posteriormente modificar su estado
    //y que pueda acceder a cualquier articulo;
    public void pagarDeuda(int monto){
        int nuevaDeuda=this.deuda-monto;
        if(nuevaDeuda<0){
            System.out.println("<--Mensaje importante-->\n");
            System.out.println("A Ingresado"+(-nuevaDeuda)+" Demas (Por Favor consulte su deuda para evitar malentendidos)\n");
        }else{
            this.deuda=nuevaDeuda;
        }
    }
    //Metodo para consultar deuda:
    public void mostrarDeuda(){
        System.out.println("Su deuda actual es de "+this.deuda);
    }
    //Metodo para configurar Usuario:
    public void configurarCuenta(){
        System.out.println("Empezemos!");
        System.out.print("Ingrese su Nombre:");
        this.nombre=leer.next();
        System.out.print("Ingrese su Apellido:");
        this.apellido=leer.next();
        System.out.print("Ingrese su edad:");
        this.edad=leer2.nextInt();
        System.out.print("Ingrese un numero con el que lo identifiquemos:");
        this.numeroDeCliente=leer2.nextInt();
        System.out.println("<--Cuenta creada con exito-->");
    }
    //Metodo para guardar un libro:
    public void guardarLibro(Libros libro){
        misLibros.add(libro);
        System.out.println("<--Guardado exitoso-->");
    }
    //Metodo mostrar mis libros:
    public void mostrarMisLibros(){
        System.out.println("<--Libros Personales-->");
        for(int i=0;i<misLibros.size();i++){
            System.out.println("Libro "+(i+1)+":"+misLibros.get(i).getTitulo());
        }
    }
}


