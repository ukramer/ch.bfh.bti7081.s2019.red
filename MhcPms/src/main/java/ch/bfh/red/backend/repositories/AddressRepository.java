package ch.bfh.red.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.bfh.red.backend.models.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {

}
