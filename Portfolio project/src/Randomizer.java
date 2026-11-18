import java.util.Random;

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.queue.Queue;
import components.queue.Queue1L;
import components.sequence.Sequence;
import components.sequence.Sequence3;
import components.stack.Stack;
import components.stack.Stack1L;
import components.tree.Tree;
import components.tree.Tree1;

/**
 * Shuffles a {@code Queue}, {@code Stack}, {@code Sequence},
 * {@code BinaryTree}, or {@code Tree}.
 *
 *  <p>
 * {@code Set} and {@code Map} aren't here because they don't have an order
 * to shuffle in the first place.
 *
 * <p>
 * Only kernel methods are used, so this works with any implementation
 * (Queue1L, Queue2, BinaryTree1, Tree1, etc).
 */
public final class Randomizer {

    /** It's where random comes from. */
    private static Random rnd = new Random();

    /** Preventing instantiation. */
    private Randomizer() {
    }

    /**
     * Randomly shuffles {@code s}.
     *
     * @param <T> - entry type
     * @param s - the sequence to shuffle
     * @updates s
     * @ensures s is a uniformly random permutation of #s
     */
    public static <T> void randomize(Sequence<T> s) {
        Sequence<T> shuffled = new Sequence3<T>();
        while (s.length() > 0) {
            int pick = rnd.nextInt(s.length());
            shuffled.add(shuffled.length(), s.remove(pick));
        }
        s.transferFrom(shuffled);
    }

    /**
     * Randomly shuffles {@code q}.
     *
     * @param <T> - entry type
     * @param q - the queue to shuffle
     * @updates q
     * @ensures q is a uniformly random permutation of #s
     */
    public static <T> void randomize(Queue<T> q) {
        Sequence<T> temp = new Sequence3<T>();
        while (q.length() > 0) {
            temp.add(temp.length(), q.dequeue());
        }
        randomize(temp);
        while (temp.length() > 0) {
            q.enqueue(temp.remove(0));
        }
    }

    /**
     * Randomly shuffles {@code Stack s}.
     *
     * @param <T> - entry type
     * @param s - the sequence to shuffle
     * @updates s
     * @ensures s is a uniformly random permutation of #s
     */
    public static <T> void randomize(Stack<T> s) {
        Sequence<T> temp = new Sequence3<T>();
        while (s.length() > 0) {
            temp.add(temp.length(), s.pop());
        }
        randomize(temp);
        while (temp.length() > 0) {
            s.push(temp.remove(0));
        }
    }

    /**
     * Randomly shuffles {@code t}.
     *
     * @param <T> - entry type
     * @param t - the binary tree to shuffle
     * @updates t
     * @ensures t is a uniformly random permutation of #s
     */
    public static <T> void randomize(BinaryTree<T> t) {
        Sequence<T> labels = new Sequence3<T>();
        for (T label : t) {
            labels.add(labels.length(), label);
        }
        randomize(labels);
        fill(t, labels);
    }

    /**
     * Randomly shuffles the labels of {@code t}, keeping its shape.
     *
     * @param <T>
     *            label type
     * @param t
     *            the tree (its root node is where the data enters)
     * @updates t
     * @ensures t has the same shape as #t, and its labels are a uniformly
     *          random permutation of the labels of #t
     */
    public static <T> void randomize(Tree<T> t) {
        Sequence<T> labels = new Sequence3<T>();
        for (T label : t) {
            labels.add(labels.length(), label);
        }
        randomize(labels);
        fill(t, labels);
    }
    /**
     * Appends the labels of {@code t} to {@code labels} in pre-order, so that
     * I can randomize binary trees and regular trees into different structures
     */
    private static <T> void collect(BinaryTree<T> t, Sequence<T> labels) {
        if (t.size() > 0) {
            BinaryTree<T> left = new BinaryTree1<T>();
            BinaryTree<T> right = new BinaryTree1<T>();
            T root = t.disassemble(left, right);
            labels.add(labels.length(), root);
            collect(left, labels);
            collect(right, labels);
            t.assemble(root, left, right);
        }
    }

