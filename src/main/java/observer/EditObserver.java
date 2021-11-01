package observer;

import model.Edit;

public interface EditObserver {
    void update(Edit edit);
}
