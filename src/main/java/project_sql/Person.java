package project_sql;

public class Person {
    private String name;
    private String surname;
    private int id;
    private String email;

    public Person(String name, String surname, int id, String email) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static void main(String[] args) {

    }

    @Override
    public String toString() {
        return "\n"+"Name: " + name +
                ", Surname: " + surname +
                ", ID: " + id +
                ", email: " + email+"\n" ;
    }
}
