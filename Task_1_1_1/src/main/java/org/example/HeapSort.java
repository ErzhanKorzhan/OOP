package org.example;

import java.util.Scanner;

public class HeapSort
{
    // Sorting the original array of length len by the Pyramid Sorting Method
    public void sort(int[] arr, int len)
    {
        // Building a heap (in our case, rearranging the array)
        for (int i = len / 2 - 1; i >= 0; --i)
        {
            heapify(arr, len, i);
        }

        // One by one extract the elements from our heap
        for (int i = len-1; i >= 0; --i)
        {
            // Moving the root of tree to the end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Calling the heapify procedure
            heapify(arr, i, 0);
        }
    }

    // The procedure for sifting our array (convert the subtree into a binary heap)
    private void heapify(int[] arr, int n, int i)
    {
        int largest = i; // Init large element
        int left = 2*i + 1; // left = 2*i + 1
        int righ = 2*i + 2; // right = 2*i + 2

        // If the left child element is larger than the root
        if (left < n && arr[left] > arr[largest])
        {
            largest = left;
        }

        // If the right child element is larger than the largest element
        if (righ < n && arr[righ] > arr[largest])
        {
            largest = righ;
        }

        // If the largest element is not the root
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively convert the affected subtree into a heap
            heapify(arr, n, largest);
        }
    }

    // Reading and returning an array after sorting
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        // Reading value of len
        int len = scan.nextInt();
        int[] arr = new int[len];
        // Reading values of a massive
        for(int i = 0; i < len; ++i)
        {
            arr[i] = scan.nextInt();
        }

        // The beginning of sorting
        HeapSort ob = new HeapSort();
        ob.sort(arr, len);

        // Output of the sorted array
        System.out.println("\nSorted array is");
        System.out.print("[");
        for (int i = 0; i < len; ++i)
        {
            if (i != len-1)
            {
                System.out.print(arr[i]+", ");
            }
            else
            {
                System.out.print(arr[i]);
            }
        }
        System.out.println("]");
    }
}