package DAO;

import model.Advice;

public class AdviceDAO {
    private static AdviceDAO adviceDAO;
    private Advice advice;

    public Advice getAdvice(int adviceId) {
        return advice;
    }

    //Todo
    public static AdviceDAO getInstance() {
        return adviceDAO;
    }
}






