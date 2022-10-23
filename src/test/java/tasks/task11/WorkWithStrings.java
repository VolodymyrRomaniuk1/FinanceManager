package tasks.task11;

public class WorkWithStrings {
    public static void run() {
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Task: Return the numbers 1..100 \n");

        String firstName = "firstName";
        String lastName = "lastName";
        String fullName = firstName + " " + lastName;
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println(fullName);
                continue;
            }
            if (i % 3 == 0) {
                System.out.println(firstName);
            } else if (i % 5 == 0) {
                System.out.println(lastName);
            } else {
                System.out.println(i);
            }
        }
    }
}
