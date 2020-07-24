package io.laidani.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.laidani.models.Well;
import io.laidani.repositories.IWellDAO;

@Service
public class WellService implements IWellService{
	
	@Autowired
	IWellDAO wellDAO;
	
	@Override
	public List<Well> findAllWells() {		
		return wellDAO.getAllWells(); 
	}	
	
	@Override
	public void addWell (Well well) {
		wellDAO.addWell(well);
	}

}
