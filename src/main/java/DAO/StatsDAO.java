package DAO;

import Client.ClientVariables;
import com.google.gson.JsonObject;
import model.Stats;
import service.RequestService;

import java.io.IOException;
import java.util.ArrayList;

public class StatsDAO {

    private static StatsDAO statsDAO;
    private RequestService requestService = RequestService.getInstance();

    public ArrayList<Stats> getAllStats(){
        return null;
    }

    public void postStatsToAPI(Stats stats) throws IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uuid", stats.getUuid());
        jsonObject.addProperty("index", stats.getIndex());
        jsonObject.addProperty("question", stats.getQuestion());
        jsonObject.addProperty("answer", stats.getAnswer());
        jsonObject.addProperty("advice", stats.getAdvice());
        jsonObject.addProperty("timestampUNIX", stats.getTimestampUNIX());

        String body = jsonObject.toString();
        String statsURL = ClientVariables.API_URL + "/api/stats";
        System.out.println(requestService.postRequest(statsURL, body));
    }

    public static StatsDAO getInstance() {
        if (statsDAO == null) {
            statsDAO = new StatsDAO();
        }
        return statsDAO;
    }
}
