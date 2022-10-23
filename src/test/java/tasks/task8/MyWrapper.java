package tasks.task8;

public class MyWrapper<T> {
    private T value;

    public MyWrapper(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public static void run(){
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Task: Create Wrapper<T>  class with private field of T type which is called value");
        MyWrapper<Integer> wrappedInt = new MyWrapper<>(5);
        MyWrapper<String> wrappedString = new MyWrapper<>("Wrapped string");
        MyWrapper<Boolean> wrappedBool = new MyWrapper<>(true);
        System.out.println(wrappedInt.getValue());
        System.out.println(wrappedString.getValue());
        System.out.println(wrappedBool.getValue());
    }
}