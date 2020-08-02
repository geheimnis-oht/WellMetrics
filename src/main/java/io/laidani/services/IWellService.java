package io.laidani.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import io.laidani.models.Well;

public interface IWellService {

	List<Well> findAllWells();
	Page<Well> findAll(int pageNumber, String field, String direction);	
	void saveWell(Well well);
	void updateWell(Well well, int id);
	Optional<Well> findWellById(int id);
	void deleteWell(int id);

}
