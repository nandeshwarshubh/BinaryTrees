import java.util.Stack;

public class MyBinaryTree {

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

        public static int size(Node node) {
            if (node == null) {
                return 0;
            }
            int ls = size(node.left);
            int rs = size(node.right);
            return ls + rs + 1;
        }

        public static int sum(Node node) {
            if (node == null) {
                return 0;
            }
            int lsum = sum(node.left);
            int rsum = sum(node.right);
            return lsum + rsum + node.data;
        }

        public static int max(Node node) {
            if (node == null) {
                return Integer.MIN_VALUE;
            }
            int lmax = max(node.left);
            int rmax = max(node.right);
            int tmax = Math.max(lmax, rmax);
            return Math.max(tmax, node.data);
        }

        //depth of the deepest node
        public static int height(Node node) {
            if(node == null) {
                return -1; //-1 for edges, 0 for nodes
            }
            int lh = height(node.left);
            int rh = height(node.right);
            int th = Math.max(lh,rh)+1;
            return th;
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

//            displayNode(root);
//            System.out.println(size(root));
//            System.out.println(sum(root));
//            System.out.println(max(root));
            System.out.println(height(root));
        }
    }
}
