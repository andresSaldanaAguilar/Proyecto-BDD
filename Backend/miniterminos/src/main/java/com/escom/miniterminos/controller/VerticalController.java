package com.escom.miniterminos.controller;

import java.util.Iterator;

import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.escom.miniterminos.db.BaseDAO;
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
public class VerticalController {
	
	
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
	@Autowired
	BaseDAO baseDAO;
	
	
	//Esta funcion se llama desde que la pagina cuando se carga 
	//retorna las relaciones de la base de datos
	@GetMapping("/relacionesV")
	public List<String> getRelacionesV() {
		
		List<String> list = baseDAO.obtenerTablas();	
		return list;
		
	}
	
	//Recibe el nombre de la relacion a conseguir sus atributos
	@GetMapping("/atributosV{nombreRelacion}")
	public List<String> getAtributosV(String nombreRelacion) {
		
		List<String> list = null;
		nombreRelacion = nombreRelacion.toLowerCase();
		list = baseDAO.obtenerAtributos(nombreRelacion);
		return list;
		
	}
	
	@GetMapping("/llaves{nombreRelacion}")
	public List<String> getLlaves(String nombreRelacion) {
		
		List<String> list = null;
		nombreRelacion = nombreRelacion.toLowerCase();
		list = baseDAO.llave(nombreRelacion);
		return list;
		
	}
	
	//Recibe los fragmentos a generar, y donde sse almacenaran
		@GetMapping("/enviarV{miniterminos}")
		public int enviaMiniterminos(String miniterminos, String relacion, String sitio, String nombre) {
			String[] array = miniterminos.split("\n");
			baseDAO.enviarV(sitio,nombre,relacion,array);
			return 1;
		}

}
