package io.laidani.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import io.laidani.models.Well;

public interface IWellDAO {

	void saveWell(Well well);

	List<Well> getWellByName(String name);

	Optional<Well> getWellById(int id);

	void updateWell(int uid, Well dirtyWell);

	List<Well> getAllWells();
	
	Page<Well> listAll(int pageNumber, String field, String direction);

	void deleteWell(int id);

}
