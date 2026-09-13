import static org.junit.Assert.assertEquals;
import java.util.Iterator;
import org.junit.Test;
import java.util.random.*;
import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    public Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    public Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }
    /**
     * adds together a base Set<String> (this) and a specified argument
     * @ensures addSet = #this + args
     *
     * @params args
     *      the set entries
     */
    @Test
    public final Set<String> add(String... args){
        Set<String> addSet = this.createFromArgsTest();
        for (String arg : args) {
            addSet.add(arg);
        }
        return addSet;

    }
    /**
     * removes a specified argument from #this
     * @ensures removeSet = #this - args
     *
     * @params args
     *      the set entries
     */
    @Test
    public final Set<String> remove(String... args){
        Set<String> removeSet = this.createFromArgsTest();
        for (String arg : args) {
            removeSet.remove(arg);
        }
        return removeSet;

    }
    /**
     * removes a random item from #this
     * @ensures this = #this - random arg
     *
     * @params args
     *      the set entries
     */
    @Test
    public final T removeAny(){
        Set<String> randomSet = this.createFromArgsTest();
        Iterator<String> it = randomSet.iterator();
        Random rand = new Random();
        int randomInt = rand.nextInt();
        while (i < randomInt && randomSet.hasNext()){
            T x = it.Next()
            i = i + 1;
        }
        this.remove(x);

        return x;


    }
    
    /**

     * @params args
     *      the set entries
     */
    @Test
    public final boolean contains(String... args){
        boolean containing = false;
        Set<String> containString = this.createFromArgsTest();
        for (String arg : args) {
            if (containString.contains(arg)) {
                containing = true;
            }

        }
        return containing;

    }
    
    /**
     *
     * @params args
     *      the set entries
     */
    @Test
    public final int size(String... args){
        Set<String> sizeSet = this.createFromArgsTest();
        Iterator it = sizeSet.iterator();
        int i = 0;
        while (it.hasNext()) {
            it.next();
            i = i + 1;
        }
        return i;
    }


    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size

}
