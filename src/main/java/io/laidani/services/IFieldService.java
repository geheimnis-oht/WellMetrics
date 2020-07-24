package io.laidani.services;

import java.util.List;

import io.laidani.models.Field;

public interface IFieldService {

	void addField(Field field);

	void updateField(Field field, int id);

	List<Field> findAllFields();

	void deleteField(int id);

	void saveField(Field field);

}
