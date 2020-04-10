public class BacktrackingBST implements Backtrack, ADTSet<BacktrackingBST.Node> {
    private Stack stack;
    private Stack redoStack;
    BacktrackingBST.Node root = null;

    // Do not change the constructor's signature
    public BacktrackingBST(Stack stack, Stack redoStack) {
        this.stack = stack;
        this.redoStack = redoStack;
    }

    public Node getRoot() {
        return root;
    }

    public Node search(int x) {
        return search(x, root);
    }

    private Node search(int x, Node searchNode) {
        if (searchNode == null)
            return null;
        else if (searchNode.getKey() == x)
            return searchNode;
        else if (searchNode.getKey() > x)
            return search(x, searchNode.left);
        else
            return search(x, searchNode.right);
    }

    public void insert(BacktrackingBST.Node z) {
        if (root == null) {
            //insert value 0
            root = z;
            Object[] insertData = new Object[2];
            insertData[0] = 0;
            insertData[1] = z;
            stack.push(insertData);
        }
        else insert(z, root);
    }

    private void insert(BacktrackingBST.Node z, Node compareNode) {
        if (z.getKey() < compareNode.getKey())
            if (compareNode.left != null)
                insert(z, compareNode.left);
            else {
                //insert value 0
                z.parent = compareNode;
                compareNode.left = z;
                Object[] insertData = new Object[2];
                insertData[0] = 0;
                insertData[1] = z;
                stack.push(insertData);
            }
        else if (z.getKey() > compareNode.getKey()) {
            if (compareNode.right != null)
                insert(z, compareNode.right);
            else {
                //insert value 0
                z.parent = compareNode;
                compareNode.right = z;
                Object[] insertData = new Object[2];
                insertData[0] = 0;
                insertData[1] = z;
                stack.push(insertData);
            }
        }
        // if the value exist z is not inserted
    }


    public void delete(Node x) {
        if (x.left != null | x.right != null) {
            if (x.left == null) {
                x.right.parent = x.parent;
                x.parent.left = x.right;
                x = x.right;
            }
            else if (x.left == null) {
                x.left.parent = x.parent;
                x.parent.right = x.right;
                x = x.left;
            }
            else {
                Node rightMinimum = minimum(x.right);
                x.key = rightMinimum.getKey();
                x.value = rightMinimum.getValue();

                delete(rightMinimum);
            }
        }
        else {
            Node p = x.parent;
            if (p == null)
                root = null;
            else if (p.right == x)
                p.right = null;
            else p.left = null;
        }
    }


    public Node minimum() {
        return minimum(root);
    }

    private Node minimum(Node findMin) {
        if (findMin.left == null)
            return findMin;
        else
            return minimum(findMin.left);
    }

    public Node maximum() {
        return maximum(root);
    }

    private Node maximum(Node findMax) {
        if (findMax.right == null)
            return findMax;
        else
            return minimum(findMax.right);
    }

    public Node successor(Node x) {
        //not sure
        return x.right;
    }

    public Node predecessor(Node x) {
        //not sure
        return x.left;
    }

    @Override
    public void backtrack() {
        // TODO: implement your code here
    }

    @Override
    public void retrack() {
        // TODO: implement your code here
    }

    public void printPreOrder(){
        print();
    }

    @Override
    public void print() {
        String toPrintString = "";
        toPrintString = print(toPrintString, root);
        System.out.println(toPrintString.substring(0, toPrintString.length() - 1));
    }

    private String print(String toPrintString, Node toPrint) {

        if (toPrint != null) {
            toPrintString = toPrintString + toPrint.getKey() + " ";

            if (toPrint.left != null)
                toPrintString = print(toPrintString, toPrint.left);

            if (toPrint.right != null)
                toPrintString = print(toPrintString, toPrint.right);
        }

        return toPrintString;
    }

    public static class Node{
        //These fields are public for grading purposes. By coding conventions and best practice they should be private.
        public BacktrackingBST.Node left;
        public BacktrackingBST.Node right;

        private BacktrackingBST.Node parent;
        private int key;
        private Object value;

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Stack a = new Stack();
        Stack b = new Stack();
        //int[] A={1, 2, 4, 6, 7, 8};
        BacktrackingBST A = new BacktrackingBST (a, b);
        Node A1 = new Node (5,null);
        Node A2 = new Node (1,null);
        Node A3 = new Node (10,null);
        Node A4 = new Node (6,null);
        Node A5 = new Node (7,null);
        Node A6 = new Node (14,null);


        A.insert(A1);
        A.insert(A2);
        A.insert(A3);
        A.insert(A4);
        A.insert(A5);
        A.insert(A6);
        //A.backtrack();
        //System.out.println (A.search(3));
        A.delete(A1);
        A.print();
    }

}