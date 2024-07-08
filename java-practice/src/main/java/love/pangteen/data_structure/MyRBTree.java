package love.pangteen.data_structure;

import java.util.Comparator;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/8 12:12
 **/
public class MyRBTree<T> {

    private final Comparator<? super T> comparator;
    private Node<T> root;
    private int len;

    public MyRBTree(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public void add(T value){
        if(root == null) {
            len = 1;
            root = new Node<>(value);
            root.isBlack = true;
            return;
        }
        Node<T> relate = find(value);
        int compare = compare(relate.value, value);
        if(compare != 0) {
            Node<T> now = new Node<>(value);
            now.parent = relate;
           if(compare > 0) {
               // 节点小，往左边放。
               relate.left = now;
           } else {
               relate.right = now;
           }
           analyze(now);
           ++ this.len;
        }
    }

    public boolean contains(T value){
        Node<T> res = find(value);
        return res != null && compare(res.value, value) == 0;
    }

    public Node<T> root(){
        return root;
    }

    public int size(){
        return this.len;
    }

    /**
     * 对当前节点进行分析，需要进行什么更新操作。
     */
    private void analyze(Node<T> now){
        Node<T> parent = now.parent;
        if(parent == null){
            now.isBlack = true; // 根节点直接染黑。
        } else if(! parent.isBlack){
            Node<T> grand = parent.parent;
            boolean isLeftChild = isLeftChild(now, parent);
            boolean isLeftParent = isLeftChild(parent, grand);
            Node<T> uncle = isLeftParent ? grand.right : grand.left;
            if(uncle == null || uncle.isBlack){
                if(isLeftChild){
                    if(isLeftParent){
                        rightRotate(grand);
                        parent.isBlack = true;
                        now.isBlack = false;
                        grand.isBlack = false;
                    } else {
                        rightRotate(parent);
                        analyze(parent);
                    }
                } else {
                    if(isLeftParent){
                        leftRotate(parent);
                        analyze(parent);
                    } else {
                        leftRotate(grand);
                        parent.isBlack = true;
                        now.isBlack = false;
                        grand.isBlack = false;
                    }
                }
            } else {
                // 无需旋转，直接染色。
                now.isBlack = false;
                parent.isBlack = true;
                uncle.isBlack = true;
                grand.isBlack = false;
                analyze(grand);
            }
        }
    }

    /*
     * 对红黑树的节点(x)进行左旋转。
     *
     * 左旋示意图(对节点x进行左旋)：
     *      px                              px
     *     /                               /
     *    x                               y
     *   /  \      --(左旋)->             / \
     *  lx   y                          x   ry
     *     /   \                       /  \
     *    ly   ry                     lx  ly
     */
    private void leftRotate(Node<T> x) {
        Node<T> y = x.right;

        // 处理x的右节点和y的左节点。
        x.right = y.left;
        if(y.left != null) y.left.parent = x.right;
        y.left = x;

        // 处理y和x的父节点。
        y.parent = x.parent;
        if(x.parent == null){
            this.root = y;
        } else {
            if(x.equals(x.parent.left)) x.parent.left = y;
            else x.parent.right = y;
        }
        x.parent = y;
    }

    /*
     * 对红黑树的节点(y)进行右旋转。
     *
     * 右旋示意图(对节点y进行左旋)：
     *            py                               py
     *           /                                /
     *          y                                x
     *         /  \       --(右旋)->             /  \
     *        x   ry                           lx   y
     *       / \                                   / \
     *      lx  rx                                rx  ry
     *
     */
    private void rightRotate(Node<T> y){
        Node<T> x = y.left;

        // 处理x的右节点和y的左节点。
        y.left = x.right;
        if(x.right != null) x.right.parent = y;
        x.right = y;

        // 处理y和x的父节点。
        x.parent = y.parent;
        if(y.parent == null){
            this.root = x;
        } else {
            if(y.equals(y.parent.left)) y.parent.left = x;
            else y.parent.right = x;
        }
        y.parent = x;
    }

    private boolean isLeftChild(Node<T> now, Node<T> parent){
        return now.equals(parent.left);
    }

    /**
     * 根据值定位到最近的Node，复杂度O(log n)
     */
    private Node<T> find(T value){
        Node<T> now = root;
        Node<T> res = null;
        while(now != null){
            res = now;
            int result = compare(now.value, value);
            if(result == 0) return now;
            else if(result > 0) now = now.left;
            else now = now.right;
        }
        return res;
    }

    private int compare(Node<T> x, Node<T> y){
        return compare(x.value, y.value);
    }

    private int compare(T x, T y){
        return this.comparator.compare(x, y);
    }

    public static class Node<T> {

        private final T value;
        private boolean isBlack;
        private Node<T> left, right, parent;
        private int height;

        public Node(T value){
            this.value = value;
            this.isBlack = false;
        }

        public T value() {
            return value;
        }
    }
}
