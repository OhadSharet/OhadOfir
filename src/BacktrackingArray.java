public class BacktrackingArray implements Array<Integer>, Backtrack {
    private Stack stack;
    private int[] arr;
    private int nonEmptyCellsNumber = 0;

    // Do not change the constructor's signature
    public BacktrackingArray(Stack stack, int size) {
        this.stack = stack;
        arr = new int[size];
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

        if (nonEmptyCellsNumber == arr.length)
            throw new IndexOutOfBoundsException("The array is full");

        arr[nonEmptyCellsNumber] = x;
        nonEmptyCellsNumber++;

        //value 0 insert
        int[] insertData = new int [2];
        insertData[0] = x;
        insertData[1] = 0;
        stack.push(insertData);
    }

    @Override
    public void delete(Integer index) {

        if (index < nonEmptyCellsNumber) {

            arr[index] = arr[nonEmptyCellsNumber - 1];
            nonEmptyCellsNumber--;

            //value 1 delete
            int[] deleteData = new int [2];
            deleteData[0] = arr[index];
            deleteData[1] = 1;
            stack.push(deleteData);
        }
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
        int[] backtrackData = (int[]) stack.pop();

        //value 0 insert (thus the value need to be deleted)
        //value 1 delete (thus the value need to be inserted)
        if (backtrackData[1] == 0) {
            boolean toStop = false;
            for (int i = 0; i < nonEmptyCellsNumber & !toStop; i++)
                if (backtrackData[0] == arr[i]) {
                    delete(i);
                    toStop = true;
                }
        }
        else {
            insert(backtrackData[0]);
        }
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
        System.out.println(toPrint.substring(0, toPrint.length() - 1));
    }
}