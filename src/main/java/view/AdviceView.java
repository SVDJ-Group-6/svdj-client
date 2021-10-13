package view;

import controller.AdviceController;
import javafx.scene.layout.StackPane;
import model.Advice;
import observer.AdviceObserver;

public class AdviceView implements AdviceObserver {


    private AdviceController adviceController = AdviceController.getInstance();
    private Advice advice;

    public AdviceView(int adviceId){
        adviceController.registerObserver(this);
        adviceController.loadAdvice(adviceId);
    }

    public StackPane getAdvicePane() {
        return new StackPane();
    }

    @Override
    public void update(Advice advice) {

    }
}
