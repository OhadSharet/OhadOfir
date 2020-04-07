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

        //value 0 insert
        int[] insertData = new int [3];
        insertData[0] = x;
        insertData[1] = nonEmptyCellsNumber;
        insertData[2] = 0;
        stack.push(insertData);
    }

    @Override
    public void delete(Integer index) {

        if (index < nonEmptyCellsNumber) {

            for (int i = index; i < nonEmptyCellsNumber - 1; i++)
                arr[i] = successor(i);
            nonEmptyCellsNumber--;

            //value 1 delete
            int[] deleteData = new int [3];
            deleteData[0] = arr[index];
            deleteData[1] = index;
            deleteData[2] = 1;
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

        //value 0 insert
        //value 1 delete
        if (backtrackData[2] == 0) {
            int index = backtrackData[1];
            delete(index);
        }
        else {
            int index = backtrackData[1];
            for (int i = nonEmptyCellsNumber; i > index; i++)
                arr[i] = predecessor(i);
            arr[index] = backtrackData[0];
            nonEmptyCellsNumber++;
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