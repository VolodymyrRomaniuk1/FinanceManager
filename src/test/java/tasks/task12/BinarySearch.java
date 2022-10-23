package tasks.task12;

public class BinarySearch {
    public static void run(){
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Task: Binary search \n");
        int array[] = {-453, -300, -299, -250, -150, -100, -33, -2, 0, 1, 2, 3, 34, 60, 98, 100, 103, 140, 154, 240, 301, 344};

        System.out.println(binarySearch(array, -250, 0, array.length-1));
    }

    public static int binarySearch(int[] sortedArray, int key, int start, int end) {
        int index = Integer.MAX_VALUE;

        while (start <= end) {
            int mid = start  + ((end - start) / 2);
            if (sortedArray[mid] < key) {
                start = mid + 1;
            } else if (sortedArray[mid] > key) {
                end = mid - 1;
            } else if (sortedArray[mid] == key) {
                index = mid;
                break;
            }
        }
        return index;
    }
}
