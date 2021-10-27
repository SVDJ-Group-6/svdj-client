package DAO;

import Admin.AdminVariables;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.Login;
import observer.LoginObserver;
import service.RequestService;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginDAO {
    static LoginDAO loginDAO;
    private final Login login;
    private RequestService requestService = RequestService.getInstance();
    private Gson gson = new Gson();

    public LoginDAO() {
        login = new Login();
    }

    static public LoginDAO getInstance() {
        if (loginDAO == null) {
            loginDAO = new LoginDAO();
        }
        return loginDAO;
    }

    public String requestToken(String username, String password) throws NoSuchAlgorithmException, IOException {
        String hashed_password = convertMD5(password);
        String url = AdminVariables.API_URL + "/api/auth";

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("password_hash", hashed_password);

        String response = requestService.postRequest(url, jsonObject.toString());

        String token = gson.fromJson(response, JsonObject.class).get("token").getAsString();
        return token;
    }

    private String convertMD5(String value) throws NoSuchAlgorithmException {
        byte[] bytesOfMessage = value.getBytes(StandardCharsets.UTF_8);
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BigInteger number = new BigInteger(1, md5.digest(bytesOfMessage));
        return number.toString(16);
    }

    public void setMessage(String message) {
        login.setMessage(message);
    }

    public void registerObserver(LoginObserver loginObserver) {
        login.registerObserver(loginObserver);
    }
}
