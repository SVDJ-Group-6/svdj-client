package view;

import controller.EditController;
import model.Node;
import observer.EditObserver;

import java.util.ArrayList;

public class EditView  implements EditObserver {
    //Todo in class diagram with uppercase "E"
    private EditController editController;
    private ArrayList<Node> nodes;

    @Override
    public void update(ArrayList<Node> nodes) {

    }
}
