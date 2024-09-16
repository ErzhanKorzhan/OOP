package org.example;

import java.util.Scanner;

public class HeapSort
{
    public void read_mas_elems(int[] arr, int len)
    {
        Scanner scan = new Scanner(System.in);
        for(int i = 0; i < len; ++i)
        {
            arr[i] = scan.nextInt();
        }
    }

    public void out_mas_elems(int[] arr, int len)
    {
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

    public int[] sort(int[] mas, int len)
    {
        int[] arr = mas.clone();
        building_heap(arr,len);

        for (int i = len-1; i >= 0; --i)
        {
            swap(arr, i, 0);
            heapify(arr, i, 0);
        }
        return arr;
    }

    private void building_heap(int[] arr, int len)
    {
        for (int i = len / 2 - 1; i >= 0; --i)
        {
            heapify(arr, len, i);
        }
    }

    private void swap(int[] arr, int i, int j)
    {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    private void heapify(int[] arr, int n, int i)
    {
        int largest = i; // Init large element
        int left = 2*i + 1; // left = 2*i + 1
        int righ = 2*i + 2; // right = 2*i + 2

        if (left < n && arr[left] > arr[largest])
        {
            largest = left;
        }

        if (righ < n && arr[righ] > arr[largest])
        {
            largest = righ;
        }

        if (largest != i)
        {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }
}