package brief8.todolist;

import Classes.Category;
import Classes.Task;
import Classes.Users;
import Implementation.ImplDaoCategory;
import Implementation.ImplDaoTask;
import Implementation.ImplDaoUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.util.Callback;

public class MainController implements Initializable {

//Home///////////////////////////////////////////////////
    @FXML
    private AnchorPane PaneHome;
    @FXML
    private ListView <Task>listViewToDo;
    @FXML
    private ListView <Task> listViewDoing;
    @FXML
    private ListView <Task>listViewDone;


    @FXML
    private Button UserAccountBtn;
    @FXML
    private Button TaskManagementBtn;
    @FXML
    private Button CategoriesManegementBtn;

//Task//////////////////////////////////////////////////////
    public static TableView<Task> table_info;
    public static ObservableList<Task> data_table;
    @FXML
    private TableView<Task> tableTask;
    @FXML
    private TableColumn<Task,String> Ctitle;
    @FXML
    private TableColumn <Task,String> Cdescription;
    @FXML
    private TableColumn <Task,String> Cstatus;
    @FXML
    private TableColumn <Task,String> Cdeadline;
    @FXML
    private TableColumn <Task,String> Ccategories;
    @FXML
    private TableColumn<Task, String> CeditTask;
    @FXML
    private TableColumn <Task,Button> CdeleteTask;

    private boolean update;
    @FXML
    private AnchorPane PaneTask;

    @FXML
    private ComboBox ComboStatus;
    @FXML
    private TextField descTxt;
    @FXML
    private ComboBox ComboCategory;
    @FXML
    private DatePicker deadlineTxt;
    @FXML
    private TextField titleTxt;
    @FXML
    private Button btnAddTask;

 //category////////////////////////////////////////////////////////
    @FXML
    private TextField nameCattxt;

    @FXML
    private Button btnAddCategory;

    @FXML
    private AnchorPane PaneCategory;
    @FXML
    private TableView <Category> tableCategory;
    @FXML
    private TableColumn <Category,String> CnameCat;
    @FXML
    private TableColumn <Category,Integer> CidCat;
    @FXML
    private Button btnDeleteCategory;
    @FXML
    private TextField txtIdCatUpdate;
    @FXML
    private TextField txtNameCatUpdate;
    @FXML
    private Button btnUpdateCategory;

    //Account//////////////////////////////////////////////////////

    @FXML
    private AnchorPane PaneAccount;


    @FXML
    private VBox VboxToDisplayAccountInfo;
    @FXML
    private Label LabelLastName;
    @FXML
    private Label LabelFirstName;
    @FXML
    private Label LabelUserName;
    @FXML
    private Label LabelPassword;
    @FXML
    private Label LabelId;



    ObservableList<Task>  TaskList = FXCollections.observableArrayList();
    ObservableList ComboBoxCategory = FXCollections.observableArrayList();
    public ImplDaoTask imt = new ImplDaoTask();
    public ImplDaoUser imu =new ImplDaoUser();
    public ImplDaoCategory imc =new ImplDaoCategory();

    public Task task=new Task();
    public Users user =new Users();
    public Category category=new Category();
    @FXML
    private Button HomeBtn;




    @Deprecated


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        deadlineTxt.setValue(LocalDate.now());
        //refreshTable();
        ShowTask();
        LoadComBoxStatus();
        ShowAccountInfo();
        ShowCategories();
        LoadListViewTaskToDo();
        LoadListViewTaskDoing();
        LoadListViewTaskDone();

    }
////////////////////AccountManagement////////////////////////////////

    public void ShowAccountInfo(){
         String firstname=LoginController.FirstName;
         String lastname=LoginController.LastName;
         String username=LoginController.UserName;

        LabelId.setText(String.valueOf(imu.HaveIdUser(user)));
        LabelFirstName.setText(firstname);
        LabelLastName.setText(lastname);
        LabelUserName.setText(username);
        LabelPassword.setText("------");
    }


