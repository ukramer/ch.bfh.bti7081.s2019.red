package ch.bfh.red.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.repositories.AddressRepository;

@Service("addressService")
public class AddressService {
	private AddressRepository addressRepository;
	
	public AddressService(@Autowired AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	
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
