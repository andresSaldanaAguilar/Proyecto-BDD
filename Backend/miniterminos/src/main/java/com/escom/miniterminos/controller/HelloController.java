package com.escom.miniterminos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	//Probar en postman con: http://localhost:8080/prueba
	@GetMapping("/prueba")
	public String getSaludo() {
		return "hola andres";
	}
	
}
