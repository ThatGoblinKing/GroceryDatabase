import java.util.ArrayList;

public class SortAndSearch {
    public GenericItem[] search(ArrayList<GenericItem> inputted, String searchTerm, int varSelect) {
        ArrayList<GenericItem> searched = new ArrayList<GenericItem>();
        GenericItem[] returned;
        inputted = deNull(inputted);
        for (int i = 0; i < inputted.size(); i++) {
            GenericItem item = inputted.get(i);
            if (item != null) {
                item.prepareForSearch(varSelect);
                if (item.searchTerm != null && item.searchTerm.contains(searchTerm.toUpperCase())) {
                    searched.add(item);
                }
            }
        }
        returned = new GenericItem[searched.size()];
        for (int i = 0; i < searched.size(); i++) {
            returned[i] = searched.get(i);
        }
        return returned;
    }
    
    private ArrayList<GenericItem> deNull(ArrayList<GenericItem> inputted){
        ArrayList<GenericItem> output = new ArrayList<GenericItem>();
        for(GenericItem item : inputted) {
            if (item != null) output.add(item);
        }
        return output;
    }
    
    public GenericItem[] sort(GenericItem[] inputted, int order, int varSelect) {
        GenericItem[] sort = new GenericItem[inputted.length];

        for (int i = 0; i < inputted.length; i++) {
            {
            sort[i] = inputted[i];
            sort[i].prepareForSearch(varSelect);
            }
        }
        GenericItem tempValue = sort[0];
        if (varSelect == 4) {
            if (order == 1) {
                for (int i = 0; i < sort.length; i++) {
                    if (tempValue.searchYear < sort[i].searchYear && i > 0) {
                        sort[i - 1] = sort[i];
                        sort[i] = tempValue;
                        i = 0;
                    }
                    tempValue = sort[i];
                }
            } else {
                for (int i = 0; i < sort.length; i++) {
                    if (tempValue.searchYear > sort[i].searchYear && i > 0) {
                        sort[i - 1] = sort[i];
                        sort[i] = tempValue;
                        i = 0;
                    }
                    tempValue = sort[i];
                }
            }
        } else {
            if (order == 1) {
                for (int i = 0; i < sort.length; i++) {
                    if (tempValue.searchTerm.charAt(0) > sort[i].searchTerm.charAt(0) && i > 0) {
                        sort[i - 1] = sort[i];
                        sort[i] = tempValue;
                        i = 0;
                    }
                    tempValue = sort[i];
                }
            } else {
                for (int i = 0; i < sort.length; i++) {
                    if (tempValue.searchTerm.charAt(0) < sort[i].searchTerm.charAt(0) && i > 0) {
                        sort[i - 1] = sort[i];
                        sort[i] = tempValue;
                        i = 0;
                    }
                    tempValue = sort[i];
                }
            }
        }
        return sort;
    }

    public GenericItem[] sort(ArrayList<GenericItem> inputted, int order, int varSelect) {
        inputted = deNull(inputted);
        GenericItem[] sort = new GenericItem[inputted.size()];
        for (int i = 0; i < inputted.size(); i++) {
            sort[i] = inputted.get(i);
            sort[i].prepareForSearch(varSelect);
        }
        GenericItem tempValue = sort[0];
        if (varSelect == 4) {
            if (order == 1) {
                for (int i = 0; i < sort.length; i++) {
                    if (tempValue.searchYear < sort[i].searchYear && i > 0) {
                        sort[i - 1] = sort[i];
                        sort[i] = tempValue;
                        i = 0;
                    }
                    tempValue = sort[i];
                }
            } else {
                for (int i = 0; i < sort.length; i++) {
                    if (tempValue.searchYear > sort[i].searchYear && i > 0) {
                        sort[i - 1] = sort[i];
                        sort[i] = tempValue;
                        i = 0;
                    }
                    tempValue = sort[i];
                }
            }
        } else {
            if (order == 1) {
                for (int i = 0; i < sort.length; i++) {
                    if (tempValue != null && tempValue.searchTerm.charAt(0) > sort[i].searchTerm.charAt(0) && i > 0) {
                        sort[i - 1] = sort[i];
                        sort[i] = tempValue;
                        i = 0;
                    }
                    if (tempValue != null)
                    tempValue = sort[i];
                }
            } else {
                for (int i = 0; i < sort.length; i++) {
                    if (tempValue.searchTerm.charAt(0) < sort[i].searchTerm.charAt(0) && i > 0) {
                        sort[i - 1] = sort[i];
                        sort[i] = tempValue;
                        i = 0;
                    }
                    tempValue = sort[i];
                }
            }
        }
        return sort;
    }
}
