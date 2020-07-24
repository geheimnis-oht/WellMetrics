package io.laidani.repositories;

import java.util.List;
import java.util.Optional;
import io.laidani.models.Well;

public interface IWellDAO {

	void addWell(Well well);

	List<Well> getWellByName(String name);

	Optional<Well> getWellById(int id);

	void updateWell(int uid, Well dirtyWell);

	List<Well> getAllWells();

}
