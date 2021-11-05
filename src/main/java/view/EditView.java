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
    private final int BUTTON_FONT_SIZE = 20;
    private final int TITLE_FONT_SIZE = 20;
    private final int FIELD_FONT_SIZE = 16;

    private final int LOGO_WIDTH = 480;
    private final int LOGO_HEIGHT = 128;

    private final int ADD_BUTTON_WIDTH = 25;
    private final int ADD_BUTTON_HEIGHT = 25;

    private final int CONTAINER_SPACING = 25;

    private final int SCROLLPANE_CONTENT_WIDTH = 585;
    private final int SCROLLPANE_CONTAINER_WIDTH = 600;

    private final String BUTTON_COLOR = "#FFFFFF";
    private final String HOVER_BUTTON_COLOR = "#000000";

    private final String ANSWER_BACKGROUND_COLOR = "#EDECEC";

    private final String QUESTION_ACTION_COLOR = "#9CC2D4";

    private final String FONT_FAMILY = "Arial";

    private Text headerText = new Text();
    private VBox questionItemList = new VBox(10);
    private VBox adviceItemList = new VBox(10);
    private VBox questionParent = new VBox();
    private VBox adviceParent = new VBox();
    private VBox headerContainer = new VBox();
    private HBox logoHeader = new HBox();
    private GridPane actionContainer;
    private ScrollPane questionScrollPane = new ScrollPane();
    private ScrollPane adviceScrollPane = new ScrollPane();
    private Button backButton, applyButton;
    private Button addQuestionButton = new Button();
    private Button addAdviceButton = new Button();
    private HBox scrollParent;
    private HBox leftSideScrollPane = new HBox();
    private HBox rightSideScrollPane = new HBox();

    private Text leftScrollPaneHeader = new Text("Vragen & Antwoorden: ");
    private Text rightScrollPaneHeader = new Text("Adviezen: ");
    private HBox questionActionContainer = new HBox();
    private HBox adviceActionContainer = new HBox();
    private HBox leftScrollPaneHeaderBox = new HBox();
    private HBox rightScrollPaneHeaderBox = new HBox();

    private FileInputStream logoInput, backgroundInput, deleteInput, addInput;
    private Image logoImage, backgroundImage, deleteImage, addImage;
    private ImageView logoImageView, deleteImageView, addQuestionImageView, addAdviceImageView;

    public EditView() {
        try {
            logoImage = new Image(getClass().getClassLoader().getResourceAsStream("logo.png"), LOGO_WIDTH, LOGO_HEIGHT, true, false);
            logoImageView = new ImageView(logoImage);

            backgroundImage = new Image(getClass().getClassLoader().getResourceAsStream("background.png"));

            addImage = new Image(getClass().getClassLoader().getResourceAsStream("add_btn.png"));
            addQuestionImageView = new ImageView(addImage);
            addQuestionImageView.setFitWidth(ADD_BUTTON_WIDTH);
            addQuestionImageView.setFitHeight(ADD_BUTTON_HEIGHT);

            addAdviceImageView = new ImageView(addImage);
            addAdviceImageView.setFitWidth(ADD_BUTTON_WIDTH);
            addAdviceImageView.setFitHeight(ADD_BUTTON_HEIGHT);

            logoHeader.setBackground(
                    new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
            logoHeader.getChildren().add(logoImageView);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        editController.registerObserver(this);
        editController.initialize();
    }

    public Scene getEditScene() {
        headerText.setText("Vragenlijst");
        headerText.setFont(Font.font(FONT_FAMILY, FontWeight.NORMAL, HEADER_FONT_SIZE));
        headerText.setFill(Color.WHITE);
        headerText.setTextAlignment(TextAlignment.LEFT);

        backButton = new Button("Terug");
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

        applyButton = new Button("Bevestigen");
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

        actionContainer = new GridPane();
        actionContainer.setHgap(CONTAINER_SPACING);
        actionContainer.setAlignment(Pos.CENTER_RIGHT);
        actionContainer.add(backButton, 0, 0);
        actionContainer.add(applyButton, 1, 0);

        headerContainer.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(actionContainer, Priority.ALWAYS);
        headerContainer.getChildren().addAll(headerText, actionContainer);

        leftScrollPaneHeader.setFill(Color.BLACK);
        leftScrollPaneHeader.setTextAlignment(TextAlignment.CENTER);
        leftScrollPaneHeader.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, TITLE_FONT_SIZE));

        rightScrollPaneHeader.setFill(Color.BLACK);
        rightScrollPaneHeader.setTextAlignment(TextAlignment.CENTER);
        rightScrollPaneHeader.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, TITLE_FONT_SIZE));

        questionActionContainer.setAlignment(Pos.CENTER_RIGHT);
        questionActionContainer.getChildren().add(addQuestionButton);

        adviceActionContainer.setAlignment(Pos.CENTER_RIGHT);
        adviceActionContainer.getChildren().add(addAdviceButton);

        leftScrollPaneHeaderBox.setPadding(new Insets(0, 0, 0, 10));
        leftScrollPaneHeaderBox.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(questionActionContainer, Priority.ALWAYS);
        leftScrollPaneHeaderBox
                .setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        leftScrollPaneHeaderBox.getChildren().addAll(leftScrollPaneHeader, questionActionContainer);

        rightScrollPaneHeaderBox.setPadding(new Insets(0, 0, 0, 10));
        rightScrollPaneHeaderBox.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(adviceActionContainer, Priority.ALWAYS);
        rightScrollPaneHeaderBox
                .setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        rightScrollPaneHeaderBox.getChildren().addAll(rightScrollPaneHeader, adviceActionContainer);

        addQuestionButton.setGraphic(addQuestionImageView);
        addQuestionButton.setAlignment(Pos.CENTER_RIGHT);
        addQuestionButton
                .setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        addQuestionButton.setOnMouseClicked(e -> {
            editController.addQuestion();
        });

        addAdviceButton.setGraphic(addAdviceImageView);
        addAdviceButton.setAlignment(Pos.CENTER_RIGHT);
        addAdviceButton
                .setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        addAdviceButton.setOnMouseClicked(e -> {
            editController.addAdvice();
        });

        questionItemList.setPadding(new Insets(0, 0, 10, 0));

        adviceItemList.setPadding(new Insets(0, 0, 10, 0));

        questionScrollPane
                .setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        questionScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        questionScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        questionScrollPane.setMinWidth(SCROLLPANE_CONTAINER_WIDTH);
        questionScrollPane.setMaxWidth(SCROLLPANE_CONTAINER_WIDTH);
        questionScrollPane.setContent(questionItemList);

        leftSideScrollPane.setAlignment(Pos.BASELINE_LEFT);
        leftSideScrollPane.getChildren().add(questionScrollPane);

        adviceScrollPane
                .setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        adviceScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        adviceScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        adviceScrollPane.setMinWidth(SCROLLPANE_CONTAINER_WIDTH);
        adviceScrollPane.setMaxWidth(SCROLLPANE_CONTAINER_WIDTH);
        adviceScrollPane.setContent(adviceItemList);

        rightSideScrollPane.setAlignment(Pos.BASELINE_RIGHT);
        rightSideScrollPane.getChildren().add(adviceScrollPane);

        questionParent.setAlignment(Pos.TOP_CENTER);
        questionParent.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox.setHgrow(questionParent, Priority.ALWAYS);

        adviceParent.setAlignment(Pos.TOP_CENTER);
        adviceParent.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox.setHgrow(adviceParent, Priority.ALWAYS);

        scrollParent = new HBox(CONTAINER_SPACING);
        scrollParent.getChildren().addAll(questionParent, adviceParent);

        VBox root = new VBox(CONTAINER_SPACING);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(CONTAINER_SPACING));
        root.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        root.getChildren().addAll(headerContainer, scrollParent);

        return new Scene(root);
    }

    private VBox createQuestionBox(ArrayList<Node> nodes, Node node) {
        VBox questionBox = new VBox(10);
        questionBox.setPadding(new Insets(0, 0, 10, 0));
        questionBox.setStyle(
                "-fx-border-width: 10; -fx-border-color: black; -fx-border-style: hidden hidden solid hidden;");

        HBox questionHeader = createQuestionHeader(node.getQuestion());
        questionBox.getChildren().add(questionHeader);

        ArrayList<Answer> answers = node.getAnswers();
        for (Answer answer : answers) {
            VBox answerBox = createAnswerBox(nodes, node.getQuestion(), answer);
            questionBox.getChildren().add(answerBox);
        }

        HBox actionRow = createQuestionActionRow(node.getQuestion().getId());

        questionBox.setMinWidth(SCROLLPANE_CONTENT_WIDTH);
        questionBox.setMaxWidth(SCROLLPANE_CONTENT_WIDTH);
        questionBox.getChildren().add(actionRow);

        return questionBox;
    }

    private HBox createQuestionHeader(Question question) {
        HBox header = new HBox(5);
        Text questionID = new Text(question.getId() + ": ");
        TextField questionField = new TextField(question.getValue());

        questionID.setFont(Font.font(FONT_FAMILY, FontWeight.BLACK, TITLE_FONT_SIZE));
        HBox.setHgrow(questionField, Priority.ALWAYS);
        questionField.setFont(Font.font(FONT_FAMILY, FontWeight.BLACK, TITLE_FONT_SIZE));
        questionField.setFocusTraversable(false);

        questionField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                editController.changeQuestion(question.getId(), questionField.getText());
            }
        });

        header.setAlignment(Pos.CENTER_LEFT);
        header.getChildren().addAll(questionID, questionField);

        return header;
    }

    private VBox createAnswerBox(ArrayList<Node> nodes, Question question, Answer answer) {
        VBox answerBox = new VBox(5);

        TextField answerText = new TextField(answer.getValue()); // Get answer value
        HBox.setHgrow(answerText, Priority.ALWAYS);
        answerText.setFont(Font.font(FONT_FAMILY, FontWeight.BLACK, TITLE_FONT_SIZE));
        answerText.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                editController.changeAnswer(question.getId(), answer.getId(), answerText.getText());
            }
        });

        try {
            deleteImage = new Image(getClass().getClassLoader().getResourceAsStream("delete_btn.png"));
            deleteImageView = new ImageView(deleteImage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Button deleteAnswerButton = new Button("");
        deleteAnswerButton.setGraphic(deleteImageView);
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

        ComboBox<Node> referQuestionBox = createReferQuestionBox(nodes, question, answer);

        answerBox.setPadding(new Insets(10));
        answerBox.setAlignment(Pos.CENTER_LEFT);
        answerBox.setBackground(new Background(
                new BackgroundFill(Color.web(ANSWER_BACKGROUND_COLOR), CornerRadii.EMPTY, Insets.EMPTY)));
        answerBox.getChildren().addAll(firstRow, referText, referQuestionBox);

        return answerBox;
    }

    private HBox createQuestionActionRow(int questionID) {
        HBox actionRow = new HBox(12.5);
        Button addAnswerButton = new Button("Antwoord toevoegen");
        addAnswerButton.setPadding(new Insets(BUTTON_PADDING / 2));
        addAnswerButton.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FIELD_FONT_SIZE));
        addAnswerButton.setTextFill(Color.WHITE);
        addAnswerButton.setStyle(String.format("-fx-background-color: %s;", HOVER_BUTTON_COLOR));
        addAnswerButton.setOnMouseEntered(e -> {
            addAnswerButton.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;",
                    QUESTION_ACTION_COLOR, HOVER_BUTTON_COLOR));
        });
        addAnswerButton.setOnMouseExited(e -> {
            addAnswerButton.setStyle(String.format("-fx-background-color: %s;", HOVER_BUTTON_COLOR));
        });
        addAnswerButton.setOnMouseClicked(e -> {
            editController.addAnswer(questionID);
        });

        Button deleteQuestionButton = new Button("Verwijderen");
        deleteQuestionButton.setPadding(new Insets(BUTTON_PADDING / 2));
        deleteQuestionButton.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FIELD_FONT_SIZE));
        deleteQuestionButton.setTextFill(Color.WHITE);
        deleteQuestionButton.setStyle(String.format("-fx-background-color: %s;", HOVER_BUTTON_COLOR));
        deleteQuestionButton.setOnMouseEntered(e -> {
            deleteQuestionButton.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;",
                    QUESTION_ACTION_COLOR, HOVER_BUTTON_COLOR));
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
                if (answer.getNextQuestionId() != null && answer.getNextQuestionId() == node.getQuestion().getId()) {
                    referQuestionBox.getSelectionModel().select(i);
                }
            }

            if (node.getAdvice() != null) {
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
        VBox adviceBox = new VBox(10);
        adviceBox.setPadding(new Insets(0, 0, 10, 0));
        adviceBox.setStyle(
                "-fx-border-width: 10; -fx-border-color: black; -fx-border-style: hidden hidden solid hidden;");

        HBox adviceHeader = createAdviceHeader(advice);
        VBox adviceBody = createAdviceBody(advice);

        adviceBox.setMinWidth(SCROLLPANE_CONTENT_WIDTH);
        adviceBox.setMaxWidth(SCROLLPANE_CONTENT_WIDTH);
        adviceBox.getChildren().addAll(adviceHeader, adviceBody);

        return adviceBox;
    }

    private HBox createAdviceHeader(Advice advice) {
        HBox header = new HBox(5);
        Text adviceID = new Text(advice.getId() + ": ");
        TextField adviceField = new TextField(advice.getValue());

        adviceID.setFont(Font.font(FONT_FAMILY, FontWeight.BLACK, TITLE_FONT_SIZE));
        HBox.setHgrow(adviceField, Priority.ALWAYS);
        adviceField.setFont(Font.font(FONT_FAMILY, FontWeight.BLACK, TITLE_FONT_SIZE));
        adviceField.setFocusTraversable(false);

        adviceField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                editController.changeAdvice(advice.getId(), adviceField.getText());
            }
        });

        header.setAlignment(Pos.CENTER_LEFT);
        header.getChildren().addAll(adviceID, adviceField);

        return header;
    }

    private Text createAdviceLabel(String labelString) {
        Text adviceLabel = new Text(labelString);
        adviceLabel.setFont(Font.font(FONT_FAMILY, FontWeight.BLACK, FIELD_FONT_SIZE));

        return adviceLabel;
    }

    private TextField createAdviceTextField(String textFieldString) {
        TextField adviceTextField = new TextField(textFieldString);
        adviceTextField.setPadding(new Insets(5));
        adviceTextField.setFont(Font.font(FONT_FAMILY, FontWeight.BLACK, FIELD_FONT_SIZE));

        return adviceTextField;
    }

    private VBox createAdviceGroup(Text label, TextField field) {
        VBox adviceGroup = new VBox();
        adviceGroup.setPadding(new Insets(5));
        adviceGroup.getChildren().addAll(label, field);

        return adviceGroup;
    }

    private VBox createAdviceBody(Advice advice) {
        VBox advicePropertyBox = new VBox(5);

        Text adviceDescriptionLabel = createAdviceLabel("Description");
        TextField adviceDescriptionField = createAdviceTextField(advice.getDescription());
        adviceDescriptionField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                editController.changeAdviceDescription(advice.getId(), adviceDescriptionField.getText());
            }
        });

        Text adviceInfoURLLabel = createAdviceLabel("Info url");
        TextField adviceInfoURLField = createAdviceTextField(advice.getMoreInfoURL());
        adviceInfoURLField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                editController.changeAdviceMoreInfoURL(advice.getId(), adviceInfoURLField.getText());
            }
        });

        Text adviceVideoURLLabel = createAdviceLabel("Video url");
        TextField adviceVideoURLField = createAdviceTextField(advice.getVideoURL());
        adviceVideoURLField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                editController.changeAdviceVideoURL(advice.getId(), adviceVideoURLField.getText());
            }
        });

        Text adviceOtherFundLabel = createAdviceLabel("Other fund url");
        TextField adviceOtherFundField = createAdviceTextField(advice.getOtherFundURL());
        adviceOtherFundField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                editController.changeAdviceOtherFundURL(advice.getId(), adviceOtherFundField.getText());
            }
        });

        VBox adviceDescriptionGroup = createAdviceGroup(adviceDescriptionLabel, adviceDescriptionField);
        VBox adviceInfoURLGroup = createAdviceGroup(adviceInfoURLLabel, adviceInfoURLField);
        VBox adviceVideoURLGroup = createAdviceGroup(adviceVideoURLLabel, adviceVideoURLField);
        VBox adviceOtherFundGroup = createAdviceGroup(adviceOtherFundLabel, adviceOtherFundField);

        HBox adviceActionRow = createAdviceActionRow(advice.getId());

        HBox firstRow = new HBox();
        firstRow.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(adviceDescriptionGroup, Priority.ALWAYS);
        firstRow.getChildren().addAll(adviceDescriptionGroup);

        VBox secondRow = new VBox();
        secondRow.setAlignment(Pos.CENTER_LEFT);
        secondRow.setBackground(new Background(
                new BackgroundFill(Color.web(ANSWER_BACKGROUND_COLOR), CornerRadii.EMPTY, Insets.EMPTY)));

        secondRow.getChildren().addAll(adviceInfoURLGroup, adviceVideoURLGroup, adviceOtherFundGroup);

        advicePropertyBox.setPadding(new Insets(10));
        advicePropertyBox.setAlignment(Pos.CENTER_LEFT);
        advicePropertyBox.getChildren().addAll(firstRow, secondRow, adviceActionRow);

        return advicePropertyBox;
    }

    private HBox createAdviceActionRow(int adviceID) {
        HBox adviceactionrow = new HBox(12.5);

        Button deleteAdviceButton = new Button("Verwijderen");
        deleteAdviceButton.setPadding(new Insets(BUTTON_PADDING / 2));
        deleteAdviceButton.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FIELD_FONT_SIZE));
        deleteAdviceButton.setTextFill(Color.WHITE);
        deleteAdviceButton.setStyle(String.format("-fx-background-color: %s;", HOVER_BUTTON_COLOR));
        deleteAdviceButton.setOnMouseEntered(e -> {
            deleteAdviceButton.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;",
                    QUESTION_ACTION_COLOR, HOVER_BUTTON_COLOR));
        });
        deleteAdviceButton.setOnMouseExited(e -> {
            deleteAdviceButton.setStyle(String.format("-fx-background-color: %s;", HOVER_BUTTON_COLOR));
        });
        deleteAdviceButton.setOnMouseClicked(e -> {
            editController.removeAdvice(adviceID);
        });

        adviceactionrow.getChildren().addAll(deleteAdviceButton);

        return adviceactionrow;
    }

    @Override
    public void update(Edit edit) {
        questionItemList.getChildren().removeAll(questionItemList.getChildren());
        adviceItemList.getChildren().removeAll(adviceItemList.getChildren());
        questionParent.getChildren().removeAll(questionParent.getChildren());
        adviceParent.getChildren().removeAll(adviceParent.getChildren());

        ArrayList<Node> nodes = edit.getNodes();
        for (Node node : nodes) {
            if (node.getQuestion() != null) {
                VBox questionBox = createQuestionBox(nodes, node);
                questionItemList.getChildren().add(questionBox);
            }

            if (node.getAdvice() != null) {
                VBox adviceBox = createAdviceBox(node.getAdvice());
                adviceItemList.getChildren().add(adviceBox);
            }
        }

        questionParent.getChildren().addAll(leftScrollPaneHeaderBox, leftSideScrollPane);
        adviceParent.getChildren().addAll(rightScrollPaneHeaderBox, rightSideScrollPane);
    }
}
