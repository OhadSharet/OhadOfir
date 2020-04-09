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

        int middle = 0; // default value (the array is empty)

        if (nonEmptyCellsNumber == 0) {
            arr[middle] = x;
            nonEmptyCellsNumber++;
        }
        else {
            //idk why the ++ need to come here
            nonEmptyCellsNumber++;

            boolean found = false;
            int low = 0, high = nonEmptyCellsNumber - 1;
            while (low <= high & !found){
                middle = (low+high)/2;
                if(arr[middle] == x){
                    found = true;
                }
                else
                if (x < arr[middle])
                    high = middle-1;
                else
                    low = middle+1;
            }
            for (int i = nonEmptyCellsNumber; i > middle; i--)
                arr[i] = predecessor(i);

            arr[middle] = x;
        }

        //value 0 insert
        int[] insertData = new int [2];
        insertData[0] = x;
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

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Stack a = new Stack();
        int[] A={1, 1, 2, 14, 15,16, 23, 99, 100, 100, 100, 132, 193, 196, 197};
        BacktrackingSortedArray BSA = new BacktrackingSortedArray(a, 20);
        for (int i=0; i < A.length; i++) {
            BSA.insert(A[i]);
        }
        BSA.print();
    }
}