package Classes;

import brief8.todolist.HelloController;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;

public class Task {

  private   Integer IdTask;
  private   String Title;
  private   String Description;
  private   String Status;
  private   Date Deadline;
  private Integer Idct;
  private Integer Idur;

  Button Update,Edit;




  public Integer getIdTask() {
    return IdTask;
  }

  public void setIdTask(Integer idTask) {
    IdTask = idTask;
  }


  public String getTitle() {
    return Title;
  }

  public void setTitle(String title) {
    Title = title;
  }


  public String getDescription() {
    return Description;
  }

  public void setDescription(String description) {
    Description = description;
  }


  public String getStatus() {
    return Status;
  }

  public void setStatus(String status) {
    Status = status;
  }


  public Date getDeadline() {
    return Deadline;
  }

  public void setDeadline(Date deadline) {
    this.Deadline = deadline;
  }


  public Integer getIdct() {
    return Idct;
  }

  public void setIdct(Integer idCat) {
    this.Idct = idCat;
  }


  public Integer getIdur() {
    return Idur;
  }

  public void setIdur(Integer idUser) {
    this.Idur = idUser;
  }


  public Button getUpdate() {return Update;}

  public void setUpdate(Button update) {Update = update;}


  public Button getEdit() {return Edit;}

  public void setEdit(Button edit) {Edit = edit;}



  public Task() {}

  public Task(Integer idTask) {
    IdTask = idTask;
  }
  public Task(String title, String description, String status, Date deadline, Integer idct) {
    super();
    this.Title = title;
    this.Description = description;
    this.Status = status;
    this.Deadline = deadline;
    this.Idct = idct;
  }

  public Task(String title, String description, String status, Date deadline, Integer idct, Integer idur, Button update, Button edit) {
    this.Title = title;
    this.Description = description;
    this.Status = status;
    this.Deadline = deadline;
    this.Idct = idct;
    this.Idur = idur;
    this.Update = update;
    this.Edit = edit;
  }

  public Task(Integer idTask, String title, String description, String status, Date deadline, Integer idct, Integer idur, Button update, Button edit) {
    this.IdTask = idTask;
    this.Title = title;
    this.Description = description;
    this.Status = status;
    this.Deadline = deadline;
    this.Idct = idct;
    this.Idur = idur;
    this.Update = update;
    this.Edit = edit;

    update.setOnAction((e) -> {
      ObservableList<Task> tasks = HelloController.table_info.getSelectionModel().getSelectedItems();
      Iterator var3 = tasks.iterator();

      while(var3.hasNext()) {
        Task task = (Task)var3.next();
        if (task.getUpdate() == update) {
//          System.out.println("Id: " + task.getIdTask());
          System.out.println("Title: " + task.getTitle());
          System.out.println("Description: " + task.getDescription());
          System.out.println("Status: " + task.getStatus());
          System.out.println("Deadline: " + task.getDeadline());
          /*System.out.println("IdCat: " + task.getIdct());
          System.out.println("IdUser: " + task.getIdur());*/
        }
      }

    });
  }

  @Override
  public String toString() {
    return "Task :" +
            "IdTask='" + IdTask + '\'' +
            ", Title='" + Title + '\'' +
            ", Description='" + Description + '\'' +
            ", Status='" + Status + '\'' +
            ", Deadline=" + Deadline +
            ", IdCat='" + Idct + '\'' +
            ", IdUser='" + Idur + '\'' +
            '}';
  }
}
