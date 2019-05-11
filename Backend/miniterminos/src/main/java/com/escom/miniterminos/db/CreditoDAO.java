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

import com.escom.miniterminos.entities.CreditoBean;


@Repository
public class CreditoDAO {
	
	@Qualifier("datasource1")
	@Autowired
	private DataSource dataSource;
	
	public static class CreditoMapper implements ResultSetMapper<CreditoBean>{

		@Override
		public CreditoBean map(int index, ResultSet r, StatementContext ctx) throws SQLException {
			CreditoBean credito = new CreditoBean();
			credito.setIdCredito(r.getInt("idCredito"));
			credito.setNombre(r.getString("nombre"));
			credito.setMonto(r.getString("monto"));
		
			return credito;
		}
		
	}
	
	@RegisterMapper(CreditoMapper.class)
	interface CreditoSQL{
		@SqlQuery("SHOW COLUMNS FROM CLIENTE")
		List<String> atributos();
	}
	
	private CreditoSQL createConnection() {
		Connection conn =  DataSourceUtils.getConnection(dataSource);
        Handle handle = DBI.open(conn);
        return handle.attach(CreditoSQL.class);
	}
	
	public List<String> obtenerAtributos(){
		CreditoSQL creditoSQL = createConnection();
		return creditoSQL.atributos();
	}
	

	
	
	
}