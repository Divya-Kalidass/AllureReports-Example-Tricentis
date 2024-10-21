

    package org.example.DemoWebShopTests

    import org.testng.ITestContext;
    import org.testng.ITestListener;
    import org.testng.ITestResult;
    import org.testng.Reporter;

    import java.io.BufferedWriter;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.text.SimpleDateFormat;
    import java.util.ArrayList;
    import java.util.Date;
    import java.util.List;

    public class TestResultListener3 implements ITestListener {

        // A list to hold test result data
        private List<TestResult> results = new ArrayList<>();

        // Helper method to format duration in HH:mm:ss format
        private String formatDuration(long millis) {
            long seconds = (millis / 1000) % 60;
            long minutes = (millis / (1000 * 60)) % 60;
            long hours = millis / (1000 * 60 * 60);
            return String.format('%02d:%02d:%02d', hours, minutes, seconds);
        }

        @Override
        public void onTestStart(ITestResult result) {
            // Capture test start time
            long startTime = System.currentTimeMillis();
            result.setAttribute("startTime", startTime);  // Storing start time in the result
            Reporter.log("Test Started: " + result.getMethod().getMethodName() + " at " + startTime);
        }

        @Override
        public void onTestSuccess(ITestResult result) {
            // Capture test stop time and calculate duration
            long stopTime = System.currentTimeMillis();
            long duration = stopTime - (long) result.getAttribute("startTime");

            // Get suite name
            String suiteName = result.getTestContext().getSuite().getName();

            // Convert start and stop times to human-readable format
            String startTimeStr = formatTime((long) result.getAttribute("startTime"));
            String stopTimeStr = formatTime(stopTime);

            // Convert duration to HH:mm:ss format
            String durationStr = formatDuration(duration);

            results.add(new TestResult(result.getMethod().getMethodName(), startTimeStr, stopTimeStr, durationStr, suiteName, "Test Passed Successfully", "PASS"));
            Reporter.log("Test " + result.getMethod().getMethodName() + " passed in " + durationStr + ".");
        }

        @Override
        public void onTestFailure(ITestResult result) {
            // Capture test stop time and calculate duration
            long stopTime = System.currentTimeMillis();
            long duration = stopTime - (long) result.getAttribute("startTime");

            // Capture the failure reason (exception message or stack trace)
            Throwable throwable = result.getThrowable();
            String output = throwable != null ? throwable.toString() : "Unknown Failure";

            // Get suite name
            String suiteName = result.getTestContext().getSuite().getName();

            // Convert start and stop times to human-readable format
            String startTimeStr = formatTime((long) result.getAttribute("startTime"));
            String stopTimeStr = formatTime(stopTime);
            // Convert duration to HH:mm:ss format
            String durationStr = formatDuration(duration);

            results.add(new TestResult(result.getMethod().getMethodName(), startTimeStr, stopTimeStr, durationStr, suiteName, output, "FAIL"));
            Reporter.log("Test " + result.getMethod().getMethodName() + " failed in " + durationStr + ". Reason: " + output);
        }

        @Override
        public void onTestSkipped(ITestResult result) {
            // Capture test stop time and calculate duration
            long stopTime = System.currentTimeMillis();
            long duration = stopTime - (long) result.getAttribute("startTime");

            // Get suite name
            String suiteName = result.getTestContext().getSuite().getName();

            // Convert start and stop times to human-readable format
            String startTimeStr = formatTime((long) result.getAttribute("startTime"));
            String stopTimeStr = formatTime(stopTime);

            String durationStr = formatDuration(duration);

            results.add(new TestResult(result.getMethod().getMethodName(), startTimeStr, stopTimeStr, durationStr, suiteName, "Test Skipped", "SKIPPED"));
        }

        @Override
        public void onFinish(ITestContext context) {
            // Generate the HTML report once all tests are finished
            generateHtmlReport();
        }

        private void generateHtmlReport() {
            StringBuilder html = new StringBuilder();
            html.append("<!DOCTYPE html>")
                    .append("<html lang='en'>")
                    .append("<head><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'>")
                    .append("<title>BCT Test Execution Report</title><style>")
                    .append("body { font-family: 'Arial', sans-serif; background: linear-gradient(135deg, #6c5ce7, #00cec9); margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; height: 100vh; position: relative; }")
                    .append(".container { width: 90%; max-width: 1200px; background: linear-gradient(135deg, #5545ba, #225d5c); border-radius: 15px; box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1); padding: 40px; color: #fff; text-align: center; margin-top: 20px; }")
                    .append("h1 { font-size: 2.5rem; margin-bottom: 20px; color: #fff; text-transform: uppercase; }")
                    .append(".welcome-heading { position: absolute; top: 20px; text-align:center; font-size: 2.5rem; color: #fff; }")
                    .append("table { width: 100%; margin-top: 20px; border-spacing: 0; border-radius: 10px; overflow: hidden; background-color: #1f2a47; }")
                    .append("thead { background: rgba(255, 255, 255, 0.1); font-size: 1.2rem; letter-spacing: 1px; text-transform: uppercase; color: #fff; }")
                    .append("th { padding: 10px; text-align: center; font-weight: 500; border-bottom: 2px solid #4e5b6e; transition: all 0.3s ease; }")
                    .append("td { padding: 10px; text-align: center; }")  // Increase padding for table cells
                    .append("tr { height: 50px; }")  // Set a specific height for table rows
                    .append("tr:nth-child(even) { background-color: rgba(255, 255, 255, 0.05); }")
                    .append("tr:hover { background-color: rgba(255, 255, 255, 0.2); }")
                    .append(".image-container { position: absolute; top: 20px; right: 20px; }")
                    .append(".image-container img { width: 90px; height: 40px; border-radius: 10%; }")
                    .append("</style></head>")
                    .append("<body>")
                    // Welcome Heading
                    .append("<div class='welcome-heading'>BCT TEST AUTOMATION REPORT</div>")
                    // Image in the upper right corner
                    .append("<div class='image-container'><img src='./bct-logo-2.jpg' alt='BCT Logo'/></div>")
                    .append("<div class='container'><h1>Test Execution Report</h1>")
                    .append("<div class='table-container'><table><thead><tr><th>Test Case</th><th>Start time</th><th>Stop time</th><th>Run Duration</th><th>Suite Name</th><th>Output</th><th>Result</th></tr></thead><tbody>");

            // Loop through the results and add each test case details to the HTML
            for (TestResult result : results) {
                html.append("<tr class='")
                        .append(result.getStatus().toLowerCase())
                        .append("'><td>")
                        .append(result.getMethodName())
                        .append("</td><td>")
                        .append(result.getStartTime())
                        .append("</td><td>")
                        .append(result.getStopTime())
                        .append("</td><td>")
                        .append(result.getDuration())
                        .append("</td><td>")
                        .append(result.getSuiteName())
                        .append("</td><td>")
                        .append(result.getOutput())
                        .append("</td><td>")
                        .append(result.getStatus())
                        .append("</td></tr>");
            }

            html.append("</tbody></table></div></div></body></html>");

            // Save the HTML report to a file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("test-report-python.html"))) {
                writer.write(html.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        // Helper method to format time in HH:mm:ss.SSS format
        private String formatTime(long millis) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
            return sdf.format(new Date(millis));
        }

        // Helper class to store test result data
        private static class TestResult {
            private final String methodName;
            private final String startTime;
            private final String stopTime;
            private final String duration;
            private final String suiteName;
            private final String output;
            private final String status;

            public TestResult(String methodName, String startTime, String stopTime, String duration, String suiteName, String output, String status) {
                this.methodName = methodName;
                this.startTime = startTime;
                this.stopTime = stopTime;
                this.duration = duration;
                this.suiteName = suiteName;
                this.output = output;
                this.status = status;
            }

            public String getMethodName() {
                return methodName;
            }

            public String getStartTime() {
                return startTime;
            }

            public String getStopTime() {
                return stopTime;
            }

            public String getDuration() {
                return duration;
            }

            public String getSuiteName() {
                return suiteName;
            }

            public String getOutput() {
                return output;
            }

            public String getStatus() {
                return status;
            }
        }
    }

    