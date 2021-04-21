package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SecondaryController implements Initializable {

    @FXML
    private Label balanceId;

    @FXML
    private TextField inputField;

    @FXML
    private Button withdrawButton;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        balanceId.setText(Double.toString(Singleton.getInstance().getLoggedIn().getBalance()));
    }

    @FXML
    private void withdraw() {
        double withdrawAmount = Double.parseDouble(inputField.getText());
        if (Singleton.getInstance().getLoggedIn().getBalance() >= withdrawAmount) {
            Singleton.getInstance().getLoggedIn().setBalance(Singleton.getInstance().getLoggedIn().getBalance() - withdrawAmount);
            balanceId.setText(Double.toString(Singleton.getInstance().getLoggedIn().getBalance()));
            DBHandler dbHandler = new DBHandler();
            dbHandler.updateBalance(Singleton.getInstance().getLoggedIn().getId(), Singleton.getInstance().getLoggedIn().getBalance());
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Insufficient funds");
            errorAlert.showAndWait();
        }

    }
}