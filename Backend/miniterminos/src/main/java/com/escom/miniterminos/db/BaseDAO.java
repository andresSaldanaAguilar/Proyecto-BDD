package com.escom.miniterminos.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.ResultSetMetaData;

@Repository
public class BaseDAO {
	@Qualifier("datasource1")
	@Autowired
	private DataSource dataSource1;
	
	@Qualifier("datasource2")
	@Autowired
	private DataSource dataSource2;
	
	@Qualifier("datasource3")
	@Autowired
	private DataSource dataSource3;
	
	Connection localConnection,foreignConnection;
	Statement stm;
	ResultSet rs;

	
	interface CategoriaSQL{
		@SqlQuery("SHOW TABLES")
		List<String> tablas();	
	}
	
	
	private CategoriaSQL createConnection() {
		localConnection =  DataSourceUtils.getConnection(dataSource1);
        Handle handle = DBI.open(localConnection);
        return handle.attach(CategoriaSQL.class);
	}
	
	
	public List<String> obtenerTablas(){
		CategoriaSQL creditoSQL = createConnection();
		return creditoSQL.tablas();
	}
	
	public List<String> obtenerAtributos(String table){
		
		List<String> list = new ArrayList<String>();
		localConnection =  DataSourceUtils.getConnection(dataSource1);
		
		try {
			stm = localConnection.createStatement();		
			rs = stm.executeQuery("show columns from "+table+";");
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int evaluar( String relacion , String predicado ){
		
		int res=0;
		
		try {
			localConnection =  DataSourceUtils.getConnection(dataSource1);
			stm = localConnection.createStatement();
			rs = stm.executeQuery("select count(*) FROM "+relacion+" where "+predicado);
			
			if(rs.next()){
				res = Integer.parseInt(rs.getString("count(*)"));
			}
			
			localConnection.close();
			stm.close();
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	
		return res;
	}
	
	public int verificar( String relacion , String[] predicado ){
		
		int res1=0 , res2=0 , x;
		String cat="";
		
		try {
			localConnection =  DataSourceUtils.getConnection(dataSource1);
			stm = localConnection.createStatement();
			rs = stm.executeQuery("select count(*) FROM "+relacion);
			
			if(rs.next()){
				res1 = Integer.parseInt(rs.getString("count(*)"));
			}
			
			cat = predicado[0];
			
			for( x=1 ; x<predicado.length ; x++ )
			{
				cat += " or "+predicado[x];
			}
			
			rs = stm.executeQuery("select count(*) FROM "+relacion+" where "+cat);
			
			if(rs.next()){
				res2 = Integer.parseInt(rs.getString("count(*)"));
			}
			
			localConnection.close();
			stm.close();
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		System.out.println( cat );
		
		if( res1 == res2 )
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	public int enviar(String sitio, String nombreTablas, String relacion, String[] miniterminos) {

		//conexion local, base de datos destino
		localConnection =  DataSourceUtils.getConnection(dataSource1);
		
		//buscando que base de datos es el destino
		if(sitio.equals("Sitio 1")) {
			foreignConnection =  DataSourceUtils.getConnection(dataSource1);
		}
		else if(sitio.equals("Sitio 2")) {
			foreignConnection =  DataSourceUtils.getConnection(dataSource2);
		}
		else {
			foreignConnection =  DataSourceUtils.getConnection(dataSource3);
		}
		
		
		for(int i = 0; i < miniterminos.length ; i++) {
			try {
				//creamos las tabla
				stm = foreignConnection.createStatement();		
				stm.executeUpdate("CREATE TABLE "+nombreTablas+(i+1)+" LIKE "+relacion+";");
				
				stm = localConnection.createStatement();
				rs = stm.executeQuery("select * FROM "+relacion+" where "+miniterminos[i].substring(4));
				
				//poblamos la tabla
				while(rs.next()) {
					ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
					int columnsNumber = rsmd.getColumnCount();
					String query = "";
					
					//traemos los valores a insertar
					for(int j = 1; j <= columnsNumber; j++) {

						//integer
						if(rsmd.getColumnType(j)== 3 || rsmd.getColumnType(j)== 4 || rsmd.getColumnType(j)== 6 || rsmd.getColumnType(j)== 8) {
							query += rs.getString(j)+",";
						}
						//string
						else {
							query += "'"+rs.getString(j)+"',";
						}						
					}
					stm = foreignConnection.createStatement();
					stm.executeUpdate("insert into "+nombreTablas+(i+1)+" values ("+query.substring(0,query.length()-1)+");");
				}
			} 				
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return 1;
	}
	
	public int enviarV(String sitio, String nombreTablas, String relacion, String[] miniterminos) {

		List<String> list = new ArrayList<String>();
		String[] array;
		
		//conexion local, base de datos destino
		localConnection =  DataSourceUtils.getConnection(dataSource1);
		
		//buscando que base de datos es el destino
		if(sitio.equals("Sitio 1")) {
			foreignConnection =  DataSourceUtils.getConnection(dataSource1);
		}
		else if(sitio.equals("Sitio 2")) {
			foreignConnection =  DataSourceUtils.getConnection(dataSource2);
		}
		else {
			foreignConnection =  DataSourceUtils.getConnection(dataSource3);
		}
		
		
		for(int i = 0; i < miniterminos.length ; i++) {
			try {
				//creamos las tabla
				stm = foreignConnection.createStatement();		
				stm.executeUpdate("CREATE TABLE "+nombreTablas+(i+1)+" LIKE "+relacion+";");
				
				stm = localConnection.createStatement();
				rs = stm.executeQuery("select * FROM "+relacion );
				
				//poblamos la tabla
				while(rs.next()) {
					ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
					int columnsNumber = rsmd.getColumnCount();
					String query = "";
					
					//traemos los valores a insertar
					for(int j = 1; j <= columnsNumber; j++) {

						//integer
						if(rsmd.getColumnType(j)== 3 || rsmd.getColumnType(j)== 4 || rsmd.getColumnType(j)== 6 || rsmd.getColumnType(j)== 8) {
							query += rs.getString(j)+",";
						}
						//string
						else {
							query += "'"+rs.getString(j)+"',";
						}						
					}
					stm = foreignConnection.createStatement();
					stm.executeUpdate("insert into "+nombreTablas+(i+1)+" values ("+query.substring(0,query.length()-1)+");");
				}
				
				array = miniterminos[i].substring(6, miniterminos[i].length()-1).split(",");
				list = new ArrayList<String>();
				
				rs = stm.executeQuery("show columns from "+relacion+";");
				while(rs.next()) {
					list.add(rs.getString(1));
				}
				
				for(int j=0 ; j<array.length ; j++){
					list.remove(array[j]);
				}
				for(int j=0 ; j<list.size() ; j++){
					stm.executeUpdate("alter table "+nombreTablas+(i+1)+" drop "+list.get(j)+";");
				}
				
			} 				
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return 1;
	}
	
	public List<String> llave( String relacion ) {
	
		List<String> list = new ArrayList<String>();
		try 
		{
			localConnection =  DataSourceUtils.getConnection(dataSource1);
			stm = localConnection.createStatement();
			rs = stm.executeQuery("SELECT k.column_name FROM information_schema.table_constraints t JOIN information_schema.key_column_usage k USING(constraint_name,table_schema,table_name) WHERE t.constraint_type='PRIMARY KEY' AND t.table_schema='proyectoisw' AND t.table_name='"+relacion+"';");
		
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}

