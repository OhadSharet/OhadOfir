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
        else if (((Integer) searchNode.getValue()).intValue() == x)
            return searchNode;
        else if (((Integer) searchNode.getValue()).intValue() > x)
            return search(x, searchNode.left);
        else
            return search(x, searchNode.right);
    }

    public void insert(BacktrackingBST.Node z) {
        // TODO: implement your code here
    }

    public void delete(Node x) {
        // TODO: implement your code here
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
        String toPrint = "";
        toPrint = printPreOrder(toPrint, root);
        System.out.println(toPrint.substring(0, toPrint.length() - 1));
    }

    private String printPreOrder(String toPrintString, Node toPrint) {

        if (toPrint != null) {

            if (toPrint.left != null)
                toPrintString = print(toPrintString, toPrint.left);

            toPrintString = toPrintString + toPrint.getKey() + " ";

            if (toPrint.right != null)
                toPrintString = print(toPrintString, toPrint.right);
        }

        return toPrintString;
    }

    @Override
    public void print() {
        String toPrint = "";
        toPrint = print(toPrint, root);
        System.out.println(toPrint.substring(0, toPrint.length() - 1));
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

}