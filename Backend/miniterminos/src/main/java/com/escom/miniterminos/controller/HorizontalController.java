package com.escom.miniterminos.controller;

import java.util.Iterator;

import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.escom.miniterminos.db.CategoriaDAO;
import com.escom.miniterminos.db.ClienteDAO;
import com.escom.miniterminos.db.CreditoDAO;
import com.escom.miniterminos.db.PagoDAO;
import com.escom.miniterminos.db.ProductoDAO;
import com.escom.miniterminos.db.SubcategoriaDAO;
import com.escom.miniterminos.db.TiendaDAO;
import com.escom.miniterminos.db.TiendaProductoDAO;
import com.escom.miniterminos.services.CreditoService;

import java.util.List;

@RestController
public class HorizontalController {
	
	
	@Autowired
	CreditoDAO creditoDAO;
	@Autowired
	TiendaDAO tiendaDAO;
	@Autowired
	PagoDAO pagoDAO;
	@Autowired
	ClienteDAO clienteDAO;
	@Autowired
	ProductoDAO productoDAO;
	@Autowired
	TiendaProductoDAO tiendaproductoDAO;
	@Autowired
	CategoriaDAO categoriaDAO;
	@Autowired
	SubcategoriaDAO subcategoriaDAO;
	
	
	//Esta funcion se llama desde que la pagina cuando se carga 
	//retorna las relaciones de la base de datos
	@GetMapping("/relaciones")
	public String getRelaciones() {
		//Funcion que consigue las relaciones de la base de datos
		
		return "relaciones";
	}
	
	//Recibe el nombre de la relacion a conseguir sus atributos
	@GetMapping("/atributos{nombreRelacion}")
	public List<String> getAtributos(String nombreRelacion) {
		
		List<String> list = null;
		
		if(nombreRelacion.equals("Credito")) {
			list = creditoDAO.obtenerAtributos();
		}
		else if(nombreRelacion.equals("Tienda")) {
			list = tiendaDAO.obtenerAtributos();
		}
		else if(nombreRelacion.equals("Pago")) {
			list = pagoDAO.obtenerAtributos();
		}
		else if(nombreRelacion.equals("Cliente")) {
			list = clienteDAO.obtenerAtributos();
		}
		else if(nombreRelacion.equals("Producto")) {
			list = productoDAO.obtenerAtributos();
		}
		else if(nombreRelacion.equals("TiendaProducto")) {
			list = tiendaproductoDAO.obtenerAtributos();
		}
		else if(nombreRelacion.equals("Categoria")) {
			list = categoriaDAO.obtenerAtributos();
		}
		else{
			list = subcategoriaDAO.obtenerAtributos();
		}
		
		
		
		return list;
	}
	
	//Recibe los predicados a válidar, retorna solo los predicados válidos
	@GetMapping("/validar/predicados{predicados}")
	public String validaPredicados(String predicados) {
		//Funcion encargada de retornar un arreglo de cadenas con los predicados validos
		System.out.println(predicados);
		String[] arregloPredicados = predicados.split("\n");
		return "";
	}

	//Recibe los miniterminos a válidar, retorna solo los miniterminos válidos
	@PostMapping("/validar/miniterminos")
	public String validaMiniterminos() {
		return "validando miniterminos";
	}

}
