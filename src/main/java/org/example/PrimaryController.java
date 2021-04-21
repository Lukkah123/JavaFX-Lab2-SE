package org.example;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class PrimaryController implements Initializable {

    DBHandler dbHandler = new DBHandler();

    @FXML
    private TextField nameTextfield, passwordTextfield;

    @FXML
    private void switchToSecondary() throws IOException, SQLException {

        String name = nameTextfield.getText();
        String password = passwordTextfield.getText();

        AccountModel correctUser = dbHandler.getUserByUserNameAndPassword(name, password);

        if (correctUser != null){
            Singleton.getInstance().setLoggedIn(correctUser);
            App.setRoot("secondary");
        } else {
            nameTextfield.setText("");
            passwordTextfield.setText("");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
