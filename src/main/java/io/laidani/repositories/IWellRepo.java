package io.laidani.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.laidani.models.Well;

@Repository
public interface IWellRepo extends JpaRepository<Well, Integer> {

	List<Well> findByWellNameIgnoreCase(String name);
}
