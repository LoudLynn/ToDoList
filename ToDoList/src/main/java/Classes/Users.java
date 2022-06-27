package Classes;

public class Users {
    Integer idUser;
    String lastName;
    String firstName;
    String login;
    String pwd;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Users() {
        super();
    }

    public Users(Integer idUser) {
        this.idUser = idUser;
    }

    public Users(Integer idUser, String lastName, String firstName, String login, String pwd) {
        this.idUser = idUser;
        this.lastName = lastName;
        this.firstName = firstName;
        this.login = login;
        this.pwd = pwd;
    }

    public Users(String login, String pwd) {
        this.login = login;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "Users :" +
                "idUser='" + idUser + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", login='" + login + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
