package com.escom.miniterminos.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.escom.miniterminos.entities.TableroBean;

@Repository
public class TableroDAO {
	
	@Qualifier("datasource1")
	@Autowired
	private DataSource dataSource;
	
	// Iterator para los SELECT
		public static class TableroMapper implements ResultSetMapper<TableroBean>{
			@Override
			public TableroBean map(int index, ResultSet r, StatementContext ctx) throws SQLException {
				TableroBean tablero = new TableroBean();
				tablero.setIdTablero((Integer) r.getObject("idTablero"));
				tablero.setNombre(r.getString("Nombre"));
				tablero.setIdTablero((Integer) r.getObject("idPortafolio"));
				
				return tablero;
			}
		}
		
		@RegisterMapper(TableroMapper.class)
		interface TableroSQL{
			@SqlQuery("select * from tablero")
			List<TableroBean> selectTablero();
			
			@SqlQuery("select * from tablero where idPortafolio = :it")
			List<TableroBean> selectTableroPortafolio(@Bind long idPortafolio);
			
			 @SqlUpdate("insert into tablero (Nombre, idPortafolio)" +
	                 " values(:nombre, :idPortafolio) ")
			@GetGeneratedKeys
			Integer insertTablero(@BindBean TableroBean tablero);
		}

		private TableroSQL createConnection() {
			Connection conn =  DataSourceUtils.getConnection(dataSource);
	        Handle handle = DBI.open(conn);
	        return handle.attach(TableroSQL.class);
		}
		
		public List<TableroBean> obtenerTablero(){
			TableroSQL tableroSQL = createConnection();
			return tableroSQL.selectTablero();
		}
		
		public List<TableroBean> obtenerTableroPortafolio(long idPortafolio){
			TableroSQL tableroSQL = createConnection();
			return tableroSQL.selectTableroPortafolio(idPortafolio);
		}
		
		public Integer insertarTablero(TableroBean tableroBean) {
			TableroSQL tableroSQL = createConnection();
			return tableroSQL.insertTablero(tableroBean);
		}
}
