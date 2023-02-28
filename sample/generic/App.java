package generic;

public class App {
    public static void main(String[] args) {
        MyMap<Integer, String> mapOne = new MyMap<>(1, "One");
        System.out.println("Key: " + mapOne.getKey() + ", Value: " + mapOne.getValue());
    }
}
