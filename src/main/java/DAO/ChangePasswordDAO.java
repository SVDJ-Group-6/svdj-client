package DAO;

import Admin.AdminVariables;
import com.google.gson.JsonObject;
import service.RequestService;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class ChangePasswordDAO {
    static ChangePasswordDAO changePasswordDAO;
    private RequestService requestService = RequestService.getInstance();


    static public ChangePasswordDAO getInstance() {
        if (changePasswordDAO == null) {
            changePasswordDAO = new ChangePasswordDAO();
        }
        return changePasswordDAO;
    }

    public void postPassword(String newPassword, String recoveryCode) throws NoSuchAlgorithmException, IOException {
        String url = AdminVariables.API_URL + "/api/auth/change_password/" + recoveryCode;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("password",newPassword);
        requestService.postRequest(url, jsonObject.toString());
    }

}
