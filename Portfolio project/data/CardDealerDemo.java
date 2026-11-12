import components.queue.Queue;
import components.queue.Queue1L;
import components.sequence.Sequence;
import components.sequence.Sequence3;
import components.stack.Stack;
import components.stack.Stack1L;

/**
 * USE CASE 1 (standalone): Randomizer used directly as a utility.
 *
 * <p>
 * A card dealer. A 52-card deck is a {@code Stack}; we shuffle it with
 * {@code Randomizer.randomize}, deal hands into {@code Queue}s, and then use
 * {@code Randomizer.setSeed} to show that a shuffle can be reproduced exactly.
 *
 * Basically just for gambling. Did you expect different?
 *
 * <p>
 * Here Randomizer is just a tool the program calls; nothing about the
 * program's own data representation depends on it.
 */
public final class CardDealerDemo {

    /**
     * for instantiation reasons.
     */
    private CardDealerDemo() {
    }

    /**
     * Creates a new deck.
     * @return - returns a new deck
     */
    private static Stack<String> newDeck() {
        String[] suits = { "S", "H", "D", "C" };
        String[] ranks = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "J", "Q", "K" };
        Stack<String> deck = new Stack1L<String>();
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.push(rank + suit);
            }
        }
        return deck;
    }

    /**
     * Deals cars to each player's hand.
     * @param deck - deck of cards
     * @param players - num of players
     * @param cards - num of cards
     * @return - returns the hand for a player
     */
    private static Sequence<Queue<String>> deal(Stack<String> deck,
            int players, int cards) {
        Sequence<Queue<String>> hands = new Sequence3<Queue<String>>();
        for (int p = 0; p < players; p++) {
            hands.add(p, new Queue1L<String>());
        }
        for (int c = 0; c < cards; c++) {
            for (int p = 0; p < players; p++) {
                Queue<String> hand = hands.remove(p);
                hand.enqueue(deck.pop());
                hands.add(p, hand);
            }
        }
        return hands;
    }

    /**
     *
     * @param hands - The cards a player has
     */
    private static void show(Sequence<Queue<String>> hands) {
        for (int p = 0; p < hands.length(); p++) {
            System.out.println("  Player " + (p + 1) + ": " + hands.entry(p));
        }
    }

    /**
     * You ever wanted to be a card dealer? Here's your chance.
     * @param args
     */
    public static void main(String[] args) {

        Stack<String> deck = newDeck();
        Randomizer.randomize(deck);
        System.out.println("Random deal (different every run):");
        show(deal(deck, 4, 5));
        System.out.println("  Cards left in deck: " + deck.length());

        //this is what the seed is for - to reproduce shuffles
        Randomizer.setSeed(42);
        Stack<String> a = newDeck();
        Randomizer.randomize(a);
        Sequence<Queue<String>> first = deal(a, 4, 5);

        Randomizer.setSeed(42);
        Stack<String> b = newDeck();
        Randomizer.randomize(b);
        Sequence<Queue<String>> second = deal(b, 4, 5);

        System.out.println("\nSeed 42, run 1:");
        show(first);
        System.out.println("Seed 42, run 2 (should be identical):");
        show(second);

        boolean same = true;
        for (int p = 0; p < 4; p++) {
            same &= first.entry(p).toString().equals(second.entry(p).toString());
        }
        System.out.println("Reproducible: " + same);
    }
}
