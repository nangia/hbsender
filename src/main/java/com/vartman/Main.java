package com.vartman;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        Properties mySettings = new Properties();
        mySettings.load(new FileInputStream("reportsender.cfg"));

        String host = mySettings.getProperty("url");
        String ports = mySettings.getProperty("port");
        if (host == null) {
            System.out.println("Invalid configuration for url in reportsender.cfg");
        }
        if (ports == null) {
            System.out.println("Invalid configuration for url in reportsender.cfg");
        }

        String url = String.format("http://%s:%s/", host, ports); // IP address and port where testbench.exe is running
        String filepath = "/Users/sandeep/Downloads/testfile.pdf"; // path to the file that is to be sent
        String testdate = "2016-05-01T05:50"; // date and time of the test
        String phone = "919872882112"; // phone number of the patient. Note that it includes country code
        String description = "Laboratory Description + Test conducted"; // Laboratory description
        String patient_name = "Sandeep Nangia"; // name of patient
        String patient_email = "sandeep.nangia@gmail.com"; // leave blank if not known
        String test_unique_id = "unique-test-id"; // unique test id for which this report is provided
        String patient_unique_id = "CRnumber0734"; // CR number of patient
        String labid = "unique-lab-id"; // unique lab id of the lab

        ReportSenderUtility httpsender = new ReportSenderUtility();

        System.out.println("\nSend Http POST request");
        try {
            String response = httpsender.sendPost(url, filepath, testdate, phone,
                    description, patient_name, patient_email,
                    test_unique_id, patient_unique_id, labid
            );
            System.out.println("\nResponse = " + response);
            // Must log response even when success
        } catch (HBIOException e) {
            int status = e.status;
            String response = e.response;
            System.err.println("status =" + Integer.toString(status) + " response = " + response);
            // Check the status value and decide if a retry needs to be done or not
            // Must log the value of status and response
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
            // An IO Exception happened.
            // Log the value of e.getMessage()
            // We must try again as this seems to be some connection failure
        }

    }
}

