package com.escom.miniterminos.db;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDAO {
	@Qualifier("datasource1")
	@Autowired
	private DataSource dataSource;

	
	interface CategoriaSQL{
		@SqlQuery("SHOW TABLES")
		List<String> tablas();
	}
	
	private CategoriaSQL createConnection() {
		Connection conn =  DataSourceUtils.getConnection(dataSource);
        Handle handle = DBI.open(conn);
        return handle.attach(CategoriaSQL.class);
	}
	
	public List<String> obtenerTablas(){
		CategoriaSQL creditoSQL = createConnection();
		return creditoSQL.tablas();
	}
}

