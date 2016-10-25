package modelos;
import java.sql.*;
import java.sql.ResultSet;

public class ConexionBD {
    protected String driver = "org.postgresql.Driver";
    protected String connect = "jdbc:postgresql://localhost:5432/cachito";
    protected String user = "postgres";
    protected String pass = "";
    Connection c = null;
    
    public void ConexionBD(){
      try {
         
          Class.forName(driver);
         this.c = DriverManager.getConnection(connect,user,pass);
         
      } catch (Exception e) {
       
          e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Conexion establecida");
    }
    
    public ResultSet consult(String sql) {
        Statement stmt = null;
        ResultSet result = null;
        try{
            stmt = this.c.createStatement();
            result = stmt.executeQuery(sql);
            stmt.close();
            this.c.commit();
            this.c.close();
        }catch (Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Consulta realizada");
        return result;
    }
    
    public void close(){
        try{
        this.c.close();
        } catch (Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Conexion cerrada");
    }
}

