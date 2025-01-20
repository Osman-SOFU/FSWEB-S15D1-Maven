package org.example.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Grocery {

    // public static ArrayList
    public static ArrayList<String> groceryList = new ArrayList<>();

    // Konsol uygulaması için startGrocery methodu
    public static void startGrocery() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nSeçenekler: ");
            System.out.println("0 - Uygulamayı durdur");
            System.out.println("1 - Ürün ekle");
            System.out.println("2 - Ürün çıkar");
            System.out.print("Seçiminiz: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // nextInt sonrasında scanner buffer temizliği

            switch (choice) {
                case 0:
                    isRunning = false;
                    break;
                case 1:
                    System.out.println("Eklenmesini istediğiniz elemanları giriniz.");
                    String itemsToAdd = scanner.nextLine();
                    addItems(itemsToAdd);
                    break;
                case 2:
                    System.out.println("Cıkarılmasını istediğiniz elemanları giriniz.");
                    String itemsToRemove = scanner.nextLine();
                    removeItems(itemsToRemove);
                    break;
                default:
                    System.out.println("Geçersiz seçenek. Lütfen 0, 1 ya da 2 giriniz.");
                    break;
            }
        }
        scanner.close();
    }

    // Eleman ekleme metodu
    public static void addItems(String input) {
        String[] items = input.split(",\\s*"); // Virgül ile ayrılan elemanları böl
        for (String item : items) {
            if (!checkItemIsInList(item)) {  // Listeye tekrar eklemeyi önlemek için kontrol
                groceryList.add(item);
            }
        }
        printSorted();
    }

    // Eleman çıkarma metodu
    public static void removeItems(String input) {
        String[] items = input.split(",\\s*");
        for (String item : items) {
            if (checkItemIsInList(item)) {
                groceryList.remove(item);
            }
        }
        printSorted();
    }

    // Liste içinde eleman kontrolü
    public static boolean checkItemIsInList(String product) {
        return groceryList.contains(product);
    }

    // Listeyi sıralayıp ekrana basma metodu
    public static void printSorted() {
        Collections.sort(groceryList);
        System.out.println("Güncel Liste: " + groceryList);
    }
}
