import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

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
 * JUnit tests for {@code Randomizer}.
 */
public class RandomizerTest {

    /** Number of entries used in the larger tests. */
    private static final int N = 20;

    /**
     * Seeds the randomizer before every test.
     */
    @Before
    public void setUp() {
        Randomizer.setSeed(12345L);
    }

    /**
     * Returns the entries of any supported component as a sorted list.
     *
     * @param component
     *            a Queue, Stack, Sequence, BinaryTree, or Tree of Integers
     * @return the sorted entries
     */
    @SuppressWarnings("unchecked")
    private static List<Integer> sortedEntries(Object component) {
        List<Integer> out = new ArrayList<Integer>();
        Iterable<Integer> it = (Iterable<Integer>) component;
        for (Integer x : it) {
            out.add(x);
        }
        Collections.sort(out);
        return out;
    }

    /**
     * Returns the entries 1..n as a sorted list.
     *
     * @param n
     *            the number of entries
     * @return {@code [1, 2, ..., n]}
     */
    private static List<Integer> oneTo(int n) {
        List<Integer> out = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            out.add(i);
        }
        return out;
    }

    /**
     * Creates a sequence holding 1..n in order.
     *
     * @param n
     *            the number of entries
     * @return the sequence
     */
    private static Sequence<Integer> sequenceOf(int n) {
        Sequence<Integer> s = new Sequence3<Integer>();
        for (int i = 1; i <= n; i++) {
            s.add(s.length(), i);
        }
        return s;
    }

    /**
     * Creates a queue holding 1..n, front to back.
     *
     * @param n
     *            the number of entries
     * @return the queue
     */
    private static Queue<Integer> queueOf(int n) {
        Queue<Integer> q = new Queue1L<Integer>();
        for (int i = 1; i <= n; i++) {
            q.enqueue(i);
        }
        return q;
    }

    /**
     * Creates a stack holding 1..n with n on top.
     *
     * @param n
     *            the number of entries
     * @return the stack
     */
    private static Stack<Integer> stackOf(int n) {
        Stack<Integer> s = new Stack1L<Integer>();
        for (int i = 1; i <= n; i++) {
            s.push(i);
        }
        return s;
    }

    /**
     * Creates a one-node binary tree.
     *
     * @param label
     *            the label
     * @return the tree
     */
    private static BinaryTree<Integer> binaryLeaf(int label) {
        BinaryTree<Integer> t = new BinaryTree1<Integer>();
        t.assemble(label, new BinaryTree1<Integer>(),
                new BinaryTree1<Integer>());
        return t;
    }

    /**
     * Creates a 7-node perfect binary tree labeled 1..7.
     *
     * @return the tree
     */
    private static BinaryTree<Integer> binaryTreeOfSeven() {
        BinaryTree<Integer> left = new BinaryTree1<Integer>();
        left.assemble(2, binaryLeaf(4), binaryLeaf(5));
        BinaryTree<Integer> right = new BinaryTree1<Integer>();
        right.assemble(3, binaryLeaf(6), binaryLeaf(7));
        BinaryTree<Integer> root = new BinaryTree1<Integer>();
        root.assemble(1, left, right);
        return root;
    }

    /**
     * Creates a one-node general tree.
     *
     * @param label
     *            the label
     * @return the tree
     */
    private static Tree<Integer> treeLeaf(int label) {
        Tree<Integer> t = new Tree1<Integer>();
        t.assemble(label, new Sequence3<Tree<Integer>>());
        return t;
    }

    /**
     * Creates a general tree labeled 1..6: root 1 with children 2 (which has
     * children 4 and 5), 3, and 6.
     *
     * @return the tree
     */
    private static Tree<Integer> treeOfSix() {
        Sequence<Tree<Integer>> grandkids = new Sequence3<Tree<Integer>>();
        grandkids.add(0, treeLeaf(4));
        grandkids.add(1, treeLeaf(5));
        Tree<Integer> two = new Tree1<Integer>();
        two.assemble(2, grandkids);

        Sequence<Tree<Integer>> kids = new Sequence3<Tree<Integer>>();
        kids.add(0, two);
        kids.add(1, treeLeaf(3));
        kids.add(2, treeLeaf(6));
        Tree<Integer> root = new Tree1<Integer>();
        root.assemble(1, kids);
        return root;
    }

    /**
     * Sequence shuffle keeps the same entries and length.
     */
    @Test
    public void testSequencePreservesEntries() {
        Sequence<Integer> s = sequenceOf(N);
        Randomizer.randomize(s);
        assertEquals(N, s.length());
        assertEquals(oneTo(N), sortedEntries(s));
    }

    /**
     * Sequence shuffle actually changes the order.
     */
    @Test
    public void testSequenceChangesOrder() {
        Sequence<Integer> s = sequenceOf(N);
        Randomizer.randomize(s);
        assertFalse(sequenceOf(N).equals(s));
    }

    /**
     * Shuffling an empty sequence leaves it empty.
     */
    @Test
    public void testSequenceEmpty() {
        Sequence<Integer> s = new Sequence3<Integer>();
        Randomizer.randomize(s);
        assertEquals(0, s.length());
    }

    /**
     * Shuffling a one-entry sequence leaves it unchanged.
     */
    @Test
    public void testSequenceSingle() {
        Sequence<Integer> s = sequenceOf(1);
        Randomizer.randomize(s);
        assertEquals(sequenceOf(1), s);
    }

    /**
     * Queue shuffle keeps the same entries and length.
     */
    @Test
    public void testQueuePreservesEntries() {
        Queue<Integer> q = queueOf(N);
        Randomizer.randomize(q);
        assertEquals(N, q.length());
        assertEquals(oneTo(N), sortedEntries(q));
    }

    /**
     * Queue shuffle actually changes the order.
     */
    @Test
    public void testQueueChangesOrder() {
        Queue<Integer> q = queueOf(N);
        Randomizer.randomize(q);
        assertFalse(queueOf(N).equals(q));
    }

    /**
     * Shuffling an empty queue leaves it empty.
     */
    @Test
    public void testQueueEmpty() {
        Queue<Integer> q = new Queue1L<Integer>();
        Randomizer.randomize(q);
        assertEquals(0, q.length());
    }

    /**
     * Stack shuffle keeps the same entries and length.
     */
    @Test
    public void testStackPreservesEntries() {
        Stack<Integer> s = stackOf(N);
        Randomizer.randomize(s);
        assertEquals(N, s.length());
        assertEquals(oneTo(N), sortedEntries(s));
    }

    /**
     * Stack shuffle actually changes the order.
     */
    @Test
    public void testStackChangesOrder() {
        Stack<Integer> s = stackOf(N);
        Randomizer.randomize(s);
        assertFalse(stackOf(N).equals(s));
    }

    /**
     * Shuffling an empty stack leaves it empty.
     */
    @Test
    public void testStackEmpty() {
        Stack<Integer> s = new Stack1L<Integer>();
        Randomizer.randomize(s);
        assertEquals(0, s.length());
    }

    /**
     * Binary tree shuffle keeps the same labels, size, and height.
     */
    @Test
    public void testBinaryTreePreservesLabelsAndShape() {
        BinaryTree<Integer> t = binaryTreeOfSeven();
        int size = t.size();
        int height = t.height();
        Randomizer.randomize(t);
        assertEquals(size, t.size());
        assertEquals(height, t.height());
        assertEquals(oneTo(7), sortedEntries(t));
    }

    /**
     * Binary tree shuffle actually moves labels around over many tries.
     */
    @Test
    public void testBinaryTreeChangesLabels() {
        boolean changed = false;
        for (int i = 0; i < 10 && !changed; i++) {
            BinaryTree<Integer> t = binaryTreeOfSeven();
            Randomizer.randomize(t);
            changed = !binaryTreeOfSeven().equals(t);
        }
        assertTrue(changed);
    }

    /**
     * Shuffling an empty binary tree leaves it empty.
     */
    @Test
    public void testBinaryTreeEmpty() {
        BinaryTree<Integer> t = new BinaryTree1<Integer>();
        Randomizer.randomize(t);
        assertEquals(0, t.size());
    }

    /**
     * Shuffling a one-node binary tree leaves it unchanged.
     */
    @Test
    public void testBinaryTreeSingleNode() {
        BinaryTree<Integer> t = binaryLeaf(9);
        Randomizer.randomize(t);
        assertEquals(binaryLeaf(9), t);
    }

    /**
     * Tree shuffle keeps the same labels, size, and height.
     */
    @Test
    public void testTreePreservesLabelsAndShape() {
        Tree<Integer> t = treeOfSix();
        int size = t.size();
        int height = t.height();
        Randomizer.randomize(t);
        assertEquals(size, t.size());
        assertEquals(height, t.height());
        assertEquals(oneTo(6), sortedEntries(t));
    }

    /**
     * Tree shuffle actually moves labels around over many tries.
     */
    @Test
    public void testTreeChangesLabels() {
        boolean changed = false;
        for (int i = 0; i < 10 && !changed; i++) {
            Tree<Integer> t = treeOfSix();
            Randomizer.randomize(t);
            changed = !treeOfSix().equals(t);
        }
        assertTrue(changed);
    }

    /**
     * Shuffling an empty tree leaves it empty.
     */
    @Test
    public void testTreeEmpty() {
        Tree<Integer> t = new Tree1<Integer>();
        Randomizer.randomize(t);
        assertEquals(0, t.size());
    }

    /**
     * Shuffling a one-node tree leaves it unchanged.
     */
    @Test
    public void testTreeSingleNode() {
        Tree<Integer> t = treeLeaf(9);
        Randomizer.randomize(t);
        assertEquals(treeLeaf(9), t);
    }

    /**
     * The same seed gives the same shuffle.
     */
    @Test
    public void testSameSeedSameResult() {
        Queue<Integer> a = queueOf(N);
        Queue<Integer> b = queueOf(N);
        Randomizer.setSeed(42L);
        Randomizer.randomize(a);
        Randomizer.setSeed(42L);
        Randomizer.randomize(b);
        assertEquals(a, b);
    }

    /**
     * The Object overload dispatches to every supported component type.
     */
    @Test
    public void testObjectDispatch() {
        Sequence<Integer> s = sequenceOf(N);
        Queue<Integer> q = queueOf(N);
        Stack<Integer> st = stackOf(N);
        BinaryTree<Integer> bt = binaryTreeOfSeven();
        Tree<Integer> t = treeOfSix();

        Randomizer.randomize((Object) s);
        Randomizer.randomize((Object) q);
        Randomizer.randomize((Object) st);
        Randomizer.randomize((Object) bt);
        Randomizer.randomize((Object) t);

        assertEquals(oneTo(N), sortedEntries(s));
        assertEquals(oneTo(N), sortedEntries(q));
        assertEquals(oneTo(N), sortedEntries(st));
        assertEquals(oneTo(7), sortedEntries(bt));
        assertEquals(oneTo(6), sortedEntries(t));
    }

    /**
     * The Object overload rejects unsupported types.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testObjectDispatchUnsupported() {
        Randomizer.randomize((Object) "not a component");
    }

    /**
     * Scramble empties the source and returns a structure with the same
     * entries, for every source type.
     */
    @Test
    public void testScramblePreservesEntries() {
        Sequence<Integer> s = sequenceOf(N);
        Object fromSequence = Randomizer.scramble(s);
        assertEquals(0, s.length());
        assertEquals(oneTo(N), sortedEntries(fromSequence));

        Queue<Integer> q = queueOf(N);
        Object fromQueue = Randomizer.scramble(q);
        assertEquals(0, q.length());
        assertEquals(oneTo(N), sortedEntries(fromQueue));

        Stack<Integer> st = stackOf(N);
        Object fromStack = Randomizer.scramble(st);
        assertEquals(0, st.length());
        assertEquals(oneTo(N), sortedEntries(fromStack));

        BinaryTree<Integer> bt = binaryTreeOfSeven();
        Object fromBinary = Randomizer.scramble(bt);
        assertEquals(0, bt.size());
        assertEquals(oneTo(7), sortedEntries(fromBinary));

        Tree<Integer> t = treeOfSix();
        Object fromTree = Randomizer.scramble(t);
        assertEquals(0, t.size());
        assertEquals(oneTo(6), sortedEntries(fromTree));
    }

    /**
     * Scramble of an empty component returns an empty structure.
     */
    @Test
    public void testScrambleEmpty() {
        Object result = Randomizer.scramble(new Queue1L<Integer>());
        assertNotNull(result);
        assertTrue(sortedEntries(result).isEmpty());
    }

    /**
     * Over many scrambles, more than one kind of structure comes back.
     */
    @Test
    public void testScrambleReturnsVariedTypes() {
        List<Class<?>> seen = new ArrayList<Class<?>>();
        for (int i = 0; i < 100; i++) {
            Class<?> c = Randomizer.scramble(queueOf(5)).getClass();
            if (!seen.contains(c)) {
                seen.add(c);
            }
        }
        assertTrue(seen.size() > 1);
    }

    /**
     * Scramble rejects unsupported types.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testScrambleUnsupported() {
        Randomizer.scramble("not a component");
    }
}