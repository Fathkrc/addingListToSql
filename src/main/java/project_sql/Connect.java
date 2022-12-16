package project_sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Connect {
    private String kullaniciAdi="postgres";
    private String parola="asdasd123";
    private String dbIsmi="postgres";
    private String host="localhost";
    private int port=5432;
    private Statement statement=null;
    private Connection con=null;
    private PreparedStatement preparedStatement=null;

    public static void main(String[] args) {
        Connect bag=new Connect();
        System.out.println();
        // bag.verileriYazdir();
        // bag.preparedVeriGetir(32);
        List<Person> personList=new ArrayList<>();
        personList.add(new Person("Yasar","Karaca",25,"yasar@gmail"));
        personList.add(new Person("Gozde","Karaca",70,"gozde@gmail"));
        personList.add(new Person("Mehmet","Kose",25,"mehmet@gmail"));
        personList.add(new Person("Veli","Gok",25,"veli@gmail"));
        personList.add(new Person("Ahmet","Furuk",25,"ahmet@gmail"));
        System.out.println();
        bag.listEkle(personList);
        bag.baglantiyikes();

    }

    private void baglantiyikes() {

        try {
            preparedStatement.close();
            con.close();
            statement.close();
            System.out.println("Bağlantı kesildi..");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Connect(){
        //jdbc:postgresql://localhost:5432/techproed","postgres","asdasd123"
        //String url=jdbc:postgresql://localhost:5432/techproed","postgres","asdasd123"
        String url="jdbc:postgresql://"+host+":"+port+"/"+dbIsmi;
        try{
            Class.forName("org.postgresql.Driver");
            con= DriverManager.getConnection(url,kullaniciAdi,parola);
            System.out.println("Bağlantı kuruldu..");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver bulunamadı");
        } catch (SQLException e) {
            System.out.println("Bağlantı başarısız");
        }
    }
    public void verileriYazdir(){
        String sorgu="Select * from people";
        try {
            statement=con.createStatement();
            ResultSet rs=statement.executeQuery(sorgu);
            while(rs.next()){
                rs.getString(1);
                String name=rs.getString(1);
                String surname=rs.getString(2);
                int id=rs.getInt(3);
                String email=rs.getString(4);
                System.out.println("ID: "+id+" name : "+ name+" surname : "+surname+" email : "+email);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void listEkle(){
        String url="insert into people(name,surname,id,email)values( ?,?,?,?)";
        try {
            preparedStatement=con.prepareStatement(url);
            preparedStatement.setString(1,"Seyma");
            preparedStatement.setString(2,"Karaca");
            preparedStatement.setInt(3,23);
            preparedStatement.setString(4,"seyma@gmail.com");
            preparedStatement.execute();
            System.out.println("veri eklendi");
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
    public void preparedVeriGetir(int id){
        String sorgu="Select * From people where id=?";
        try {
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setInt(1,id);
           ResultSet rs= preparedStatement.executeQuery();
           while(rs.next()){
               System.out.print(rs.getString(1)+" "
                       +rs.getString(2)+" "
                       +rs.getInt(3)+" " +rs.getString(4));
           }
           rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public  void listEkle(List<Person> list){
        String name;
        String surname;
        int id;
        String email;
        String url="Insert into people(name,surname,id,email)values(?,?,?,?)";
        for (Person w:list) {
            name=w.getName();
            surname= w.getSurname();
            id=w.getId();
            email=w.getEmail();
            try {
                preparedStatement=con.prepareStatement(url);
                preparedStatement.setString(1,name);
                preparedStatement.setString(2,surname);
                preparedStatement.setInt(3,id);
                preparedStatement.setString(4,email);
                preparedStatement.execute();
                System.out.println("List eklendi...");
            } catch (SQLException e) {
                System.out.println("olmadı çocuklar");
            }
        }
    }

}
