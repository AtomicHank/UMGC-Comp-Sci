public class MergeSort extends SortAbstract {
    @Override
    public void sort(int[] array) {
        startSort();
        int[] scratch = new int[array.length];
        mergeSort(array, scratch, 0, array.length - 1);
        endSort();
    }

    private void mergeSort(int[] array, int[] scratch, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(array, scratch, left, mid);
        mergeSort(array, scratch, mid + 1, right);
        merge(array, scratch, left, mid, right);
    }

    private void merge(int[] array, int[] scratch, int left, int mid, int right) {
        int leftIndex = left;
        int rightIndex = mid + 1;
        int scratchIndex = left;

        while (leftIndex <= mid && rightIndex <= right) {
            incrementCount();
            if (array[leftIndex] <= array[rightIndex]) {
                scratch[scratchIndex++] = array[leftIndex++];
            } else {
                scratch[scratchIndex++] = array[rightIndex++];
            }
        }

        while (leftIndex <= mid) {
            scratch[scratchIndex++] = array[leftIndex++];
        }

        while (rightIndex <= right) {
            scratch[scratchIndex++] = array[rightIndex++];
        }

        for (int i = left; i <= right; i++) {
            array[i] = scratch[i];
        }
    }
}
