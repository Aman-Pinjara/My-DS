public class MyBinaryTree{
    static class BinaryTree{
        private static class Node{
            Node left;
            Node right;
            int data;
            public Node(int data){
                this.data = data;
            }
        }

        private Node root;
        public BinaryTree(){
            root = null;
        }
        private static Node append(Node node, int data){
            if(node == null){
                return new Node(data);
            }
            if(data<node.data){
                node.left = append(node.left, data);
            }else{
                node.right = append(node.right, data);
            }
            return node;
        }
        private static Node inOrderSuccessor(Node node){
            return leftMost(node.right);
        }
        private static Node leftMost(Node node){
            Node temp = node;
            while(temp.left!=null){
                temp = temp.left;
            }
            return temp;
        }
        private static Node remove(Node node, int data){
            if(node == null){
                return null;
            }
            if(node.data == data){
                //no children
                if(node.left==null && node.right==null){
                    return null;
                }

                //Only One child
                if(node.left==null){
                    return node.right;
                }
                if(node.right==null){
                    return node.left;
                }

                //Both child
                Node inordersuccessor = inOrderSuccessor(node);
                node.data = inordersuccessor.data;
                node.right = remove(node.right, inordersuccessor.data);
            }else if(data<node.data){
                node.left = remove(node.left, data);
            }else{
                node.right = remove(node.right, data);
            }
            return node;
        }
        private static void inorder(Node node){
            if(node == null) return;
            inorder(node.left);
            System.out.print(node.data+" ");
            inorder(node.right);
        }

        void append(int data){
            root = append(root, data);
        }
        void printInOrder(){
            inorder(root);
        }
        void remove(int data){
            root = remove(root, data);
        }
    }
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.append(27);
        tree.append(2);
        tree.append(32);
        tree.append(7);
        tree.printInOrder();
        tree.remove(27);
        tree.printInOrder();
    }
}