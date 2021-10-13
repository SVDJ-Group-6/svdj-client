package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

    public static RequestService getInstance() {
        if (requestService == null) {
            requestService = new RequestService();
        }

        return requestService;
    }
}
