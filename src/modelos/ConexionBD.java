package modelos;
import java.sql.*;
import java.sql.ResultSet;

public class ConexionBD {
    protected String driver = "org.postgresql.Driver";
    protected String connect = "jdbc:postgresql://localhost:5432/cachito";
    protected String user = "postgres";
    protected String pass = "";
    
    
    public void conexion(){
      Connection c = null;
      try {
         Class.forName(driver);
         c = DriverManager
            .getConnection(connect,user,pass);
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Conexion establecida");
   }
    
    public void consulta(String sql) {
        Connection c = null;
        Statement stmt = null;
        ResultSet result = null;
        try{
            stmt = c.createStatement();
            result = stmt.executeQuery(sql);
            stmt.close();
            c.commit();
            c.close();
        }catch (Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
        }
        System.out.println("Cambios Realizados");
    }
}

