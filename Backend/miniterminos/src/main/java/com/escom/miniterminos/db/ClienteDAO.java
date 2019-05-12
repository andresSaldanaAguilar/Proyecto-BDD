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

import com.escom.miniterminos.entities.ClienteBean;


@Repository
public class ClienteDAO {
	
	@Qualifier("datasource1")
	@Autowired
	private DataSource dataSource;
	
	public static class ClienteMapper implements ResultSetMapper<ClienteBean>{

		@Override
		public ClienteBean map(int index, ResultSet r, StatementContext ctx) throws SQLException {
			ClienteBean cliente = new ClienteBean();
			cliente.setIdCliente(r.getInt("idCliente"));
			cliente.setNombre(r.getString("nombre"));
			cliente.setapPaterno(r.getString("apPaterno"));
		    cliente.setapMaterno(r.getString("apMaterno"));
            cliente.setsexo(r.getString("sexo"));
            cliente.setemail(r.getString("email"));
            cliente.setsalario(r.getDouble("salario"));
			return cliente;
		}
		
	}
	
	@RegisterMapper(ClienteMapper.class)
	interface ClienteSQL{
		@SqlQuery("SHOW COLUMNS FROM CLIENTE")
		List<String> atributos();
	}
	
	private ClienteSQL createConnection() {
		Connection conn =  DataSourceUtils.getConnection(dataSource);
        Handle handle = DBI.open(conn);
        return handle.attach(ClienteSQL.class);
	}
	
	public List<String> obtenerAtributos(){
		ClienteSQL creditoSQL = createConnection();
		return creditoSQL.atributos();
	}

}