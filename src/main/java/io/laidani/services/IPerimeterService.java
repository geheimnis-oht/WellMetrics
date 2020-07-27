package io.laidani.services;

import java.util.List;
import java.util.Optional;

import io.laidani.models.Perimeter;

public interface IPerimeterService {

	List<Perimeter> findAllPerimeters();

	void savePerimeter(Perimeter perimeter);

	void deletePerimeter(int id);

	Optional<Perimeter> findPerimeterById(int id);

}
