package tasks.task1;

import tasks.ScannerSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HowManyOddNumbers {

    private HowManyOddNumbers(){}

    public static void run() {
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Task: Enter three numbers. Find out how many of them are odd");
        final Scanner in = ScannerSingleton.getInstance();
        List<Integer> numbers = new ArrayList<>();

        System.out.println("Enter 3 int numbers:");
        while (numbers.size() < 3) {
//            numbers.add(in.nextInt());
            numbers.add(3);
            numbers.add(2);
            numbers.add(5);
        }

        int counter = 0;
        for (Integer number : numbers) {
            if(number % 2 != 0){
                counter++;
            }
        }

        System.out.println("Odd numbers: " + counter);
    }
}