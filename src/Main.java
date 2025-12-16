import java.util.Scanner;


public class Main {

    private static Scanner escaner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean menuOn = true;

        do {
            Menu();
            int opcion = readIn("Escoge una opcion: ");

            switch(opcion){
                case 1 -> OperacionesBasicasMenu();
                case 2 -> OperacionesAvanzadasMenu();
                case 3 -> HistorialMenu();
                case 0 -> {
                    System.out.println("Saliendo... ¡Hasta luego!");
                    menuOn = false;
                }
                default -> System.out.println("Opción no válida.");

            }

        }while (menuOn);

    }

    private static void Menu(){
        System.out.println("\n==============================");
        System.out.println("     CALCULADORA (Consola)     ");
        System.out.println("==============================");
        System.out.println("1) Operaciones básicas (+ - * / %)");
        System.out.println("2) Operaciones avanzadas (pow, sqrt, factorial)");
        System.out.println("3) Historial");
        System.out.println("0) Salir");
        System.out.println("==============================");
    }

    private static void OperacionesBasicasMenu(){
        String[] ops = { "+", "-", "*", "/", "%" };

        boolean volver = false;
        while(!volver){
            System.out.println("Operaciones basicas: ");
            for (int i = 0; i < ops.length; i++){
                System.out.println((i+1) + ") " + ops[i]);
            }
            System.out.println("0) Volver");

            int opcion = readIn("Escoge una opcion: ");

            if (opcion == 0){
                volver = true;
                continue;
            }

            if (opcion < 1 || opcion > ops.length) {
                System.out.println("Opcion no valida");
                continue;
            }


        }
    }

    private static void OperacionesAvanzadasMenu(){

    }

    private static void HistorialMenu(){

    }

    private static int readIn (String msg){
        while (true){
            System.out.print(msg);
            try{
                return escaner.nextInt();
            }catch (Exception e) {
                System.out.println("Error: introduce un numero entero");
                escaner.next();
            }
        }
    }
}