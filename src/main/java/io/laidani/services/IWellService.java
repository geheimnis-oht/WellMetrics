package io.laidani.services;

import java.util.List;

import io.laidani.models.Well;

public interface IWellService {

	List<Well> findAllWells();

	void addWell(Well well);

}
