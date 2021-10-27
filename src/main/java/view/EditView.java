package view;

import Admin.AdminVariables;
import controller.EditController;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import model.Advice;
import model.Answer;
import model.Edit;
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
    private EditController editController = EditController.getInstance();

    private final double BUTTON_PADDING = 12.5;
    private final int HEADER_FONT_SIZE = 64;
    private final int BUTTON_FONT_SIZE = 22;
    private final int QUESTION_TITLE_FONT_SIZE = 22;

    private final int LOGO_WIDTH = 480;
    private final int LOGO_HEIGHT = 128;

    private final String BUTTON_COLOR = "#FFFFFF";
    private final String HOVER_BUTTON_COLOR = "#000000";

    private final String ANSWER_BACKGROUND_COLOR = "#EDECEC";

    private final String QUESTION_ACTION_COLOR = "#9CC2D4";

    private final String FONT_FAMILY = "Arial";

    private VBox questionList = new VBox(10);

    public EditView() {
        editController.registerObserver(this);
        editController.loadAllNodes();
    }

    public Scene getEditScene() {

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
        addButton.setOnAction(e -> {
            editController.addQuestion();
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
        applyButton.setOnAction(e -> {
            editController.submitChanges();
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

        ScrollPane questionScrollPane = new ScrollPane();

        // TODO: Add alit if questionBoxes
        questionList.setMinWidth(1200);

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

    public Scene getAdviceEditScene() {
        VBox root = new VBox(25);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(25));

        return new Scene(root);
    }

    private VBox createQuestionBox(ArrayList<Node> nodes, Node node) {
        VBox questionBox = new VBox(10);
        questionBox.setPadding(new Insets(17.5));
        questionBox.setStyle("-fx-border-width: 10; -fx-border-color: black; -fx-border-style: hidden hidden solid hidden;");
        /**
         * Question box is made out of
         * - questionHeader: HBox
         * - answerBox: VBox
         * - actionRow: HBox
         */

        // Question
        HBox questionHeader = createQuestionHeader(node.getQuestion());
        questionBox.getChildren().add(questionHeader);

        // AnswerBox
        ArrayList<Answer> answers = node.getAnswers();
        for (Answer answer : answers) {
            VBox answerBox = createAnswerBox(nodes, node.getQuestion(), answer);
            questionBox.getChildren().add(answerBox);
        }

        // ActionRow
        HBox actionRow = createActionRow(node.getQuestion().getId());
        questionBox.getChildren().add(actionRow);
        return questionBox;
    }

    private HBox createQuestionHeader(Question question) {
        HBox header = new HBox(5);
        Text questionID = new Text(question.getId() + ": ");
        TextField questionField = new TextField(question.getValue());

        header.setAlignment(Pos.CENTER_LEFT);
        header.getChildren().addAll(questionID, questionField);

        questionID.setFont(Font.font(FONT_FAMILY, FontWeight.BLACK, QUESTION_TITLE_FONT_SIZE));
        HBox.setHgrow(questionField, Priority.ALWAYS);
        questionField.setFont(Font.font(FONT_FAMILY, FontWeight.BLACK, QUESTION_TITLE_FONT_SIZE));
        questionField.setFocusTraversable(false);

        questionField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                editController.changeQuestion(question.getId(), questionField.getText());
            }
        });

        return header;
    }

    private VBox createAnswerBox(ArrayList<Node> nodes, Question question, Answer answer) {
        VBox answerBox = new VBox(5);

        TextField answerText = new TextField(answer.getValue()); // Get answer value
        HBox.setHgrow(answerText, Priority.ALWAYS);
        answerText.setFont(Font.font(FONT_FAMILY, FontWeight.BLACK, QUESTION_TITLE_FONT_SIZE));
        answerText.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                editController.changeAnswer(question.getId(), answer.getId(), answerText.getText());
            }
        });

        Button deleteAnswerButton = new Button("");

        try {
            FileInputStream deleteInput = new FileInputStream("./src/main/resources/delete_btn.png");
            Image deleteImage = new Image(deleteInput);
            ImageView deleteImageView = new ImageView(deleteImage);
            deleteAnswerButton.setGraphic(deleteImageView);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        deleteAnswerButton
                .setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        deleteAnswerButton.setAlignment(Pos.CENTER_RIGHT);
        deleteAnswerButton.setOnMouseClicked(e -> {
            editController.removeAnswer(question.getId(), answer.getId());
        });

        HBox deleteAnswerContainer = new HBox();
        deleteAnswerContainer.setAlignment(Pos.CENTER_RIGHT);
        deleteAnswerContainer.getChildren().add(deleteAnswerButton);

        HBox firstRow = new HBox();
        firstRow.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(deleteAnswerContainer, Priority.ALWAYS);
        firstRow.getChildren().addAll(answerText, deleteAnswerContainer);

        Text referText = new Text("Refereer door naar vraag:");
        referText.setFont(Font.font(FONT_FAMILY, FontWeight.BLACK, 18));

        // TODO SHOW ALL POSSIBLE QUESTIONS AND ADVICES
        ComboBox<Node> referQuestionBox = createReferQuestionBox(nodes, question, answer);

        answerBox.setPadding(new Insets(10));
        answerBox.setAlignment(Pos.CENTER_LEFT);
        answerBox.setBackground(new Background(
                new BackgroundFill(Color.web(ANSWER_BACKGROUND_COLOR), CornerRadii.EMPTY, Insets.EMPTY)));
        answerBox.getChildren().addAll(firstRow, referText, referQuestionBox);

        return answerBox;
    }

    private HBox createActionRow(int questionID) {
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
            editController.addAnswer(questionID);
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
            editController.removeQuestion(questionID);
        });

        actionRow.getChildren().addAll(addAnswerButton, deleteQuestionButton);

        return actionRow;
    }

    private ComboBox<Node> createReferQuestionBox(ArrayList<Node> nodes, Question question, Answer answer) {
        ComboBox<Node> referQuestionBox = new ComboBox<>();

        referQuestionBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Node node) {
                if (node != null && node.getQuestion() != null) {
                    return node.getQuestion().getId() + ": " + node.getQuestion().getValue();
                }

                if (node != null && node.getAdvice() != null) {
                    return "Advice " + node.getAdvice().getId() + ": " + node.getAdvice().getValue();
                }

                return null;
            }

            @Override
            public Node fromString(String s) {
                return null;
            }
        });

        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);

            referQuestionBox.getItems().add(node);

            if (node.getQuestion() != null) {
                // If the next question id is that node, select it
                if (answer.getNextQuestionId() != null && answer.getNextQuestionId() == node.getQuestion().getId()) {
                    referQuestionBox.getSelectionModel().select(i);
                }
            }

            if (node.getAdvice() != null) {
                // If the next question id is that node, select it
                if (answer.getAdviceId() != null && answer.getAdviceId() == node.getAdvice().getId()) {
                    referQuestionBox.getSelectionModel().select(i);
                }
            }
        }

        referQuestionBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (newValue.getAdvice() != null) {
                editController.changeReference(question.getId(), answer.getId(), newValue.getAdvice());
            }
            if (newValue.getQuestion() != null) {
                editController.changeReference(question.getId(), answer.getId(), newValue.getQuestion());
            }
        });

        referQuestionBox.setBackground(
                new Background(new BackgroundFill(Color.web("#C4C4C4"), CornerRadii.EMPTY, Insets.EMPTY)));
        referQuestionBox.setStyle("-fx-font-weight: bold;");

        return referQuestionBox;
    }

    private VBox createAdviceBox(Advice advice) {
        return new VBox(25);
    }

    private HBox createAdviceHeader() {
        return new HBox(25);
    }

    private VBox createAdviceBody() {
        return new VBox(25);
    }

    private HBox createAdviceAction() {
        return new HBox(25);
    }

    @Override
    public void update(Edit edit) {
        questionList.getChildren().removeAll(questionList.getChildren());

        ArrayList<Node> nodes = edit.getNodes();
        for (Node node : nodes) {
            if (node.getQuestion() != null) {
                VBox questionBox = createQuestionBox(nodes, node);
                questionList.getChildren().add(questionBox);
            }

            if (node.getAdvice() != null) {
                //TODO AdviceBox?
            }
        }
    }
}
