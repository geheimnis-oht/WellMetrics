package io.laidani.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.laidani.models.Perimeter;
import io.laidani.repositories.IPerimeterDAO;

@Service
public class PerimeterService implements IPerimeterService {
	
	@Autowired
	IPerimeterDAO perimeterDAO;
	
	@Override
	public List<Perimeter> findAllPerimeters() {
		return perimeterDAO.getAllPerimeters();
	}
	
	@Override
	public Optional<Perimeter> findPerimeterById(int id) {
		return perimeterDAO.getPerimeterById(id);
	}
	
	@Override
	public void savePerimeter(Perimeter perimeter) {
		perimeterDAO.savePerimeter(perimeter);
	}
	
	@Override
	public void deletePerimeter(int id) {
		perimeterDAO.deletePerimeter(id);
	}
	
	
	
}
