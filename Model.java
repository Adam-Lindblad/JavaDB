import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class Model {

    private Connection connection;
    private Statement statement;
    private ResultSet resultset;

    private DefaultTableModel tableModel;
    private String[][] str;
    private String[] colNames;
    private int colCount, rowCount, i;

    private String url, username, password, sql, id, fname, ename;

    public Model(){
        //Koppla upp DB

        //Ta emot SQLfrågor
        //Bygga View
        //Koppla ihop med Controller


        tableModel = new DefaultTableModel();
    }

    public DefaultTableModel taUtAlla(){

        connectDB();

        try {

            sql = "SELECT * FROM movie;";
            resultset = statement.executeQuery(sql);

            colCount = 0;
            rowCount = 3;
            i = 0;

            while(resultset.next()){
                colCount++;
            }
            resultset.first();

            str = new String[colCount][rowCount];
            colNames = new String[]{"ID", "Förnamn", "Efternamn"};

            while(i < colCount){
                id = resultset.getString("id");
                fname = resultset.getString("fnamn");
                ename = resultset.getString("enamn");

                resultset.next();

                str[i][0] = id;
                str[i][1] = fname;
                str[i][2] = ename;

                i++;
            }

            tableModel = new DefaultTableModel(str, colNames);


        } catch (SQLException err){
            System.out.println(err);
        }

        disconnectDB();


        return tableModel;

    }

    public DefaultTableModel taUtSpecifik(String fNamnInput, String eNamnInput, String idInput, String radioInput){

        connectDB();

        try {

            if(radioInput == "Förnamn") {
                sql = "SELECT * FROM movie WHERE fnamn = \"" + fNamnInput + "\";";
            }
            if (radioInput == "Efternamn"){
                sql = "SELECT * FROM movie WHERE enamn = \"" + eNamnInput + "\";";
            }
            if (radioInput == "id"){
                sql = "SELECT * FROM movie WHERE id = \"" + idInput + "\";";
            }

            statement = connection.prepareStatement(sql);
            resultset = statement.executeQuery(sql);

            colCount = 0;
            rowCount = 3;
            i = 0;

            while(resultset.next()){
                colCount++;
            }
            resultset.first();

            str = new String[colCount][rowCount];
            colNames = new String[]{"ID", "Förnamn", "Efternamn"};

            while(i < colCount){
                id = resultset.getString("id");
                fname = resultset.getString("fnamn");
                ename = resultset.getString("enamn");

                resultset.next();

                str[i][0] = id;
                str[i][1] = fname;
                str[i][2] = ename;

                System.out.println(id + " " + fname + " " + ename);

                i++;
            }

            tableModel = new DefaultTableModel(str, colNames);


        } catch (SQLException err){
            System.out.println(err);
        }

        disconnectDB();

        return tableModel;

    }

    public void addToDB(String fnamnInput, String enamnInput){

        connectDB();

        try {

            sql = "INSERT INTO movie(fnamn, enamn) VALUES(\"" + fnamnInput + "\", \"" + enamnInput + "\");";
            statement = connection.prepareStatement(sql);
            resultset = statement.executeQuery(sql);

        } catch (SQLException err){
            System.out.println("Lägga till error: " +err);
        }

        disconnectDB();
    }

    public void updateDB(String fnamnInput, String enamnInput, String idInput){

        connectDB();

        try {

            sql = "UPDATE movie SET fnamn=\"" + fnamnInput + "\", enamn=\"" + enamnInput + "\" WHERE id = \"" + idInput + "\";";
            statement = connection.prepareStatement(sql);
            resultset = statement.executeQuery(sql);

        } catch (SQLException err){
            System.out.println("Lägga till error: " +err);
        }

        disconnectDB();
    }

    public void deleteFromDB(int id){

        connectDB();

        try {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM movie WHERE id=?");
            statement.setInt(1, id);
            resultset = statement.executeQuery();

        } catch (SQLException err){
            System.out.println("Delete error: " + err);
        }

        disconnectDB();
    }

    public void connectDB(){

        url = "jdbc:mysql://localhost/famouspeople";
        username = "root";
        password = "";

        try {

            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

        } catch (SQLException err){
            System.out.println("Connect error: " +err);
        }

    }

    public void disconnectDB(){

        try {
            resultset.close();
            connection.close();
        } catch (SQLException err){
            System.out.println("Disconnect error: " + err);
        }
        
    }
}