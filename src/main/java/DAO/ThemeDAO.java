package DAO;

import ClientApplication.ClientVariables;
import com.google.gson.Gson;
import model.Theme;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ThemeDAO {
    private static ThemeDAO themeDAO;
    private Gson gson = new Gson();

    /**
     * Returns a Theme object that is either fetched from the API,
     * if this the theme object is not cached.
     * @return The theme object from API
     * @throws IOException
     */
    public Theme getTheme() throws IOException {
        if (ClientVariables.theme == null) {
            ClientVariables.theme = getThemeFromAPI();
        }
        return ClientVariables.theme;
    }

    private Theme getThemeFromAPI() throws IOException {
        String themeURL = ClientVariables.API_URL + "/api/theme";
        Theme theme = gson.fromJson(getResponse(themeURL), Theme.class);
        return theme;
    }

    private String getResponse(String URL) throws IOException {
        System.out.println(URL);
        java.net.URL endpointURL = new URL(URL);
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

    public static ThemeDAO getInstance() {
        if (themeDAO == null) {
            themeDAO = new ThemeDAO();
        }
        return themeDAO;
    }
}
