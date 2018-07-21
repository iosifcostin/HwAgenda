import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaDb {

    final String URL = "jdbc:postgresql://localhost:5432/iosif_costin";
    final String USERNAME = "postgres";
    final String PASSWORD = "asdfasdf";

    public void insert (String nume, String telefon) {

        try {
            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("INSERT INTO agenda (nume, telefon) VALUES (?,?)");
            pSt.setString(1,nume);
            pSt.setString(2,telefon);

            int rowInserted = pSt.executeUpdate();

            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Persoana> read (){
        List<Persoana> rezultat = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM agenda");

            while (rs.next()){
               String nume = rs.getString("nume");
               String telefon = rs.getString("telefon");
               Persoana p = new Persoana();
               p.setNume(nume);
               p.setTelefon(telefon);
               rezultat.add(p);

            }
            return rezultat;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update (String nume, String telefon,String id){

        int id2 = Integer.parseInt(id);
        try {
            Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("UPDATE agenda SET nume=?, telefon=? WHERE id=?");
            pSt.setString(1, nume );
            pSt.setString(2,telefon);
            pSt.setLong(3,id2);
            int rowUpdated = pSt.executeUpdate();

            pSt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id){

        int id2 = Integer.parseInt(id);

        try {
            Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("DELETE FROM agenda WHERE id=? ");
            pSt.setLong(1,id2);

            int rowDeleted = pSt.executeUpdate();
            pSt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
//        List<Persoana> p = new AgendaDb().read();
//        for (Persoana persoana : p){
//            System.out.println(persoana.getNume().trim() + " - " + persoana.getTelefon().trim());

//        new AgendaDb().update("Mariana","07577778777","2");
//        new AgendaDb().delete("4");
        }
    }

