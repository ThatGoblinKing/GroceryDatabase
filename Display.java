import java.util.ArrayList;

final public class Display {
    private static final int HIGH_TO_LOW = 1, LOW_TO_HIGH = 2;

    public static GenericItem[] sortAndSearch(ArrayList<GenericItem> inventory, String searchedTerm,
            int searchedVariable, boolean displayAll, int searchOrder, int sortedVariable) {
        GenericItem[] inventoryOutput = prepare(inventory, searchedVariable, sortedVariable);
        prepare(inventory, searchedVariable, sortedVariable);
        sort(inventoryOutput, 0, inventoryOutput.length - 1, searchOrder);

        if (!displayAll) {
            inventoryOutput = search(inventoryOutput, searchedTerm);
        }
        return inventoryOutput;
    }

    private static GenericItem[] search(GenericItem[] inventory, String searchedTerm) {
        ArrayList<GenericItem> temporaryInventory = new ArrayList<GenericItem>();
        for (GenericItem item : inventory) {
            if (item.searchVariable.contains(searchedTerm.toUpperCase())
                    || item.searchVariable.equalsIgnoreCase(searchedTerm)) {
                temporaryInventory.add(item);
            }
        }
        GenericItem[] prepared = new GenericItem[temporaryInventory.size()];
        for (int currentIndex = 0; currentIndex < temporaryInventory.size(); currentIndex++) {
            prepared[currentIndex] = temporaryInventory.get(currentIndex);
        }
        GenericItem[] returned = new GenericItem[temporaryInventory.size()];
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

    private static void merge(GenericItem[] toSort, int leftMost, int middle, int rightMost, int sortOrder) {

        int subarrayOneSize = middle - leftMost + 1;
        int rightSubArraySize = rightMost - middle;

        GenericItem[] L = new GenericItem[subarrayOneSize];
        GenericItem[] R = new GenericItem[rightSubArraySize];

        for (int leftIndex = 0; leftIndex < subarrayOneSize; ++leftIndex)
            L[leftIndex] = toSort[leftMost + leftIndex];
        for (int rightIndex = 0; rightIndex < rightSubArraySize; ++rightIndex)
            R[rightIndex] = toSort[middle + 1 + rightIndex];

        int leftIndex = 0, rightIndex = 0;

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

        while (leftIndex < subarrayOneSize) {
            toSort[mergedIndex] = L[leftIndex];
            leftIndex++;
            mergedIndex++;
        }

        while (rightIndex < rightSubArraySize) {
            toSort[mergedIndex] = R[rightIndex];
            rightIndex++;
            mergedIndex++;
        }
    }

    private static void sort(GenericItem[] toSort, int leftMost, int rightMost, int sortOrder) {
        if (leftMost < rightMost) {

            int middle = leftMost + (rightMost - leftMost) / 2;

            sort(toSort, leftMost, middle, sortOrder);
            sort(toSort, middle + 1, rightMost, sortOrder);

            merge(toSort, leftMost, middle, rightMost, sortOrder);
        }
    }
}
