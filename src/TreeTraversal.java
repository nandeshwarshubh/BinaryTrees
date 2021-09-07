import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class TreeTraversal {

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

        public static void preTraversal(Node node) {
            if (node == null) {
                return;
            }
            System.out.println(node.data);
            preTraversal(node.left);
            preTraversal(node.right);
        }

        public static void postTraversal(Node node) {
            if (node == null) {
                return;
            }
            postTraversal(node.left);
            postTraversal(node.right);
            System.out.println(node.data);

        }

        public static void inTraversal(Node node) {
            if (node == null) {
                return;
            }
            inTraversal(node.left);
            System.out.println(node.data);
            inTraversal(node.right);
        }

        /*
         * queue
         * Remove print add child
         *
         * */
        public static void levelOrderTraversal(Node node) {
            Queue<Node> mq = new ArrayDeque<>();
            mq.add(node);

            while (mq.size() > 0) {
                int count = mq.size();
                for (int i = 0; i < count; i++) {
                    node = mq.remove();
                    System.out.print(node.data + " ");

                    if (node.left != null) {
                        mq.add(node.left);
                    }

                    if (node.right != null) {
                        mq.add(node.right);
                    }

                }
                System.out.println();
            }

        }


        /*
         * 1 - pre state++ left
         * 2 - in  state++ right
         * 3 - post pop
         *
         *
         * */
        public static void iterativeTraversal(Node node) {
            Stack<Pair> st = new Stack<>();
            Pair rtp = new Pair(node, 1);
            st.push(rtp);

            String pre = "";
            String in = "";
            String post = "";

            while (st.size() > 0) {
                Pair top = st.peek();
                if (top.state == 1) {
                    pre+=top.node.data + " ";
                    top.state++;
                    if(top.node.left != null) {
                        Pair lp = new Pair(top.node.left, 1);
                        st.push(lp);
                    }
                } else if (top.state == 2) {
                    in+=top.node.data + " ";
                    top.state++;
                    if(top.node.right != null) {
                        Pair lp = new Pair(top.node.right, 1);
                        st.push(lp);
                    }
                } else if (top.state == 3) {
                    post+=top.node.data + " ";
                    st.pop();
                }
            }

            System.out.println(pre);
            System.out.println(in);
            System.out.println(post);
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

//            preTraversal(root);
//            inTraversal(root);
//            postTraversal(root);
//            levelOrderTraversal(root);
            iterativeTraversal(root);
        }
    }
}
