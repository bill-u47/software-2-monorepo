import java.util.Iterator;

import org.junit.Test;

import components.queue.Queue;
import components.queue.Queue1L;
import components.queue.Queue2;
import components.set.Set;
import components.set.SetSecondary;

/**
 * {@code Set} represented as a {@code Queue} of elements with implementations
 * of primary methods.
 *
 * @param <T>
 *            type of {@code Set} elements
 * @convention |$this.elements| = |entries($this.elements)|
 * @correspondence this = entries($this.elements)
 */
public class Set2<T> extends SetSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Elements included in {@code this}.
     */
    private Queue<T> elements;

    /**
     * Finds {@code x} in {@code q} and, if such exists, moves it to the front
     * of {@code q}.
     *
     * @param <T>
     *            type of {@code Queue} entries
     * @param q
     *            the {@code Queue} to be searched
     * @param x
     *            the entry to be searched for
     * @updates q
     * @ensures <pre>
     * perms(q, #q)  and
     * if <x> is substring of q
     *  then <x> is prefix of q
     * </pre>
     */
    private static <T> void moveToFront(Queue<T> q, T x) {
        assert q != null : "Violation of: q is not null";
        Queue<T> next = new Queue2<>();
        next.transferFrom(q);
        while (next.length() != 0){
            if (next.dequeue() == x) {
                q.flip();
                q.enqueue(x);
                q.flip();
            }

        }
    }

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.elements = new Queue1L<T>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Set2() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @SuppressWarnings("unchecked")
    @Override
    public final Set<T> newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(Set<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Set2<?> : ""
                + "Violation of: source is of dynamic type Set2<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Set2<?>, and
         * the ? must be T or the call would not have compiled.
         */
        Set2<T> localSource = (Set2<T>) source;
        this.elements = localSource.elements;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void add(T x) {
        assert x != null : "Violation of: x is not null";
        assert !this.contains(x) : "Violation of: x is not in this";
        Set<T> temp = this.newInstance();
        temp.transferFrom(this);
        temp.add(x);

        this.transferFrom(temp);
    }

    @Override
    public final T remove(T x) {
        assert x != null : "Violation of: x is not null";
        assert this.contains(x) : "Violation of: x is in this";

        Set<T> temp = this.newInstance();
        temp.transferFrom(this);
        temp.remove(x);

        // This line added just to make the component compilable.
        return x;
    }

    @Test 
    public final T removeAny() {
        assert this.size() > 0 : "Violation of: |this| > 0";

        Set<T> randomQ = this.newInstance();
        randomQ.transferFrom(this);
        Iterator<T> it = randomQ.iterator();
        T x = null;
        while (it.hasNext()){
            x = it.next();
        }

        randomQ.remove(x);

        // This line added just to make the component compilable.
        return x;
    }

    @Override
    public final boolean contains(T x) {
        boolean containing = false;
        Set<T> containString = this.newInstance();
        containString.transferFrom(this);

        Iterator<T> it = containString.iterator();
        T next = null;
        while (it.hasNext()) {
            next = it.next();
            if (next.equals(x)) {
                containing = true;
            }
        }
        return containing;
    }

    @Override
    public final int size() {
        Set<T> sizeSet = this.newInstance();
        Iterator<T> it = sizeSet.iterator();
        int i = 0;
        while (it.hasNext()) {
            it.next();
            i = i + 1;
        }
        return i;
    }

    @Override
    public final Iterator<T> iterator() {
        return this.elements.iterator();
    }

}
