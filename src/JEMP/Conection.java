     package JEMP;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class Conection {
	private String URL = "jdbc:postgresql://localhost:5432/S.C";
	private String USER = "postgres";
	private String PASS = "1322052426";
	private Statement stmt = null;
	private Connection conn;
	private ResultSet rs;

	public void onConnection(){	
		try{
			Class.forName("org.postgresql.Driver");
			
			conn = DriverManager.getConnection(this.URL, this.USER, this.PASS);
		}
		catch(Exception e){ System.out.println("Error al conectar "+e); }
	}
	
	public void connClose(){
		try{ conn.close();}
		catch(Exception e){ System.out.println("Error al cerrar conn "+e);}
	}
	
	public void stmtClose(){
		try{ stmt.close();}
		catch(Exception e){ System.out.println("Error al cerrar stmt "+e);}
	}
	
	public void execute(String s){
		onConnection();
		
		try{ stmt = conn.createStatement(); stmt.executeQuery (s); }
		catch(SQLException e){ System.out.println("Error al ejecutar el query "+e); }
	}
	
	public ResultSet select(String s){
		onConnection();
		execute(s);
		
		try{ rs = stmt.getResultSet(); }
		catch(Exception e){ System.out.println("Error al tomar los datos de la tabla "+e);}
		return rs;
	}
}