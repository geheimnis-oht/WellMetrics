package io.laidani.services;

import java.util.List;
import java.util.Optional;

import io.laidani.models.Well;

public interface IWellService {

	List<Well> findAllWells();
	void saveWell(Well well);
	void updateWell(Well well, int id);
	Optional<Well> findWellById(int id);
	void deleteWell(int id);

}
