package tasks;

import tasks.task1.HowManyOddNumbers;
import tasks.task11.WorkWithStrings;
import tasks.task10.WorkWithMaps;
import tasks.task12.BinarySearch;
import tasks.task3.GetContinentOfCountry;
import tasks.task4.Product;
import tasks.task5.WorkWithIntegerArray;
import tasks.task6.Employee;
import tasks.task7.Worker;
import tasks.task8.MyWrapper;
import tasks.task9.WorkWithCollections;

public class Main {
    public static void main(String[] args) {
        HowManyOddNumbers.run();
        GetContinentOfCountry.run();
        Product.run();
        WorkWithIntegerArray.run();
        Employee.run();
        Worker.run();
        MyWrapper.run();
        WorkWithCollections.run();
        WorkWithMaps.run();
        WorkWithStrings.run();
        BinarySearch.run();
    }
}