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

import com.escom.miniterminos.entities.PagoBean;


@Repository
public class PagoDAO {
	
	@Qualifier("datasource1")
	@Autowired
	private DataSource dataSource;
	
	public static class PagoMapper implements ResultSetMapper<PagoBean>{

		@Override
		public PagoBean map(int index, ResultSet r, StatementContext ctx) throws SQLException {
			PagoBean pago = new PagoBean();
			pago.setIdPago(r.getInt("idPago"));
			pago.setidcredito(r.getInt("idcredito"));
            pago.setidtienda(r.getInt("idtienda"));
			pago.setidcliente(r.getInt("idcliente"));
		    pago.setfechaPago(r.getDate("fechaPago"));
            pago.setidproducto(r.getInt("idproducto"));
			return pago;
		}
		
	}
	
	@RegisterMapper(PagoMapper.class)
	interface PagoSQL{
		@SqlQuery("SHOW COLUMNS FROM PAGO")
		List<String> atributos();
	}
	
	private PagoSQL createConnection() {
		Connection conn =  DataSourceUtils.getConnection(dataSource);
        Handle handle = DBI.open(conn);
        return handle.attach(PagoSQL.class);
	}
	
	public List<String> obtenerAtributos(){
		PagoSQL creditoSQL = createConnection();
		return creditoSQL.atributos();
	}

}