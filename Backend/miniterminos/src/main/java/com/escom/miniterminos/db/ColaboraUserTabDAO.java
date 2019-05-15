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

import com.escom.miniterminos.entities.ColaboraUserTabBean;



@Repository
public class ColaboraUserTabDAO {
	
	@Qualifier("datasource1")
	@Autowired
	private DataSource dataSource;
	
	// Iterator para los SELECT
		public static class ColaboraUserTabMapper implements ResultSetMapper<ColaboraUserTabBean>{
			@Override
			public ColaboraUserTabBean map(int index, ResultSet r, StatementContext ctx) throws SQLException {
				ColaboraUserTabBean colabora = new ColaboraUserTabBean();
				colabora.setIdUsuario((Integer) r.getObject("idUsuario"));
				colabora.setIdTablero((Integer) r.getObject("idTablero"));
				
				return colabora;
			}
		}

		@RegisterMapper(ColaboraUserTabMapper.class)
		interface ColaboraUserTabSQL{
			@SqlQuery("select * from colaborausertab")
			List<ColaboraUserTabBean> selectColabora();
			
			@SqlQuery("select * from colaborausertab where idUsuario = :it")
			List<ColaboraUserTabBean> selectColaboraUsuario(@Bind long idUsuario);
			
			 @SqlUpdate("insert into colaborausertab (id.Usuario, idTablero)" +
	                 " values(:idUsuario, :idTablero) ")
			@GetGeneratedKeys
			Integer insertColabora(@BindBean ColaboraUserTabBean colabora);
		}
		
		private ColaboraUserTabSQL createConnection() {
			Connection conn =  DataSourceUtils.getConnection(dataSource);
	        Handle handle = DBI.open(conn);
	        return handle.attach(ColaboraUserTabSQL.class);
		}
		
		public List<ColaboraUserTabBean> obtenerColabora(){
			ColaboraUserTabSQL colaboraSQL = createConnection();
			return colaboraSQL.selectColabora();
		}
		
		public List<ColaboraUserTabBean> obtenerColaboraUsuario(long idUsuario){
			ColaboraUserTabSQL colaboraSQL = createConnection();
			return colaboraSQL.selectColaboraUsuario(idUsuario);
		}
		
		public Integer insertarColabora(ColaboraUserTabBean colaboraBean) {
			ColaboraUserTabSQL colaboraSQL = createConnection();
			return colaboraSQL.insertColabora(colaboraBean);
		}

}
