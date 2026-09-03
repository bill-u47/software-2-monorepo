
import components.map.Map;
import components.map.Map1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
/**
 *
 * @class wordCounter
 *  I wanna get rid of the javadoc comment warning augh
 */
public class wordCounter {



    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        out.print("Input name of input text file:");
        String inputFile = in.nextLine();
        out.print("Enter the name of the output file:");
        String outputFile = in.nextLine();
        Map<String, Integer> newMap = new Map1L<>();

        SimpleReader fileReader = new SimpleReader1L(inputFile);
        while (!fileReader.atEOS()) {
            String line = fileReader.nextLine();
            String[] words = line.split("[^A-Za-z0-9]+");
            //it says that if item is not a character, and there's 1+ of them in a row,
            //then split on it. ^ negates the class, it doesn't split on A-Z, a-z, or 0-9,
            //and the + checks to see if there's 1 or more of the item that needs split.
            for (String item : words) {
                if (item.isEmpty()) {
                    continue;
                }
                if (newMap.hasKey(item)) {
                    int oldCount = newMap.value(item);
                    newMap.remove(item);
                    newMap.add(item, oldCount + 1);
                } else {
                    newMap.add(item, 1);
                }
            }
        }
        out.print(newMap);
        SimpleWriter fileOut = new SimpleWriter1L(outputFile);
        fileOut.println("<html>");
        fileOut.println("<body>");
        fileOut.println("<h2>Word counts in " + inputFile + "</h2>");
        fileOut.println("<table>");
        fileOut.println("<tr>");
        fileOut.println("<th>" + "Words: " + "</th>");
        fileOut.println("<th>" + "Counts: " + "</th>");
        fileOut.println("</tr>");
        for (Map.Pair<String, Integer> pair : newMap) {
            fileOut.println("<tr>");
            fileOut.println("<td>" + pair.key() + "</td>");
            fileOut.println("<td>" + pair.value() + "</td>");
            fileOut.println("</tr>");
        }

        fileOut.println("</table>");




        in.close();
        out.close();
        fileReader.close();
        fileOut.close();
    }
}
