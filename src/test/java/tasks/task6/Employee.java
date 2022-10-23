package tasks.task6;

import lombok.*;
import tasks.ScannerSingleton;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Employee {
    private String name;
    private int departmentNumber;
    private int salary;

    public static void run(){
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Task: Create a class Employee with fields name, department number, salary. Create five objects of class Employee. Display: \n\tall employees of a certain department (enter department number in the console)\n\tarrange workers by the field salary in descending order \n");

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Employee1", 1, 1500));
        employees.add(new Employee("Employee2", 2, 900));
        employees.add(new Employee("Employee3", 4, 1600));
        employees.add(new Employee("Employee4", 2, 3000));
        employees.add(new Employee("Employee5", 1, 1000));

        final Scanner in = ScannerSingleton.getInstance();
        System.out.println("Enter department number: ");
//        int departmentNumber = in.nextInt();
        int departmentNumber = 2;

        System.out.println("Employees in department " + departmentNumber + " :");
        for (Employee employee:employees) {
            if(employee.getDepartmentNumber() == departmentNumber){
                System.out.println(employee);
            }
        }

        employees.sort(Comparator.comparingInt(Employee::getSalary));
        Collections.reverse(employees);
        System.out.println("Employees sorted by salary:");
        System.out.println(employees);

    }
}