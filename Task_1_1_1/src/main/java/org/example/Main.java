package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        HeapSort masFunc = new HeapSort();
        Scanner scan = new Scanner(System.in);

        //Array Init
        int len = scan.nextInt();
        int[] arr = new int[len];
        masFunc.read_mas_elems(arr, len);

        int[] out_mas = masFunc.sort(arr, len);

        masFunc.out_mas_elems(out_mas, len);
    }
}
