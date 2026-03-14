public class QuickSort extends SortAbstract {
    @Override
    public void sort(int[] array) {
        startSort();
        quickSort(array, 0, array.length - 1);
        endSort();
    }

    private void quickSort(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivotIndex = partition(array, low, high);
        quickSort(array, low, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, high);
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            incrementCount();
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
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
