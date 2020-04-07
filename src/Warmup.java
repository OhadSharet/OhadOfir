public class Warmup {
    public static int backtrackingSearch(int[] arr, int x, int fd, int bk, Stack myStack) {
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

    public static int consistentBinSearch(int[] arr, int x, Stack stack) {

        int num_of_steps_back=0;
        BinSerchData last_step;
        int l = 0, r = arr.length - 1,m;
        while (l <= r) {
            num_of_steps_back = isConsistent(arr);
            if (num_of_steps_back >0)
            {
                for(int i=0;i<num_of_steps_back;i++)
                {
                    last_step = (BinSerchData)stack.pop();
                    l= last_step.l;
                    r = last_step.r;
                }
            }
            else {
                stack.push(new BinSerchData(l, r));
            }

                m = l + (r - l) / 2;

                // Check if x is present at mid
                if (arr[m] == x)
                    return m;

                // If x greater, ignore left half
                if (arr[m] < x)
                    l = m + 1;

                    // If x is smaller, ignore right half
                else
                    r = m - 1;



        }

        // if we reach here, then element was
        // not present
        return -1;
    }

    class BinSerchData {

        public int l;
        public int r;

        public BinSerchData(int l ,int r)
        {
            this.l = l;
            this.r = r;
        }

    }

    private static int isConsistent(int[] arr) {
        double res = Math.random() * 100 - 75;

        if (res > 0) {
            return (int)Math.round(res / 10);
        } else {
            return 0;
        }
    }
}
