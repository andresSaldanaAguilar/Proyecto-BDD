package com.escom.miniterminos.services;


import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.escom.miniterminos.db.CreditoDAO;

@Component
public class CreditoService {
	@Autowired
	private CreditoDAO CreditoDAO;
	
	@Transactional
	public List<String>obtenerAtributos(){
		return CreditoDAO.obtenerAtributos();
	}

}
