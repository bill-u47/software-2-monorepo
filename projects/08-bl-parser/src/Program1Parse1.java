import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Dennis Huang
 *
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires <pre>
     * [<"INSTRUCTION"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an instruction string is a proper prefix of #tokens]  and
     *    [the beginning name of this instruction equals its ending name]  and
     *    [the name of this instruction does not equal the name of a primitive
     *     instruction in the BL language] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to the block string that is the body of
     *          the instruction string at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [report an appropriate error message to the console and terminate client]
     * </pre>
     */
    private static String parseInstruction(Queue<String> tokens,
            Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
                + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";

        // TODO - fill in body
        SimpleWriter out = new SimpleWriter1L();
        String[] primitives = { "move", "turnleft", "turnright", "infect",
                "skip" };

        tokens.dequeue();
        String instructionName = tokens.dequeue();
        tokens.dequeue();
        body.parseBlock(tokens);
        tokens.dequeue();
        String endInstructionName = tokens.dequeue();

        for (String primitive : primitives) {
            assert instructionName != primitive : "Violation of: Instruction "
                    + "name must not equal name of primitive instruction";
        }

        assert instructionName.equals(endInstructionName) : "Violation of: "
                + "Beginning Instruction name must match ending Instruction name";

        // This line added just to make the program compilable.
        return instructionName;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";
        Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        // TODO - fill in body

        String program = tokens.dequeue();
        assert tokens.front().equals(
                "PROGRAM") : "Violation of: Program must start with PROGRAM";

        String programName = tokens.dequeue();
        String is = tokens.dequeue(); //Should dequeue "IS"
        assert tokens.front().equals("IS") : "Violation of: Expected IS";

        Map<String, Statement> context = this.newContext();

        while (tokens.front().equals("INSTRUCTION")) {
            Statement instructionBody = this.newBody();
            String instructionName = parseInstruction(tokens, instructionBody);
            if (!context.hasKey(instructionName)) {
                context.add(instructionName, instructionBody);
            }
        }

        String begin = tokens.dequeue();
        assert tokens.front().equals("BEGIN") : "Violation of: Expected BEGIN";

        Statement block = this.newBody();
        block.parseBlock(tokens);

        String end = tokens.dequeue();
        assert tokens.front().equals("END");

        String endName = tokens.dequeue();
        assert endName.equals(
                programName) : "Violation of: Beginning program name must match ending program name";

    }

    /*
     * Main test method -------------------------------------------------------
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get input file name
         */
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }

}
