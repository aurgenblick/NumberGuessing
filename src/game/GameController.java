package game;

import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

//@TODO p2p app
import javafx.fxml.Initializable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class GameController {

    @FXML
    ComboBox<Integer> maxNumber;
    @FXML
    TextField committedNumbers;
    @FXML
    Button commitButton;
    @FXML
    ListView<Integer> commitedNumbers = new ListView<>();

    public void commitNumber(KeyEvent keyEvent) {
        if (keyEvent.isControlDown() && keyEvent.getCode().equals(KeyCode.ENTER)){
            commit();
        }
    }

    public void commitButton(MouseEvent guessEvent) {
        commit();
        committedNumbers.requestFocus();
    }

    private void commit(){

    }
}
