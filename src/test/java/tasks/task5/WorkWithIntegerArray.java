package tasks.task5;

public class WorkWithIntegerArray {
    public static void run(){
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Task: Create an array of ten integers. Display: \n\tthe biggest of these numbers\n\tthe sum of positive numbers in the array \n\tthe amount of negative numbers in the array\n\tWhat values there are more: negative or positive\n");

        int array[] = {1, 3, -4, -10, 3, 11, 50, -22, 5, 10};
        int biggestNumber = Integer.MIN_VALUE;
        int sumOfPositiveNumbers = 0;
        int amountOfNegativeNumbers = 0;

        for (int i = 0; i < 10; i++){
            if(array[i] > biggestNumber){
                biggestNumber = array[i];
            }
            if(array[i] > 0){
                sumOfPositiveNumbers += array[i];
            }
            if(array[i] < 0){
                amountOfNegativeNumbers++;
            }
        }

        System.out.println("Biggest number: " + biggestNumber);
        System.out.println("Sum of positive numbers: " + sumOfPositiveNumbers);
        System.out.println("Amount of negative numbers: " + amountOfNegativeNumbers);
        if(amountOfNegativeNumbers > 5){
            System.out.println("There are more negative numbers than positive numbers");
        }else if(amountOfNegativeNumbers < 5){
            System.out.println("There are more positive numbers than negative numbers");
        }else {
            System.out.println("There is equal amount of positive and negative numbers");
        }
    }
}
