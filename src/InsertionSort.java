public class InsertionSort extends Strategy {

    @Override
    public int[] sort(int[] array) {
        int j;
        int temp;
        for (int i = 1; i < array.length; i++) {
            j = i;
            while (j > 0 && array[j-1] > array[j]) {
                temp = array[j];
                array[j] = array[j-1];
                array[j-1] = temp;
                j = j-1;
            }
        }
        return array;
    }

    @Override
    public String getName() {
        return "Insertion Sort";
    }
}
