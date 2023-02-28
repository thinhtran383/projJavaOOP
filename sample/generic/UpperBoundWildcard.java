package generic;

import java.util.ArrayList;

public class UpperBoundWildcard {
    public static double avergeValue(ArrayList<? extends Number> list) {
        if (list.size() == 0 || list == null)
            return 0;
        double sum = 0;
        for (var number : list) {
            sum += number.doubleValue();
        }
        return sum / list.size();
    }

    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        System.out.println("Average of integers: " + avergeValue(integers));
        ArrayList<Double> doubles = new ArrayList<>();
        doubles.add(1.2);
        doubles.add(2.3);
        doubles.add(3.4);
        doubles.add(4.5);
        doubles.add(5.6);
        System.out.println("Average of doubles: " + avergeValue(doubles));
    }
}
