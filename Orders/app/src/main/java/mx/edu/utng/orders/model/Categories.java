package mx.edu.utng.orders.model;

/**
 * Created by Jorge on 23/02/2017.
 */
public class Categories {

    private String idCategory;
    private String name;
    private String description;
    private int number;

    public Categories(String idCategory, String name, String description, int number) {
        this.idCategory = idCategory;
        this.name = name;
        this.description = description;
        this.number = number;
    }

    public Categories() {
        this("","","",0);
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "idCategory='" + idCategory + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", number=" + number +
                '}';
    }
}
