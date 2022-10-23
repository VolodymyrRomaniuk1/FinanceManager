package tasks.task3;

import tasks.ScannerSingleton;

import java.util.Scanner;

public class GetContinentOfCountry {
    public static void run(){
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Task: Enter the name of the country. Print the name of the continent.");
        final Scanner in = ScannerSingleton.getInstance();
        System.out.println("Enter Country name:");
//        String country = in.nextLine();
        String country = "USA";

        if(Continent.EUROPE.getCountries().contains(country)){
            System.out.println(country + " is in Europe");
            return;
        }
        if(Continent.ASIA.getCountries().contains(country)){
            System.out.println(country + " is in Asia");
            return;
        }
        if(Continent.AFRICA.getCountries().contains(country)){
            System.out.println(country + " is in Africa");
            return;
        }
        if(Continent.NORTH_AMERICA.getCountries().contains(country)){
            System.out.println(country + " is in North America");
            return;
        }
        if(Continent.SOUTH_AMERICA.getCountries().contains(country)){
            System.out.println(country + " is in South America");
            return;
        }
        if(Continent.AUSTRALIA.getCountries().contains(country)){
            System.out.println(country + " is in Australia");
            return;
        }
        if(Continent.ANTARCTICA.getCountries().contains(country)){
            System.out.println(country + " is in Antarctica");
            return;
        }
        System.out.println("No such country on any continent");
    }
}
