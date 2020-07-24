package io.laidani.repositories;

import java.util.List;
import java.util.Optional;

import io.laidani.models.Field;

public interface IFieldDAO {

	Optional<Field> getFieldById(int id);

	Optional<Field> getFieldByName(String name);

	List<Field> getAllFields();

	void addField(Field field);

	void deleteField(int id);

	void updateField(Field field, int id);

}