    /**
     * Fills using the collected data - adds the data of {@code labels} to {@code t}
     */
    private static <T> void fill(BinaryTree<T> t, Sequence<T> labels) {
        if (t.size() > 0) {
            BinaryTree<T> left = new BinaryTree1<T>();
            BinaryTree<T> right = new BinaryTree1<T>();
            t.disassemble(left, right);
            T newRoot = labels.remove(0);
            fill(left, labels);
            fill(right, labels);
            t.assemble(newRoot, left, right);
        }
    }
    /**
     * Appends the labels of {@code t} to {@code labels} in pre-order,
     * leaving {@code t} unchanged.
     * @param t
     * @param <T>
     * @param labels
     */
    private static <T> void collect(Tree<T> t, Sequence<T> labels) {
        if (t.size() > 0) {
            Sequence<Tree<T>> children = new Sequence3<Tree<T>>();
            T root = t.disassemble(children);
            labels.add(labels.length(), root);
            for (int i = 0; i < children.length(); i++) {
                Tree<T> child = children.remove(i);
                collect(child, labels);
                children.add(i, child);
            }
            t.assemble(root, children);
        }
    }

    /**
     * Replaces the labels of {@code t} with those of {@code labels} in
     * pre-order, keeping the shape. Root gets the first label.
     *
     * @requires labels.length() = t.size()
     * @param labels
     * @param t
     * @param <T>
     */
    private static <T> void fill(Tree<T> t, Sequence<T> labels) {
        if (t.size() > 0) {
            Sequence<Tree<T>> children = new Sequence3<Tree<T>>();
            t.disassemble(children);
            T newRoot = labels.remove(0);
            for (int i = 0; i < children.length(); i++) {
                Tree<T> child = children.remove(i);
                fill(child, labels);
                children.add(i, child);
            }
            t.assemble(newRoot, children);
        }
    }



    /**
     * Shuffles the data in {@code component} AND secretly moves it into a
     * randomly chosen, different-looking data structure (Queue, Stack,
     * Sequence, BinaryTree, or Tree). The caller gets back an {@code Object}
     * and is never told which kind it is; good luck guessing.
     *
     * <p>
     * Java is statically typed, so the original variable cannot change type in
     * place. Instead the data is MOVED out: the original is left empty and the
     * mystery structure holds the shuffled data.
     *
     * @param component
     *            a Queue, Stack, Sequence, BinaryTree, or Tree
     * @return a mystery structure containing the shuffled data of #component
     * @updates component
     * @ensures component is empty, and the returned structure contains
     *          exactly the entries of #component in random order
     * @throws IllegalArgumentException
     *             if the component type is not supported
     */
    public static Object scramble(Object component) {
        Sequence<Object> data = drain(component);
        randomize(data);
        switch (rnd.nextInt(5)) {
            case 0:
                Queue<Object> q = new Queue1L<Object>();
                while (data.length() > 0) {
                    q.enqueue(data.remove(0));
                }
                return q;
            case 1:
                Stack<Object> st = new Stack1L<Object>();
                while (data.length() > 0) {
                    st.push(data.remove(0));
                }
                return st;
            case 2:
                return data;
            case 3:
                return buildBinaryTree(data);
            default:
                return buildTree(data);
        }
    }

    /** Moves every entry of {@code component} into a new Sequence. */
    @SuppressWarnings("unchecked")
    private static Sequence<Object> drain(Object component) {
        Sequence<Object> out = new Sequence3<Object>();
        if (component instanceof Sequence) {
            Sequence<Object> s = (Sequence<Object>) component;
            while (s.length() > 0) {
                out.add(out.length(), s.remove(0));
            }
        } else if (component instanceof Queue) {
            Queue<Object> q = (Queue<Object>) component;
            while (q.length() > 0) {
                out.add(out.length(), q.dequeue());
            }
        } else if (component instanceof Stack) {
            Stack<Object> s = (Stack<Object>) component;
            while (s.length() > 0) {
                out.add(out.length(), s.pop());
            }
        } else if (component instanceof BinaryTree) {
            BinaryTree<Object> t = (BinaryTree<Object>) component;
            for (Object label : t) {
                out.add(out.length(), label);
            }
            t.clear();
        } else if (component instanceof Tree) {
            Tree<Object> t = (Tree<Object>) component;
            for (Object label : t) {
                out.add(out.length(), label);
            }
            t.clear();
        } else {
            throw new IllegalArgumentException(
                    "Unsupported component: " + component.getClass().getName());
        }
        return out;
    }

