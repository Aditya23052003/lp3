import java.util.PriorityQueue;
import java.util.Scanner;

class MinHeapNode {
    char data;
    int freq;
    MinHeapNode left, right;

    MinHeapNode(char data, int freq) {
        this.data = data;
        this.freq = freq;
        this.left = null;
        this.right = null;
    }
}

// Comparator class for priority queue
class Compare implements java.util.Comparator<MinHeapNode> {
    public int compare(MinHeapNode left, MinHeapNode right) {
        return left.freq - right.freq;
    }
}

public class HuffmanCoding {

    // Function to print the Huffman codes for each character
    public static void printCodes(MinHeapNode root, String str) {
        if (root == null) {
            return;
        }
        if (root.data != '$') {
            System.out.println(root.data + ": " + str);
        }
        printCodes(root.left, str + "0");
        printCodes(root.right, str + "1");
    }

    // Function to build the Huffman Tree and generate codes
    public static void huffmanCodes(char[] data, int[] freq, int size) {
        MinHeapNode left, right, top;
        // Priority Queue to store the nodes based on frequency
        PriorityQueue<MinHeapNode> minHeap = new PriorityQueue<>(size, new Compare());

        // Creating a leaf node for each character and adding it to the priority queue
        for (int i = 0; i < size; i++) {
            minHeap.add(new MinHeapNode(data[i], freq[i]));
        }

        // Iterate while the size of the queue is more than 1
        while (minHeap.size() != 1) {
            // Extract the two nodes with the lowest frequency
            left = minHeap.poll();
            right = minHeap.poll();

            // Create a new internal node with a frequency equal to the sum of the two nodes' frequencies
            top = new MinHeapNode('$', left.freq + right.freq);
            top.left = left;
            top.right = right;

            // Add the new node to the priority queue
            minHeap.add(top);
        }

        // Print the Huffman codes
        printCodes(minHeap.peek(), "");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of characters: ");
        int n = scanner.nextInt();
        char[] arr = new char[n];
        int[] freq = new int[n];

        System.out.print("Enter characters: ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.next().charAt(0);
        }

        System.out.print("Enter frequencies: ");
        for (int i = 0; i < n; i++) {
            freq[i] = scanner.nextInt();
        }

        // Generate Huffman Codes
        huffmanCodes(arr, freq, n);

        scanner.close();
    }
}

