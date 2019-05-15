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

import com.escom.miniterminos.entities.TareaBean;

@Repository
public class TareaDAO {
	
	@Qualifier("datasource1")
	@Autowired
	private DataSource dataSource;
	
	// Iterator para los SELECT
		public static class TareaMapper implements ResultSetMapper<TareaBean>{
			@Override
			public TareaBean map(int index, ResultSet r, StatementContext ctx) throws SQLException {
				TareaBean tarea = new TareaBean();
				tarea.setIdTarea((Integer) r.getObject("idTarea"));
				tarea.setSwag(r.getString("Swag"));
				tarea.setFechaMod(r.getString("FechaMod"));
				tarea.setValorNegocios((Integer) r.getObject("ValorNegocios"));
				tarea.setTitulo(r.getString("Titulo"));
				tarea.setPrioridad(r.getString("Prioridad"));
				tarea.setEstado(r.getString("Estado"));
				tarea.setFechaCreacion(r.getString("FechaCreacion"));
				tarea.setTipoTarea(r.getString("TipoTarea"));
				tarea.setProgreso(r.getString("Progreso"));
				tarea.setIdColumna((Integer) r.getObject("idColumna"));
				tarea.setIdTablero((Integer) r.getObject("idTablero"));
				tarea.setIdUsuario((Integer) r.getObject("idUsuario"));
				tarea.setBloqueo(r.getString("Bloqueo"));
				return tarea;
			}
		}
		
		@RegisterMapper(TareaMapper.class)
		interface TareaSQL{
			@SqlQuery("select * from tarea")
			List<TareaBean> selectTarea();
			
			@SqlQuery("select * from tarea where idUsuario = :it")
			List<TareaBean> selectTareaUsuario(@Bind long idUsuario);
			
			 @SqlUpdate("insert into tarea (Swag, FechaMod, ValorNegocios, Titulo, Prioridad, Estado, FechaCreacion, TipoTarea, Progreso, idColumna, idTablero, idUsuario, Bloqueo)" +
	                 " values(:swag, :fechaMod, :valorNegocios, :titulo, :prioridad, :estado, :fechaCreacion, :tipoTarea, :progreso, :idColumna, :idTablero, :idUsuario, :bloqueo) ")
			@GetGeneratedKeys
			Integer insertTarea(@BindBean TareaBean tarea);
			 	 
		}
		
		private TareaSQL createConnection() {
			Connection conn =  DataSourceUtils.getConnection(dataSource);
	        Handle handle = DBI.open(conn);
	        return handle.attach(TareaSQL.class);
		}
		
		public List<TareaBean> obtenerTareas(){
			TareaSQL tareaSQL = createConnection();
			return tareaSQL.selectTarea();
		}
		
		public List<TareaBean> obtenerTareaUsuario(long idUsuario){
			TareaSQL tareaSQL = createConnection();
			return tareaSQL.selectTareaUsuario(idUsuario);
		}
		
		public Integer insertarTarea(TareaBean tareaBean) {
			TareaSQL tareaSQL = createConnection();
			return tareaSQL.insertTarea(tareaBean);
		}
		
}
