import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Item {
    int value;
    int weight;

    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

class FractionalKnapsack {

    // Comparator to sort items by value-to-weight ratio
    static class CompareItem implements Comparator<Item> {
        public int compare(Item a, Item b) {
            double r1 = (double) a.value / a.weight;
            double r2 = (double) b.value / b.weight;
            return Double.compare(r2, r1); // Sort in descending order
        }
    }

    // Function to get the maximum value in the knapsack
    public static double fractionalKnapsack(int W, List<Item> items, int n) {
        // Sort items by value-to-weight ratio
        Collections.sort(items, new CompareItem());
        int curWeight = 0;
        double finalValue = 0.0;

        for (int i = 0; i < n; i++) {
            if (curWeight + items.get(i).weight <= W) {
                curWeight += items.get(i).weight;
                finalValue += items.get(i).value;
            } else {
                int remain = W - curWeight;
                finalValue += items.get(i).value * ((double) remain / items.get(i).weight);
                break;
            }
        }
        return finalValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();
        List<Item> items = new ArrayList<>();
        int value, weight;

        for (int i = 0; i < n; i++) {
            System.out.print("Enter value and weight for item " + (i + 1) + ": ");
            value = scanner.nextInt();
            weight = scanner.nextInt();
            items.add(new Item(value, weight));
        }

        System.out.print("Enter the maximum capacity of the knapsack: ");
        int W = scanner.nextInt();
        System.out.println("Maximum value in Knapsack = " + fractionalKnapsack(W, items, n));
        scanner.close();
    }
}