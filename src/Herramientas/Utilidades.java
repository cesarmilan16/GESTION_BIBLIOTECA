package Herramientas;

import java.util.Scanner;

public class Utilidades {
    static Scanner scanner = new Scanner(System.in);

    public static boolean leerBoolean(String dato) {
        while (true) {
            try {
                System.out.println("Dar " + dato);
                String datoLeido = scanner.nextLine();
                if (datoLeido.equals("1")) {
                    return true;
                } else if (datoLeido.equals("0")) {
                    return false;
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Valor incorrecto. Por favor, ingrese '1' para verdadero o '0' para falso.");
            }
        }
    }
}
