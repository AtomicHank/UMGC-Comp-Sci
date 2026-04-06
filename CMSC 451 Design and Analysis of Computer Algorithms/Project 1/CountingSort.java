public class CountingSort extends SortAbstract {
    @Override
    public void sort(int[] array) {
        startSort();
        if (array.length < 2) {
            endSort();
            return;
        }

        int min = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
            if (array[i] > max) {
                max = array[i];
            }
        }

        int range = max - min + 1;
        int[] counts = new int[range];
        int[] output = new int[array.length];

        for (int value : array) {
            // Critical operation: update of frequency for each value.
            incrementCount();
            counts[value - min]++;
        }

        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            int value = array[i];
            int countIndex = value - min;
            int outIndex = --counts[countIndex];
            // Critical operation: placing one value into sorted output.
            incrementCount();
            output[outIndex] = value;
        }

        System.arraycopy(output, 0, array, 0, array.length);
        endSort();
    }
}
