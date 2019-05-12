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

import com.escom.miniterminos.entities.TiendaProductoBean;

@Repository
public class TiendaProductoDAO {
	
	@Qualifier("datasource1")
	@Autowired
	private DataSource dataSource;
	
	public static class TiendaProductoMapper implements ResultSetMapper<TiendaProductoBean>{

		@Override
		public TiendaProductoBean map(int index, ResultSet r, StatementContext ctx) throws SQLException {
			TiendaProductoBean tiendaProducto = new TiendaProductoBean();
			tiendaProducto.setidtienda(r.getInt("idtienda"));
			tiendaProducto.setidproducto(r.getInt("idproducto"));
            tiendaProducto.setnoExistencias(r.getInt("noExistencias"));
			return tiendaProducto;
		}
	
	}
	
	@RegisterMapper(TiendaProductoMapper.class)
	interface TiendaProductoSQL{
		@SqlQuery("SHOW COLUMNS FROM TIENDAPRODUCTO")
		List<String> atributos();
	}
	
	private TiendaProductoSQL createConnection() {
		Connection conn =  DataSourceUtils.getConnection(dataSource);
        Handle handle = DBI.open(conn);
        return handle.attach(TiendaProductoSQL.class);
	}
	
	public List<String> obtenerAtributos(){
		TiendaProductoSQL creditoSQL = createConnection();
		return creditoSQL.atributos();
	}

}