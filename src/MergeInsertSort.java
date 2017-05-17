public class MergeInsertSort extends Strategy {

    private int k;

    public MergeInsertSort(int subListSizeLimit) {
        this.k = subListSizeLimit;
    }

    @Override
    public int[] sort(int[] array) {
        return mergeInsertionSort(array, k);
    }

    @Override
    public String getName() {
        return "Merge-Insert Sort";
    }

    private int[] mergeInsertionSort(int[] a, int k) {
        if (a.length <= k) {
            return insertionSort(a);
        } else {
            int leftSize = a.length/2;
            int rightSize = a.length - leftSize;
            int[] left = new int[leftSize];
            int[] right = new int[rightSize];

            int leftIndex = 0;
            int rightIndex = 0;
            for (int i = 0; i < a.length; i++) {
                if (i < leftSize) {
                    left[leftIndex] = a[i];
                    leftIndex++;
                } else {
                    right[rightIndex] = a[i];
                    rightIndex++;
                }
            }

            left = mergeInsertionSort(left, k);
            right = mergeInsertionSort(right, k);

            return merge(left, right);
        }
    }

    private int[] insertionSort(int[] a) {
        int j, temp;
        for (int i = 1; i < a.length; i++) {
            j = i;
            while (j > 0 && a[j-1] > a[j]) {
                temp = a[j];
                a[j] = a[j-1];
                a[j-1] = temp;
                j = j-1;
            }
        }
        return a;
    }

    private int[] merge(int[] left, int[] right) {
        int size = left.length + right.length;
        int[] a = new int[size];
        int leftIndex = 0;
        int rightIndex = 0;
        for (int i = 0; i < size; i++) {
            if ((leftIndex < left.length && rightIndex < right.length && left[leftIndex] < right[rightIndex]) || rightIndex == right.length) {
                a[i] = left[leftIndex];
                leftIndex++;
            } else {
                a[i] = right[rightIndex];
                rightIndex++;
            }
        }
        return a;
    }
}
