import java.util.Scanner;


public class Main {

    private static final Scanner escaner = new Scanner(System.in);

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

            double a = readDouble("Primer numero: ");
            double b = readDouble("Segundo numero: ");

        }
    }

    private static void OperacionesAvanzadasMenu(){

        boolean volver = false;

        while(!volver) {
            System.out.println("\n--- Operaciones avanzadas ---");
            System.out.println("1) Potencia (a^b)");
            System.out.println("2) Raíz cuadrada");
            System.out.println("3) Factorial");
            System.out.println("0) Volver");

            int opcion = readIn("Escoge una opcion: ");
        }

    }

    private static void HistorialMenu(){

        boolean back = false;

        while (!back){
            System.out.println("\n--- Historial ---");
            System.out.println("1) Ver historial");
            System.out.println("2) Buscar por operador");
            System.out.println("3) Ordenar por resultado");
            System.out.println("4) Ordenar por fecha");
            System.out.println("5) Borrar historial");
            System.out.println("0) Volver");

            int opcion = readIn("Escoge una opcion: ");
        }

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

    private static double readDouble(String msg){
        while(true){
            System.out.println(msg);
            try{
                return escaner.nextDouble();
            }catch (Exception e){
                System.out.println("Error: Introduce un numero valido");
                escaner.next();
            }
        }
    }
}

class Calculadora{

    public double opBasicas(double a, double b, String op) {
        return (op){
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) throw new IllegalArgumentException("División por cero.");
                yield a / b;
            }
            case "%" -> {
                if (b == 0) throw new IllegalArgumentException("Módulo por cero.");
                yield a % b;
            }
            default -> throw new IllegalArgumentException("Operacion invalida")
        };
    }

    public double Potencia(double a, double b){
        return Math.pow(a,b);
    }

    public double raiz(double a){
        if (a < 0) throw new IllegalArgumentException("Número negativo.");
        return Math.sqrt(a);
    }

    public long factorial(int n){
        if (n < 0) throw new IllegalArgumentException("Factorial negativo.");
        long res = 1;
        for (int i = 2; i <= n; i++) {
            res *= i;
        }
        return res;
    }
}

