package tasks.task9;

import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;

public class WorkWithCollections {
    public static void run() {
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Task: Declare collection myCollection of 10 integers and fill it...");
        List<Integer> myCollection = new ArrayList<>();
        myCollection.add(6);
        myCollection.add(4);
        myCollection.add(-5);
        myCollection.add(33);
        myCollection.add(10);
        myCollection.add(-99);
        myCollection.add(150);
        myCollection.add(5);
        myCollection.add(3);
        myCollection.add(1);

        printList(myCollection);
        List<Integer> newCollection = new ArrayList<>();
        for (Integer integer : myCollection) {
            if (integer > 5) {
                newCollection.add(myCollection.indexOf(integer));
            }
        }
        System.out.println("\nIndexes of elements greater than 5: ");
        System.out.println(newCollection);

        for (Integer index : newCollection) {
            if (myCollection.get(index) > 20) {
                myCollection.set(index, Integer.MIN_VALUE);
            }
        }
        System.out.println("\nUpdated list of ints: ");
        printList(myCollection);

        myCollection.add(2, 1);
        myCollection.add(5, -4);
        myCollection.add(8, -3);

        System.out.println("\nUpdated list of ints: ");
        printList(myCollection);
    }

    public static void printList(List<Integer> list) {
        for (int i = 0; i < list.size(); i++){
            System.out.println("position - " + i + " value = " + list.get(i));
        }
    }
}