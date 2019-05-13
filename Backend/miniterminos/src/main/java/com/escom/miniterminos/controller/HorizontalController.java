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
	@Autowired
	BaseDAO baseDAO;
	
	
	//Esta funcion se llama desde que la pagina cuando se carga 
	//retorna las relaciones de la base de datos
	@GetMapping("/relaciones")
	public List<String> getRelaciones() {
		
		List<String> list = baseDAO.obtenerTablas();
		
		return list;
	}
	
	//Recibe el nombre de la relacion a conseguir sus atributos
	@GetMapping("/atributos{nombreRelacion}")
	public List<String> getAtributos(String nombreRelacion) {
		
		List<String> list = null;
		
		nombreRelacion = nombreRelacion.toLowerCase();
		
		if(nombreRelacion.equals("credito")) {
			list = creditoDAO.obtenerAtributos();
		}
		else if(nombreRelacion.equals("tienda")) {
			list = tiendaDAO.obtenerAtributos();
		}
		else if(nombreRelacion.equals("pago")) {
			list = pagoDAO.obtenerAtributos();
		}
		else if(nombreRelacion.equals("cliente")) {
			list = clienteDAO.obtenerAtributos();
		}
		else if(nombreRelacion.equals("producto")) {
			list = productoDAO.obtenerAtributos();
		}
		else if(nombreRelacion.equals("tiendaproducto")) {
			list = tiendaproductoDAO.obtenerAtributos();
		}
		else if(nombreRelacion.equals("categoria")) {
			list = categoriaDAO.obtenerAtributos();
		}
		else{
			list = subcategoriaDAO.obtenerAtributos();
		}
		return list;
	}
	
	//Recibe los predicados a válidar, retorna solo los predicados válidos
	@GetMapping("/validar/predicados{predicados}")
	public String validaPredicados(String predicados, String relacion) {
		//Funcion encargada de retornar un arreglo de cadenas con los predicados validos
		
		int x;
		int validacion1 = 1 , validacion2;
		
		String[] arregloPredicados = predicados.split("\n");
		
		for( x=0 ; x<arregloPredicados.length ; x++ ){
			if( baseDAO.evaluar(relacion,arregloPredicados[x]) == 0 ){
				validacion1 = 0;
			}
			System.out.println( baseDAO.evaluar(relacion,arregloPredicados[x] ) );
		}
		
		validacion2 = baseDAO.verificar(relacion, arregloPredicados);
		
		
		if( validacion1 == 1 && validacion2 == 1){
			return "1";
		}
		else{
			return "0";
		}
	}
	
	//Recibe los predicados y genera los miniterminos
	@GetMapping("/generar/miniterminos{predicados}")
	public String generaMiniterminos(String predicados, String relacion) {
		System.out.println("Relacion:\n"+relacion);
		System.out.println("predicados:\n"+predicados);
		String[] arregloPredicados = predicados.split("\n");
		return "";
	}

	//Recibe los miniterminos a válidar, retorna solo los miniterminos válidos
	@GetMapping("/validar/miniterminos{miniterminos}")
	public String validaMiniterminos(String miniterminos, String relacion) {
		
		int x;
		String fin = "";
		
		String[] arregloMiniterminos = miniterminos.split("\n");
		
		for( x=0 ; x<arregloMiniterminos.length ; x++ ){
			if( baseDAO.evaluar(relacion,arregloMiniterminos[x].substring(3)) != 0 ){
				fin += arregloMiniterminos[x] + "\n";
			}
		}
		System.out.println(fin);
		
		return fin;
	}
	
	//Recibe los miniterminos a generar, y donde sse almacenaran
	@GetMapping("/enviar{miniterminos}")
	public String enviaMiniterminos(String miniterminos, String relacion, String sitio) {
		System.out.println("Relacion:\n"+relacion);
		System.out.println("Miniterminos:\n"+miniterminos);
		System.out.println("Sitio:\n"+sitio);
		String[] arregloPredicados = miniterminos.split("\n");
		return "";
	}

}
