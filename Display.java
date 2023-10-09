import java.util.ArrayList;

final public class Display {
    private static final int HIGH_TO_LOW = 1, LOW_TO_HIGH = 2;

    public static GenericItem[] sortAndSearch(ArrayList<GenericItem> inventory, String searchedTerm,
            int searchedVariable, boolean displayAll, int searchOrder, int sortedVariable) {
        GenericItem[] inventoryOutput = prepare(inventory, searchedVariable, sortedVariable);
        prepare(inventory, searchedVariable, sortedVariable);
        sort(inventoryOutput, 0, inventoryOutput.length-1, searchOrder);

        if (!displayAll) {
           inventoryOutput = search(inventoryOutput, searchedTerm);
        }
        return inventoryOutput;
    }

    public static GenericItem[] search(GenericItem[] inventory, String searchedTerm) {
        ArrayList<GenericItem> temporaryInventory = new ArrayList<GenericItem>();
        GenericItem[] returned = new GenericItem[inventory.length];
        for (GenericItem item : inventory){
            if (item.searchVariable.contains(searchedTerm.toUpperCase())) {
                temporaryInventory.add(item);
            }
            }
            GenericItem[] prepared = new GenericItem[temporaryInventory.size()];
        for (int currentIndex = 0; currentIndex < temporaryInventory.size(); currentIndex++) {
            prepared[currentIndex] = temporaryInventory.get(currentIndex);
        }
        returned = new GenericItem[temporaryInventory.size()];
        for (int currentIndex = 0; currentIndex < temporaryInventory.size(); currentIndex++) {
            returned[currentIndex] = temporaryInventory.get(currentIndex);
        }

        return returned;
    }

       private static GenericItem[] prepare(ArrayList<GenericItem> inventory, int searchedVariable, int sortedVariable) {
        ArrayList<GenericItem> temporaryInventory = new ArrayList<GenericItem>();
        for (GenericItem item : inventory) {
            if (item != null) {
                temporaryInventory.add(item);
                item.prepareForSearch(searchedVariable);
                item.prepareForSort(sortedVariable);
            }
        }
        GenericItem[] prepared = new GenericItem[temporaryInventory.size()];
        for (int currentIndex = 0; currentIndex < temporaryInventory.size(); currentIndex++) {
            prepared[currentIndex] = temporaryInventory.get(currentIndex);
        }
        return prepared;
    }

    private static void merge(GenericItem toSort[], int leftMost, int middle, int rightMost, int sortOrder) {
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
            if (sortOrder == HIGH_TO_LOW) {
                if (L[leftIndex].sortVariable.compareTo(R[rightIndex].sortVariable) <= 0) {
                    toSort[mergedIndex] = L[leftIndex];
                    leftIndex++;
                } else {
                    toSort[mergedIndex] = R[rightIndex];
                    rightIndex++;
                }
            } else if (sortOrder == LOW_TO_HIGH) {
                if (L[leftIndex].sortVariable.compareTo(R[rightIndex].sortVariable) >= 0) {
                    toSort[mergedIndex] = L[leftIndex];
                    leftIndex++;
                } else {
                    toSort[mergedIndex] = R[rightIndex];
                    rightIndex++;
                }
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
    private static void sort(GenericItem toSort[], int leftMost, int rightMost, int sortOrder) {
        if (leftMost < rightMost) {

            // Find the middle point
            int middle = leftMost + (rightMost - leftMost) / 2;

            // Sort first and second halves
            sort(toSort, leftMost, middle, sortOrder);
            sort(toSort, middle + 1, rightMost, sortOrder);

            // Merge the sorted halves
            merge(toSort, leftMost, middle, rightMost, sortOrder);
        }
    }
}
