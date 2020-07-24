package io.laidani.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.laidani.models.Field;
import io.laidani.repositories.IFieldDAO;

@Service
public class FieldService implements IFieldService {
	
	@Autowired
	IFieldDAO fieldDAO;
	
	@Override
	public List<Field> findAllFields(){
		return fieldDAO.getAllFields();
	}
	
	@Override
	public void addField(Field field) {
		fieldDAO.addField(field);
	}
	
	@Override
	public void updateField(Field field, int id) {
		Optional<Field> opField = fieldDAO.getFieldById(id);
		if (opField.isPresent()) {
			field.setUid(id);
			fieldDAO.addField(field);
		}
	}
	
	// TODO : enhance deletion
	
	@Override
	public void deleteField(int id) {
		fieldDAO.deleteField(id);
	}

	@Override
	public void saveField(Field field) {
		fieldDAO.addField(field);
	}

}   
