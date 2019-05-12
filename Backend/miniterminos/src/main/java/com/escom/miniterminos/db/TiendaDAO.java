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

import com.escom.miniterminos.entities.TiendaBean;


@Repository
public class TiendaDAO {
	
	@Qualifier("datasource1")
	@Autowired
	private DataSource dataSource;
	
	public static class TiendaMapper implements ResultSetMapper<TiendaBean>{

		@Override
		public TiendaBean map(int index, ResultSet r, StatementContext ctx) throws SQLException {
			TiendaBean tienda = new TiendaBean();
			tienda.setIdTienda(r.getInt("idTienda"));
			tienda.setnombre(r.getString("nombre"));
            tienda.setdireccion(r.getString("direccion"));
			tienda.setestado(r.getString("estado"));
		    tienda.setcp(r.getInt("cp"));
            tienda.settel(r.getString("tel"));
			return tienda;
		}
		
	}
	
	@RegisterMapper(TiendaMapper.class)
	interface TiendaSQL{
		@SqlQuery("SHOW COLUMNS FROM TIENDA")
		List<String> atributos();
	}
	
	private TiendaSQL createConnection() {
		Connection conn =  DataSourceUtils.getConnection(dataSource);
        Handle handle = DBI.open(conn);
        return handle.attach(TiendaSQL.class);
	}
	
	public List<String> obtenerAtributos(){
		TiendaSQL creditoSQL = createConnection();
		return creditoSQL.atributos();
	}

}