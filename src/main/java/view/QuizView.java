package view;

import controller.QuizController;
import controller.ThemeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import model.Answer;
import model.Question;
import model.Quiz;
import model.Theme;
import observer.QuizObserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class QuizView implements QuizObserver {


    VBox question_Answers_box;
    private QuizController quizController = QuizController.getInstance();
    private ThemeController themeController = ThemeController.getInstance();
    private Theme theme = themeController.getTheme();

    private Answer selectedAnswer;
    private Text question;
    private HBox answerButtonsContainer;

    private Button previous;
    private Button next;

    public QuizView() {
        this.intializeQuiz();
    }

    private void intializeQuiz(){

        question_Answers_box = new VBox(20);
        // initialize the text element
        question = new QuestionCreator(1, "").createCustomQuestion();
        //Make a new Container to hold buttons
        answerButtonsContainer = new HBox();

        //initialize the back and next buttons
        previous = new ButtonCreator("Previous","9CC2D4").createCustomButton();
        previous.setOnAction((event -> {
            quizController.back();
        }));

        next = new ButtonCreator("Next","9CC2D4").createCustomButton();
        next.setOnAction((event -> {
            if (selectedAnswer != null) {
                quizController.next(selectedAnswer);
            }
        }));

        quizController.registerObserver(this);
        quizController.loadFirst();
    }

    public VBox getQuizPane() {
        VBox mainContainer = new VBox();

        //picture of svdj
        BackgroundImage background = null;
        try {
            background = new BackgroundImage(new Image(new FileInputStream("./src/main/resources/background.png")),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // give the main container a background
        mainContainer.setBackground(new Background(background));

        //put logo in container
        VBox logoContainer = new VBox();
        Image logo = null;
        try {
            logo = new Image(new FileInputStream("./src/main/resources/SVDJ_logo.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView logoView = new ImageView(logo);
        logoView.setFitHeight(128);
        logoView.setFitWidth(480);
        logoView.setPreserveRatio(true);
        logoContainer.getChildren().add(logoView);

        VBox secondaryContainer = new VBox();

        question_Answers_box.setMaxWidth(1000);
        question_Answers_box.setMaxHeight(600);
        question_Answers_box.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius: 10;");
        secondaryContainer.setAlignment(Pos.CENTER);

        //make a question
        HBox questionBox = new HBox();
        questionBox.getChildren().add(new QuestionCreator(1, question.getText()).createCustomQuestion());
        //questionBox.setStyle("-fx-background-color: rgb(100, 6, 25); -fx-background-radius: 10;");

        question_Answers_box.getChildren().add(questionBox);
        question_Answers_box.getChildren().add(answerButtonsContainer);

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

        question_Answers_box.getChildren().add(navGrid);

        secondaryContainer.getChildren().add(question_Answers_box);

        mainContainer.getChildren().add(logoContainer);
        mainContainer.getChildren().add(secondaryContainer);
/*
        VBox vbox = new VBox();
        vbox.getChildren().add(question);
        vbox.getChildren().add(answerButtonsContainer);
        vbox.getChildren().add(previous);
        vbox.getChildren().add(next);
        mainContainer.getChildren().add(vbox);

 */





        return mainContainer;
    }

    @Override
    public void update(Quiz quiz) {
        ArrayList<Question> questions = quiz.getQuestions();
        ArrayList<ArrayList<Answer>> allAnswers = quiz.getAnswers();

        Question currentQuestion = questions.get(questions.size() - 1);
        ArrayList<Answer> currentAnswers = allAnswers.get(allAnswers.size() - 1);

        // Empty HBOX for the new Buttons.
        this.answerButtonsContainer.getChildren().removeAll(this.answerButtonsContainer.getChildren());

        ArrayList<Button> newButtons = new ArrayList<>();
        for (Answer answer : currentAnswers) {
            Button tmpBtn = new ButtonCreator(answer.getValue()).createCustomButton();

            tmpBtn.setOnAction((event -> {
                this.selectedAnswer = answer;
            }));
            newButtons.add(tmpBtn);
        }

        // Change value of elements
        this.previous.setVisible(questions.size() != 1);
        this.question.setText(currentQuestion.getValue());

        /*Todo scrollpane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefViewportWidth(answerButtonsContainer.getPrefWidth());
        scrollPane.setPrefViewportHeight(answerButtonsContainer.getPrefHeight());

         */

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

    public VBox getQuestion_Answers_box() {
        return question_Answers_box;
    }
}
