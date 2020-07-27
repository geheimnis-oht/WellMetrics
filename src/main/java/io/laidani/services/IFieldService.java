package io.laidani.services;

import java.util.List;
import java.util.Optional;

import io.laidani.models.Field;

public interface IFieldService {

	void updateField(Field field, int id);

	List<Field> findAllFields();

	void deleteField(int id);

	void saveField(Field field);

	Optional<Field> findFieldById(int id);

}
