package io.laidani.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.laidani.models.Field;

@Repository
public interface IFieldRepo extends JpaRepository<Field, Integer> {

	Optional<Field> findByFieldNameIgnoreCase(String name);
	List<Field> findByFieldNameContaining(String name);
	
}
