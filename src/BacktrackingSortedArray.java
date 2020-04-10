public class BacktrackingSortedArray implements Array<Integer>, Backtrack {
    private Stack stack;
    private int[] arr;
    private int nonEmptyCellsNumber = 0;

    // Do not change the constructor's signature
    public BacktrackingSortedArray(Stack stack, int size) {
        this.stack = stack;
        arr = new int[size];
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

        if (nonEmptyCellsNumber == arr.length)
            throw new IndexOutOfBoundsException("The array is full");

        boolean toStop = false;
        int index = 0;

        for (int i = nonEmptyCellsNumber - 1; i >= 0 & !toStop; i--) {

            if (x > arr[i]) {
                arr[i + 1] = x;
                index = i + 1;
                toStop = true;
            }
            else
                arr[i + 1] = predecessor(i + 1);
        }

        if (!toStop) {
            arr[0] = x;
        }

        nonEmptyCellsNumber++;

        //value 0 insert
        int[] insertData = new int [2];
        insertData[0] = index;
        insertData[1] = 0;
        stack.push(insertData);


    }

    @Override
    public void delete(Integer index) {

        if (index < nonEmptyCellsNumber) {

            for (int i = index; i < nonEmptyCellsNumber - 1; i++)
                arr[i] = successor(i);
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
        if (nonEmptyCellsNumber == 0)
            return -1;
        return 0;
    }

    @Override
    public Integer maximum() {
        return nonEmptyCellsNumber - 1;
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

        if (!stack.isEmpty()) {
            int[] backtrackData = (int[]) stack.pop();

            //value 0 insert (thus the value need to be deleted)
            //value 1 delete (thus the value need to be inserted)
            if (backtrackData[1] == 0) {
                delete(backtrackData[0]);
                stack.pop();
            }
            else {
                insert(backtrackData[0]);
                stack.pop();
            }
            System.out.println("backtracking performed");
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

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Stack a = new Stack();
        int[] A={1, 9, 2, 14, 15,16, 23, 99, 100, 100, 100, 132, 193, 196, 197};
        BacktrackingSortedArray BSA = new BacktrackingSortedArray(a, 15);
        for (int i=0; i < A.length; i++) {
            BSA.insert(A[i]);
        }
        BSA.print();
    }
}