package com.escom.miniterminos.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.StatementContext;
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

import com.escom.miniterminos.entities.UsuarioBean;

@Repository
public class UsuarioDAO {
	@Qualifier("datasource1")
	@Autowired
	private DataSource dataSource;
	
	// Iterator para los SELECT Usuario
	public static class UsuarioMapper implements ResultSetMapper<UsuarioBean>{
		@Override
		public UsuarioBean map(int index, ResultSet r, StatementContext ctx) throws SQLException {
			UsuarioBean usuario = new UsuarioBean();
			usuario.setIdUsuario((Integer) r.getObject("idUsuario"));
			usuario.setUserName(r.getString("userName"));
			usuario.setNombre(r.getString("Nombre"));
			usuario.setApellidoPaterno(r.getString("ApPaterno"));
			usuario.setApellidoMaterno(r.getString("ApMaterno"));
			usuario.setCorreo(r.getString("Correo"));
			usuario.setPassword(r.getString("Contrasena"));
			
			return usuario;
		}
	}
	
	@RegisterMapper(UsuarioMapper.class)
	interface UsuarioSQL{
		@SqlQuery("select * from usuario")
		List<UsuarioBean> selectUsuario();
		
		 @SqlUpdate("insert into usuario (userName, Nombre, ApPaterno, ApMaterno, Correo, Contrasena)" +
                 " values(:userName, :nombre, :apellidoPaterno, :apellidoMaterno, :correo, :password) ")
		@GetGeneratedKeys
		Integer insertUsuario(@BindBean UsuarioBean usuario);
	}
	
	private UsuarioSQL createConnection() {
		Connection conn =  DataSourceUtils.getConnection(dataSource);
        Handle handle = DBI.open(conn);
        return handle.attach(UsuarioSQL.class);
	}
	
	public List<UsuarioBean> obtenerUsuarios(){
		UsuarioSQL usuarioSQL = createConnection();
		return usuarioSQL.selectUsuario();
	}
	
	public Integer insertarUsuario(UsuarioBean bean) {
		UsuarioSQL usuarioSQL = createConnection();
		return usuarioSQL.insertUsuario(bean);
	}
}
