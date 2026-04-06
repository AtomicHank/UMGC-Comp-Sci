import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CMSCProject {
    private static final int RUNS_PER_SIZE = 40;
    private static final int SIZE_COUNT = 12;
    private static final int SIZE_STEP = 100;
    private static final int WARMUP_RUNS = 200;
    private static final long BASE_SEED = 4512026L;

    public static void main(String[] args) throws IOException {
        SortAbstract quickSort = new QuickSort();
        SortAbstract mergeSort = new MergeSort();

        warmUp(quickSort, mergeSort);

        try (BufferedWriter quickWriter = new BufferedWriter(new FileWriter("QuickSort.txt"));
             BufferedWriter mergeWriter = new BufferedWriter(new FileWriter("MergeSort.txt"))) {
            for (int sizeIndex = 1; sizeIndex <= SIZE_COUNT; sizeIndex++) {
                int size = sizeIndex * SIZE_STEP;
                quickWriter.write(Integer.toString(size));
                mergeWriter.write(Integer.toString(size));

                for (int run = 0; run < RUNS_PER_SIZE; run++) {
                    int[] input = generateData(size, BASE_SEED + (long) size * 1000 + run);
                    recordRun(quickSort, input, quickWriter);
                    recordRun(mergeSort, input, mergeWriter);
                }

                quickWriter.newLine();
                mergeWriter.newLine();
            }
        }
    }

    private static void warmUp(SortAbstract quickSort, SortAbstract mergeSort) {
        for (int run = 0; run < WARMUP_RUNS; run++) {
            int size = 32 + (run % 64);
            int[] input = generateData(size, BASE_SEED + run);
            quickSort.sort(input.clone());
            mergeSort.sort(input.clone());
        }
    }

    private static int[] generateData(int size, long seed) {
        Random random = new Random(seed);
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt(size * 20 + 1);
        }
        return data;
    }

    private static void recordRun(SortAbstract sort, int[] input, BufferedWriter writer) throws IOException {
        int[] copy = input.clone();
        sort.sort(copy);
        verifySorted(copy);
        writer.write(" ");
        writer.write(Long.toString(sort.getCount()));
        writer.write(" ");
        writer.write(Long.toString(sort.getTime()));
    }

    private static void verifySorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {
                throw new UnsortedException("Array is not sorted at index " + i);
            }
        }
    }
}
