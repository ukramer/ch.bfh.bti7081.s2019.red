package ch.bfh.red.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ch.bfh.red.backend.models.Address;

@Repository("addressRepository")
public interface AddressRepository extends CrudRepository<Address, Integer> {

}
