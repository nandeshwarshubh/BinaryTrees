import java.util.Stack;

public class PrintSingleChildNodes {

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

        public static void printSingleChildNodes(Node node, Node parent) {

            if (node == null) {
                return;
            }
            if (parent != null && parent.left == node && parent.right == null) {
                System.out.println(node.data);
            }
            if (parent != null && parent.right == null && parent.left == null) {
                System.out.println(node.data);
            }

            printSingleChildNodes(node.left, node);
            printSingleChildNodes(node.right, node);
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

            printSingleChildNodes(root, null);
        }
    }
}
