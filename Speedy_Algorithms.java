import java.util.ArrayList;
import java.util.Arrays;
public class Speedy_Algorithms
{
    public static void main(String[]args)
    {
        int size = 10;
        do
        {
            int arr[] = new int[size];
            for(int i=0; i<arr.length; i++)
                arr[i]=(int)(Math.random()*10000000+1);
            //Sorting
            System.out.println("SIZE:"+size+"____________________________________________\n");
            selection(arr);
            radix(arr);
            merge(arr);
            System.out.println("\n");

            //Print sorted array
            size=size*10;
        }while(size<=100000000);
    }//end main

    static public void print(int arr[])
    {
        for (int i=0; i<arr.length; ++i)
            System.out.print(arr[i]+" ");
    }
    static public void selection(int arr[])
    {
        //*************************************************************
        long startTime = System.nanoTime();
        for(int i=0; i<(arr.length-1); i++)
        {
            //Lowest value goes in the beginning
            int index = i;
            for(int j=(i+1); j<arr.length; j++)
                if(arr[j] < arr[index])
                    index = j;
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
        long endTime = System.nanoTime();
        print(arr);
        System.out.println("\nSelection Sort:\t\t"+((endTime-startTime)*0.000001)+" milliseconds");
        //*************************************************************
    }

    static public void radix(int arr[])
    {
        long startTime = System.nanoTime();
        final int RADIX = 10;
        ArrayList<Integer> bucketsArray[] = new ArrayList[RADIX];

        for (int count = 0; count < bucketsArray.length; count++)
            bucketsArray[count] = new ArrayList<>();

        boolean maxDigitsLengthReached = false;
        int temp = -1, placeValue = 1;

        while (!maxDigitsLengthReached)
        {
            maxDigitsLengthReached = true;
            for (Integer element : arr)
            {
                temp = element / placeValue;
                bucketsArray[temp % RADIX].add(element);
                if (maxDigitsLengthReached && temp > 0)
                    maxDigitsLengthReached = false;
            }
            int a = 0;
            for (int b = 0; b < RADIX; b++)
            {
                for (Integer i : bucketsArray[b])
                    arr[a++] = i;
                bucketsArray[b].clear();
            }
            placeValue = placeValue * RADIX;
        }
        long endTime = System.nanoTime();
        //print(arr[]);
        System.out.println("\nMerge Sort\t\t"+((endTime-startTime)*0.000001)+" milliseconds");

    }//end radix

    static void merge(int arr[])
    {
        int l = 0;
        int r = (arr.length-1);

        long startTime = System.nanoTime();
        recursiveSort(l, r, arr);
        long endTime = System.nanoTime();

        //print(arr[]);
        System.out.println("\nRadix Sort\t\t"+((endTime-startTime)*0.000001)+" milliseconds");
    }

    static void recursiveSort(int l, int r, int arr[])
    {
        if(l < r)
        {
            int m = (l+r)/2;
            recursiveSort(l, m, arr);
            recursiveSort(m+1, r, arr);
            merge(l, m, r, arr);
        }
    }//end method

    static void merge(int l, int m, int r, int[] arr)
    {
        int x = m-l+1;
        int y = r-m;
        int []larr = new int[x];
        int []rarr = new int[y];

        for(int i=0; i<x; i++)
            larr[i] = arr[l+i];

        for(int j=0; j<y; j++)
            rarr[j] = arr[m+1+j];

        int i=0;
        int j=0;
        int k=l;
        while(i<x && j<y)
        {
            if(larr[i]<=rarr[j])
            {
                arr[k] = larr[i];
                i++;
            }
            else
            {
                arr[k] = rarr[j];
                j++;
            }
            k++;
        }//end while

        while(i<x)
        {
            arr[k] = larr[i];
            i++;
            k++;
        }//end while

        while(j<y)
        {
            arr[k]=rarr[j];
            j++;
            k++;
        }
    }//end method
}//end class
