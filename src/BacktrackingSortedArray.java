public class BacktrackingSortedArray implements Array<Integer>, Backtrack {
    private Stack stack;
    private int[] arr;
    private int nonEmptyCellsNumber;

    // Do not change the constructor's signature
    public BacktrackingSortedArray(Stack stack, int size) {
        this.stack = stack;
        arr = new int[size];
        nonEmptyCellsNumber = 0;
    }
    @Override
    public Integer get(int index){
        return arr[index];
    }

    @Override
    public Integer search(int x) {
        int output = -1;   // default (not found) value
        boolean found = false;
        int low = 0, high = nonEmptyCellsNumber - 1;
        while (low <= high & !found){
            int middle = (low+high)/2;
            if(arr[middle] == x){
                output = middle;
                found = true;
            }
            else
            if (x < arr[middle])
                high = middle-1;
            else
                low = middle+1;
        }
        return output;
    }

    @Override
    public void insert(Integer x) {
        // TODO: implement your code here
    }


    @Override
    public void delete(Integer index) {
        // TODO: implement your code here
    }

    @Override
    public Integer minimum() {
        return arr[0];
    }

    @Override
    public Integer maximum() {
        return arr[nonEmptyCellsNumber - 1];
    }

    @Override
    public Integer successor(Integer index) {
        return arr[index + 1];
    }

    @Override
    public Integer predecessor(Integer index) {
        return arr[index - 1];
    }

    @Override
    public void backtrack() {
        // TODO: implement your code here
    }

    @Override
    public void retrack() {
        // Do not implement anything here!!
    }

    @Override
    public void print() {
        // TODO: implement your code here
    }
}
