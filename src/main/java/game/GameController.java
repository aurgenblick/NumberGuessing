package game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@TODO p2p app


public class GameController implements Initializable {


    @FXML
    public ComboBox<String> level;
    @FXML
    public ComboBox<String> difficulty;
    @FXML
    public Button p2pGame;
    @FXML
    public Button startButton;
    @FXML
    public TextArea committedNumbers;
    @FXML
    public TextField guessField;
    @FXML
    public Label shotsLeft;
    @FXML
    public Button guessButton;

    private Integer shots = 5;
    private int maxNum = 10;
    private int randomNum = 0;


    public void initialize(URL url, ResourceBundle resourceBundle) {

        /**
         * The game control elements are the following .
         * <p>
         * Drop-down menu for choosing the range of the numbers -
         * implemented via ComboBox.
         */
        level.setItems(
                FXCollections.observableArrayList("from 1 to 10", "from 1 to 100", "from 1 to 1000")
        );
        difficulty.setItems(FXCollections.observableArrayList("given 5 shots", "given 2 shots", "given 1 shot"));
        guessField.setEditable(false);
        guessButton.setVisible(false);
        shotsLeft.setStyle("-fx-alignment:center;");
        shotsLeft.setVisible(false);
    }

    @FXML
    protected void setLevel(ActionEvent actionEvent) {
        if (level.getValue().equals("from 1 to 10")) {
            difficulty.setItems(FXCollections.observableArrayList("given 5 shots", "given 2 shots", "given 1 shot"));
            maxNum = 10;
//            difficulty.setValue("given 5 shots");
//            shots = 5;
        } else if (level.getValue().equals("from 1 to 100")) {
            difficulty.setItems(FXCollections.observableArrayList("given 50 shots",
                    "given 20 shots",
                    "given 5 shots"));
            maxNum = 100;
//            difficulty.setValue("given 50 shots");
//            shots = 50;
        } else {
            difficulty.setItems(FXCollections.observableArrayList("given 500 shots",
                    "given 200 shots",
                    "given 7 shots"));
            maxNum = 1000;
//            difficulty.setValue("given 500 shots");
//            shots = 500;
        }
    }

    /**
     * Drop-down menu for choosing the difficulty depending on the range -
     * implemented via ComboBox.
     **/
    @FXML
    protected void setDifficulty(ActionEvent actionEvent) {
        if (difficulty.getValue() != null) {
            switch (difficulty.getValue()) {
                case "given 5 shots":
                    shots = 5;
                    break;
                case "given 2 shots":
                    shots = 2;
                    break;
                case "given 1 shot":
                    shots = 1;
                    break;
                case "given 50 shots":
                    shots = 50;
                    break;
                case "given 20 shots":
                    shots = 20;
                    break;
                case "given 500 shots":
                    shots = 500;
                    break;
                case "given 200 shots":
                    shots = 200;
                    break;
                case "given 7 shots":
                    shots = 7;
                    break;
            }
        }
    }


    /**
     * Button for starting a new game with the currently set properties.
     */
    @FXML
    protected void startNewGame(ActionEvent actionEvent) {
        committedNumbers.clear();
        setLevel(new ActionEvent());
        setDifficulty(new ActionEvent());
        Random rng = new Random();
        randomNum = rng.nextInt(maxNum);
        System.out.println(maxNum + " " + shots + " " + randomNum);
        guessField.clear();
        guessField.setStyle("");
        guessField.setEditable(true);
        guessButton.setVisible(true);
        shotsLeft.setText(shots.toString() + " shots left\n ");
        shotsLeft.setVisible(true);
        guessField.requestFocus();
    }

    /**
     * Text Area for showing previously entered numbers.
     */

    static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public void guessEnter(KeyEvent e) {
        if (e.getCode().equals(KeyCode.ENTER)) {
            guess(new ActionEvent());
        }
    }

    public void guess(ActionEvent actionEvent) {
        shots--;
        try {
            int guessNum = Integer.parseInt(guessField.getText());
            if (guessNum == randomNum) {
                guessButton.setVisible(false);
                guessField.setStyle("-fx-border-color: green; -fx-border-width: 5");
                shotsLeft.setText("GREAT!");
                guessField.setEditable(false);
            } else {
                if (guessField.getText() == null) {
                    committedNumbers.appendText("-\n");
                } else if (guessNum > randomNum) {
                    committedNumbers.appendText("                 <" + guessNum + "\n");
                } else if (guessNum < randomNum) {
                    committedNumbers.appendText("     " + guessNum + "<" + "\n");

                } else if (guessNum == randomNum) {
                    committedNumbers.appendText(String.valueOf(guessNum));
                    shotsLeft.setText("GREAT!\n");
                }
                if (shots == 0) {
                    guessField.setEditable(false);
                    guessButton.setVisible(false);
                    guessField.setStyle("-fx-border-color: red; -fx-border-width: 5");
                    shotsLeft.setText("SORRY!\nThe number was\n");
                    guessField.setText(String.valueOf(randomNum));
                    startButton.requestFocus();
                } else {
                    guessField.clear();
                    guessField.requestFocus();
                    shotsLeft.setText(String.valueOf(shots) + " shots left");
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
