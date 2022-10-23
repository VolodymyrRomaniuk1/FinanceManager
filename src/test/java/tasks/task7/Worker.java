package tasks.task7;

import java.util.ArrayList;
import java.util.List;

public class Worker extends Person{

    public Worker(FullName fullName, int age) {
        super(fullName, age);
    }

    public Worker() {
    }

    public static void run() {
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Task: Develop a FullName class with the firstName and lastName fields of type String, which would correspond to the principle of encapsulation...");

        List<Person> people = new ArrayList<>();
        people.add(new Worker(new FullName("firstName1", "lastName1"), 30));
        people.add(new Worker(new FullName("firstName2", "lastName2"), 28));

        System.out.println(people.get(1).info());
    }
}
