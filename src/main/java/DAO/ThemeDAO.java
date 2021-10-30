package DAO;

import Client.ClientVariables;
import com.google.gson.Gson;
import model.Theme;
import service.RequestService;

import java.io.IOException;

public class ThemeDAO {
    private static ThemeDAO themeDAO;
    private Gson gson = new Gson();
    private RequestService requestService = RequestService.getInstance();

    /**
     * Returns a Theme object that is either fetched from the API, if this the theme
     * object is not cached.
     * 
     * @return The theme object from API
     * @throws IOException
     */
    public Theme getTheme() throws IOException {
        if (ClientVariables.theme == null) {
            ClientVariables.theme = getThemeFromAPI();
        }
        return ClientVariables.theme;
    }

    public void postTheme(Theme theme) throws IOException {
        String themeURL = ClientVariables.API_URL + "/api/theme" + "?token=a612078c8a93ccc084ee565cfc471bb6";
        String json = gson.toJson(theme);

        // requestService.postRequest(themeURL, json);
    }

    private Theme getThemeFromAPI() throws IOException {
        String themeURL = ClientVariables.API_URL + "/api/theme";
        Theme theme = gson.fromJson(requestService.getRequest(themeURL, null), Theme.class);
        return theme;
    }

    public static ThemeDAO getInstance() {
        if (themeDAO == null) {
            themeDAO = new ThemeDAO();
        }
        return themeDAO;
    }
}
