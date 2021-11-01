package DAO;

import Admin.AdminVariables;
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
        String themeURL = AdminVariables.API_URL + "/api/theme";
        String body = gson.toJson(theme);
        requestService.postRequest(themeURL, body, AdminVariables.token);
    }

    private Theme getThemeFromAPI() throws IOException {
        String themeURL = ClientVariables.API_URL + "/api/theme";
        String response = requestService.getRequest(themeURL, null);
        Theme theme = gson.fromJson(response, Theme.class);
        return theme;
    }

    public static ThemeDAO getInstance() {
        if (themeDAO == null) {
            themeDAO = new ThemeDAO();
        }
        return themeDAO;
    }
}
