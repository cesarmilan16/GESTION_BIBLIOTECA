package Herramientas;

import java.util.Scanner;

public class Utilidades {
    // Crea un objeto Scanner estático para leer la entrada del usuario desde la consola
    static Scanner scanner = new Scanner(System.in);

    // Método para leer valores booleanos
    public static boolean leerBoolean(String dato) {
        while (true) {
            try {
                System.out.println(dato);
                String datoLeido = scanner.nextLine();
                if (datoLeido.equals("1")) {
                    return true;
                } else if (datoLeido.equals("0")) {
                    return false;
                // Si no es ni "1" ni "0" lanza una excepción
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Valor incorrecto. Por favor, ingrese '1' para verdadero o '0' para falso.");
            }
        }
    }

    // Método para leer valores int
    public static int leerNum(String dato) {
        while (true) {
            try {
                System.out.println(dato);
                String datoLeido = scanner.nextLine();
                int datoNum = Integer.parseInt(datoLeido);
                return datoNum;
            // Si no introduce un número lanza la excepción
            } catch (Exception e) {
                System.out.println("Valor erroneo");
            }
        }
    }
}