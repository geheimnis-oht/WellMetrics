package io.laidani.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.laidani.models.Perimeter;

@Repository
public interface IPerimeterRepo extends JpaRepository<Perimeter, Integer> {

}
