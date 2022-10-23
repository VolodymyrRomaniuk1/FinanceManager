package tasks.task7;

import tasks.task6.Employee;

import java.util.ArrayList;
import java.util.List;

public abstract class Person {
    private FullName fullName;
    private int age;

    public Person(FullName fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }

    public Person() {
    }

    public String info() {
        return "First name: " + fullName.getFirstName() + " Last name: " + fullName.getLastName() + " Age: " + getAge();
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
