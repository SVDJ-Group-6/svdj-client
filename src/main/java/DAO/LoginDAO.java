package DAO;

public class LoginDAO {

    private static LoginDAO loginDAO;
    //Todo what does this return ?
    private String requestToken(String username, String password_hash){
        return "";
    }
    //Todo
    public static LoginDAO getInstance() {
        return loginDAO;
    }
}
