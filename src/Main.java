import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


public class Main {

    private static final Scanner escaner = new Scanner(System.in);

    public static void main(String[] args) {
        Calculadora calc = new Calculadora();


        boolean menuOn = true;

        do {
            Menu();
            int opcion = readIn("Escoge una opcion: ");

            switch(opcion){
                case 1 -> OperacionesBasicasMenu(calc);
                case 2 -> OperacionesAvanzadasMenu(calc);
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

    private static void OperacionesBasicasMenu(Calculadora calc, Historial historial){
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

            try{
                double result = calc.opBasicas(a, b, ops[opcion -1]);
                System.out.println("Resultado: " + result);
            }catch (IllegalAccessError e){
                System.out.println("Error: " +e.getMessage());
            }
        }
    }

    private static void OperacionesAvanzadasMenu(Calculadora calc, Historial historial){

        boolean volver = false;

        while(!volver) {
            System.out.println("\n--- Operaciones avanzadas ---");
            System.out.println("1) Potencia (a^b)");
            System.out.println("2) Raíz cuadrada");
            System.out.println("3) Factorial");
            System.out.println("0) Volver");

            int opcion = readIn("Escoge una opcion: ");

            try{
                switch (opcion){
                    case 1 -> {
                        double a = readDouble("Base: ");
                        double b = readDouble("Exponente: ");
                        double result = calc.Potencia(a,b);
                        System.out.println("Resultado: "+result);

                    }
                    case 2 -> {
                        double a = readDouble("Numero: ");
                        double result = calc.raiz(a);
                        System.out.println("Resultado: "+result);
                    }
                    case 3 -> {
                        int n = readIn("Numero entero: ");
                        long result = calc.factorial(n);
                        System.out.println("Resultado: "+result);
                    }
                    case 0 -> volver = true;
                    default -> System.out.println("Opcion no valida");
                }
            }catch (IllegalAccessError e){
                System.out.println("Error: "+ e.getMessage());
            }
        }

    }

    private static void HistorialMenu(Historial historial){

        boolean volver = false;

        while (!volver){
            System.out.println("\n--- Historial ---");
            System.out.println("1) Ver historial");
            System.out.println("2) Buscar por operador");
            System.out.println("3) Ordenar por resultado");
            System.out.println("4) Ordenar por fecha");
            System.out.println("5) Borrar historial");
            System.out.println("0) Volver");

            int opcion = readIn("Escoge una opcion: ");

            switch(opcion){
                case 1 -> historial.printAll();
                case 2 -> {
                    System.out.println("Operador: ");
                    String op = escaner.next();
                    historial.BuscarPorOperador(op);
                }
                case 3 -> {
                    historial.OrdenarResultado();
                    System.out.println("Historial Ordenado");
                }
                case 4 -> {
                    historial.OrdenarFecha();
                    System.out.println("Historial Ordenado");
                }
                case 5 -> {
                    historial.Limpiar();
                    System.out.println("Historial Borrado");
                }
                case 0 -> volver = true;
                default -> System.out.println("Opcion no valida");
            }
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
        return switch (op){
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
            default -> throw new IllegalArgumentException("Operacion invalida");
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

class Operacion{
    double a;
    double b;
    String operador;
    double result;
    LocalDateTime data;

    Operacion(double a, double b, String operador, double result){
        this.a = a;
        this.b = b;
        this.operador = operador;
        this.result = result;
        this.data = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "[" + data.format(f) + "] " + a + " " + operador + " " + b + " = " + result;
    }
}

class Historial{
    ArrayList<Operacion> historial = new ArrayList<>();

    void add (Operacion op){
        historial.add(op);
    }

    void printAll(){
        if (historial.isEmpty()){
            System.out.println("Historial vacio");
            return;
        }
        for(Operacion operacion : historial){
            System.out.println(operacion);
        }
    }

    void BuscarPorOperador(String op){
        for (Operacion o : historial){
            if (o.operador.equals(op)){
                System.out.println(o);
            }
        }
    }

    void OrdenarResultado(){
        historial.sort(Comparator.comparingDouble(o -> o.result));
    }

    void OrdenarFecha(){
        historial.sort((a,b) -> b.data.compareTo(a.data));
    }

    void Limpiar(){
        historial.clear();
    }
}