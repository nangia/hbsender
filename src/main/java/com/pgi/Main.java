package com.pgi;

import java.io.FileInputStream;
import java.util.Properties;

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

		String url = String.format("http://%s:%s/", host, ports); // IP address
																	// and port
																	// where
																	// testbench.exe
																	// is
																	// running
		String filepath = "/Users/sandeep/Downloads/testfile.pdf"; // path to
																	// the file
																	// that is
																	// to be
																	// sent
		String testdate = "2016-05-01T05:50"; // date and time of the test
		String phone = "919872882112"; // phone number of the patient. Note that
										// it includes country code
		String description = "Laboratory Description + Test conducted"; // Laboratory
																		// description
		String patient_name = "Sandeep Nangia"; // name of patient
		String patient_email = "sandeep.nangia@gmail.com"; // leave blank if not
															// known
		String test_unique_id = "unique-test-id"; // unique test id for which
													// this report is provided
		String patient_unique_id = "CRnumber0734"; // CR number of patient
		String labid = "unique-lab-id"; // unique lab id of the lab

		ReportSenderUtility httpsender = new ReportSenderUtility();

		System.out.println("\nSend Http POST request");
		String response = httpsender.sendPost(url, filepath, testdate, phone, description, patient_name, patient_email, test_unique_id, patient_unique_id, labid);
		System.out.println("\nResponse = " + response);

	}
}
