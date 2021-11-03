package DAO;

import Client.ClientVariables;
import com.google.gson.Gson;
import model.Advice;
import service.RequestService;

import java.io.IOException;

public class AdviceDAO {
    private static AdviceDAO adviceDAO;
    private Gson gson = new Gson();
    private RequestService requestService = RequestService.getInstance();

    public Advice getAdviceFromAPI(int adviceId) throws IOException {
        String adviceURL = ClientVariables.API_URL + "/api/advices/" + adviceId;
        Advice advice = gson.fromJson(requestService.getRequest(adviceURL, null), Advice.class);
        return advice;
    }

    public void sendEmailWithAPI(int adviceId,String email) throws IOException {
        String emailURL = ClientVariables.API_URL + "/api/advices/" + adviceId + "/email/" + email;
        requestService.getRequest(emailURL, null);
    }

    public static AdviceDAO getInstance() {
        if (adviceDAO == null) {
            adviceDAO = new AdviceDAO();
        }
        return adviceDAO;
    }
}






