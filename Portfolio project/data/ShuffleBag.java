import components.queue.Queue;
import components.queue.Queue1L;
import components.sequence.Sequence;
import components.sequence.Sequence3;

/**
 * USE CASE 2 (as a representation): Randomizer used INSIDE the implementation
 * of a new abstract data type.
 *
 * <p>
 * A {@code ShuffleBag} hands out random items.
 *  every item is handed out exactly once per "round" before any item
 * repeats (think tetris).
 *
 * <p>
 * Abstract model: {@code this} = (items: sequence of T, ...), with operation
 * {@code next()} returning an element of items.
 *
 * <p>
 * Representation: the current round is a {@code Queue} that is refilled and
 * reshuffled by {@code Randomizer.randomize(Queue)} whenever it runs out.
 *
 * <p>
 * Convention: {@code |items| > 0}, and {@code round} holds a permutation of
 * some suffix of a shuffle of {@code items}.
 *
 * <p>
 * Correspondence: the abstract value is {@code items}; {@code round} is purely
 * for debugging/denoting and is invisible to the client.
 *
 * @param <T>
 *            item type
 */
public final class ShuffleBag<T> {

    /** Full set of items. */
    private final Sequence<T> items;

    /** Items needing to be handed out still. */
    private final Queue<T> round;

    /**
     * Creates a bag over the given items.
     *
     * @param source
     *            the items (left unchanged)
     * @requires source.length() > 0
     */
    public ShuffleBag(Sequence<T> source) {
        assert source.length() > 0 : "Violation of: source.length() > 0";
        this.items = new Sequence3<T>();
        for (int i = 0; i < source.length(); i++) {
            this.items.add(i, source.entry(i));
        }
        this.round = new Queue1L<T>();
    }

    /**
     * Returns the next item. Every item appears exactly once between refills.
     *
     * @return the next random item
     */
    public T next() {
        if (this.round.length() == 0) {
            for (int i = 0; i < this.items.length(); i++) {
                this.round.enqueue(this.items.entry(i));
            }
            Randomizer.randomize(this.round);
        }
        return this.round.dequeue();
    }

    /**
     * Number of items left before the next shuffle.
     * @return - returns # of items.
     */
    public int remainingInRound() {
        return this.round.length();
    }

    /**
     * it's the tetris pieces.
     * @param args
     */
    public static void main(String[] args) {
        Sequence<String> pieces = new Sequence3<String>();
        String[] names = { "I", "O", "T", "S", "Z", "J", "L" };
        for (int i = 0; i < names.length; i++) {
            pieces.add(i, names[i]);
        }

        ShuffleBag<String> bag = new ShuffleBag<String>(pieces);

        System.out.println("Three rounds from a ShuffleBag (7 pieces each):");
        for (int round = 1; round <= 3; round++) {
            StringBuilder sb = new StringBuilder("  Round " + round + ": ");
            for (int i = 0; i < names.length; i++) {
                sb.append(bag.next()).append(' ');
            }
            System.out.println(sb);
        }
        System.out.println("Each round contains every piece exactly once.\n");

        java.util.Random naive = new java.util.Random();
        StringBuilder sb = new StringBuilder("Naive random picks:     ");
        for (int i = 0; i < 21; i++) {
            sb.append(names[naive.nextInt(names.length)]).append(' ');
        }
        System.out.println(sb);
        System.out.println("(droughts and streaks are possible; the bag prevents them)");
    }
}
