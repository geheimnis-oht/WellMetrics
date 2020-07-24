package io.laidani.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.laidani.models.WellTest;

@Repository
public interface IWellTestRepo extends JpaRepository<WellTest, Integer>{

}
