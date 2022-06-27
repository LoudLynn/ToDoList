package Classes;

public class Category {
    Integer idCat;
    String nameCat;

    public Integer getIdCat() {
        return idCat;
    }

    public void setIdCat(Integer idCat) {
        this.idCat = idCat;
    }

    public String getNameCat() {
        return nameCat;
    }

    public void setNameCat(String nameC) {
        this.nameCat = nameC;
    }

    public Category() {
    }

    public Category(Integer idCat) {

        this.idCat = idCat;
    }

    public Category(String nameCat) {
        this.nameCat = nameCat;
    }

    public Category(Integer idCat, String nameCat) {
        this.idCat = idCat;
        this.nameCat = nameCat;
    }

    @Override
    public String toString() {
        return "Category :" +
                "idCat='" + idCat + '\'' +
                ", nameC='" + nameCat + '\'' +
                '}';
    }
}
