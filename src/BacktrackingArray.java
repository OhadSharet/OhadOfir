public class BacktrackingArray implements Array<Integer>, Backtrack {
    private Stack stack;
    private int[] arr;
    private int nonEmptyCellsNumber;

    // Do not change the constructor's signature
    public BacktrackingArray(Stack stack, int size) {
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
        for (int i = 0; i < nonEmptyCellsNumber; i++)
            if (arr[i] == x)
                return i;
        return -1;
    }

    @Override
    public void insert(Integer x) {
        arr[nonEmptyCellsNumber] = x;
        nonEmptyCellsNumber++;
    }

    @Override
    public void delete(Integer index) {
        // TODO: implement your code here
    }

    @Override
    public Integer minimum() {
        int minValue = arr[0];
        for (int i = 1; i < nonEmptyCellsNumber; i++)
            if (minValue > arr[i])
                minValue = arr[i];
        return minValue;
    }

    @Override
    public Integer maximum() {
        int maxValue = arr[0];
        for (int i = 1; i < nonEmptyCellsNumber; i++)
            if (maxValue < arr[i])
                maxValue = arr[i];
        return maxValue;
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
        String toPrint = "";
        for (int i = 0; i < nonEmptyCellsNumber; i++)
            toPrint = toPrint + arr[i] + " ";
        System.out.println(toPrint.substring(0, toPrint.length() - 2));
    }
}
