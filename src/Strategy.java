public abstract class Strategy {

    private boolean hasFailed = false;

    double run(int[] array) {
        long startTime = System.currentTimeMillis();
        int[] a = sort(array);
        long stopTime = System.currentTimeMillis();
        checkArray(a);
        return (stopTime - startTime)/1000.0;
    }
    abstract int[] sort(int[] array);
    abstract String getName();

    public void resetHasFailed() {
        hasFailed = false;
    }

    public boolean hasFailed() {
        return hasFailed;
    }

    private void checkArray(int[] a) {
        int last = -1;
        for (int num : a) {
            if (num < last) {
                hasFailed = true;
                break;
            }
            last = num;
        }
    }
}
