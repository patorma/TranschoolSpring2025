package com.patricio.contreras.service;

import org.springframework.stereotype.Service;

import com.patricio.contreras.mapper.RecorridoMapper;
import com.patricio.contreras.repository.RecorridoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecorridoService {

	private final RecorridoRepository recorridoRepository;
	
	private final RecorridoMapper recorridoMapper;
	
	//quede aqui
	 
}
