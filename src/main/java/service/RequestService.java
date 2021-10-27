package service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RequestService {
    private static RequestService requestService;

    public String getResponse(String URL) throws IOException {
        URL endpointURL = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) endpointURL.openConnection();
        connection.setRequestMethod("GET");

        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuilder stringBuilder = new StringBuilder();

        while (true) {
            String line = bufferedReader.readLine();

            if (line == null) {
                break;
            }

            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }

    public String postRequest(String URL, String body) throws IOException {
        URL endpointURL = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) endpointURL.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        OutputStream outputStream = connection.getOutputStream();
        byte[] input = body.getBytes(StandardCharsets.UTF_8);
        outputStream.write(input);
        outputStream.flush();
        outputStream.close();

        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuilder stringBuilder = new StringBuilder();

        while (true) {
            String line = bufferedReader.readLine();

            if (line == null) {
                break;
            }

            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }

    public String deleteRequest(String URL) throws IOException {
        URL endpointURL = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) endpointURL.openConnection();
        connection.setRequestMethod("DELETE");

        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuilder stringBuilder = new StringBuilder();

        while (true) {
            String line = bufferedReader.readLine();

            if (line == null) {
                break;
            }

            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }

    public static RequestService getInstance() {
        if (requestService == null) {
            requestService = new RequestService();
        }

        return requestService;
    }
}
