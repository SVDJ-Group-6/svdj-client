package DAO;

import model.Stats;

import java.util.ArrayList;

public class StatsDAO {

    private static StatsDAO statsDAO;

    public ArrayList<Stats> getAllStats(){
        return null;
    }

    //Todo this says returns a statsService mate, whachumean bruh?
    public static StatsDAO getInstance() {
        return statsDAO;
    }
}
