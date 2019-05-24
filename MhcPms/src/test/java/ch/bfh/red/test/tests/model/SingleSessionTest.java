package ch.bfh.red.test.tests.model;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.models.AcademicTitle;
import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.services.IService;
import ch.bfh.red.test.tests.StartupTest;

public class SingleSessionTest extends StartupTest {
    
    @Autowired
    private IService<Address> addressService;
    
    @Autowired
    private IService<SessionType> sessionTypeService;
    
    @Autowired
    private IService<Patient> patientService;
    
    @Autowired
    private IService<Therapist> therapistService;
    
    @Autowired
    private IService<SingleSession> singleSessionService;
    
    @Autowired
    private IService<AcademicTitle> academicTitleIService;
    
    @Test
    public void testSessionTypeMapping() {
        Address address = new Address("OneToOne funktioniert auch Strasse", ":)", 1234, "Bern");
        addressService.add(address);
        
        Patient patient = new Patient("Ueli", "Kramer", address);
        patientService.add(patient);
        
        AcademicTitle title = new AcademicTitle("Dr.", "-");
        academicTitleIService.add(title);
        
        Therapist therapist = new Therapist("marle34", "1234", title, "Marlies", "Lotti", address);
        therapistService.add(therapist);
        
        SessionType type1 = new SessionType("Talk", "-");
        sessionTypeService.add(type1);
        
        SingleSession session1 = new SingleSession(patient, therapist, new Date(),
                new Date(), type1);
        singleSessionService.add(session1);
        
        SingleSession session2 = new SingleSession(patient, therapist, new Date(),
                new Date(), type1);
        singleSessionService.add(session2);
        
    }
    
}
