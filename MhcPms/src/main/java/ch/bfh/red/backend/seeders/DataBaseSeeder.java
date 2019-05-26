package ch.bfh.red.backend.seeders;


import ch.bfh.red.backend.factories.AddressFactory;
import ch.bfh.red.backend.factories.PatientFactory;
import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.repositories.AcademicTitleRepository;
import ch.bfh.red.backend.repositories.AddressRepository;
import ch.bfh.red.backend.repositories.PatientRepository;
import ch.bfh.red.backend.repositories.TherapistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataBaseSeeder {


    private AddressRepository addressRepository;
    private PatientRepository patientRepository;
    private TherapistRepository therapistRepository;
    private AcademicTitleRepository academicTitleRepository;

    @Autowired
    public DataBaseSeeder(AddressRepository addressRepository,
            PatientRepository patientRepository,
            TherapistRepository therapistRepository,
            AcademicTitleRepository academicTitleRepository){
        this.addressRepository = addressRepository;
        this.patientRepository = patientRepository;
        this.therapistRepository = therapistRepository;
        this.academicTitleRepository = academicTitleRepository;

    }

    @EventListener
    public void seed(ContextRefreshedEvent event){
        seedPatientTable();
        seedTherapistTable();
        seedAcademicTitleTable();
    }



    public void seedPatientTable(){
        AddressFactory addressFactory = new AddressFactory();
        List<Address> addressList = addressFactory.generateAddresses(200);
        PatientFactory patientFactory = new PatientFactory();
        List<Patient> patientList  = patientFactory.generatePatients(addressList);
        addressRepository.saveAll(addressList);
        patientRepository.saveAll(patientList);
    }

    public void seedTherapistTable(){
        ;
    }

    public void seedAcademicTitleTable(){
        ;
    }
}
