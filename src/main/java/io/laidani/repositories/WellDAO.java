package io.laidani.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.laidani.models.Well;

@Service
public class WellDAO implements IWellDAO {
	
	@Autowired
	private IWellRepo wellRepo;
	
	@Autowired
	private IWellPNSRepo wellpnsRepo;
	
	
	@Override
	public void saveWell(Well well) {
		wellRepo.save(well);
	}
	
	@Override
	public void updateWell(int uid, Well dirtyWell) {
	   Optional<Well> well = wellRepo.findById(uid);
	   if (well.isPresent()) {
		   dirtyWell.setUid(uid);  
		   wellRepo.save(dirtyWell);
	   }
	}
	
	public void deleteWell(int uid) {
	   /*
	    * TODO : enhance deletion ... 	
	    */
	   wellRepo.deleteById(uid);
	}
	
	
	@Override
	public Optional<Well> getWellById(int id) {
		return wellRepo.findById(id);
	}
	
	@Override
	public List<Well> getWellByName(String name) {
		return wellRepo.findByWellNameIgnoreCase(name);
	}
	
	@Override
	public List<Well> getAllWells() {
		return wellRepo.findAll();		
	}

	@Override
	public Page<Well> listAll(int pageNumber, String field, String direction) {
	
		Sort sort = direction.equals("asc") ? Sort.by(field).ascending() : Sort.by(field).descending();
		Pageable pageable = PageRequest.of(pageNumber, 10, sort);
		return wellpnsRepo.findAll(pageable);
	}
	
}
