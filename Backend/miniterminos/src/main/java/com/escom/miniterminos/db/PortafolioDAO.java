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

import com.escom.miniterminos.entities.PortafolioBean;

@Repository
public class PortafolioDAO {
	
	@Qualifier("datasource1")
	@Autowired
	private DataSource dataSource;
	
	// Iterator para los SELECT
	public static class PortafolioMapper implements ResultSetMapper<PortafolioBean>{
		@Override
		public PortafolioBean map(int index, ResultSet r, StatementContext ctx) throws SQLException {
			PortafolioBean portafolio = new PortafolioBean();
			
			portafolio.setIdPortafolio((Integer) r.getObject("idPortafolio"));
			portafolio.setPortafolio(r.getString("Portafolio"));
			portafolio.setEstado((Integer) r.getObject("Estado"));
			portafolio.setFechaCreacion(r.getString("FechaCreacion"));
			portafolio.setSwag(r.getString("Swag"));
			portafolio.setIdUsuario((Integer) r.getObject("idUsuario"));
			
			return portafolio;
		}
	}
	
	@RegisterMapper(PortafolioMapper.class)
	interface PortafolioSQL{
		@SqlQuery("select * from portafolio")
		List<PortafolioBean> selectPortafolio();
		
		@SqlQuery("select * from portafolio where idUsuario = :it")
		List<PortafolioBean> selectPortafoliosUsuario(@Bind long idUsuario);
		
		@SqlUpdate("insert into portafolio (Portafolio, Estado, FechaCreacion, Swag, idUsuario)" +
                 " values(:portafolio, :estado, :fechaCreacion, :swag, :idUsuario) ")
		@GetGeneratedKeys
		Integer insertPortafolio(@BindBean PortafolioBean portafolio);
	}
	
	private PortafolioSQL createConnection() {
		Connection conn =  DataSourceUtils.getConnection(dataSource);
        Handle handle = DBI.open(conn);
        return handle.attach(PortafolioSQL.class);
	}
	
	public List<PortafolioBean> obtenerPortafolios(){
		PortafolioSQL portafolioSQL = createConnection();
		return portafolioSQL.selectPortafolio();
	}
	
	public List<PortafolioBean> obtenerPortafoliosUsuario(long idUsuario){
		PortafolioSQL portafolioSQL = createConnection();
		return portafolioSQL.selectPortafoliosUsuario(idUsuario);
	}
	
	public Integer insertarPortafolio(PortafolioBean portafolioBean) {
		PortafolioSQL portafolioSQL = createConnection();
		return portafolioSQL.insertPortafolio(portafolioBean);
	}
	
}
