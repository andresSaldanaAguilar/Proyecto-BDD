package com.escom.miniterminos.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.jdbc.core.JdbcTemplate;
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

@Repository
public class BaseDAO {
	@Qualifier("datasource1")
	@Autowired
	private DataSource dataSource;
	
	Connection conn;
	Statement stm;
	ResultSet rs;

	
	interface CategoriaSQL{
		@SqlQuery("SHOW TABLES")
		List<String> tablas();
		
	}
	
	
	private CategoriaSQL createConnection() {
		conn =  DataSourceUtils.getConnection(dataSource);
        Handle handle = DBI.open(conn);
        return handle.attach(CategoriaSQL.class);
	}
	
	
	public List<String> obtenerTablas(){
		CategoriaSQL creditoSQL = createConnection();
		return creditoSQL.tablas();
	}
	
	public int evaluar( String relacion , String predicado ){
		
		int res=0;
		
		try {
			conn =  DataSourceUtils.getConnection(dataSource);
			stm = conn.createStatement();
			rs = stm.executeQuery("select count(*) FROM "+relacion+" where "+predicado);
			
			if(rs.next()){
				res = Integer.parseInt(rs.getString("count(*)"));
			}
			
			conn.close();
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
			conn =  DataSourceUtils.getConnection(dataSource);
			stm = conn.createStatement();
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
			
			conn.close();
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
}

