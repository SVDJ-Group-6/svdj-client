package observer;

import model.ChangePassword;

public interface ChangePasswordObserver {
    void update(ChangePassword changePassword);
}
