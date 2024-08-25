package SistemaGestorBiblioteca;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.Scanner;

public class Biblioteca {
    public static void main(String[] args){
        //Creamos una instancia Scanner para leer información:
        Scanner leer=new Scanner(System.in);
        /*
        Este proyecto esta hecho con el fin de automatizar la tarea de alguien que trabaja en una biblioteca
        -La idea es dar ciertas funcionalidades para permitir que el usuario pueda adquirir libros y demas;
         */
        //Vamos a comenzar creando una 4 instancias genaricas de mi clase libros
        Libros libro1=new Libros("Juan ramirez","La luna azul",3445);
        Libros libro2=new Libros("Rogelio Ortega","El espacio y su inmensidad",2734);
        Libros libro3=new Libros("Axel gomez ","Caminar o correr",4239);
        ArrayList<Libros> Biblioteca=new ArrayList<>();
        Biblioteca.add(libro1);
        Biblioteca.add(libro2);
        Biblioteca.add(libro3);
        //Creamos 2 instancias de Usuario , tan solo para probar el funcionamiento
        Usuario Usuario1=new Usuario();
        Usuario Usuario2=new Usuario();
        //Creamos la variables a utilizar;
        boolean control=true;
        int opcion=0;
        int numeroDecliente;
        System.out.println("<--Bienvenido a nuestro Sistema Gestor de libros-->");
        while (control){

            System.out.println("<--Menu-->");
            System.out.print("1)Ver lista\n2)Pedir libro\n3)Mis libros\n4)Pagar deuda\n5)Registrarme\n6)salir\n>");
            opcion=leer.nextInt();
            switch (opcion){
                case 1:
                        mostrarLibros(Biblioteca);
                    break;
                case 2:
                        mensaje();
                        numeroDecliente=leer.nextInt();
                        pedirlibro(Biblioteca,numeroDecliente,Usuario1,Usuario2);
                    break;
                case 3:
                        mensaje();
                        numeroDecliente=leer.nextInt();
                        mislibros(Biblioteca,numeroDecliente,Usuario1,Usuario2);

                    break;
                case 4:
                        System.out.println("Ingrese su numero de cliente");
                        numeroDecliente=leer.nextInt();
                        pagarDeuda(numeroDecliente,Usuario1,Usuario2);
                    break;
                case 5:
                         if(Usuario1.getNombre().equalsIgnoreCase("")){
                             Usuario1=registrar();
                         }else{
                             if(Usuario2.getNombre().equalsIgnoreCase("")){
                                 Usuario2=registrar();
                             }else{
                                 System.out.println("Nuestra Lista de usuarios esta llena!");
                             }
                         }
                    break;
                case 6:
                    control=false;
                    break;
                default:
                    System.out.println("Opcion no existente!");
            }
        }
    }
    //Metodos que pertenecen a la clase principal:
    public static void mostrarLibros(ArrayList<Libros> Biblioteca){
        Scanner teclado=new Scanner(System.in);
        Scanner teclado2=new Scanner(System.in);
        String nombre="";
        System.out.println("<--Biblioteca-->");
        for (Libros elemento : Biblioteca){
            System.out.println("Titulo:"+elemento.getTitulo());
        }
        System.out.println("----------------");
        do{
            System.out.println("Ingrese el nombre de un libro para obtener más info o ingrese salir para evitar esta funcion");
            if(!nombre.equalsIgnoreCase("")){
                nombre=teclado2.nextLine();
            }else {
                nombre=teclado.nextLine();
            }
            if(!nombre.equalsIgnoreCase("salir")){
               for(Libros elemento: Biblioteca){
                   if(elemento.getTitulo().equalsIgnoreCase(nombre)){
                       elemento.verInformacion();
                   }
               }
            }
        }while (!nombre.equalsIgnoreCase("salir"));

    }
    public static void pedirlibro(ArrayList<Libros>Biblioteca,int numeroCliente,Usuario u1,Usuario u2){
        Scanner leer=new Scanner(System.in);
        int indice=0;
        Libros libro;

        if(u1.getNumeroDeCliente()==numeroCliente||u2.getNumeroDeCliente()==numeroCliente){
            System.out.println("<--Seleccione un libro-->");
            for(int i=0;i<Biblioteca.size();i++){
                System.out.println("Libro "+(i+1)+":"+Biblioteca.get(i).getTitulo());
            }
            //Validar que el numero de libro exista:
            do{
                System.out.print("Libro a adquirir:");
                indice=leer.nextInt();
                if(indice<1||indice>3){
                    System.out.println("<--Ese libro no existe-->");
                }
            }while (indice<1 || indice>3);
            //Guardar Libro en variable local
            libro=Biblioteca.get(indice-1);
            if(numeroCliente==u1.getNumeroDeCliente()){
                guardarEnInventario(u1,indice,libro,Biblioteca);
            }else{
                guardarEnInventario(u2,indice,libro,Biblioteca);
            }

        }else{
            System.out.println("<--Numero de cliente invalido (En caso de no tener un codigo de cliente ->registrarse)-->");
        }

    }
    public static void mislibros(ArrayList<Libros>Biblioteca,int numeroCliente,Usuario u1,Usuario u2){
        Scanner leer=new Scanner(System.in);
        int opcion=0;
        System.out.println("1)Devolver libro 2)Agregar comentario");
        opcion=leer.nextInt();
        if(opcion==1) {
            if (numeroCliente == u1.getNumeroDeCliente() || numeroCliente == u2.getNumeroDeCliente()) {
                System.out.println("Indique el libro a devolver:");
                if (numeroCliente == u1.getNumeroDeCliente()) {
                    devolverLibro(u1,Biblioteca);
                } else {
                    devolverLibro(u2,Biblioteca);
                }
                System.out.println("<--Mensaje importante-->");
                System.out.println("El libro fue devuelto y retirado de tu biblioteca personal");
            } else {
                mostrarMensajeError();
            }
        }else{
            if (numeroCliente == u1.getNumeroDeCliente() || numeroCliente == u2.getNumeroDeCliente()) {
                System.out.println("Indique el libro a el que va agregar el comentario");
                if (numeroCliente == u1.getNumeroDeCliente()) {
                    hacerComentario(u1,Biblioteca);
                } else {
                    hacerComentario(u2,Biblioteca);
                }
                System.out.println("<--Comentario exitoso-->");
            } else {
                mostrarMensajeError();
            }

        }

    }
    public static Usuario registrar(){
        Usuario Usuario=new Usuario();
        Usuario.configurarCuenta();
        return Usuario;
    }
    public static void pagarDeuda(int numeroCliente,Usuario u1,Usuario u2){
        Scanner leer=new Scanner(System.in);
        int opcion=0;
        if(numeroCliente==u1.getNumeroDeCliente()||numeroCliente==u2.getNumeroDeCliente()){
            System.out.println("1)Consultar deuda 2)pagar");
            opcion=leer.nextInt();
            if(numeroCliente==u1.getNumeroDeCliente()){
                evaluaropcion(u1,opcion);
            }else{
                evaluaropcion(u2,opcion);
            }
        }else{
            mostrarMensajeError();
        }

    }
    public static void mostrarMensajeError(){
            System.out.println("<--Numero de cliente invalido (En caso de no tener un codigo de cliente ->registrarse)-->");
    }
    public static void devolverLibro(Usuario usuario,ArrayList<Libros> Biblioteca){
        Scanner leer=new Scanner(System.in);
        int libro=0;
        usuario.mostrarMisLibros();
        libro=leer.nextInt();
        Biblioteca.add(usuario.getMisLibros().get(libro - 1));
        usuario.getMisLibros().remove(libro - 1);
    }

