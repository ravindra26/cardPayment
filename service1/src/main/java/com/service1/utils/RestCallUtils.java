package com.service1.utils;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import org.jboss.logging.Logger;

/**
 *
 * @author Ravindra
 */
public class RestCallUtils {

    Logger logger = Logger.getLogger("myLogger");

    public String makeGetRequestWithJSONResponse(String url, Map<String, String> headers) throws MalformedURLException, ProtocolException, IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            con.setRequestProperty(entry.getKey(), entry.getValue());
        }
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        logger.info("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_ACCEPTED || responseCode == HttpURLConnection.HTTP_CREATED) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            logger.info("Response:" + response.toString());
            return response.toString();
        } else {
            System.out.println(responseCode);
            System.out.println("GET request not worked");
            return null;
        }
    }

    public String makePOSTJSONRequest(String urlString, String data, Map<String, String> headers) throws MalformedURLException, ProtocolException, IOException {
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            con.setRequestProperty(entry.getKey(), entry.getValue());
        }
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = data.getBytes();
            os.write(input, 0, input.length);
        }
        int responseCode = con.getResponseCode();
        System.out.println(responseCode);
        if (responseCode !=0) {
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
                return response.toString();
            }
        } else {
            logger.info("Error occured in POST request");
            return null;
        }
    }

}
