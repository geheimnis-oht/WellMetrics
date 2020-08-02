package io.laidani.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.laidani.models.WellMapping;

@Service
public class WellMappingDAO implements IWellMappingDAO {
	
	@Autowired
	IWellMappingRepo wellMappingRepo;
	
	@Override
	public Optional<WellMapping> findByUid(int id) {
		return wellMappingRepo.findById(id);
	}
		
	@Override
	public Optional<WellMapping> findByWid(int id) {
		return wellMappingRepo.findByWid(id);
	}
	
	@Override
	public void saveWm(WellMapping wellMapping) {
		wellMappingRepo.save(wellMapping);
	}
	
	@Override
	public void updateWm(int id, WellMapping wellMapping) {
		Optional<WellMapping> opWm = wellMappingRepo.findById(id);
		if (opWm.isPresent()) {
			WellMapping wM = opWm.get();
			wM.setUid(id);
			wellMappingRepo.save(wM);
		}
 	}
	
	@Override
	public void deleteWm(int id) {
		wellMappingRepo.deleteById(id);
	}
}
