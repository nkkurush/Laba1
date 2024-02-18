import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.min;

public class Main {
    static int m;
    static int n;
    static int minLimit;
    static int maxLimit;

    public static void main(String[] args) {
        //Задача 1
        print("Hello world!");
        //Задача 2
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        minLimit = sc.nextInt();
        maxLimit = sc.nextInt();
        //selectionSort(genMatrix(m,n,minLimit,maxLimit));
        //insertionSort(genMatrix(m,n,minLimit,maxLimit));
        //bubbleSort(genMatrix(m,n,minLimit,maxLimit));
        //shellSort(genMatrix(m, n, minLimit, maxLimit));
        //quickSortMatrix(genMatrix(m, n, minLimit, maxLimit));
        tournamentSortMatrix(genMatrix(m, n, minLimit, maxLimit));
    }

    public static void print(String s) {
        System.out.println(s);
    }

    //Задача 2
    /*Написать генератор случайных матриц(многомерных),
    который принимает опциональные параметры m, n, min_limit, max_limit,
    где m и n указывают размер матрицы, а min_lim и max_lim - минимальное и максимальное значение
    для генерируемого числа.*/
    public static int[][] genMatrix(int m, int n, int min_limit, int max_limit) {
        Random random = new Random();
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(min_limit, max_limit);
            }
        }
        return matrix;
        /*for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }*/
    }
    /*Реализовать методы сортировки строк числовой матрицы в соответствии с заданием.
    Оценить время работы каждого алгоритма сортировки и сравнить его со временем стандартной функции сортировки.
    Испытания проводить на сгенерированных матрицах.*/

    // Сортировка выбором
    public static void selectionSort(int[][] arr) {
        for (int i1 = 0; i1 < m; i1++) {
            for (int i = 0; i < n - 1; i++) {
                int minIndex = i;
                int minIndex2 = i1;
                for (int j = i + 1; j < n; j++) {
                    if (arr[i1][j] < arr[i1][minIndex]) {
                        minIndex = j;
                        minIndex2 = i1;
                    }
                }
                int temp = arr[minIndex2][minIndex];
                arr[minIndex2][minIndex] = arr[minIndex2][i];
                arr[minIndex2][i] = temp;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        //Время выполнения возросло до o(n*n*n) ,было о(n*n)
    }

    //Сортировка вставкой
    public static void insertionSort(int[][] arr) {
        for (int i1 = 0; i1 < m; i1++) {
            for (int i = 1; i < n; i++) {
                for (int j = i; j > 0 && arr[i1][j] < arr[i1][j - 1]; j--) {
                    int t = arr[i1][j - 1];
                    arr[i1][j - 1] = arr[i1][j];
                    arr[i1][j] = t;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Сортировка пузырком
    public static void bubbleSort(int[][] arr) {
        for (int i1 = 0; i1 < m; i1++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (arr[i1][j] > arr[i1][j + 1]) {
                        int t = arr[i1][j + 1];
                        arr[i1][j + 1] = arr[i1][j];
                        arr[i1][j] = t;
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Сортировка Шелла
    public static void shellSort(int[][] arr) {
        for (int i1 = 0; i1 < m; i1++) {
            for (int s = n / 2; s > 0; s /= 2) {
                for (int i = s; i < n; ++i) {
                    for (int j = i - s; j >= 0 && arr[i1][j] > arr[i1][j + s]; j -= s) {
                        int t = arr[i1][j];
                        arr[i1][j] = arr[i1][j + s];
                        arr[i1][j + s] = t;
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        //время сортировки было о(nlogn) стало o(n*nlogn)
    }

    //Быстрая сортировка
    public static void quickSortMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            quickSort(matrix[i], 0, n - 1);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        //Время работы о(n*nlogn)
    }

    public static void quickSort(int arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }
        int swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }

    //Турнирная сортировка
    public static void tournamentSortMatrix(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            sortRow(arr[i]);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void sortRow(int[] arr) {
        int n = arr.length;
        int[] temp = new int[n];
        System.arraycopy(arr, 0, temp, 0, n);
        int[] indexArray = new int[n];
        for (int i = 0; i < n; i++) {
            indexArray[i] = i;
        }
        sortUtil(arr, 0, n - 1, temp, indexArray);
    }

    public static void sortUtil(int[] arr, int low, int high, int[] temp, int[] indexArray) {
        if (low < high) {
            int mid = (low + high) / 2;
            sortUtil(arr, low, mid, temp, indexArray);
            sortUtil(arr, mid + 1, high, temp, indexArray);
            merge(arr, low, mid, high, temp, indexArray);
        }
    }

    public static void merge(int[] arr, int low, int mid, int high, int[] temp, int[] indexArray) {
        int i = low;
        int j = mid + 1;
        int k = low;
        while (i <= mid && j <= high) {
            if (arr[i] <= arr[j]) {
                temp[k] = arr[i];
                indexArray[k] = i;
                i++;
            } else {
                temp[k] = arr[j];
                indexArray[k] = j;
                j++;
            }
            k++;
        }
        while (i <= mid) {
            temp[k] = arr[i];
            indexArray[k] = i;
            i++;
            k++;
        }
        for (k = low; k <= high; k++) {
            arr[k] = temp[k];
        }
    }
}