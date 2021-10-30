package DAO;

import Admin.AdminVariables;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.Login;
import observer.LoginObserver;
import service.RequestService;

import java.io.IOException;

public class LoginDAO {

    private final RequestService requestService;
    private final Gson gson;

    public LoginDAO() {
        this.requestService = RequestService.getInstance();
        this.gson = new Gson();
    }

    public String getToken(String username, String password) throws IOException {
        String requestURL = AdminVariables.API_URL + "/api/auth/login";
        String requestBody = String.format("{ \"username\": \"%s\", \"password\": \"%s\" }", username, password);
        String response = requestService.postRequest(requestURL, requestBody, null);
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        return jsonObject.get("token").getAsString();
    }

    static LoginDAO loginDAO;
    static public LoginDAO getInstance() {
        if (loginDAO == null) loginDAO = new LoginDAO();
        return loginDAO;
    }
}
