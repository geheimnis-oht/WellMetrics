package io.laidani.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.laidani.models.Perimeter;

@Service
public class PerimeterDAO implements IPerimeterDAO {
	
	@Autowired
	IPerimeterRepo perimeterRepo;
	
	@Override
	public List<Perimeter> getAllPerimeters(){
		return perimeterRepo.findAll();
	}
	
	@Override
	public Optional<Perimeter> getPerimeterById(int id){
		return perimeterRepo.findById(id);
	}
	
	@Override
	public void savePerimeter(Perimeter perimeter) {
		perimeterRepo.save(perimeter);
	}
	
	@Override
	public void updatePerimeter(Perimeter perimeter, int id) {
		Optional<Perimeter> opPerimeter = perimeterRepo.findById(id);
		if(opPerimeter.isPresent()) {
			perimeter.setUid(id);
			perimeterRepo.save(perimeter);
		}
	}
	
	@Override
	public void deletePerimeter(int id) {
		perimeterRepo.deleteById(id);
	}

}
