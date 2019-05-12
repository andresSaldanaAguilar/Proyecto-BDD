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

import com.escom.miniterminos.entities.SubcategoriaBean;


@Repository
public class SubcategoriaDAO {
	
	@Qualifier("datasource1")
	@Autowired
	private DataSource dataSource;
	
	public static class SubcategoriaMapper implements ResultSetMapper<SubcategoriaBean>{

		@Override
		public SubcategoriaBean map(int index, ResultSet r, StatementContext ctx) throws SQLException {
			SubcategoriaBean subcategoria = new SubcategoriaBean();
			subcategoria.setidsubcategoria(r.getInt("idsubcategoria"));
			subcategoria.setnombre(r.getString("nombre"));
            subcategoria.setidCategoria(r.getInt("idcategoria"));
			return subcategoria;
		}
	
	}
	
	@RegisterMapper(SubcategoriaMapper.class)
	interface SubcategoriaSQL{
		@SqlQuery("SHOW COLUMNS FROM SUBCATEGORIA")
		List<String> atributos();
	}
	
	private SubcategoriaSQL createConnection() {
		Connection conn =  DataSourceUtils.getConnection(dataSource);
        Handle handle = DBI.open(conn);
        return handle.attach(SubcategoriaSQL.class);
	}
	
	public List<String> obtenerAtributos(){
		SubcategoriaSQL creditoSQL = createConnection();
		return creditoSQL.atributos();
	}

}