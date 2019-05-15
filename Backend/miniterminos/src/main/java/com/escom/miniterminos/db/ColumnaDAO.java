package com.escom.miniterminos.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.escom.miniterminos.entities.ColumnaBean;

@Repository
public class ColumnaDAO {
	
	@Qualifier("datasource1")
	@Autowired
	private DataSource dataSource;
	
	// Iterator para los SELECT
		public static class ColumnaMapper implements ResultSetMapper<ColumnaBean>{
			@Override
			public ColumnaBean map(int index, ResultSet r, StatementContext ctx) throws SQLException {
				ColumnaBean columna = new ColumnaBean();
				columna.setIdColumna((Integer) r.getObject("idColumna"));
				columna.setNombre(r.getString("Nombre"));
				columna.setNumColumna((Integer) r.getObject("NumColumna"));
				columna.setLimitesWIP((Integer) r.getObject("LimitesWIP"));
				columna.setIdTablero((Integer) r.getObject("idTablero"));
				
				return columna;
			}
		}

		@RegisterMapper(ColumnaMapper.class)
		interface ColumnaSQL{
			@SqlQuery("select * from columna")
			List<ColumnaBean> selectColumna();
			
			@SqlQuery("select * from columna where idTablero = :it")
			List<ColumnaBean> selectColumnaTablero(@Bind long idTablero);
			
			 @SqlUpdate("insert into columna (Nombre, NumColumna, LimitesWIP, idTablero)" +
	                 " values(:nombre, :numColumna, :limitesWIP, :idTablero) ")
			@GetGeneratedKeys
			Integer insertColumna(@BindBean ColumnaBean columna);
		}
		
		private ColumnaSQL createConnection() {
			Connection conn =  DataSourceUtils.getConnection(dataSource);
	        Handle handle = DBI.open(conn);
	        return handle.attach(ColumnaSQL.class);
		}
		
		public List<ColumnaBean> obtenerColumna(){
			ColumnaSQL columnaSQL = createConnection();
			return columnaSQL.selectColumna();
		}
		
		public List<ColumnaBean> obtenerColumnaTablero(long idTablero){
			ColumnaSQL columnaSQL = createConnection();
			return columnaSQL.selectColumnaTablero(idTablero);
		}
		
		public Integer insertarColumna(ColumnaBean columnaBean) {
			ColumnaSQL columnaSQL = createConnection();
			return columnaSQL.insertColumna(columnaBean);
		}
}
