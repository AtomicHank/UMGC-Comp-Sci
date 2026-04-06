public class SelectionSort extends SortAbstract {
    @Override
    public void sort(int[] array) {
        startSort();
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                // Critical operation: comparison between two data values.
                incrementCount();
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
        endSort();
    }

    private void swap(int[] array, int left, int right) {
        if (left == right) {
            return;
        }
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
