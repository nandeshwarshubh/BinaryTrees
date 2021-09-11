import java.util.Stack;

public class isABalacedTree {

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
         *  for all nodes |lh-rh| <= 1
         *
         */


        static boolean isBalanced = true;

        public static int isBalanced(Node node) {

            if (node == null) {
                return 0;
            }


            int lh = isBalanced(node.left);
            int rh = isBalanced(node.right);

            int gap = Math.abs(lh - rh);
            if (gap > 1) {
                isBalanced = false;
            }

            int th = Math.max(lh, rh) + 1;
            return th;
        }


        public static class BalPair {
            int height;
            boolean isBalanced;
        }

        public static BalPair isBalanced2(Node node) {
            if (node == null) {
                BalPair bp = new BalPair();
                bp.height = 0;
                bp.isBalanced = true;
                return bp;
            }

            BalPair lbp = isBalanced2(node.left);
            BalPair rbp = isBalanced2(node.right);

            BalPair mp = new BalPair();
            mp.isBalanced = Math.abs(lbp.height - rbp.height) <= 1 &&
                    lbp.isBalanced && rbp.isBalanced;
            mp.height = Math.max(lbp.height, rbp.height) + 1;

            return mp;
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

            isBalanced(root);
            System.out.println(isBalanced);


            System.out.println(isBalanced2(root).isBalanced);
        }
    }
}
