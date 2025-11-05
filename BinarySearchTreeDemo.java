public class BinarySearchTreeDemo {

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        TreeNode(int value) {
            this.value = value;
        }
    }

    static class BinarySearchTree {
        TreeNode root;

        
        public TreeNode findValue(TreeNode current, int target) {
            if (current == null) {
                return null;
            }
            if (current.value == target) {
                return current;
            }
            if (target < current.value) {
                return findValue(current.left, target);
            } else {
                return findValue(current.right, target);
            }
        }

        public TreeNode find(int target) {
            return findValue(root, target);
        }

        
        public void insertNode(TreeNode current, int newValue) {
            if (newValue == current.value) {
                System.out.println("Valor duplicado: " + newValue);
                return;
            }
            if (newValue < current.value) {
                if (current.left != null) {
                    insertNode(current.left, newValue);
                } else {
                    current.left = new TreeNode(newValue);
                    current.left.parent = current;
                }
            } else {
                if (current.right != null) {
                    insertNode(current.right, newValue);
                } else {
                    current.right = new TreeNode(newValue);
                    current.right.parent = current;
                }
            }
        }

        public void insert(int newValue) {
            if (root == null) {
                root = new TreeNode(newValue);
            } else {
                insertNode(root, newValue);
            }
        }

       

        private void inorderRec(TreeNode node) {
            if (node != null) {
                inorderRec(node.left);
                System.out.print(node.value + " ");
                inorderRec(node.right);
            }
        }

        public void inorder() {
            System.out.print("Recorrido in_order: ");
            inorderRec(root);
            System.out.println();
        }

        private void preOrderRec(TreeNode node) {
            if (node != null) {
                System.out.print(node.value + " ");
                preOrderRec(node.left);             
                preOrderRec(node.right);           
            }
        }

        public void preOrder() {
            System.out.print("Recorrido PreOrder: ");
            preOrderRec(root);
            System.out.println();
        }

        private void postOrderRec(TreeNode node) {
            if (node != null) {
                postOrderRec(node.left);           
                postOrderRec(node.right);           
                System.out.print(node.value + " "); 
            }
        }

        public void postOrder() {
            System.out.print("Recorrido PostOrder: ");
            postOrderRec(root);
            System.out.println();
        }

        public void delete(int value) {
            TreeNode nodeToDelete = find(value);
            if (nodeToDelete == null) {
                System.out.println("Nodo no encontrado: " + value);
                return;
            }
            removeTreeNode(this, nodeToDelete);
        }

        private void removeTreeNode(BinarySearchTree tree, TreeNode node) {
            
            if (node.left == null && node.right == null) {
                if (node == tree.root) {
                    tree.root = null;
                } else if (node.parent.left == node) {
                    node.parent.left = null;
                } else {
                    node.parent.right = null;
                }
                return;
            }

            else if (node.left == null || node.right == null) {
                TreeNode child = (node.left != null) ? node.left : node.right;

                if (node == tree.root) {
                    tree.root = child;
                    child.parent = null;
                } else if (node.parent.left == node) {
                    node.parent.left = child;
                    child.parent = node.parent;
                } else {
                    node.parent.right = child;
                    child.parent = node.parent;
                }
                return;
            }

            
            else {
               
                TreeNode successor = findMin(node.right);

                
                removeTreeNode(tree, successor);

                
                if (node == tree.root) {
                    tree.root = successor;
                } else if (node.parent.left == node) {
                    node.parent.left = successor;
                } else {
                    node.parent.right = successor;
                }
                successor.parent = node.parent;

                successor.left = node.left;
                if (node.left != null) {
                    node.left.parent = successor;
                }

                successor.right = node.right;
                if (node.right != null) {
                    node.right.parent = successor;
                }
            }
        }

        private TreeNode findMin(TreeNode node) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
    }


    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        System.out.println("Creando árbol binario de búsqueda");
        int[] values = {5, 2, 11, 3, 6, 16, 4, 9, 13};
        for (int v : values) {
            tree.insert(v);
        }

        tree.inorder();   
        tree.preOrder();  
        tree.postOrder(); 

        System.out.println("\nEliminando nodo 6");
        tree.delete(6);
        tree.inorder();   

        System.out.println("\nEliminando nodo 9 ");
        tree.delete(9);
        tree.inorder();   
    }
}
