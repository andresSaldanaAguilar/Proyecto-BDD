package com.escom.miniterminos.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.escom.miniterminos.db.CreditoDAO;
import com.escom.miniterminos.services.CreditoService;

import java.util.List;

@RestController
public class HorizontalController {
	
	
	@Autowired
	CreditoService creditoService;
	
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
			list = creditoService.obtenerAtributos();
		}
		
		return list;
	}
	
	//Recibe los predicados a válidar, retorna solo los predicados válidos
	@GetMapping("/validar/predicados{predicados}")
	public String validaPredicados(String predicados) {
		//Funcion encargada de retornar un arreglo de cadenas con los predicados validos
		System.out.println(predicados);
		String[] arregloPredicados = predicados.split("\n");
		System.out.println(arregloPredicados.length);
		return "";
	}

	//Recibe los miniterminos a válidar, retorna solo los miniterminos válidos
	@PostMapping("/validar/miniterminos")
	public String validaMiniterminos() {
		return "validando miniterminos";
	}

}
