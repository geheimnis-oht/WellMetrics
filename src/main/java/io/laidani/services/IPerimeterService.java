package io.laidani.services;

import java.util.List;

import io.laidani.models.Perimeter;

public interface IPerimeterService {

	void addPerimeter(Perimeter perimeter);

	List<Perimeter> findAllPerimeters();

	void savePerimeter(Perimeter perimeter);

	void deletePerimeter(int id);

}
