<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>IVF Success Estimator Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .form-container {
            max-width: 700px;
            margin: 50px auto;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }
        .form-title {
            color: #4e73df;
            text-align: center;
            margin-bottom: 20px;
        }
        .btn-custom {
            background-color: #4e73df;
            color: #fff;
        }
        .btn-custom:hover {
            background-color: #3759b7;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="form-container">
            <h2 class="form-title">IVF Success Estimator Form</h2>
            <form method="post" action="IVFCalculatorServlet">
                <h4 class="text-primary">User Input - Formula Selection Parameters</h4>
                
                <div class="mb-3">
			        <label for="own-eggs" class="form-label">Using Own Eggs:</label>
			        <select id="own-eggs" name="own_eggs" class="form-select" required onchange="togglePastIVF()">
			            <option value="true">TRUE</option>
			            <option value="false">FALSE</option>
			        </select>
			    </div>
			    
			    <div class="mb-3" id="past-ivf-container">
			        <label for="past-ivf" class="form-label">Used IVF in the Past:</label>
			        <select id="past-ivf" name="past_ivf" class="form-select" required onchange="checkNA()">
			            <option value="true">TRUE</option>
			            <option value="false">FALSE</option>
			            <option value="na">N/A</option>
			        </select>
			    </div>
                
                <div class="mb-3">
                    <label for="infertility-reason-known" class="form-label">Knows Infertility Reason:</label>
                    <select id="infertility-reason-known" name="infertility_reason_known" class="form-select" required>
                        <option value="true">TRUE</option>
                        <option value="false">FALSE</option>
                    </select>
                </div>
                <h4 class="text-primary">Formula Calculation Parameters</h4>
                <div class="mb-3">
                    <label for="age" class="form-label">Age (20-50):</label>
                    <input type="number" id="age" name="age" class="form-control" min="20" max="50" required>
                </div>
                <div class="mb-3">
                    <label for="weight" class="form-label">Weight (lbs, 80-300):</label>
                    <input type="number" id="weight" name="weight" class="form-control" min="80" max="300" required>
                </div>
                <div class="mb-3">
                    <label for="height" class="form-label">Height (e.g., 5'8"):</label>
                    <input type="text" id="height" name="height" class="form-control" pattern="^\d+'\d+&quot;$" required>
                </div>
                <h4 class="text-primary">Reasons for Infertility</h4>
                  
                 <div class="mb-3">
				    <label for="tubal_factor" class="form-label">Tubal Factor:</label>
				    <select id="tubal_factor" name="tubal_factor" class="form-select" required>
				        <option value="true" selected>TRUE</option>
				        <option value="false">FALSE</option>
				    </select>
				</div>

				<div class="mb-3">
				    <label for="male_factor" class="form-label">Male Factor Infertility:</label>
				    <select id="male_factor" name="male_factor" class="form-select" required>
				        <option value="true" selected>TRUE</option>
				        <option value="false">FALSE</option>
				    </select>
				</div>
                 
                <div class="mb-3">
                    <label for="endometriosis" class="form-label">Endometriosis:</label>
                    <select id="endometriosis" name="endometriosis" class="form-select" required>
                        <option value="true" selected>TRUE</option>
                        <option value="false">FALSE</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="ovulatory-disorder" class="form-label">Ovulatory Disorder:</label>
                    <select id="ovulatory-disorder" name="ovulatory_disorder" class="form-select" required>
                        <option value="true" selected>TRUE</option>
                        <option value="false">FALSE</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="diminished-ovarian-reserve" class="form-label">Diminished Ovarian Reserve:</label>
                    <select id="diminished-ovarian-reserve" name="diminished_ovarian_reserve" class="form-select" required>
                        <option value="true">TRUE</option>
                        <option value="false" selected>FALSE</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="uterine-factor" class="form-label">Uterine Factor:</label>
                    <select id="uterine-factor" name="uterine_factor" class="form-select" required>
                        <option value="true">TRUE</option>
                        <option value="false" selected>FALSE</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="other-reason" class="form-label">Other Infertility Reason:</label>
                    <select id="other-reason" name="other_reason" class="form-select" required>
                        <option value="true">TRUE</option>
                        <option value="false" selected>FALSE</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="unexplained" class="form-label">Unexplained Infertility:</label>
                    <select id="unexplained" name="unexplained" class="form-select" required>
                        <option value="true">TRUE</option>
                        <option value="false" selected>FALSE</option>
                    </select>
                </div>
                <h4 class="text-primary">Pregnancy History</h4>
                <div class="mb-3">
                    <label for="prior-pregnancies" class="form-label">Number of Prior Pregnancies (Gravida):</label>
                    <input type="number" id="prior-pregnancies" name="prior_pregnancies" class="form-control" min="0" required>
                </div>
                <div class="mb-3">
                    <label for="live-births" class="form-label">Number of Live Births:</label>
                    <input type="number" id="live-births" name="live_births" class="form-control" min="0" required>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-custom">Submit</button>
                </div>
            </form>
        </div>
    </div>
    
    
    <script>
    function togglePastIVF() {
        var ownEggsSelect = document.getElementById('own-eggs');
        var ownEggsValue = ownEggsSelect.value;
        var pastIVFSelect = document.getElementById('past-ivf');
        var pastIVFValue = pastIVFSelect.value;

        // If "Using Own Eggs" is TRUE
        if (ownEggsValue === 'true') {
            // Check if the past IVF field is set to "N/A"
            if (pastIVFValue === 'na') {
                alert(
                    "If 'Used IVF in the Past' is set to N/A, 'Using Own Eggs' cannot be TRUE. Changing it back to FALSE."
                );
                ownEggsSelect.value = 'false'; // Automatically set "Using Own Eggs" to FALSE
            }
        }
    }

    function checkNA() {
        var pastIVFSelect = document.getElementById('past-ivf');
        var pastIVFValue = pastIVFSelect.value;
        var ownEggsSelect = document.getElementById('own-eggs');

        // If "Used IVF in the Past" is N/A
        if (pastIVFValue === 'na') {
            alert(
                "If N/A, the answer to this question is not relevant in the formula selection. Automatically setting 'Using Own Eggs' to FALSE."
            );
            ownEggsSelect.value = 'false'; // Automatically set "Using Own Eggs" to FALSE
        }
    }

    // Call the function on page load to ensure correct behavior
    window.onload = function () {
        togglePastIVF();
    };


</script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
