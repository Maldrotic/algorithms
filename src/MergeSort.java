public class MergeSort extends Strategy {

    @Override
    public int[] sort(int[] array) {
        return mergeSort(array);
    }

    @Override
    public String getName() {
        return "Merge Sort";
    }

    private int[] mergeSort(int[] a) {
        if (a.length == 1) {
            return a;
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

            left = mergeSort(left);
            right = mergeSort(right);

            return merge(left, right);
        }
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
