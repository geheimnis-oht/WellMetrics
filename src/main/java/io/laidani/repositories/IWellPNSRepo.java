package io.laidani.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import io.laidani.models.Well;

@Repository
public interface IWellPNSRepo extends PagingAndSortingRepository<Well, Integer> {

	 
}
