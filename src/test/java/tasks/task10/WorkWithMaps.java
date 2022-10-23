package tasks.task10;

import tasks.ScannerSingleton;

import java.util.*;

public class WorkWithMaps {
    public static void run(){
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Task: In the main() method declare map employeeMap of pairs <Integer, String>.");
        Map<Integer, String> employeeMap = new TreeMap<>();
        employeeMap.put(1, "Employee1");
        employeeMap.put(2, "Employee2");
        employeeMap.put(3, "Employee3");
        employeeMap.put(4, "Employee4");
        employeeMap.put(5, "Employee5");
        employeeMap.put(6, "Employee6");
        employeeMap.put(7, "Employee7");

        final Scanner in = ScannerSingleton.getInstance();
        System.out.println("Enter employee ID: ");
//        int id = in.nextInt();
        int id = 3;
        System.out.println(employeeMap.getOrDefault(id, "No such employee"));

        System.out.println("Enter employee name: ");
//        String name = in.nextLine();
        String name = "Employee7";

        if(employeeMap.containsValue(name)){
            System.out.println(getKeys(employeeMap, name));
        }else {
            System.out.println("No such employee");
        }
    }

    public static  <K, V> Set<K> getKeys(Map<K, V> map, V value) {
        Set<K> keys = new HashSet<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }
}
