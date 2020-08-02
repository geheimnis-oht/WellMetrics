package io.laidani.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.laidani.models.WellMapping;

@Repository
public interface IWellMappingRepo extends JpaRepository<WellMapping, Integer> {
   Optional<WellMapping> findByWid(int wid);  
}
