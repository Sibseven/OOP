import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * class of heapsort, containing functions sort, ShiftDown.
 *
 * @author vadim lavrenenkov
 *
 * @version 1.0
 */
public class Heapsort {
    /**
    * reading array from console.
     *
    * @param args -  default param for main
    */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        while (in.hasNextInt()) {
            list.add(in.nextInt());
        }
        int[] arr = list.stream().mapToInt(i -> i).toArray();
        Heapsort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    /**
    * shifting smallest element until he`s max.
    * in his subtree
     *
     * @param arr - binary heap in array form
     * @param n - number of elements in arr
     * @param now - index of element to shift
     */
    public static void shiftdown(int[] arr, int n, int now) {
        int largest = now;
        int left = 2 * now + 1;
        int right = 2 * now + 2;
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != now) {
            int temp = arr[now];
            arr[now] = arr[largest];
            arr[largest] = temp;
            shiftdown(arr, n, largest);
        }


    }
    /**
    * making a binary max heap.
    * then taking root from heap
    * and re-heap remaining tree
     * until all elements in their places
     * 
    * @param arr - array to be sorted
     */
    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            shiftdown(arr, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            shiftdown(arr, i, 0);
        }
    }
}
