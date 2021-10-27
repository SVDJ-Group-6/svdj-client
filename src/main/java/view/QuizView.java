package view;

import controller.QuizController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import model.Answer;
import model.Question;
import model.Quiz;
import observer.QuizObserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class QuizView implements QuizObserver {

    private QuizController quizController;
    private Answer selectedAnswer;
    private Text question;
    private HBox answerButtonsContainer;
    HBox questionBox;

    private Button previous;
    private Button next;

    public QuizView() {
        this.intializeQuiz();
    }

    private void intializeQuiz(){

        questionBox = new HBox();
        // initialize the text element
        question = new Text();
        //Make a new Container to hold buttons
        answerButtonsContainer = new HBox();

        //initialize the back and next buttons
        previous = new ButtonCreator("Previous","9CC2D4").createCustomButton();
        setActionListenerToPreviousButton();

        next = new ButtonCreator("Next","9CC2D4").createCustomButton();
        setActionListenerToNextButton();

        quizController = QuizController.getInstance();
        quizController.registerObserver(this);
        quizController.loadFirst();
    }

    /**
     * Adds an action listener to the previous button
     */
    private void setActionListenerToPreviousButton(){
        previous.setOnAction((event -> {
        quizController.back();
        }));
    }

    /**
     * Adds an action listener to the next button
     */
    private void setActionListenerToNextButton(){
        next.setOnAction((event -> {
        if (selectedAnswer != null) {
            quizController.next(selectedAnswer);
        }}));
    }


    //supporting methods
    private Background makeBackground(String image){
        BackgroundImage background = null;
        try {
            background = new BackgroundImage(new Image(new FileInputStream(image)),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new Background(background);
    }
    private VBox makeLogoContainer(String image){

        //put logo in container
        VBox logoContainer = new VBox();
        Image logo = null;
        try {
            logo = new Image(new FileInputStream(image));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView logoView = new ImageView(logo);
        logoView.setFitHeight(128);
        logoView.setFitWidth(480);
        logoView.setPreserveRatio(true);
        logoContainer.getChildren().add(logoView);

        return logoContainer;
    }
    private HBox makeQuestionContainer(){

        //make a question
        questionBox.getChildren().add(new QuestionCreator(question.getText()).createCustomQuestion());
        //questionBox.setStyle("-fx-background-color: rgb(100, 6, 25); -fx-background-radius: 10;");
        return questionBox;
    }
    private HBox makeAnswerContainer(){
        return answerButtonsContainer;
    }
    private HBox makeNavigationContainer(){
        //create navigation container
        HBox navGrid = new HBox(80);
        //navGrid.setStyle("-fx-background-color: rgb(83,129,215); -fx-background-radius: 10;");

        //add first button
        navGrid.getChildren().add(previous);

        //create a container
        HBox ofHbox = new HBox(40);
        ofHbox.setAlignment(Pos.CENTER);

        //make the first left white line in that container
        Line firstLine = new Line();

        firstLine.setStroke(Color.WHITE);
        firstLine.setStartX(0);
        firstLine.setStartY(350);
        firstLine.setEndX(100);
        firstLine.setEndY(350);



        ofHbox.getChildren().add(firstLine);


        Text text = new Text("OF");
        text.setFill(Color.WHITE);
        ofHbox.getChildren().add(text);

        //make the first left white line in that container
        Line secondLine = new Line();
        secondLine.setStroke(Color.WHITE);
        secondLine.setStartX(100 + 30);
        secondLine.setStartY(350);
        secondLine.setEndX(100 + 30 + 100);
        secondLine.setEndY(350);


        ofHbox.getChildren().add(secondLine);

        //add that container to the nav bar
        navGrid.getChildren().add(ofHbox);

        //add second button
        navGrid.getChildren().add(next);
        navGrid.setAlignment(Pos.CENTER);

        return navGrid;
    }
    private VBox makeSecondaryContainer(){

        VBox secondaryContainer = new VBox();
        secondaryContainer.setAlignment(Pos.CENTER);

        VBox question_Answers_box = new VBox(20);

        question_Answers_box.setMaxWidth(1000);
        question_Answers_box.setMaxHeight(600);
        question_Answers_box.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius: 10;");

        question_Answers_box.getChildren().add(makeQuestionContainer());
        question_Answers_box.getChildren().add(makeAnswerContainer());

        question_Answers_box.getChildren().add(makeNavigationContainer());
        secondaryContainer.getChildren().add(question_Answers_box);

        return secondaryContainer;
    }
    private VBox makeMainContainer(){
        VBox mainContainer = new VBox(20);
        // give the main container a background
        mainContainer.setBackground(makeBackground("./src/main/resources/background.png"));
        mainContainer.getChildren().add(makeLogoContainer("./src/main/resources/SVDJ_logo.png"));
        mainContainer.getChildren().add(makeSecondaryContainer());
        return mainContainer;
    }

    //main method that draws shit
    public VBox drawQuiz() {
        return makeMainContainer();
    }

    private void updateQuestion(Quiz quiz){
        ArrayList<Question> questions = quiz.getQuestions();
        for (Question q: questions) {

            System.out.println(q.getValue());

        }
        Question currentQuestion = questions.get(questions.size() - 1);
        // Change value of elements
        this.previous.setVisible(questions.size() != 1);
        this.question.setText(currentQuestion.getValue());
    }
    private void updateAnswers(Quiz quiz){
        ArrayList<ArrayList<Answer>> allAnswers = quiz.getAnswers();
        ArrayList<Answer> currentAnswers = allAnswers.get(allAnswers.size() - 1);
        ArrayList<Button> newButtons = new ArrayList<>();
        for (Answer answer : currentAnswers) {
            Button tmpBtn = new ButtonCreator(answer.getValue()).createCustomButton();

            tmpBtn.setOnAction((event -> {
                this.selectedAnswer = answer;
            }));
            newButtons.add(tmpBtn);
        }

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(400, 200);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(25);
        gridPane.setHgap(80);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        int rows;
        if(newButtons.size() % 2 == 0){
            rows = newButtons.size() / 2;
        }else
        {
            rows = (int) Math.floor(((newButtons.size() / 2) + 1));
        }

        int currentCol = 0;
        int currentRow = 0;
        boolean rowSpan = false;


        for (Button button: newButtons) {
            //add button
            gridPane.add(button, currentCol, currentRow);

            // keep column between 0, 1
            //if(!(currentCol % 2 == 0)){currentCol = 0;}else {currentCol++;}
            currentCol = (currentCol + 1) % 2;

            if(rowSpan && currentRow < rows){
                currentRow++;
                rowSpan = false;
            }
            else {
                rowSpan = true;
            }
        }

        //scrollPane.setContent(gridPane);
        //gridPane.add(new Pane(newButtons.get(i)), i, j);
        //this.answerButtonsContainer.getChildren().addAll(newButtons);
        this.answerButtonsContainer.getChildren().add(gridPane);
        this.selectedAnswer = null;

    }
    @Override
    public void update(Quiz quiz) {

        // Empty HBOX for the new Qeustion.
        this.questionBox.getChildren().removeAll(this.questionBox.getChildren());
        updateQuestion(quiz);

        this.makeQuestionContainer();

        // Empty HBOX for the new Buttons.
        this.answerButtonsContainer.getChildren().removeAll(this.answerButtonsContainer.getChildren());
        updateAnswers(quiz);

        this.selectedAnswer = null;

    }

}
