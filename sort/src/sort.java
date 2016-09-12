  import java.util.Scanner;
    class BTNode {
        BTNode  left, right;
        int data;
        public BTNode(int p) {
            left = null;
            right = null;
            data = p;
        }
        /* Fn to get left node */
        public BTNode getLeft()
        {
            return left;
        }
        /* Fn to get right node */
        public BTNode getRight()
        {
            return right;
        }
        /* Fn to get data from node */
        public int getData()
        {
            return data;
        }
    }
    /* Class BT */
    class BT {
        private BTNode root;
        public BT()
        {
            root = null;
        }
        /* Fn to add data */
        public void insert(int data)
        {
            root = insert(root, data);
        }
        /* Fn to insert data in a recursive way*/
        private BTNode insert(BTNode node, int data)
        {
            if (node == null)
                node = new BTNode(data);
            else
            {
                if (data <= node.getData())
                    node.left = insert(node.left, data);
                else
                    node.right = insert(node.right, data);
            }
            return node;
        }

        /* Fn for inorder traversal */
        public void inorder()
        {
            inorder(root);
        }

        private void inorder(BTNode r)
        {
            if (r != null)
            {
                inorder(r.getLeft());
                System.out.print(r.getData() + " ");
                inorder(r.getRight());
            }
        }

        /* Fn for preorder traversal */
        public void preorder()
        {
            preorder(root);
        }

        private void preorder(BTNode r)
        {
            if (r != null)
            {
                System.out.print(r.getData() + " ");
                preorder(r.getLeft());
                preorder(r.getRight());
            }
        }

        /* Fn for postorder traversal */
        public void postorder()
        {
            postorder(root);
        }

        private void postorder(BTNode r)
        {
            if (r != null)
            {
                postorder(r.getLeft());
                postorder(r.getRight());
                System.out.print(r.getData() + " ");
            }
        }
    }

    public class sort
    {

        public static void main(String args[])
        {
            Scanner sc=new Scanner(System.in);
            BT bt = new BT();

            System.out.println("How many no. of keys would you like to insert");

            int N=sc.nextInt();

            for (int i = 0; i < N; i++)
                bt.insert(sc.nextInt());
            System.out.println("Enter the next key to be inserted");

            System.out.println("Following are the keys of the tree: ");
            bt.preorder();

            System.out.println("\nThe  post order sequence is:");
            bt.postorder();

            System.out.println("\nThe in order sequence is: ");
            bt.inorder();
        }
    }
