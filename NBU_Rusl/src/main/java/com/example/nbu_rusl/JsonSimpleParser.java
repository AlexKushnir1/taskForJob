package com.example.nbu_rusl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonSimpleParser {
    public String parse(URL url) throws IOException {
      HttpURLConnection connection = (HttpURLConnection)  url.openConnection();
      connection.setRequestMethod("GET");

        InputStream inputStream = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line =bufferedReader.readLine())!=null){
            response.append(line);
        }
        bufferedReader.close();
        connection.disconnect();
        return response.toString();
    }
}
