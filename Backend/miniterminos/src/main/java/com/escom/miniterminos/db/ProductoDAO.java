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

import com.escom.miniterminos.entities.ProductoBean;

@Repository
public class ProductoDAO {
	
	@Qualifier("datasource1")
	@Autowired
	private DataSource dataSource;
	
	public static class ProductoMapper implements ResultSetMapper<ProductoBean>{

		@Override
		public ProductoBean map(int index, ResultSet r, StatementContext ctx) throws SQLException {
			ProductoBean producto = new ProductoBean();
			producto.setIdProducto(r.getInt("idProducto"));
			producto.setNombre(r.getString("nombre"));
            producto.setDescripcion(r.getString("descripcion"));
			producto.setPrecioUnitario(r.getDouble("precioUnitario"));
		    producto.setMarca(r.getString("marca"));
            producto.setIdSubcategoria(r.getInt("idSubcategoria"));
			return producto;
		}
		
	}
	
	@RegisterMapper(ProductoMapper.class)
	interface ProductoSQL{
		@SqlQuery("SHOW COLUMNS FROM PRODUCTO")
		List<String> atributos();
	}
	
	private ProductoSQL createConnection() {
		Connection conn =  DataSourceUtils.getConnection(dataSource);
        Handle handle = DBI.open(conn);
        return handle.attach(ProductoSQL.class);
	}
	
	public List<String> obtenerAtributos(){
		ProductoSQL creditoSQL = createConnection();
		return creditoSQL.atributos();
	}

}