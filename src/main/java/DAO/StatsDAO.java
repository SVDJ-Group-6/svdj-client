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
        String body = stats.toJsonString();
        String statsURL = ClientVariables.API_URL + "/api/stats";
        requestService.postRequest(statsURL, body, null);
    }

    public static StatsDAO getInstance() {
        if (statsDAO == null) {
            statsDAO = new StatsDAO();
        }
        return statsDAO;
    }
}
