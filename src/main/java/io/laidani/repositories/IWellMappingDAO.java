package io.laidani.repositories;

import java.util.Optional;

import io.laidani.models.WellMapping;

public interface IWellMappingDAO {

	void deleteWm(int id);

	void updateWm(int id, WellMapping wellMapping);

	void saveWm(WellMapping wellMapping);

	Optional<WellMapping> findByUid(int id);

	Optional<WellMapping> findByWid(int id);

}