    /** Moves the first {@code n} entries of {@code src} into a new Sequence. */
    private static Sequence<Object> take(Sequence<Object> src, int n) {
        Sequence<Object> out = new Sequence3<Object>();
        for (int i = 0; i < n; i++) {
            out.add(out.length(), src.remove(0));
        }
        return out;
    }

    /** Builds a randomly shaped binary tree from {@code data} (emptied).
    */
    private static BinaryTree<Object> buildBinaryTree(Sequence<Object> data) {
        BinaryTree<Object> t = new BinaryTree1<Object>();
        if (data.length() > 0) {
            Object root = data.remove(0);
            int leftCount = rnd.nextInt(data.length() + 1);
            BinaryTree<Object> left = buildBinaryTree(take(data, leftCount));
            BinaryTree<Object> right = buildBinaryTree(data);
            t.assemble(root, left, right);
        }
        return t;
    }

    /** Builds a randomly shaped general tree from {@code data} (emptied). */
    private static Tree<Object> buildTree(Sequence<Object> data) {
        Tree<Object> t = new Tree1<Object>();
        if (data.length() > 0) {
            Object root = data.remove(0);
            Sequence<Tree<Object>> children = new Sequence3<Tree<Object>>();
            while (data.length() > 0) {
                int chunk = 1 + rnd.nextInt(data.length());
                children.add(children.length(),
                        buildTree(take(data, chunk)));
            }
            t.assemble(root, children);
        }
        return t;
    }

    /**
     * Randomizes whatever component it is given.
     *
     * @param component
     *            a Queue, Stack, Sequence, BinaryTree, or Tree
     * @updates component
     * @requires component is one of the supported types
     * @ensures component's entries are randomly reordered
     * @throws IllegalArgumentException
     *             if the component type is not supported
     */
    @SuppressWarnings("unchecked")
    public static void randomize(Object component) {
        if (component instanceof Sequence) {
            randomize((Sequence<Object>) component);
        } else if (component instanceof Queue) {
            randomize((Queue<Object>) component);
        } else if (component instanceof Stack) {
            randomize((Stack<Object>) component);
        } else if (component instanceof BinaryTree) {
            randomize((BinaryTree<Object>) component);
        } else if (component instanceof Tree) {
            randomize((Tree<Object>) component);
        } else {
            throw new IllegalArgumentException(
                    "Unsupported component: " + component.getClass().getName());
        }
    }

    /** Quick demo. */
    public static void main(String[] args) {
        Queue<Integer> q = new Queue1L<Integer>();
        Stack<Integer> st = new Stack1L<Integer>();
        Sequence<Integer> sq = new Sequence3<Integer>();
        for (int i = 1; i <= 8; i++) {
            q.enqueue(i);
            st.push(i);
            sq.add(sq.length(), i);
        }

        BinaryTree<Integer> bt = new BinaryTree1<Integer>();
        BinaryTree<Integer> l = new BinaryTree1<Integer>();
        BinaryTree<Integer> r = new BinaryTree1<Integer>();
        l.assemble(2, new BinaryTree1<Integer>(), new BinaryTree1<Integer>());
        r.assemble(3, new BinaryTree1<Integer>(), new BinaryTree1<Integer>());
        bt.assemble(1, l, r);

        Tree<Integer> t = new Tree1<Integer>();
        Sequence<Tree<Integer>> kids = new Sequence3<Tree<Integer>>();
        for (int i = 2; i <= 3; i++) {
            Tree<Integer> k = new Tree1<Integer>();
            k.assemble(i, new Sequence3<Tree<Integer>>());
            kids.add(kids.length(), k);
        }
        t.assemble(1, kids);

        randomize((Object) q);
        randomize((Object) st);
        randomize((Object) sq);
        randomize((Object) bt);
        randomize((Object) t);

        System.out.println("Queue:      " + q);
        System.out.println("Stack:      " + st);
        System.out.println("Sequence:   " + sq);
        System.out.println("BinaryTree: " + bt);
        System.out.println("Tree:       " + t);

        Object mystery = scramble(q);
        System.out.println("Mystery:    " + mystery);
    }
}
