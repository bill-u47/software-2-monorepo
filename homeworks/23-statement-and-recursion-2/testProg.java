import components.simplewriter.SimpleWriter1L;
import components.simplewriter.SimpleWriter;
import components.statement.Statement;
import components.statement.StatementKernel.Condition;
import components.tree.Tree;
import components.tree.TreeSecondary;
import components.tree.TreeKernel;
import components.sequence.Sequence1L;
import components.sequence.Sequence;


/**
 * Refactors the given {@code Statement} so that every IF_ELSE statement
 * with a negated condition (NEXT_IS_NOT_EMPTY, NEXT_IS_NOT_ENEMY,
 * NEXT_IS_NOT_FRIEND, NEXT_IS_NOT_WALL) is replaced by an equivalent
 * IF_ELSE with the opposite condition and the "then" and "else" BLOCKs
 * switched. Every other statement is left unmodified.
 *
 * @param s
 *            the {@code Statement}
 * @updates s
 * @ensures <pre>
 * s = [#s refactored so that IF_ELSE statements with "not"
 *   conditions are simplified so the "not" is removed]
 * </pre>
 */
public static void wrapIfStmt(Statement s) {
        switch (s.kind()) {
            case BLOCK: {
                int len = s.lengthOfBlock();
                for (int i = 0; i < len; i++) {
                    Statement c = s.removeFromBlock(i);
                    wrapIfStmt(c);
                    s.addToBlock(i, c);
                }
                break;
            }
            case CALL: {
                e
                break;
            }
            default: {

            break;
            }
    }
}
public final int treeCursion(Tree<T> t) {
    Sequence<Tree<T>> children = new Sequence1L<Tree<T>>();

    return count;
}


public static void main(String[] args) {
    SimpleWriter out = new SimpleWriter1L();
    out.println("Hello world");
    out.close();
}