import java.util.Stack;

public class LargestBSTSubtree {

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
         */

        public static BSTPair largestBstNode(Node node) {
            if(node == null) {
                BSTPair bp = new BSTPair();
                bp.min = Integer.MAX_VALUE;
                bp.max = Integer.MIN_VALUE;
                bp.isBST = true;
                bp.iBstRoot = null;
                bp.largestBstSize = 0;
                return bp;
            }

            BSTPair lp = largestBstNode(node.left);
            BSTPair rp = largestBstNode(node.right);

            BSTPair mp = new BSTPair();
            mp.isBST = lp.isBST &&
                    rp.isBST &&
                    (node.data > lp.max && node.data <= rp.min);

            mp.min = Math.min(node.data, Math.min(lp.min, rp.min));
            mp.max = Math.max(node.data, Math.max(lp.max, rp.max));

            if(mp.isBST) {
                mp.iBstRoot = node;
                mp.largestBstSize = lp.largestBstSize + rp.largestBstSize + 1;
            } else if(lp.largestBstSize > rp.largestBstSize) {
                mp.iBstRoot = lp.iBstRoot;
                mp.largestBstSize = lp.largestBstSize;
            } else {
                mp.iBstRoot = rp.iBstRoot;
                mp.largestBstSize = rp.largestBstSize;
            }

            return mp;
        }

        public static class BSTPair {
            boolean isBST;
            int min;
            int max;
            Node iBstRoot;
            int largestBstSize;
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
             * if 70 was 77 then largest bst subtree will be with node 25 and size 4
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

            BSTPair pair = largestBstNode(root);
            System.out.println(pair.iBstRoot.data);
            System.out.println(pair.largestBstSize);
        }
    }
}

