public class Warmup {
    public static int backtrackingSearch(int[] arr, int x, int fd, int bk, Stack myStack) {

        //// not verified
        int xIndex = -1;
        int searchIndex = 0;
        int numOfMoves;
        boolean toStop = false;

        while (!toStop) {
            numOfMoves = fd;
            while (numOfMoves > 0 & !toStop) {
                if (searchIndex < arr.length) {
                    if (arr[searchIndex] == x) {
                        xIndex = searchIndex;
                        toStop = true;
                    }
                    else {
                        myStack.push(arr[searchIndex]);
                        searchIndex++;
                        numOfMoves--;
                    }
                }
                else {
                    toStop = true;
                }
            }
            numOfMoves = bk;
            while (numOfMoves > 0) {
                myStack.pop();
                searchIndex--;
                numOfMoves--;
            }
        }
        return xIndex;
    }

    public static int consistentBinSearch(int[] arr, int x, Stack myStack) {

        //// not verified
        int output = -1;
        boolean found = false;
        int low = 0, high = arr.length-1;
        int[] searchPrevValues;

        while (low <= high & !found){
            int numOfBackTracks = isConsistent(arr);

            if (numOfBackTracks == 0) {
                int middle = (low+high) / 2;
                if (arr[middle] == x) {
                    output = middle;
                    found = true;
                }
                else {
                    searchPrevValues = new int[]{low,high};
                    myStack.push(searchPrevValues);
                    if (x < arr[middle])
                        high = middle-1;
                    else
                        low = middle+1;
                }
            }
            else {
                while (numOfBackTracks > 0 & !myStack.isEmpty()) {
                    searchPrevValues = (int[]) myStack.pop();
                    low = searchPrevValues[0];
                    high = searchPrevValues[1];
                    numOfBackTracks--;
                }
            }
        }
        return output;

    }

    private static int isConsistent(int[] arr) {
        double res = Math.random() * 100 - 75;

        if (res > 0) {
            return (int)Math.round(res / 10);
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Stack a = new Stack();
        int[] A={1, 1, 2, 14, 15,16, 23, 99, 100, 100, 100, 132, 193, 196, 197};
        int r = consistentBinSearch(A, 193, a);
        System.out.println(r);
    }
}