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

        //at least 1 son case
        if (x.left != null | x.right != null) {
            if (x.left == null) {

                Object[] deleteData = new Object[3];
                deleteData[0] = 1;
                deleteData[1] = "right";
                deleteData[2] = x;
                stack.push(deleteData);

                x.right.parent = x.parent;

                if (x.parent != null)
                    if (x.parent.right == x)
                        x.parent.right = x.right;
                    else x.parent.left = x.right;
                else root = x.right;
            }
            else if (x.right == null) {

                Object[] deleteData = new Object[3];
                deleteData[0] = 1;
                deleteData[1] = "left";
                deleteData[2] = x;
                stack.push(deleteData);

                x.left.parent = x.parent;

                if (x.parent != null)
                    if (x.parent.right == x)
                        x.parent.right = x.left;
                    else x.parent.left = x.left;
                else root = x.left;
            }
            else {
                //2 sons case
                Node rightMinimum = minimum(x.right);

                Object[] deleteData = new Object[7];
                deleteData[0] = 1;
                deleteData[1] = "left and right";
                deleteData[2] = x;
                deleteData[3] = rightMinimum;
                deleteData[4] = rightMinimum.parent;
                deleteData[5] = rightMinimum.right;
                deleteData[6] = rightMinimum.left;
                stack.push(deleteData);

                //update rightMinimum parent *****
                Node minimumParentUpdate = rightMinimum.parent;
                boolean xIsFather = false;
                if (minimumParentUpdate == x)
                    xIsFather = true;

                if (minimumParentUpdate.right == rightMinimum)
                    minimumParentUpdate.right = null;
                else minimumParentUpdate.left = null;

                //update x parent
                Node xParentUpdate = x.parent;
                if (xParentUpdate != null)
                    if (xParentUpdate.right == x)
                        xParentUpdate.right = rightMinimum;
                    else xParentUpdate.left = rightMinimum;
                else root = rightMinimum;

                //checking if x is the parent of the right side minimum
                if(!xIsFather) {
                    rightMinimum.right = x.right;
                    rightMinimum.left = x.left;
                    rightMinimum.parent = x.parent;

                    x.left.parent = rightMinimum;
                    x.right.parent = rightMinimum;
                }
                else {
                    rightMinimum.left = x.left;
                    x.left.parent = rightMinimum;
                    x.right = rightMinimum;
                }
            }
        }
        else {
            //no sons case
            Object[] deleteData = new Object[3];
            deleteData[0] = 1;
            deleteData[1] = "none";
            deleteData[2] = x;
            stack.push(deleteData);

            Node nodeParent = x.parent;
            if (nodeParent == null)
                root = null;
            else if (nodeParent.right == x)
                nodeParent.right = null;
            else nodeParent.left = null;
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
        if (x.right != null)
            return minimum(x.right);
        else
            return x.parent;
    }

    public Node predecessor(Node x) {
        if (x.left != null)
            return maximum(x.left);
        else {
            Node xPredecessor = x.parent;
            while ((xPredecessor != null) && (successor(xPredecessor) != x))
                xPredecessor = xPredecessor.parent;
            return xPredecessor;
        }
    }

    @Override
    public void backtrack() {

        if (!stack.isEmpty()) {
            Object[] backtrackData = (Object[]) stack.pop();

            //value 0 insert (thus the value need to be deleted)
            //value 1 delete (thus the value need to be inserted)
            if ((Integer) backtrackData[0] == 0) {

                Node toDelete = ((Node) backtrackData[1]);
                delete(toDelete);
                redoStack.push(stack.pop());

            }
            else {
                Node toInsert = ((Node) backtrackData[2]);

                if (toInsert.parent == null)
                    root = toInsert;
                else {

                    //parent connect
                    if (toInsert.parent.getKey() > toInsert.getKey())
                        toInsert.parent.left = toInsert;
                    else toInsert.parent.right = toInsert;
                }

                //redo stack push
                Object[] insertData = new Object[2];
                insertData[0] = 0;
                insertData[1] = toInsert;
                redoStack.push(insertData);

                if (((String)backtrackData[1]).toString() == "none") {
                    //nothing to do
                }
                if (((String)backtrackData[1]).toString() == "left") {
                    toInsert.left.parent = toInsert;
                }
                if (((String)backtrackData[1]).toString() == "right") {
                    toInsert.right.parent = toInsert;
                }
                if (((String)backtrackData[1]).toString() == "left and right") {
                    toInsert.left.parent = toInsert;
                    toInsert.right.parent = toInsert;
                    Node toOriginalPlace = ((Node)backtrackData[3]);
                    toOriginalPlace.parent = ((Node)backtrackData[4]);
                    toOriginalPlace.left = ((Node)backtrackData[5]);
                    toOriginalPlace.right = ((Node)backtrackData[6]);

                    if (toOriginalPlace.parent.getKey() > toOriginalPlace.getKey())
                        toOriginalPlace.parent.left = toOriginalPlace;
                    else toOriginalPlace.parent.right = toOriginalPlace;
                }
            }
            System.out.println("backtracking performed");
        }
    }

    @Override
    public void retrack() {

        if (!redoStack.isEmpty()) {
            Object[] retrackData = (Object[]) redoStack.pop();

            //value 0 insert (thus the value need to be deleted)
            //value 1 delete (thus the value need to be inserted)
            if ((Integer) retrackData[0] == 0) {

                Node toDelete = ((Node) retrackData[1]);
                delete(toDelete);
                stack.push(stack.pop());

            }
            else {
                Node toInsert = ((Node) retrackData[2]);

                if (toInsert.parent == null)
                    root = toInsert;
                else {

                    //parent connect
                    if (toInsert.parent.getKey() > toInsert.getKey())
                        toInsert.parent.left = toInsert;
                    else toInsert.parent.right = toInsert;
                }

                //redo stack push
                Object[] insertData = new Object[2];
                insertData[0] = 0;
                insertData[1] = toInsert;
                stack.push(insertData);

                if (((String)retrackData[1]).toString() == "none") {
                    //nothing to do
                }
                if (((String)retrackData[1]).toString() == "left") {
                    toInsert.left.parent = toInsert;
                }
                if (((String)retrackData[1]).toString() == "right") {
                    toInsert.right.parent = toInsert;
                }
                if (((String)retrackData[1]).toString() == "left and right") {
                    toInsert.left.parent = toInsert;
                    toInsert.right.parent = toInsert;
                    Node toOriginalPlace = ((Node)retrackData[3]);
                    toOriginalPlace.parent = ((Node)retrackData[4]);
                    toOriginalPlace.left = ((Node)retrackData[5]);
                    toOriginalPlace.right = ((Node)retrackData[6]);

                    if (toOriginalPlace.parent.getKey() > toOriginalPlace.getKey())
                        toOriginalPlace.parent.left = toOriginalPlace;
                    else toOriginalPlace.parent.right = toOriginalPlace;
                }
            }
            System.out.println("retracking performed");
        }

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

    	/*

        Stack a = new Stack();
       	Stack b = new Stack();
       	//int[] A={1, 2, 4, 6, 7, 8};
       	BacktrackingBST A = new BacktrackingBST (a, b);
       	Node A1 = new Node (4,null);
       	Node A2 = new Node (2,null);
       	Node A3 = new Node (1,null);
       	Node A4 = new Node (3,null);
       	Node A5 = new Node (6,null);
        Node A6 = new Node (5,null);
       	Node A7 = new Node (7,null);


        A.insert(A1);
       	A.insert(A2);
       	A.insert(A3);
       	A.insert(A4);
   		A.insert(A5);
       	A.insert(A6);
       	A.insert(A7);
        //A.backtrack();
       	//System.out.println (A.search(3));
       	A.print();
       	A.delete(A7);
       	A.print();
       	A.backtrack();
       	A.print();
       	A.retrack();
       	A.print();





    	Stack a = new Stack();
       	Stack b = new Stack();
    	BacktrackingBST A = new BacktrackingBST (a, b);


   		Node A1 = new Node ((int)(Math.random()*100)+10,null);
    	Node A2 = new Node ((int)(Math.random()*100)+10,null);
    	Node A3 = new Node ((int)(Math.random()*100)+10,null);
   		Node A4 = new Node ((int)(Math.random()*100)+10,null);
   		Node A5 = new Node ((int)(Math.random()*100)+10,null);
   		Node A6 = new Node ((int)(Math.random()*100)+10,null);

    	System.out.println("A1 = "+A1.key+" A2 = "+A2.getKey()+" A3 = "+A3.key+" A4 = "+A4.getKey()+" A5 = "+A5.key+" A6 = "+A6.getKey());

    	A.insert(A1);
    	A.insert(A2);
    	A.insert(A3);
    	A.insert(A4);
   		A.insert(A5);
   		A.insert(A6);
   		A.print();
    	A.delete(A2);
    	A.print();d
   		A.backtrack();
   		A.print();
   		A.retrack();
    	A.print();

    	*/

        // TODO Auto-generated method stub

//		   	int[] A={1, 2, 4, 6, 7, 8};


        for (int i = 0; i<10; i++) {
            Stack a = new Stack();
            Stack b = new Stack();
            BacktrackingBST A = new BacktrackingBST (a, b);


            Node A1 = new Node ((int)(Math.random()*100)+10,null);
            Node A2 = new Node ((int)(Math.random()*100)+10,null);
            Node A3 = new Node ((int)(Math.random()*100)+10,null);
            Node A4 = new Node ((int)(Math.random()*100)+10,null);
            Node A5 = new Node ((int)(Math.random()*100)+10,null);
            Node A6 = new Node ((int)(Math.random()*100)+10,null);


    /*
    		Node A1 = new Node (81,null);
    		Node A2 = new Node (94,null);
    		Node A3 = new Node (85,null);
    		Node A4 = new Node (75,null);
    		Node A5 = new Node (29,null);
    		Node A6 = new Node (106,null);    */
            System.out.println("A1 = "+A1.key+" A2 = "+A2.getKey()+" A3 = "+A3.key+" A4 = "+A4.getKey()+" A5 = "+A5.key+" A6 = "+A6.getKey());


            A.insert(A1);
            A.insert(A2);
            A.insert(A3);
            A.insert(A4);
            A.insert(A5);
            A.insert(A6);
            A.print();
            A.delete(A2);
            A.print();
            A.backtrack();
            A.print();
        }

    }
}