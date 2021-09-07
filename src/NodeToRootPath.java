import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
        import java.util.Stack;

public class NodeToRootPath {

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

        public static void displayNode(Node node) {
            if (node == null) {
                return;
            }
            String str = "";
            str += node.left == null ? "." : node.left.data + "";
            str += "<-" + node.data + "->";
            str += node.right == null ? "." : node.right.data + "";

            System.out.println(str);

            displayNode(node.left);
            displayNode(node.right);
        }

        static ArrayList<Integer> path;
        public static boolean find(Node node, int data) {
            if(node == null) {
                return false;
            }
            if(node.data == data) {
                path.add(node.data);
                return true;
            }

            boolean findInLeftChild = find(node.left, data);
            if(findInLeftChild) {
                path.add(node.data);
                return true;
            }
            boolean findInRightChild = find(node.right, data);
            if(findInRightChild) {
                path.add(node.data);
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

            path = new ArrayList<>();
            find(root, 70);
            System.out.println(path);
        }
    }
}
