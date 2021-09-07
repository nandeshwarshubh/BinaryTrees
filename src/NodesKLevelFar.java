import java.util.ArrayList;
import java.util.Stack;

public class NodesKLevelFar {

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
         * print all elements at k level distance from root with data
         *
         * */
        public static void printNodesKLevelFar(Node node, int data, int k) {
            path = new ArrayList<>();
            find(node, data);
            for (int i = 0; i < path.size(); i++) {
                printKLevelsDown(path.get(i), k - i, i == 0 ? null : path.get(i - 1));
            }
        }

        public static void printKLevelsDown(Node node, int k, Node blocker) {
            if (node == null || k < 0 || node == blocker) {
                return;
            }
            if (k == 0) {
                System.out.println(node.data);
            }
            printKLevelsDown(node.left, k - 1, blocker);
            printKLevelsDown(node.right, k - 1, blocker);
        }

        static ArrayList<Node> path;

        public static boolean find(Node node, int data) {
            if (node == null) {
                return false;
            }
            if (node.data == data) {
                path.add(node);
                return true;
            }

            boolean findInLeftChild = find(node.left, data);
            if (findInLeftChild) {
                path.add(node);
                return true;
            }
            boolean findInRightChild = find(node.right, data);
            if (findInRightChild) {
                path.add(node);
                return true;
            }
            return false;
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

            printNodesKLevelFar(root,25, 2);
        }
    }
}
