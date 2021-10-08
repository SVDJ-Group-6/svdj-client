package view;

import controller.AdviceController;
import model.Advice;
import observer.AdviceObserver;

public class AdviceView implements AdviceObserver {


    private AdviceController adviceController;
    private Advice advice;

    public AdviceView(int adviceId){}

    @Override
    public void update(Advice advice) {

    }
}
