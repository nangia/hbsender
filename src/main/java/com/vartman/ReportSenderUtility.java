package com.vartman;


/**
 * Created by sandeep on 24/10/16 based on
 * http://stackoverflow.com/questions/34276466/simple-httpurlconnection-post-file-multipart-form-data-from-android-to-google-bl
 */
public class ReportSenderUtility {
    private final String USER_AGENT = "PGIReportSender/1.0";

    // HTTP POST request
    public String sendPost(String url, String filepath, String testdate, String phone,
                         String description, String patient_name, String patient_email,
                         String test_unique_id, String patient_unique_id,
                         String labid
    ) throws Exception {
        String charset= "UTF-8";
        MultiPartUtility multipart = new MultiPartUtility(url, charset);

        multipart.addFormField("testdate", testdate);
        multipart.addFormField("description", description);
        multipart.addFormField("phone", phone);
        multipart.addFormField("name", patient_name);
        multipart.addFormField("email", patient_email);
        multipart.addFormField("sid", test_unique_id);
        multipart.addFormField("pid", patient_unique_id);
        multipart.addFormField("labid", labid);

        //add the PDF report file here.
        multipart.addFilePart("file", filepath);

        String response = multipart.finish();
        return response;
    }
}
