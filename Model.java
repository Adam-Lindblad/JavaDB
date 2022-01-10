import java.sql.*;


public class Model {

    private Connection connection;
    private Statement statement;
    private ResultSet resultset;

    private String url, username, password, sql;

    public Model(){
        //Koppla upp DB

        //Ta emot SQLfrågor
        //Bygga View
        //Koppla ihop med Controller
    }

    public void taUtAlla(){

        connectDB();

        try {

            sql = "SELECT * FROM movie;";
            resultset = statement.executeQuery(sql);

            while(resultset.next()){
                System.out.println(resultset.getString("id") + " " + resultset.getString("fnamn") + " " + resultset.getString("enamn"));
            }


        } catch (SQLException err){
            System.out.println(err);
        }

        disconnectDB();

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