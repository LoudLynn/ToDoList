package brief8.todolist;

import Classes.Users;
import Implementation.ImplDaoUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @javafx.fxml.FXML
    private AnchorPane SignUpPane;
    @javafx.fxml.FXML
    private TextField SignUpLastName;
    @javafx.fxml.FXML
    private TextField SignUpFirstName;
    @javafx.fxml.FXML
    private TextField SignUpUserName;
    @javafx.fxml.FXML
    private PasswordField SignUpPassword;
    @javafx.fxml.FXML
    private Button SignUpBtn;
    @javafx.fxml.FXML
    private Button SignInMove;


    @javafx.fxml.FXML
    private AnchorPane SignInPane;

    @javafx.fxml.FXML
    private TextField SignInUserName;
    @javafx.fxml.FXML
    private PasswordField SignInPassword;
    @javafx.fxml.FXML
    private Button LoginBtn;

    @javafx.fxml.FXML
    private Label errorSignIn;



    public ImplDaoUser imu = new ImplDaoUser();
    public Users user = new Users();
    public static String FirstName;
    public static String LastName;
    public static String UserName;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @javafx.fxml.FXML
    public void MoveToSignIn(ActionEvent actionEvent) {
        SignUpPane.setVisible(false);
        SignInPane.setVisible(true);

    }

    @javafx.fxml.FXML
    public void SignUpOnClick(ActionEvent actionEvent) {
        if (SignUpLastName.getText().isEmpty()) {
            errorSignIn.setText("Last name is empty");
            return;
        }
        if (SignUpFirstName.getText().isEmpty()) {
            errorSignIn.setText("First name is empty");
            return;
        }
        if (SignUpUserName.getText().isEmpty()) {
            errorSignIn.setText("User name is empty");
            return;
        }
        if (SignUpPassword.getText().isEmpty()) {
            errorSignIn.setText("Password is empty");
            return;
        }

        try {
            user.setLastName(SignUpLastName.getText());
            user.setFirstName(SignUpFirstName.getText());
            user.setLogin(SignUpUserName.getText());
            user.setPwd(SignUpPassword.getText());

            imu.insert(user);
            FirstName=user.getFirstName();
            LastName=user.getLastName();
            UserName=user.getLogin();


            SignUpBtn.getScene().getWindow().hide();
            DashBoard();

        } catch (Exception e) {
            errorSignIn.setText("Not saved");
            e.printStackTrace();
        }

    }

    @javafx.fxml.FXML
    public void LoginSignInOnClick(ActionEvent actionEvent) {
        if (SignInUserName.getText().isEmpty()) {
            errorSignIn.setText("User name is empty");
            return;
        }
        if (SignInPassword.getText().isEmpty()) {
            errorSignIn.setText("Password is empty");
            return;
        }
        String login=SignInUserName.getText();
        String pwd=SignInPassword.getText();
        try {
            imu.SignIn(user,login,pwd);
            FirstName=user.getFirstName();
            LastName=user.getLastName();
            UserName=SignInUserName.getText();

            LoginBtn.getScene().getWindow().hide();
            DashBoard();

        } catch (Exception e) {
            errorSignIn.setText("Invalide Username or Password");
            e.printStackTrace();
        }

    }

    public void DashBoard() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(new Scene(root));
            stage.setTitle("My List To Do");
            Image icon =new Image(getClass().getResourceAsStream("icon.png"));
            stage.getIcons().add(icon);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
