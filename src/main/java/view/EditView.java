package view;

import Admin.AdminVariables;
import controller.EditController;
import model.Node;
import model.Question;
import observer.EditObserver;

import java.util.ArrayList;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class EditView implements EditObserver {
    // Todo in class diagram with uppercase "E"
    private EditController editController;
    private ArrayList<Node> nodes;

    public EditView() {

    }

    public Scene getEditScene() {
        final double BUTTON_PADDING = 12.5;
        final int HEADER_FONT_SIZE = 64;
        final int BUTTON_FONT_SIZE = 22;
        final int QUESTION_TITLE_FONT_SIZE = 22;

        final int LOGO_WIDTH = 480;
        final int LOGO_HEIGHT = 128;

        /* Begin Temporary */
        final String BUTTON_COLOR = "#FFFFFF";
        final String HOVER_BUTTON_COLOR = "#000000";

        final String ANSWER_BACKGROUND_COLOR = "#EDECEC";

        final String QUESTION_ACTION_COLOR = "#9CC2D4";

        final String FONT_FAMILY = "Arial";
        /* End Temporary */

        FileInputStream logoInput, backgroundInput, deleteInput;
        Image logoImage, backgroundImage, deleteImage;
        ImageView logoImageView, deleteImageView;

        HBox logoHeader = new HBox();
        logoHeader
                .setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        try {
            logoInput = new FileInputStream("./src/main/resources/logo.png");
            logoImage = new Image(logoInput, LOGO_WIDTH, LOGO_HEIGHT, true, false);
            logoImageView = new ImageView(logoImage);

            logoHeader.getChildren().add(logoImageView);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        Text headerText = new Text("Vragenlijst");
        headerText.setFont(Font.font(FONT_FAMILY, FontWeight.NORMAL, HEADER_FONT_SIZE));
        headerText.setFill(Color.WHITE);
        headerText.setTextAlignment(TextAlignment.LEFT);

        Button backButton = new Button("Terug");
        backButton.setPadding(new Insets(BUTTON_PADDING));
        backButton.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, BUTTON_FONT_SIZE));
        backButton.setTextFill(Color.BLACK);
        backButton.setStyle(String.format("-fx-background-color: %s;", BUTTON_COLOR));
        backButton.setOnMouseClicked(e -> AdminVariables.stage.setScene(new DashboardView().getDashboardScene()));
        backButton.setOnMouseEntered(e -> {
            backButton.setStyle(
                    String.format("-fx-background-color: %s; -fx-text-fill: %s;", HOVER_BUTTON_COLOR, BUTTON_COLOR));
        });
        backButton.setOnMouseExited(e -> {
            backButton.setStyle(String.format("-fx-background-color: %s;", BUTTON_COLOR));
        });

        Button addButton = new Button("Vraag toevoegen");
        addButton.setPadding(new Insets(BUTTON_PADDING));
        addButton.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, BUTTON_FONT_SIZE));
        addButton.setTextFill(Color.BLACK);
        addButton.setPrefWidth(246);
        addButton.setStyle(String.format("-fx-background-color: %s;", BUTTON_COLOR));
        addButton.setOnMouseEntered(e -> {
            addButton.setStyle(
                    String.format("-fx-background-color: %s; -fx-text-fill: %s;", HOVER_BUTTON_COLOR, BUTTON_COLOR));
        });
        addButton.setOnMouseExited(e -> {
            addButton.setStyle(String.format("-fx-background-color: %s;", BUTTON_COLOR));
        });

        Button cancelButton = new Button("Annuleren");
        cancelButton.setPadding(new Insets(BUTTON_PADDING));
        cancelButton.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, BUTTON_FONT_SIZE));
        cancelButton.setTextFill(Color.BLACK);
        cancelButton.setPrefWidth(143);
        cancelButton.setStyle(String.format("-fx-background-color: %s;", BUTTON_COLOR));
        cancelButton.setOnMouseEntered(e -> {
            cancelButton.setStyle(
                    String.format("-fx-background-color: %s; -fx-text-fill: %s;", HOVER_BUTTON_COLOR, BUTTON_COLOR));
        });
        cancelButton.setOnMouseExited(e -> {
            cancelButton.setStyle(String.format("-fx-background-color: %s;", BUTTON_COLOR));
        });

        Button applyButton = new Button("Bevestigen");
        applyButton.setPadding(new Insets(BUTTON_PADDING));
        applyButton.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, BUTTON_FONT_SIZE));
        applyButton.setTextFill(Color.BLACK);
        applyButton.setPrefWidth(160);
        applyButton.setStyle(String.format("-fx-background-color: %s;", BUTTON_COLOR));
        applyButton.setOnMouseEntered(e -> {
            applyButton.setStyle(
                    String.format("-fx-background-color: %s; -fx-text-fill: %s;", HOVER_BUTTON_COLOR, BUTTON_COLOR));
        });
        applyButton.setOnMouseExited(e -> {
            applyButton.setStyle(String.format("-fx-background-color: %s;", BUTTON_COLOR));
        });

        GridPane actionContainer = new GridPane();
        actionContainer.setHgap(25);
        actionContainer.setAlignment(Pos.CENTER_RIGHT);
        actionContainer.add(backButton, 0, 0);
        actionContainer.add(addButton, 1, 0);
        actionContainer.add(cancelButton, 2, 0);
        actionContainer.add(applyButton, 3, 0);

        HBox headerContainer = new HBox();
        headerContainer.setAlignment(Pos.CENTER_LEFT);
        headerContainer.setHgrow(actionContainer, Priority.ALWAYS);
        headerContainer.getChildren().addAll(headerText, actionContainer);

        /**
         * Scrollpane Each container = one question one question contains more answers
         * answer contains list of reference
         */

        ScrollPane questionScrollPane = new ScrollPane();
        /**
         * Begin Temporary
         * 
         * QUESTION [HBox -> (Text, HBox -> ((HBox -> Text, Button), Text, ComboBox),
         * Button, Button)]
         */

        VBox questionList = new VBox(10);

        /**
         * Voor elke vraag een questionBox
         */
        VBox questionBox = new VBox(10);

        Text questionText = new Text(String.format("%s: %s", 1, "Wat is 2+2")); // Get question value
        questionText.setFont(Font.font(FONT_FAMILY, FontWeight.BLACK, QUESTION_TITLE_FONT_SIZE));

        VBox answerBox = new VBox(5);

        Text answerText = new Text(String.format("%s", "Dat is 2 :D")); // Get answer value
        answerText.setFont(Font.font(FONT_FAMILY, FontWeight.BLACK, QUESTION_TITLE_FONT_SIZE));
        answerText.setTextAlignment(TextAlignment.LEFT);

        Button deleteAnswerButton = new Button("");

        try {
            deleteInput = new FileInputStream("./src/main/resources/delete_btn.png");
            deleteImage = new Image(deleteInput);
            deleteImageView = new ImageView(deleteImage);
            deleteAnswerButton.setGraphic(deleteImageView);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        deleteAnswerButton
                .setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        deleteAnswerButton.setAlignment(Pos.CENTER_RIGHT);
        deleteAnswerButton.setOnMouseClicked(e -> {
            System.out.println(e); // Get id, delete request on it
        });

        HBox deleteAnswerContainer = new HBox();
        deleteAnswerContainer.setAlignment(Pos.CENTER_RIGHT);
        deleteAnswerContainer.getChildren().add(deleteAnswerButton);

        HBox firstRow = new HBox();
        firstRow.setAlignment(Pos.CENTER_LEFT);
        firstRow.setHgrow(deleteAnswerContainer, Priority.ALWAYS);
        firstRow.getChildren().addAll(answerText, deleteAnswerContainer);

        Text referText = new Text("Refereer door naar vraag:");
        referText.setFont(Font.font(FONT_FAMILY, FontWeight.BLACK, 18));

        ComboBox referQuestionBox = new ComboBox<>(); // Voor elke mogelijk om door te refereren in een loop stoppen in
                                                      // deze box
        referQuestionBox.getItems().add(String.format("%s: %s", 2, "Heb je basisschool gevolgd?"));
        referQuestionBox.getItems().add(String.format("%s: %s", "END met subsidie", "Talent ontwikkeling?"));
        referQuestionBox.getSelectionModel().select(0); // get answer id, refer question - 1
        referQuestionBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            System.out.println(newValue);
        });

        referQuestionBox.setBackground(
                new Background(new BackgroundFill(Color.web("#C4C4C4"), CornerRadii.EMPTY, Insets.EMPTY)));
        referQuestionBox.setStyle("-fx-font-weight: bold;");

        answerBox.setPadding(new Insets(10));
        answerBox.setAlignment(Pos.CENTER_LEFT);
        answerBox.setBackground(new Background(
                new BackgroundFill(Color.web(ANSWER_BACKGROUND_COLOR), CornerRadii.EMPTY, Insets.EMPTY)));
        answerBox.getChildren().addAll(firstRow, referText, referQuestionBox);

        HBox actionRow = new HBox(25);
        Button addAnswerButton = new Button("Antwoord toevoegen");
        addAnswerButton.setPadding(new Insets(BUTTON_PADDING));
        addAnswerButton.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, QUESTION_TITLE_FONT_SIZE));
        addAnswerButton.setTextFill(Color.WHITE);
        addAnswerButton.setStyle(String.format("-fx-background-color: %s;", HOVER_BUTTON_COLOR));
        addAnswerButton.setOnMouseEntered(e -> {
            addAnswerButton.setStyle(
                    String.format("-fx-background-color: %s; -fx-text-fill: %s;", QUESTION_ACTION_COLOR, HOVER_BUTTON_COLOR));
        });
        addAnswerButton.setOnMouseExited(e -> {
            addAnswerButton.setStyle(String.format("-fx-background-color: %s;", HOVER_BUTTON_COLOR));
        });
        addAnswerButton.setOnMouseClicked(e -> {
            System.out.println(e); // Add answer on id
        });

        Button deleteQuestionButton = new Button("Verwijderen");
        deleteQuestionButton.setPadding(new Insets(BUTTON_PADDING));
        deleteQuestionButton.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, QUESTION_TITLE_FONT_SIZE));
        deleteQuestionButton.setTextFill(Color.WHITE);
        deleteQuestionButton.setStyle(String.format("-fx-background-color: %s;", HOVER_BUTTON_COLOR));
        deleteQuestionButton.setOnMouseEntered(e -> {
            deleteQuestionButton.setStyle(
                    String.format("-fx-background-color: %s; -fx-text-fill: %s;", QUESTION_ACTION_COLOR, HOVER_BUTTON_COLOR));
        });
        deleteQuestionButton.setOnMouseExited(e -> {
            deleteQuestionButton.setStyle(String.format("-fx-background-color: %s;", HOVER_BUTTON_COLOR));
        });
        deleteQuestionButton.setOnMouseClicked(e -> {
            System.out.println(e); // Delete question on id
        });

        actionRow.getChildren().addAll(addAnswerButton, deleteQuestionButton);

        questionBox.setPadding(new Insets(17.5));
        questionBox.setStyle(
                "-fx-border-width: 10; -fx-border-color: black; -fx-border-style: hidden hidden solid hidden;");
        questionBox.getChildren().addAll(questionText, answerBox, actionRow);

        questionList.setMinWidth(1200);
        questionList.getChildren().addAll(questionBox);

        questionScrollPane
                .setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        questionScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        questionScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        questionScrollPane.setContent(questionList);
        /**
         * End Temporary
         */

        VBox root = new VBox(25);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(25));
        try {
            backgroundInput = new FileInputStream("./src/main/resources/background.png");
            backgroundImage = new Image(backgroundInput);
            root.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        root.getChildren().addAll(logoHeader, headerContainer, questionScrollPane);

        return new Scene(root);
    }

    @Override
    public void update(ArrayList<Node> nodes) {

    }
}
