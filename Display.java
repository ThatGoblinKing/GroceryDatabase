import java.util.ArrayList;

public class Display {
    private final int HIGH_TO_LOW = 1, LOW_TO_HIGH = 2, NAME = 1, QUANTITY = 2, PRICE = 3;
    private static GenericItem[] InventoryOutput;

    public static GenericItem[] sortAndSearch(ArrayList<GenericItem> inventory, String searchedTerm,
            int searchedVariable, boolean displayAll, int searchOrder, int sortedVariable) {
        GenericItem[] inventoryOutput = prepare(inventory, searchedVariable);
        sort(inventoryOutput, 0, inventoryOutput.length-1);

        if (!displayAll) {
            for(GenericItem item : inventoryOutput)
        }
        return inventoryOutput;
    }

    public static GenericItem[] search(ArrayList<GenericItem> inventory, String searchedTerm, int searchedVariable) {
        ArrayList<GenericItem> searched = new ArrayList<GenericItem>();
        GenericItem[] returned;
        inventory = deNull(inventory);
        for (int leftIndex = 0; leftIndex < inventory.size(); leftIndex++) {
            GenericItem item = inventory.get(leftIndex);
            if (item != null) {
                item.prepareForSearch(searchedVariable);
                if (item.searchedTerm != null && item.searchedTerm.contains(searchedTerm.toUpperCase())) {
                    searched.add(item);
                }
            }
        }
        returned = new GenericItem[searched.size()];
        for (int leftIndex = 0; leftIndex < searched.size(); leftIndex++) {
            returned[leftIndex] = searched.get(leftIndex);
        }
        return returned;
    }

    private static GenericItem[] prepare(ArrayList<GenericItem> inventory, int searchedVariable) {
        ArrayList<GenericItem> temporaryInventory = new ArrayList<GenericItem>();
        for (GenericItem item : inventory) {
            if (item != null) {
                temporaryInventory.add(item);
                item.prepareForSearch(searchedVariable);
            }
        }
        GenericItem[] prepared = new GenericItem[temporaryInventory.size()];
        for (int currentIndex = 0; currentIndex < temporaryInventory.size(); currentIndex++) {
            prepared[currentIndex] = temporaryInventory.get(currentIndex);
        }
        return prepared;
    }

    private static void merge(GenericItem toSort[], int leftMost, int middle, int rightMost) {
        // Find sizes of two subarrays to be merged
        int subarrayOneSize = middle - leftMost + 1;
        int rightSubArraySize = rightMost - middle;

        // Create temp arrays
        GenericItem L[] = new GenericItem[subarrayOneSize];
        GenericItem R[] = new GenericItem[rightSubArraySize];

        // Copy data to temp arrays
        for (int leftIndex = 0; leftIndex < subarrayOneSize; ++leftIndex)
            L[leftIndex] = toSort[leftMost + leftIndex];
        for (int rightIndex = 0; rightIndex < rightSubArraySize; ++rightIndex)
            R[rightIndex] = toSort[middle + 1 + rightIndex];

        // Merge the temp arrays

        // Initial indices of first and second subarrays
        int leftIndex = 0, rightIndex = 0;

        // Initial index of merged subarray array
        int mergedIndex = leftMost;
        while (leftIndex < subarrayOneSize && rightIndex < rightSubArraySize) {
            if (L[leftIndex].sortVariable.compareTo(R[rightIndex].sortVariable) <= 0) {
                toSort[mergedIndex] = L[leftIndex];
                leftIndex++;
            } else {
                toSort[mergedIndex] = R[rightIndex];
                rightIndex++;
            }
            mergedIndex++;
        }

        // Copy remaining elements of L[] if any
        while (leftIndex < subarrayOneSize) {
            toSort[mergedIndex] = L[leftIndex];
            leftIndex++;
            mergedIndex++;
        }

        // Copy remaining elements of R[] if any
        while (rightIndex < rightSubArraySize) {
            toSort[mergedIndex] = R[rightIndex];
            rightIndex++;
            mergedIndex++;
        }
    }

    // Main function that sorts toSort[leftMost..rightMost] using
    // merge()
    private static void sort(GenericItem toSort[], int leftMost, int rightMost) {
        if (leftMost < rightMost) {

            // Find the middle point
            int middle = leftMost + (rightMost - leftMost) / 2;

            // Sort first and second halves
            sort(toSort, leftMost, middle);
            sort(toSort, middle + 1, rightMost);

            // Merge the sorted halves
            merge(toSort, leftMost, middle, rightMost);
        }
    }
}
