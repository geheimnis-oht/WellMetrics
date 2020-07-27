package io.laidani.repositories;

import java.util.List;
import java.util.Optional;

import io.laidani.models.Perimeter;

public interface IPerimeterDAO {

	Optional<Perimeter> getPerimeterById(int id);

	List<Perimeter> getAllPerimeters();

	void updatePerimeter(Perimeter perimeter, int id);

	void savePerimeter(Perimeter perimeter);

	void deletePerimeter(int id);

}
