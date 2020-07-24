package io.laidani.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.laidani.models.Field;

@Service
public class FieldDAO implements IFieldDAO {

	@Autowired
	IFieldRepo fieldRepo;
	
	@Override
	public void addField(Field field) {
	 fieldRepo.save(field);	
	}
	
	@Override
	public List<Field> getAllFields(){
		return fieldRepo.findAll();
	}
	
	@Override
	public Optional<Field> getFieldByName(String name) {
		return fieldRepo.findByFieldNameIgnoreCase(name);		
	}
	
	@Override
	public Optional<Field> getFieldById(int id) {
		return fieldRepo.findById(id);		
	}
	
	@Override
	public void updateField(Field field, int id) {
		Optional<Field> opField = fieldRepo.findById(id);
		if (opField.isPresent()) {
			field.setUid(id);
			fieldRepo.save(field);
		}
	}
	
	@Override
	public void deleteField(int id) {
		// TODO : Inhance Deletion
		fieldRepo.deleteById(id);
	}
		
}
