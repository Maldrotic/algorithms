import java.util.Random;

public class Lab1 {

    private static final long RANDOM_SEED = 1;
    private static final int SIZE = 10;
    private static final int MAX_NUM = SIZE;
    private static final int ITERATIONS = 1;
    private static final int K = 75;
    private static final boolean PRINT_ALL_TIMES = false;

    private static Random random;

    private static int[] controlArray;
    private static int[] copyArray;

    public static void main(String[] args) {
        random = new Random(RANDOM_SEED);

        controlArray = new int[SIZE];
        copyArray = new int[SIZE];

        Context insertionSortContext = new Context(new InsertionSort());
        Context mergeSortContext = new Context(new MergeSort());
        Context threadedMergeSortContext = new Context(new ThreadedMergeSort(4));
        Context mergeInsertionSortContext = new Context(new MergeInsertSort(K));
        Context quickSortContext = new Context(new QuickSort());
        Context threadedQuickSortContext = new Context(new ThreadedQuickSort());

        // Lab 1
//        Context[] contexts = new Context[] {insertionSortContext, mergeSortContext, mergeInsertionSortContext};
        // Lab 4
        Context[] contexts = new Context[] {mergeSortContext, threadedMergeSortContext, quickSortContext, threadedQuickSortContext};
        runTestResults(contexts);
    }

    private static void runTestResults(Context[] contexts) {
        System.out.println("\n--- Likely Case ---");
        fillControlArrayWithLikelyCase();
        for (Context context : contexts) {
            executeAlgorithm(context);
            printTimes(context, PRINT_ALL_TIMES);
        }

        // Reset for best case
        System.out.println("\n--- Best Case ---");
        fillControlArrayWithBestCase();
        for (Context context : contexts) {
            context.clearTimes();
            executeAlgorithm(context);
            printTimes(context, PRINT_ALL_TIMES);
        }

        // Reset for worst case
        System.out.println("\n--- Worst Case ---");
        fillControlArrayWithWorstCase();
        for (Context context : contexts) {
            context.clearTimes();
            executeAlgorithm(context);
            printTimes(context, PRINT_ALL_TIMES);
        }
    }

    private static void executeAlgorithm(Context context) {
        for (int i = 0; i < ITERATIONS; i++) {
            resetCopyArray();
            context.executeAlgorithm(copyArray);
        }
    }

    private static void printTimes(Context context, boolean printAll) {
        System.out.printf("%s times:\n", context.toString());
        if (context.hasFailedSort()) System.out.println("  *Array was not sorted correctly*");
        if (printAll) {
            for (double time : context.getRunTimes()) {
                System.out.printf("        %.4f s\n", time);
            }
        }
        System.out.printf("  avg : %.4f s\n", context.getAverageRunTime());
    }

    private static void fillControlArrayWithLikelyCase() {
        for (int i = 0; i < SIZE; i++) {
            controlArray[i] = random.nextInt(MAX_NUM);
        }
    }

    private static void fillControlArrayWithBestCase() {
        for (int i = 0; i < SIZE; i++) {
            controlArray[i] = i;
        }
    }

    private static void fillControlArrayWithWorstCase() {
        for (int i = 0; i < SIZE; i++) {
            controlArray[i] = SIZE - i;
        }
    }

    private static void resetCopyArray() {
        System.arraycopy(controlArray, 0, copyArray, 0, SIZE);
    }

}
