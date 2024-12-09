import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class IVFCalculatorServlet extends HttpServlet {
    private List<Map<String, String>> formulas;

    @Override
    public void init() throws ServletException {
        try {
        	
        	String csvFilePath = getServletContext().getRealPath("/WEB-INF/data/ivf_success_formulas.csv");
            formulas = IVFFormulaParser.loadFormulas(csvFilePath);
        } catch (IOException e) {
            throw new ServletException("Error loading IVF formulas", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Retrieve formula selection parameters
        String usingOwnEggs = req.getParameter("own_eggs").toUpperCase();
        String attemptedIVF = req.getParameter("past_ivf").toUpperCase();
        String reasonKnown = req.getParameter("infertility_reason_known").toUpperCase();

        // Retrieve calculation parameters
        int age = Integer.parseInt(req.getParameter("age"));
        double weight = Double.parseDouble(req.getParameter("weight"));
        String height = req.getParameter("height");
        boolean tubalFactor = Boolean.parseBoolean(req.getParameter("tubal_factor"));
        boolean maleFactor = Boolean.parseBoolean(req.getParameter("male_factor"));
        boolean endometriosis = Boolean.parseBoolean(req.getParameter("endometriosis"));
        boolean ovulatoryDisorder = Boolean.parseBoolean(req.getParameter("ovulatory_disorder"));
        boolean diminishedOvarianReserve = Boolean.parseBoolean(req.getParameter("diminished_ovarian_reserve"));
        boolean uterineFactor = Boolean.parseBoolean(req.getParameter("uterine_factor"));
        boolean otherReason = Boolean.parseBoolean(req.getParameter("other_reason"));
        boolean unexplained = Boolean.parseBoolean(req.getParameter("unexplained"));
        int priorPregnancies = Integer.parseInt(req.getParameter("prior_pregnancies"));
        int priorLiveBirths = Integer.parseInt(req.getParameter("live_births"));

        // BMI calculation
        if (height == null || !height.matches("\\d+'\\d+\"")) {
            throw new IllegalArgumentException("Height must be in the format feet'inches\" (e.g., 5'8\").");
        }
        String[] heightParts = height.split("'"); // Split at apostrophe
        int feet = Integer.parseInt(heightParts[0].trim());
        int inches = Integer.parseInt(heightParts[1].replace("\"", "").trim());
        
        double bmi = weight / Math.pow(feet * 12 + inches, 2) * 703;

        // Select the correct formula
        Map<String, String> selectedFormula = null;
        for (Map<String, String> formula : formulas) {
            if (formula.get("param_using_own_eggs").equals(usingOwnEggs) &&
                formula.get("param_attempted_ivf_previously").equals(attemptedIVF) &&
                formula.get("param_is_reason_for_infertility_known").equals(reasonKnown)) {
                selectedFormula = formula;
                break;
            }
        }
        
        if (selectedFormula != null) {
            // Print out the selected formula to the console
            System.out.println("Selected Formula:");
            for (Map.Entry<String, String> entry : selectedFormula.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } else {
            System.out.println("No formula selected.");
        }

        if (selectedFormula == null) {
            req.setAttribute("error", "No matching formula found");
            req.getRequestDispatcher("index.jsp").forward(req, res);
            return;
        }

        System.out.println("linear component"+selectedFormula.get("formula_age_linear_coefficient"));
        // Perform the formula calculation based on provided formula components
        double score = Double.parseDouble(selectedFormula.get("formula_intercept")) +
        	    Double.parseDouble(selectedFormula.get("formula_age_linear_coefficient")) * age +
        	    Double.parseDouble(selectedFormula.get("formula_age_power_coefficient")) * Math.pow(age, Double.parseDouble(selectedFormula.get("formula_age_power_factor"))) +
        	    Double.parseDouble(selectedFormula.get("formula_bmi_linear_coefficient")) * bmi +
        	    Double.parseDouble(selectedFormula.get("formula_bmi_power_coefficient")) * Math.pow(bmi, Double.parseDouble(selectedFormula.get("formula_bmi_power_factor"))) +
        	    
        	    // Replaced all with true/false columns
        	    (tubalFactor ? Double.parseDouble(selectedFormula.get("formula_tubal_factor_true_value")) : Double.parseDouble(selectedFormula.get("formula_tubal_factor_false_value"))) +
        	    (maleFactor ? Double.parseDouble(selectedFormula.get("formula_male_factor_infertility_true_value")) : Double.parseDouble(selectedFormula.get("formula_male_factor_infertility_false_value"))) +
        	    (endometriosis ? Double.parseDouble(selectedFormula.get("formula_endometriosis_true_value")) : Double.parseDouble(selectedFormula.get("formula_endometriosis_false_value"))) +
        	    (ovulatoryDisorder ? Double.parseDouble(selectedFormula.get("formula_ovulatory_disorder_true_value")) : Double.parseDouble(selectedFormula.get("formula_ovulatory_disorder_false_value"))) +
        	    (diminishedOvarianReserve ? Double.parseDouble(selectedFormula.get("formula_diminished_ovarian_reserve_true_value")) : Double.parseDouble(selectedFormula.get("formula_diminished_ovarian_reserve_false_value"))) +
        	    (uterineFactor ? Double.parseDouble(selectedFormula.get("formula_uterine_factor_true_value")) : Double.parseDouble(selectedFormula.get("formula_uterine_factor_false_value"))) +
        	    (otherReason ? Double.parseDouble(selectedFormula.get("formula_other_reason_true_value")) : Double.parseDouble(selectedFormula.get("formula_other_reason_false_value"))) +

        	    // Unexplained infertility using true/false value
        	    (unexplained ? Double.parseDouble(selectedFormula.get("formula_unexplained_infertility_true_value")) : Double.parseDouble(selectedFormula.get("formula_unexplained_infertility_false_value"))) +
        	    
        	    // Prior pregnancies and live births calculation
        	    Double.parseDouble(selectedFormula.get("formula_prior_pregnancies_0_value")) * (priorPregnancies == 0 ? 1 : (priorPregnancies == 1 ? 1 : 2)) + // Adjust based on priorPregnancies
        	    Double.parseDouble(selectedFormula.get("formula_prior_pregnancies_1_value")) * (priorPregnancies == 1 ? 1 : 0) + // Adjust based on priorPregnancies
        	    Double.parseDouble(selectedFormula.get("formula_prior_pregnancies_2+_value")) * (priorPregnancies >= 2 ? 1 : 0) + // Adjust for 2+ pregnancies
        	    Double.parseDouble(selectedFormula.get("formula_prior_live_births_0_value")) * (priorLiveBirths == 0 ? 1 : 0) + // Adjust based on priorLiveBirths
        	    Double.parseDouble(selectedFormula.get("formula_prior_live_births_1_value")) * (priorLiveBirths == 1 ? 1 : 0) + // Adjust based on priorLiveBirths
        	    Double.parseDouble(selectedFormula.get("formula_prior_live_births_2+_value")) * (priorLiveBirths >= 2 ? 1 : 0); // Adjust for 2+ live births

        // Calculate success rate
        double successRate = Math.exp(score) / (1 + Math.exp(score));
        String Sr=String.format("%.2f", successRate*100);
        req.setAttribute("successRate", Sr);
        req.getRequestDispatcher("result.jsp").forward(req, res);
    }

}