    public static void hacerComentario(Usuario usuario,ArrayList<Libros> Biblioteca){
        Scanner leer=new Scanner(System.in);
        Scanner leer2=new Scanner(System.in);
        int libro=0;
        String comentario="";

        usuario.mostrarMisLibros();
        libro = leer.nextInt();
        System.out.print("Ingrese el  comentario:");
        comentario=leer2.nextLine();
        comentario+=("    -Autor: "+usuario.getNombre()+" "+usuario.getApellido());
        usuario.getMisLibros().get(libro-1).agregarComentario(comentario);
    }
    public static  void evaluaropcion(Usuario usuario,int opcion){
        Scanner leer=new Scanner(System.in);
        int monto;
        if(opcion==1){
            System.out.println("Deuda:"+usuario.getDeuda());
        }else{
            if(opcion==2){
                System.out.print("Indique el monto a pagar:");
                monto=leer.nextInt();
                usuario.pagarDeuda(monto);
            }else{
                System.out.println("Opcion no existente!");
            }
        }
    }
    public static void guardarEnInventario(Usuario usuario,int indice,Libros libro,ArrayList<Libros> Biblioteca){
        if(usuario.evaluarEstado()){
            usuario.guardarLibro(libro);
            Biblioteca.remove(indice-1);
            usuario.setDeuda(2500);
        }else{
            System.out.println("<--No puede adquirir un libro nuevo porque debe dinero-->");
        }
    }
    public static void mensaje(){
        System.out.println("Ingrese su numero de cliente");
    }
}
