package DAMAccesoADatos.view;


import DAMAccesoADatos.entity.Assistant;
import DAMAccesoADatos.entity.Gift;

import java.util.List;
import java.util.Scanner;


public class ConsoleView {
    private final Scanner sc = new Scanner(System.in);


    public int menu(){
        System.out.println("\n=== GESTIÓ DE CARTES REIS MAGS ===");
        System.out.println("1. Alta xiquet (Amb carta  completa)");
        System.out.println("2. Alta regal");
        System.out.println("3. Modificació de regal");
        System.out.println("4. Alta assistent ");
        System.out.println("5. Baixa assistent");
        System.out.println("6. Llistat regals per ciutat");
        System.out.println("7. Cerca de regals per coincidencia en descripció");
        System.out.println("8. Eixir");
        System.out.print("Tria una opció: ");
        return readInt();
    }


    public String askString(String tag){
        System.out.println(tag + ": ");
        return  sc.nextLine().trim();
    }

    public int askInt(String tag){
        System.out.println(tag + ": ");
        return readInt();
    }

    public void showGifts(List<Gift> gifts){
        System.out.println("\n=== LISTADO DE REGALS ===");
        if (gifts.isEmpty()) {
            System.out.println("(sense registros)");
        } else {
            gifts.forEach(System.out::println);
        }
    }

    public void showAssistants(List<Assistant> assistants) {
        System.out.println("\n=== LISTADO DE ASSISTENTS ===");
        if (assistants.isEmpty()) {
            System.out.println("(sense registros)");
        } else {
            assistants.forEach(System.out::println);
        }
    }


    public void info(String msg) {
        System.out.println(msg);
    }
    public void error(String msg) {
        System.err.println("ERROR: " + msg);
    }
    private int readInt() {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.print("Introduce un nombre sancer válid: ");
            }
        }
    }

}


