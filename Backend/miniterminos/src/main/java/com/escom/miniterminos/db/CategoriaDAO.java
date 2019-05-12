package com.escom.miniterminos.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.escom.miniterminos.entities.CategoriaBean;

@Repository
public class CategoriaDAO {
	
	@Qualifier("datasource1")
	@Autowired
	private DataSource dataSource;
	
	public static class CategoriaMapper implements ResultSetMapper<CategoriaBean>{

		@Override
		public CategoriaBean map(int index, ResultSet r, StatementContext ctx) throws SQLException {
			CategoriaBean categoria = new CategoriaBean();
			categoria.setidcategoria(r.getInt("idcategoria"));
			categoria.setnombre(r.getString("nombre"));
			return categoria;
		}
	
	}
	
	@RegisterMapper(CategoriaMapper.class)
	interface CategoriaSQL{
		@SqlQuery("SHOW COLUMNS FROM CATEGORIA")
		List<String> atributos();
	}
	
	private CategoriaSQL createConnection() {
		Connection conn =  DataSourceUtils.getConnection(dataSource);
        Handle handle = DBI.open(conn);
        return handle.attach(CategoriaSQL.class);
	}
	
	public List<String> obtenerAtributos(){
		CategoriaSQL creditoSQL = createConnection();
		return creditoSQL.atributos();
	}

}