//////////////////////////////////////////////////////////////////////////



///////////////TaskManagement//////////////////////////////////////////////////////
    @FXML
    public void addTaskOnClick(ActionEvent actionEvent) {

        try {
            task.setTitle(titleTxt.getText());
            task.setDescription(descTxt.getText());
            task.setStatus(String.valueOf(ComboStatus.getValue()));
            task.setDeadline(deadlineTxt.getValue());
            task.setIdct(1);
            //task.setIdur(imu.HaveIdUser(user));

            imt.insert(task);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void getDate(ActionEvent actionEvent) {
        LocalDate myDate =deadlineTxt.getValue();
        String FormatterDate = myDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
   /* @Deprecated
    public void addOnClick(ActionEvent actionEvent) {
    }*/
    public void LoadComBoxStatus(){
        ObservableList<String> list = FXCollections.observableArrayList("To do", "Doing", "Done");
        ComboStatus.setItems(list);
    }
    public void LoadComBoxCategories(){
        ComboCategory.getItems().clear();
        ObservableList<Category> list =ComBoxCategories();
        ComboCategory.setItems(list);
    }
    public ObservableList<Category> ComBoxCategories(){
        ObservableList <Category> ComboBoxCategory = FXCollections.observableArrayList();
        try {
            imc.LoadCategoriesComBox(category,ComboBoxCategory);

        }catch (Exception e){
            e.printStackTrace();
        }
        return ComboBoxCategory;
    }

    public void ShowTask(){

        ObservableList<Task> list = imt.getList();
        try{
        Ctitle.setCellValueFactory(new PropertyValueFactory<Task,String>("title"));
        Cdescription.setCellValueFactory(new PropertyValueFactory<Task,String>("description"));
        Cstatus.setCellValueFactory(new PropertyValueFactory<Task,String>("status"));
        Cdeadline.setCellValueFactory(new PropertyValueFactory<Task,String>("deadline"));
        Ccategories.setCellValueFactory(new PropertyValueFactory<Task,String>("idct"));

        tableTask.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ShowTasks(){
        refreshTable();
        ObservableList<Task> list = imt.getList();
        Ctitle.setCellValueFactory(new PropertyValueFactory<Task,String>("title"));
        Cdescription.setCellValueFactory(new PropertyValueFactory<Task,String>("description"));
        Cstatus.setCellValueFactory(new PropertyValueFactory<Task,String>("status"));
        Cdeadline.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        Ccategories.setCellValueFactory(new PropertyValueFactory<Task,String>("nameC"));
        tableTask.setItems(list);

        Callback<TableColumn<Task, String>, TableCell<Task, String>> cellFoctory = (TableColumn<Task, String> param) -> {
            // make cell containing buttons
            final TableCell<Task, String> cell = new TableCell<Task, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#FE4C40;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#FFC536;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                task = tableTask.getSelectionModel().getSelectedItem();
                                imt.delete(task);
                                refreshTable();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }





                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            task = tableTask.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("MainView.fxml"));
                            try {
                                loader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            MainController TheController = loader.getController();
                            TheController.setUpdate(true);
                            TheController.setTextField(task.getTitle(), task.getDescription(),
                                    task.getStatus(), task.getDeadline(), String.valueOf(task.getIdct()));



                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        CeditTask.setCellFactory(cellFoctory);
        tableTask.setItems(TaskList);


    }
    void setUpdate(boolean b) {
        this.update = b;

    }
    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
    void setTextField(String title, String description, String status, LocalDate deadline, String nameC) {

        titleTxt.setText(title);
        descTxt.setText(description);
        ComboStatus.setValue(status);
        deadlineTxt.setValue(LOCAL_DATE("23-03-2022"));
        ComboCategory.setValue(nameC);

    }
    public void refreshTable() {
        try {
            TaskList.clear();

             imt.getList();


        } catch (Exception e) {
           e.printStackTrace();
        }

    }
////////////////////DashboardManagement/////////////////////////////////////////////////////
    @FXML
    public void TaskManagementClick(ActionEvent actionEvent) {
        PaneHome.setVisible(false);
        PaneAccount.setVisible(false);
        PaneTask.setVisible(true);
        PaneCategory.setVisible(false);
    }

    @FXML
    public void UserAccountClick(ActionEvent actionEvent) {
        PaneHome.setVisible(false);
        PaneAccount.setVisible(true);
        PaneTask.setVisible(false);
        PaneCategory.setVisible(false);
    }

    @FXML
    public void CategoriesManegementClick(ActionEvent actionEvent) {
        PaneHome.setVisible(false);
        PaneAccount.setVisible(false);
        PaneTask.setVisible(false);
        PaneCategory.setVisible(true);
    }

    @FXML
    public void HomeDashboardClick(ActionEvent actionEvent) {
        PaneHome.setVisible(true);
        PaneAccount.setVisible(false);
        PaneTask.setVisible(false);
        PaneCategory.setVisible(false);
    }

    public void LoadListViewTaskToDo(){
        listViewToDo.getItems().clear();
        ObservableList<Task> list =TaskToDo();
        listViewToDo.setItems(list);
    }
    public ObservableList<Task> TaskToDo(){
        ObservableList <Task> TaskToDoList = FXCollections.observableArrayList();
        try {
            imt.LoadToDoList(task,TaskToDoList);

        }catch (Exception e){
            e.printStackTrace();
        }
        return TaskToDoList;
    }
    public void LoadListViewTaskDoing(){
        listViewDoing.getItems().clear();
        ObservableList<Task> list =TaskDoing();
        listViewDoing.setItems(list);
    }
    public ObservableList<Task> TaskDoing(){
        ObservableList <Task> TaskDoingList = FXCollections.observableArrayList();
        try {
            imt.LoadDoingList(task,TaskDoingList);

        }catch (Exception e){
            e.printStackTrace();
        }
        return TaskDoingList;
    }
    public void LoadListViewTaskDone(){
        listViewDone.getItems().clear();
        ObservableList<Task> list =TaskDone();
        listViewDone.setItems(list);
    }
    public ObservableList<Task> TaskDone(){
        ObservableList <Task> TaskDoneList = FXCollections.observableArrayList();
        try {
            imt.LoadToDoList(task,TaskDoneList);

        }catch (Exception e){
            e.printStackTrace();
        }
        return TaskDoneList;
    }
//////////////categogiesManagement/////////////////////////////////////////////////////////////
    @FXML
    public void addCategoryOnClick(ActionEvent actionEvent) {
        try{
            category=new Category(nameCattxt.getText());
            imc.insert(category);
            clearCategoryTxtField();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void updateCategoryOnClick(ActionEvent actionEvent) {
        try{
            int Id = Integer.parseInt(txtIdCatUpdate.getText());
            category=new Category(Id);
            category.setNameCat(txtNameCatUpdate.getText());
            imc.update(category);
            clearCategoryTxtField();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void clearCategoryTxtField(){
        txtIdCatUpdate.setText("");
        txtNameCatUpdate.setText("");
    }

    @FXML
    public void deleteCategoryOnClick(ActionEvent actionEvent) {
        try{
            int Id = Integer.parseInt(txtIdCatUpdate.getText());
            category=new Category(Id);
            imc.delete(category);
            clearCategoryTxtField();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void ShowCategories(){

        ObservableList<Category> list = imc.getList();
        try{
            CidCat.setCellValueFactory(new PropertyValueFactory<Category,Integer>("idCat"));
            CnameCat.setCellValueFactory(new PropertyValueFactory<Category,String>("nameCat"));

            tableCategory.setItems(list);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void OnMouseClickCategory(Event event) {
        category=tableCategory.getSelectionModel().getSelectedItem();

        txtIdCatUpdate.setText(String.valueOf(category.getIdCat()));
        txtNameCatUpdate.setText(category.getNameCat());

    }


////////////////////////////////////////////////////////////////////////////////////////

}