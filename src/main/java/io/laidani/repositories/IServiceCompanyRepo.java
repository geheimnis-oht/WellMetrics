package io.laidani.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.laidani.models.ServiceCompany;

@Repository
public interface IServiceCompanyRepo extends JpaRepository<ServiceCompany, Integer> {

}
