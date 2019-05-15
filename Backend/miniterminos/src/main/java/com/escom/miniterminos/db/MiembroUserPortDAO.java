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

import com.escom.miniterminos.entities.MiembroUserPortBean;

@Repository
public class MiembroUserPortDAO {
	
	@Qualifier("datasource1")
	@Autowired
	private DataSource dataSource;
	
	// Iterator para los SELECT
		public static class MiembroUserPortMapper implements ResultSetMapper<MiembroUserPortBean>{
			@Override
			public MiembroUserPortBean map(int index, ResultSet r, StatementContext ctx) throws SQLException {
				MiembroUserPortBean miembro = new MiembroUserPortBean();
				miembro.setIdUsuario((Integer) r.getObject("idUsuario"));
				miembro.setIdPortafolio((Integer) r.getObject("idPortafolio"));
				
				return miembro;
			}
		}

		@RegisterMapper(MiembroUserPortMapper.class)
		interface MiembroUserPortSQL{
			@SqlQuery("select * from miembrouserport")
			List<MiembroUserPortBean> selectMiembro();
			
			@SqlQuery("select * from miembrouserport where idUsuario = :it")
			List<MiembroUserPortBean> selectMiembroUsuario(@Bind long idUsuario);
			
			 @SqlUpdate("insert into miembrouserport (id.Usuario, idPortafolio)" +
	                 " values(:idUsuario, :idPortafolio) ")
			@GetGeneratedKeys
			Integer insertMiembro(@BindBean MiembroUserPortBean miembro);
		}
		
		private MiembroUserPortSQL createConnection() {
			Connection conn =  DataSourceUtils.getConnection(dataSource);
	        Handle handle = DBI.open(conn);
	        return handle.attach(MiembroUserPortSQL.class);
		}
		
		public List<MiembroUserPortBean> obtenerMiembro(){
			MiembroUserPortSQL miembroSQL = createConnection();
			return miembroSQL.selectMiembro();
		}
		
		public List<MiembroUserPortBean> obtenerMiembroUsuario(long idUsuario){
			MiembroUserPortSQL miembroSQL = createConnection();
			return miembroSQL.selectMiembroUsuario(idUsuario);
		}
		
		public Integer insertarMiembro(MiembroUserPortBean miembroBean) {
			MiembroUserPortSQL miembroSQL = createConnection();
			return miembroSQL.insertMiembro(miembroBean);
		}


}
