package ch.bfh.red.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.repositories.AddressRepository;

@Service
public class AddressServiceImpl implements IService<Address> {
	
	@Autowired
	private AddressRepository repository;

	@Override
	public CrudRepository<Address, Integer> getRepository() {
		return repository;
	}

}
