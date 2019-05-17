package ch.bfh.red.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.repositories.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	public Address getAddressById(int id) {
		return addressRepository.findById(id).get();
	}
	
	public void saveOrUpdate(Address address) {
		addressRepository.save(address);
	}
	
	public void delete(int id) {
		addressRepository.deleteById(id);
	}

}
