package DAO;

import ClientApplication.ClientVariables;
import com.google.gson.Gson;
import model.Advice;
import service.RequestService;

import java.io.IOException;

public class AdviceDAO {
    private static AdviceDAO adviceDAO;
    private Gson gson = new Gson();
    private RequestService requestService = RequestService.getInstance();

    public Advice getAdviceFromAPI(int adviceId) throws IOException {
        String adviceURL = ClientVariables.API_URL + "/advices/" + adviceId;
        Advice advice = gson.fromJson(requestService.getResponse(adviceURL), Advice.class);
        return advice;
    }

    public static AdviceDAO getInstance() {
        if (adviceDAO == null) {
            adviceDAO = new AdviceDAO();
        }
        return adviceDAO;
    }
}






