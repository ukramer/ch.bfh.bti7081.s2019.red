//package ch.bfh.red.backend.test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//
//import ch.bfh.red.MhcPmsApplication;
//import ch.bfh.red.backend.models.Address;
//import ch.bfh.red.backend.models.Patient;
//import ch.bfh.red.backend.persistence.PersistenceManager;
//import ch.bfh.red.backend.services.IService;
//import ch.bfh.red.backend.services.PatientService;
//
//public class PatientCrudTest {
//
//	@Autowired
//	protected PatientService service = new PatientService();
//
//	private Exception createCheck = null;
//	private Exception readCheck = null;
//	private Exception updateCheck = null;
//	private Exception deleteCheck = null;
//	
////	public PatientCrudTest() {
////		AutowireCapableBeanFactory factory = MhcPmsApplication.getApplicationContext().getAutowireCapableBeanFactory();
////		factory.autowireBean( service );
////		factory.initializeBean( service, "service" );
////	}
//	
//	
//	public void before() {
//		if (service == null) {
//			System.err.println("service is null");
//			new NullPointerException().printStackTrace();
//			System.exit(0);
//		}
//			
//		
//		
//		
////		SpringApplication.run(MhcPmsApplication.class, new String[] {});
//		
//		Patient instance = null;
//		Patient instance2;
//		
//		// Create check
//		try {
//			instance = createInstance();
//			instance2 = service.persist(instance);
//		} catch (Exception e) {
//			createCheck = e;
//		}
//		
//		// Read check
////		try {
////			instance2 = service.getById(getId(instance));
////			instance = instance2;
////			final Patient readInstance1 = instance;
////			final Patient readInstance2 = instance2;
////			if (!readInstance1.equals(readInstance2)) {
////				String instances = String.format("memoryInstance=%s, dbInstance=%s",
////						readInstance1.toString(), readInstance2.toString());
////				readCheck = new Exception(
////						"Read instance is not equal to the instance in memory: "
////								+ instances);
////			}
////		} catch (Exception e) {
////			readCheck = e;
////		}
//		
//		// Update check
////		try {
////			setAnUpdateValue(instance);
////			instance2 = service.update(instance);
////			final Patient updateInstance1 = instance;
////			final Patient updateInstance2 = instance2;
////			if (!updateInstance1.equals(updateInstance2)) {
////				String instances = String.format("memoryInstance=%s, dbInstance=%s",
////						updateInstance1.toString(), updateInstance2.toString());
////				updateCheck = new Exception(
////						"Updated value could not persisted: " + instances);
////			}
////		} catch (Exception e) {
////			updateCheck = e;
////		}
//		
//		// Delete check
////		try {
////			final Integer id = getId(instance);
////			service.delete(id);
////			if (service.existById(id)) {
////				deleteCheck = new Exception("Item is still persisted: memoryInstance"
////						+ instance.toString());
////			}
////		} catch (Exception e) {
////			deleteCheck = e;
////		}
//		
//	}
//	
//	public void create() throws Exception {
//		assertException(createCheck);
//	}
//	
//	public void read() throws Exception {
//		assertException(readCheck);
//	}
//	
//	public void update() throws Exception {
//		assertException(updateCheck);
//	}
//	
//	public void delete() throws Exception {
//		assertException(deleteCheck);
//	}
//	
//	private void assertException(Exception e) throws Exception {
//		if (e != null)
//			throw e;
//	}
//	
//	protected Patient createInstance() {
//		Address address = new Address("Langstrasse", "12k", 7777, "Burgdorf");
//		return new Patient("Jürgen", "Müller", address);
//	}
//
//	protected Integer getId(Patient instance) {
//		return instance.getId();
//	}
//
//	protected void setAnUpdateValue(Patient instance) {
//		instance.setFirstName("Karl");
//	}
//	
//}
