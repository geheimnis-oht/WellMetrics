package io.laidani.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	public Optional<Well> findWellById(int id) {		
		return wellDAO.getWellById(id);
	}
	
	@Override
	public void saveWell (Well well) {
		wellDAO.saveWell(well);
	}
	
	@Override
	public void deleteWell (int id) {
		wellDAO.deleteWell(id);
	}
	
	@Override
	public void updateWell(Well well, int id) {
		Optional<Well> opWell = wellDAO.getWellById(id);
		if (opWell.isPresent()) {
			well.setUid(id);
			wellDAO.saveWell(well);
		} 
	}

	@Override
	public Page<Well> findAll(int pageNumber, String field, String direction) {
		return wellDAO.listAll(pageNumber, field, direction);
	}

}
