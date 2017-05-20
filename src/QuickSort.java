public class QuickSort extends Strategy {

    @Override
    public int[] sort(int[] array) {
        int[] a = quicksort(array, 0, array.length-1);
        return array;
    }

    @Override
    public String getName() {
        return "Quick Sort";
    }

    private int[] quicksort(int[] a, int low, int high) {
        if (low < high) {
            int pivotLocation = partition(a, low, high);
            quicksort(a, low, pivotLocation);
            quicksort(a, pivotLocation+1, high);
        }
        return a;
    }

    private int partition(int[] a, int low, int high) {
        int pivot = a[(low+high)/2];
        int i = low-1;
        int j = high+1;
        while (true) {
            do {
                i++;
            } while (a[i] < pivot);
            do {
                j--;
            } while (a[j] > pivot);

            if (i >= j) {
                return j;
            }
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
}
