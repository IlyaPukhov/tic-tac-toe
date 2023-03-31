module com.ilyap.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    exports com.ilyap.tictactoe;
    opens com.ilyap.tictactoe to javafx.fxml;
    exports com.ilyap.tictactoe.controllers;
    opens com.ilyap.tictactoe.controllers to javafx.fxml;
    exports com.ilyap.tictactoe.interfaces;
    opens com.ilyap.tictactoe.interfaces to javafx.fxml;
    exports com.ilyap.tictactoe.exceptions;
    opens com.ilyap.tictactoe.exceptions to javafx.fxml;
    exports com.ilyap.tictactoe.utils;
    opens com.ilyap.tictactoe.utils to javafx.fxml;
}