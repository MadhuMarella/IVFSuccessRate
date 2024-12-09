import java.io.*;
import java.util.*;

public class IVFFormulaParser {

    // Method to load data from the CSV file
    public static List<Map<String, String>> loadFormulas(String CSV_FILE) throws IOException {
        List<Map<String, String>> formulas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(CSV_FILE));
        String line;
        
        // Read headers
        String[] headers = br.readLine().split(",", -1); // Handle empty columns
        
        // Read data rows
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",", -1); // Handle empty values
            Map<String, String> formula = new LinkedHashMap<>(); // Maintain insertion order
            
            for (int i = 0; i < headers.length; i++) {
                formula.put(headers[i].trim(), i < values.length ? values[i].trim() : "");
            }
            formulas.add(formula);
        }
        printFormulas(formulas);
        br.close();
        return formulas;
    }

    // Method to generate and print HTML table
    public static void printFormulas(List<Map<String, String>> formulas) {
        if (formulas.isEmpty()) {
            System.out.println("No data to display.");
            return;
        }

        // Extract and print headers
        Set<String> headers = formulas.get(0).keySet();
        System.out.println("<table border='1'>");
        System.out.println("<tr>");
        for (String header : headers) {
            System.out.print("<th>" + escapeHtml(header) + "</th>");
        }
        System.out.println("</tr>");

        // Print rows
        for (Map<String, String> row : formulas) {
            System.out.println("<tr>");
            for (String header : headers) {
                System.out.print("<td>" + escapeHtml(row.get(header)) + "</td>");
            }
            System.out.println("</tr>");
        }
        System.out.println("</table>");
    }

    // Utility method to escape HTML characters
    private static String escapeHtml(String value) {
        if (value == null) return "";
        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }

    public static void main(String[] args) {
        String CSV_FILE = "data/ivf_success_formulas.csv"; // Update with the correct file path
        
        try {
            List<Map<String, String>> formulas = loadFormulas(CSV_FILE);
            printFormulas(formulas);
        } catch (FileNotFoundException e) {
            System.err.println("CSV file not found: " + CSV_FILE);
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }
}
