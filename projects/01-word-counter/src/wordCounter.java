import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Counts the words in a text file and writes the counts to an HTML table,
 * sorted alphabetically. A word is a run of letters and digits.
 */
public final class wordCounter {

    /**
     * Private constructor so this class cannot be instantiated.
     */
    private wordCounter() {
    }

    /**
     * Compares strings alphabetically, ignoring case.
     */
    private static final class StringLT implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }
    }

    /**
     * Reports whether a character is a letter or digit.
     *
     * @param c
     *            the character to check
     * @return true if {@code c} is A-Z, a-z, or 0-9
     */
    private static boolean isWordChar(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')
                || (c >= '0' && c <= '9');
    }

    /**
     * Returns the run of word characters, or the run of non-word characters,
     * that starts at the given position.
     *
     * @param text
     *            the string to read from
     * @param position
     *            the index to start at, which must be less than the length
     * @return the word or separator starting at {@code position}
     */
    private static String nextWordOrSeparator(String text, int position) {
        boolean inWord = isWordChar(text.charAt(position));
        int end = position + 1;
        while (end < text.length() && isWordChar(text.charAt(end)) == inWord) {
            end++;
        }
        return text.substring(position, end);
    }

    /**
     * Counts how many times each word appears in the input.
     *
     * @param in
     *            the input stream to read from
     * @param counts
     *            the map that receives each word and its count
     */
    private static void countWords(SimpleReader in,
            Map<String, Integer> counts) {
        while (!in.atEOS()) {
            String line = in.nextLine();
            int position = 0;
            while (position < line.length()) {
                String item = nextWordOrSeparator(line, position);
                if (isWordChar(item.charAt(0))) {
                    if (counts.hasKey(item)) {
                        Map.Pair<String, Integer> old = counts.remove(item);
                        counts.add(old.key(), old.value() + 1);
                    } else {
                        counts.add(item, 1);
                    }
                }
                position += item.length();
            }
        }
    }

    /**
     * Returns the words in the map in alphabetical order.
     *
     * @param counts
     *            the map of words to counts
     * @return the keys of {@code counts}, sorted
     */
    private static Queue<String> sortedWords(Map<String, Integer> counts) {
        Queue<String> words = new Queue1L<>();
        for (Map.Pair<String, Integer> pair : counts) {
            words.enqueue(pair.key());
        }
        words.sort(new StringLT());
        return words;
    }

    /**
     * Writes the words and their counts as an HTML table.
     *
     * @param out
     *            the output stream to write to
     * @param inputFile
     *            the name of the file that was counted
     * @param counts
     *            the map of words to counts
     * @param words
     *            the words from {@code counts}, in the order to print them
     */
    private static void outputTable(SimpleWriter out, String inputFile,
            Map<String, Integer> counts, Queue<String> words) {
        out.println("<html>");
        out.println("<body>");
        out.println("<h2>Word counts in " + inputFile + "</h2>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>Words: </th>");
        out.println("<th>Counts: </th>");
        out.println("</tr>");
        for (String word : words) {
            out.println("<tr>");
            out.println("<td>" + word + "</td>");
            out.println("<td>" + counts.value(word) + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Asks for the input and output file names, then writes the word counts
     * of the input file to the output file.
     *
     * @param args
     *            the command line arguments; unused
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter the name of the input text file: ");
        String inputFile = in.nextLine();
        out.print("Enter the name of the output file: ");
        String outputFile = in.nextLine();

        Map<String, Integer> counts = new Map1L<>();
        SimpleReader fileIn = new SimpleReader1L(inputFile);
        countWords(fileIn, counts);

        SimpleWriter fileOut = new SimpleWriter1L(outputFile);
        outputTable(fileOut, inputFile, counts, sortedWords(counts));

        fileIn.close();
        fileOut.close();
        in.close();
        out.close();
    }

}