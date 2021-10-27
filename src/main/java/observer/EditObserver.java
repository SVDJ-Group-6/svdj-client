package observer;

import model.Edit;
import model.Node;

import java.util.ArrayList;

public interface EditObserver {
    void update(Edit edit);
}
