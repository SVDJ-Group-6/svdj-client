package service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class RequestService {
    private static RequestService requestService;

    public String getRequest(String URL, String token) throws IOException {
        return createRequest(URL, null, token, "GET");
    }

    public String postRequest(String URL, String body, String token) throws IOException {
        return createRequest(URL, body, token, "POST");
    }

    public String deleteRequest(String URL, String token) throws IOException {
        return createRequest(URL, null, token, "DELETE");
    }

    private String createRequest(String URL, String body, String token, String requestMethod) throws IOException {
        URL url = new URL(URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod(requestMethod);
        if (token != null) {
            httpURLConnection.setRequestProperty("Authorization", "Bearer " + token);
        }
        if (Objects.equals(requestMethod, "POST") && body != null) {
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            byte[] input = body.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input);
            outputStream.flush();
            outputStream.close();
        }
        InputStream inputStream = httpURLConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return  stringBuilder.toString();
    }

    public static RequestService getInstance() {
        if (requestService == null) {
            requestService = new RequestService();
        }

        return requestService;
    }
}
