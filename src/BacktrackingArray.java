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

        //value 0 insert
        int[] insertData = new int [3];
        insertData[0] = 0;
        insertData[1] = nonEmptyCellsNumber;
        insertData[2] = x;
        stack.push(insertData);

        arr[nonEmptyCellsNumber] = x;
        nonEmptyCellsNumber++;
    }

    @Override
    public void delete(Integer index) {

        //value 1 delete
        int[] deleteData = new int [3];
        deleteData[0] = 1;
        deleteData[1] = index;
        deleteData[2] = arr[index];
        stack.push(deleteData);

        if (index < nonEmptyCellsNumber) {

            for (int i = index; i < nonEmptyCellsNumber - 1; i++)
                arr[i] = arr[i + 1];
            nonEmptyCellsNumber--;
        }
    }

    @Override
    public Integer minimum() {

        if (nonEmptyCellsNumber != 0) {
            int minValue = arr[0];
            int index = 0;
            for (int i = 1; i < nonEmptyCellsNumber; i++)
                if (minValue > arr[i]) {
                    minValue = arr[i];
                    index = i;
                }
            return index;
        }
        return -1;
    }

    @Override
    public Integer maximum() {

        if (nonEmptyCellsNumber != 0) {
            int maxValue = arr[0];
            int index = 0;
            for (int i = 1; i < nonEmptyCellsNumber; i++)
                if (maxValue < arr[i]) {
                    maxValue = arr[i];
                    index = i;
                }
            return index;
        }
        return -1;
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
            if (backtrackData[0] == 0) {
                int index = backtrackData[1];

                delete(index);
                stack.pop();
            }
            else {
                int index = backtrackData[1];
                for (int i = nonEmptyCellsNumber; i > index; i--)
                    arr[i] = arr[i - 1];
                System.out.println(backtrackData[2]);
                arr[index] = backtrackData[2];
                nonEmptyCellsNumber++;
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
        BacktrackingArray BA = new BacktrackingArray(a, 20);
        for (int i=0; i < A.length; i++) {
            BA.insert(A[i]);
        }
        BA.backtrack();
        BA.backtrack();
        BA.insert(9);
        BA.insert(7);
        BA.insert(11);
        BA.print();
        BA.backtrack();
        BA.delete(2);
        BA.print();
        BA.backtrack();
        BA.print();

    }
}