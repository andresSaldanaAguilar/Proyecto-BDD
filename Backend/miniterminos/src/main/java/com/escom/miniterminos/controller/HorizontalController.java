package com.escom.miniterminos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.escom.miniterminos.entities.PredicadosBean;


@RestController
public class HorizontalController {
	//Recibe el nombre de una relación de una BD y retorna los atributos de dicha relación
	@GetMapping("/relaciones")
	public String getRelaciones() {
		//Funcion que consigue las relaciones de la base de datos
		
		return "";
	}
	
	@GetMapping("/atributos")
	public String getAtributos() {
		//Funcion que consigue los atributos de la base de datos, dada una relacion
		
		return "";
	}
	
	//Recibe los predicados a válidar, retorna solo los predicados válidos
	@PostMapping("/validar/predicados")
	public String validaPredicados(@RequestBody PredicadosBean bean) {
		//Funcion encargada de retornar un arreglo de cadenas con los predicados validos
		//validaPredicados(bean.getArrayPredicados());
		
		return "";
	}

	//Recibe los miniterminos a válidar, retorna solo los miniterminos válidos
	@PostMapping("/validar/miniterminos")
	public String validaMiniterminos() {
		return "validando miniterminos";
	}
	

}
