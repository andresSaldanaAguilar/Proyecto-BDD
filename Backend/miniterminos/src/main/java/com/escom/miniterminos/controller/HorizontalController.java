package com.escom.miniterminos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HorizontalController {
	
	//Esta funcion se llama desde que la pagina cuando se carga 
	//retorna las relaciones de la base de datos
	@GetMapping("/relaciones")
	public String getRelaciones() {
		//Funcion que consigue las relaciones de la base de datos
		
		return "relaciones";
	}
	
	//Recibe el nombre de la relacion a conseguir sus atributos
	@GetMapping("/atributos{nombreRelacion}")
	public String getAtributos(String nombreRelacion) {
		//Funcion que consigue los atributos de la base de datos, dada una relacion
		System.out.println(nombreRelacion);
		return "";
	}
	
	//Recibe los predicados a válidar, retorna solo los predicados válidos
	@GetMapping("/validar/predicados{predicados}")
	public String validaPredicados(String predicados) {
		//Funcion encargada de retornar un arreglo de cadenas con los predicados validos
		System.out.println(predicados);
		return "";
	}

	//Recibe los miniterminos a válidar, retorna solo los miniterminos válidos
	@PostMapping("/validar/miniterminos")
	public String validaMiniterminos() {
		return "validando miniterminos";
	}

}
