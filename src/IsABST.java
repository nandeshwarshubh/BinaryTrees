import java.util.Stack;

public class IsABST {

    public static class Node {

        Integer data;
        Node left;
        Node right;

        Node(Integer data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public static class Pair {
            Node node;
            int state;

            public Pair(Node node, int state) {
                this.node = node;
                this.state = state;
            }
        }


        /*
         *  Should be greater than ls and should be smaller than all rs
         *
         *               50
         *           /         \
         *       25              75
         *   /      \          /    \
         * 12        37      62      87
         *
         *
         *
         *        nBST          tBST     min     max
         *  12      t           t       12      12
         *  37      t           t       37      37
         *  25      t           t       12      37
         *  62      t           t       62      62
         *  87      t           t       87      87
         *  75      t           t       62      87
         *  50      t           t       12      87
         *
         *
         */

        public static BSTPair isBST(Node node) {
            if(node == null) {
                BSTPair bp = new BSTPair();
                bp.min = Integer.MAX_VALUE;
                bp.max = Integer.MIN_VALUE;
                bp.isBST = true;
                return bp;
            }

            BSTPair lp = isBST(node.left);
            BSTPair rp = isBST(node.right);

            BSTPair mp = new BSTPair();
            mp.isBST = lp.isBST &&
                    rp.isBST &&
                    (node.data > lp.max && node.data <= rp.min);

            mp.min = Math.min(node.data, Math.min(lp.min, rp.min));
            mp.max = Math.max(node.data, Math.max(lp.max, rp.max));

            return mp;
        }

        public static class BSTPair {
            boolean isBST;
            int min;
            int max;
        }


        /*
         * 1-left
         * 2-right
         * 3-pop
         * */
        public static void main(String[] args) {

            /*
             *               50
             *           /         \
             *       25              75
             *   /      \          /    \
             * 12        37      62      87
             *         /           \
             *       30              70
             *
             *
             * if 70 was 77 then it wont be a BST
             * */


            Integer[] arr = {50, 25, 12, null, null,
                    37, 30, null, null, null,
                    75, 62, null, 70, null,
                    null, 87, null, null};

            Stack<Pair> st = new Stack<>();
            Node root = new Node(arr[0], null, null);

            Pair rp = new Pair(root, 1);
            st.push(rp);
            int idx = 0;
            while (st.size() > 0) {
                Pair top = st.peek();
                if (top.state == 1) {
                    idx++;
                    if (arr[idx] != null) {
                        top.node.left = new Node(arr[idx], null, null);
                        Pair leftPair = new Pair(top.node.left, 1);
                        st.push(leftPair);
                    } else {
                        top.node.left = null;
                    }
                    top.state++;
                } else if (top.state == 2) {
                    idx++;
                    if (arr[idx] != null) {
                        top.node.right = new Node(arr[idx], null, null);

                        Pair rightPair = new Pair(top.node.right, 1);
                        st.push(rightPair);
                    } else {
                        top.node.right = null;
                    }
                    top.state++;
                } else {
                    st.pop();
                }
            }

            System.out.println(isBST(root).isBST);
        }
    }
}
