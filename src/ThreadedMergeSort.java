public class ThreadedMergeSort extends Strategy {

    private int threads;

    public ThreadedMergeSort(int threads) {
        this.threads = threads;
    }

    @Override
    int[] sort(int[] array) {
        return mergeSort(array, threads);
    }

    @Override
    public String getName() {
        return "Threaded Merge Sort";
    }

    private int[] mergeSort(int[] a, int numThreads) {
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

            if (numThreads > 0) {
                Thread t1 = getMergeSortThread(left, numThreads);
                Thread t2 = getMergeSortThread(right, numThreads);

                t1.start();
                t2.start();

                try {
                    t1.join();
                    t2.join();
                } catch (InterruptedException ie) {
                    System.err.println(ie.getMessage());
                }
            } else {
                left = mergeSort(left, 0);
                right = mergeSort(right, 0);
            }

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

    private Thread getMergeSortThread(int[] a, int numThreads) {
        return new Thread() {
            @Override
            public void run() {
                mergeSort(a, numThreads/2);
            }
        };
    }
}
