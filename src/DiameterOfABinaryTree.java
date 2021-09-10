import java.util.Stack;

public class DiameterOfABinaryTree {

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
         *
         * Number of edges between the two farthest nodes of a tree
         * */
        public static int diameter(Node node) {

            if(node == null) {
                return 0;
            }

            // maximum distance between two nodes of lhs
            int ld = diameter(node.left);

            // maximum distance between two nodes of rhs
            int rd = diameter(node.right);

            // maximum distance between lefts deepest and rights deepest
            int f = height(node.left) + height(node.right) + 2;

            int diameter = Math.max(ld, Math.max(rd, f));
            return diameter;
        }

        //depth of the deepest node
        public static int height(Node node) {
            if (node == null) {
                return -1; //-1 for edges, 0 for nodes
            }
            int lh = height(node.left);
            int rh = height(node.right);
            int th = Math.max(lh, rh) + 1;
            return th;
        }

        static class DiaPair {
            int ht;
            int dia;
        }

        public static DiaPair diameter2(Node node) {

            if(node == null ) {
                DiaPair bp = new DiaPair();
                bp.ht = -1;
                bp.dia = 0;
                return bp;
            }
            DiaPair lp  = diameter2(node.left);
            DiaPair rp  = diameter2(node.right);

            DiaPair mp = new DiaPair();
            mp.ht = Math.max(lp.ht, rp.ht) + 1;

            int fes = lp.ht + rp.ht + 2;
            mp.dia = Math.max(fes, Math.max(lp.dia, rp.dia));

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

//            System.out.println(diameter(root));
            DiaPair p = diameter2(root);
            System.out.println(p.dia);
        }
    }
}
