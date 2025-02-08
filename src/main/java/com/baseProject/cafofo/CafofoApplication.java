package com.baseProject.cafofo;

import com.baseProject.cafofo.entity.*;
import com.baseProject.cafofo.repo.CustomerRepo;
import com.baseProject.cafofo.repo.OfferRepo;
import com.baseProject.cafofo.repo.OwnerRepo;
import com.baseProject.cafofo.repo.PropertyRepo;
import com.baseProject.cafofo.user.Role;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class CafofoApplication {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	OwnerRepo ownerRepo;
	@Autowired
	PropertyRepo propertyRepo;
	@Autowired
	OfferRepo offerRepo;


	public static void main(String[] args) {

		SpringApplication.run(CafofoApplication.class, args);
	}

	@PostConstruct
	public void populate(){

		Customer customer = new Customer();
		customer.setFirstname("Jhon");
		customer.setLastname("Smith");
		customer.setEmail("smith@gmail.com");
		customer.setPassword(passwordEncoder.encode("1234"));
		customer.setSecretAnswer(passwordEncoder.encode("1234"));
		customer.setActive(true);
		customer.setRole(Role.CUSTOMER);
		customerRepo.save(customer);

		Customer customer1 = new Customer();
		customer1.setFirstname("John");
		customer1.setLastname("Doe");
		customer1.setEmail("john.doe@example.com");
		customer1.setPassword(passwordEncoder.encode("1234"));
		customer1.setSecretAnswer(passwordEncoder.encode("answer123"));
		customer1.setActive(true);
		customer1.setRole(Role.CUSTOMER);
		customerRepo.save(customer1);

		Customer customer2 = new Customer();
		customer2.setFirstname("Alice");
		customer2.setLastname("Johnson");
		customer2.setEmail("alice.j@example.com");
		customer2.setPassword(passwordEncoder.encode("1234"));
		customer2.setSecretAnswer(passwordEncoder.encode("answer456"));
		customer2.setActive(true);
		customer2.setRole(Role.CUSTOMER);
		customerRepo.save(customer2);

		Owner owner = new Owner();
		owner.setFirstname("Cyrus");
		owner.setLastname("Shrestha");
		owner.setEmail("cyyrus@mail.com");
		owner.setPassword(passwordEncoder.encode("1234"));
		owner.setSecretAnswer(passwordEncoder.encode("1234"));
		owner.setActive(true);
		owner.setRole(Role.OWNER);
		//Save the new owner
		ownerRepo.save(owner);

		Owner owner1 = new Owner();
		owner1.setFirstname("Cyrusss");
		owner1.setLastname("Shresthaaaa");
		owner1.setEmail("cyrusss@mail.com");
		owner1.setPassword(passwordEncoder.encode("1234"));
		owner1.setSecretAnswer(passwordEncoder.encode("1234"));
		owner1.setActive(true);
		owner1.setRole(Role.OWNER);
		//Save the new owner
		ownerRepo.save(owner1);

		// Address 1
		Address address1 = new Address();
		address1.setCountry("United States");
		address1.setState("New York");
		address1.setCity("New York City");
		address1.setStreet("123 Main St");
		address1.setNumber("Apt 101");
		address1.setZip("10001");

		// Address 2
		Address address2 = new Address();
		address2.setCountry("United States");
		address2.setState("California");
		address2.setCity("Los Angeles");
		address2.setStreet("456 Oak St");
		address2.setNumber("Apt 203");
		address2.setZip("12345");

		// Address 3
		Address address3 = new Address();
		address3.setCountry("United States");
		address3.setState("Texas");
		address3.setCity("Austin");
		address3.setStreet("789 Pine St");
		address3.setNumber("Suite 101");
		address3.setZip("54321");

		// Address 4
		Address address4 = new Address();
		address4.setCountry("Canada");
		address4.setState("Ontario");
		address4.setCity("Toronto");
		address4.setStreet("101 Maple Ave");
		address4.setNumber("Unit 302");
		address4.setZip("M5H 2N2");

		//Address 5
		Address address5 = new Address();
		address5.setCountry("Canada");
		address5.setState("Ontario");
		address5.setCity("Toronto");
		address5.setStreet("101 Maple Ave");
		address5.setNumber("Unit 302");
		address5.setZip("M5H 2N2");

		PropImage propImage = new PropImage();
		propImage.setPath("1707514861328_house1.jpeg");

		PropImage propImage1 = new PropImage();
		propImage1.setPath("1707514861328_house2.jpeg");

		PropImage propImage2 = new PropImage();
		propImage2.setPath("1707514861328_house2.jpeg");

		PropImage propImage3 = new PropImage();
		propImage3.setPath("1707514861328_house2.jpeg");

		PropImage propImage4 = new PropImage();
		propImage4.setPath("1707514861328_house2.jpeg");

		PropImage propImage5 = new PropImage();
		propImage5.setPath("1707514861328_house2.jpeg");

		Collection<PropImage> propImages = new ArrayList<>();
		propImages.add(propImage);


		Collection<PropImage> propImages1 = new ArrayList<>();
		propImages1.add(propImage2);


		Collection<PropImage> propImages2 = new ArrayList<>();
		propImages2.add(propImage3);

		Collection<PropImage> propImages3 = new ArrayList<>();
		propImages3.add(propImage4);

		Collection<PropImage> propImages4 = new ArrayList<>();
		propImages4.add(propImage5);



		// Create Property 1
		Property property1 = new Property();
		property1.setPropertyName("Cozy Apartment");
		property1.setPrice(120000.0);
		property1.setNumberOfBed(2);
		property1.setNumberOfBathRoom(1);
		property1.setFactAndFeatures("Spacious living room, modern kitchen");
		property1.setHomeType(HomeType.APARTMENT);
		property1.setDealType(DealType.FOR_SALE);
		property1.setArea(85.5);
		property1.setApprovalStatus(true);
		property1.setAddress(address1);
		property1.setImage(propImages);
		property1.setOwner(owner);
		propertyRepo.save(property1);

		// Create Property 2
		Property property2 = new Property();
		property2.setPropertyName("Family House with Garden");
		property2.setPrice(250000.0);
		property2.setNumberOfBed(4);
		property2.setNumberOfBathRoom(2);
		property2.setFactAndFeatures("Large garden, family-friendly");
		property2.setHomeType(HomeType.HOUSE);
		property2.setDealType(DealType.FOR_RENT);
		property2.setArea(200.0);
		property2.setApprovalStatus(true);
		property2.setAddress(address2);
		property2.setImage(propImages1);
		property2.setOwner(owner);
		propertyRepo.save(property2);

		// Create Property 3
		Property property3 = new Property();
		property3.setPropertyName("Modern Condo with a View");
		property3.setPrice(180000.0);
		property3.setNumberOfBed(1);
		property3.setNumberOfBathRoom(1);
		property3.setFactAndFeatures("Stunning city view, modern amenities");
		property3.setHomeType(HomeType.CONDO);
		property3.setDealType(DealType.FOR_RENT);
		property3.setArea(65.0);
		property3.setApprovalStatus(true);
		property3.setImage(propImages2);
		property3.setAddress(address3);
		property3.setOwner(owner1);
		propertyRepo.save(property3);

		// Property 6
		Property property6 = new Property();
		property6.setPropertyName("Luxury Villa");
		property6.setPrice(850000.0);
		property6.setNumberOfBed(5);
		property6.setNumberOfBathRoom(6);
		property6.setFactAndFeatures("Private pool, spacious garden, ocean view");
		property6.setHomeType(HomeType.APARTMENT);
		property6.setDealType(DealType.FOR_RENT);
		property6.setArea(4000.0);
		property6.setApprovalStatus(true); // Approved
		property6.setImage(propImages3);
		property6.setAddress(address4); // Assuming address with ID 6 exists
		property6.setOwner(ownerRepo.findById(4L).orElse(null)); // Assuming owner with ID 1 exists

		// Property 7
		Property property7 = new Property();
		property7.setPropertyName("Modern Apartment");
		property7.setPrice(300000.0);
		property7.setNumberOfBed(2);
		property7.setNumberOfBathRoom(2);
		property7.setFactAndFeatures("City view, close to amenities");
		property7.setHomeType(HomeType.APARTMENT);
		property7.setDealType(DealType.FOR_SALE);
		property7.setArea(1200.0);
		property7.setApprovalStatus(true); // Approved
		property7.setImage(propImages4);
		property7.setAddress(address5); // Assuming address with ID 7 exists
		property7.setOwner(ownerRepo.findById(5L).orElse(null)); // Assuming owner with ID 2 exists
		propertyRepo.saveAll(Arrays.asList(property6,property7));

		// Offer 1
		Offer offer1 = new Offer();
		offer1.setCustomer(customerRepo.findById(1L).orElse(null)); // Assuming customer with ID 1 exists
		offer1.setOfferStatus(OfferStatus.PENDING);
		offer1.setOfferPrice(15000.0);
		offer1.setOfferDate(LocalDateTime.now());
		offer1.setProperty(propertyRepo.findById(1L).orElse(null)); // Assuming property with ID 1 exists

		// Offer 2
		Offer offer2 = new Offer();
		offer2.setCustomer(customerRepo.findById(2L).orElse(null)); // Assuming customer with ID 2 exists
		offer2.setOfferStatus(OfferStatus.ACCEPTED);
		offer2.setOfferPrice(20000.0);
		offer2.setOfferDate(LocalDateTime.now().minusDays(7)); // Offer made 7 days ago
		offer2.setProperty(propertyRepo.findById(2L).orElse(null)); // Assuming property with ID 2 exists

		// Offer 3
		Offer offer3 = new Offer();
		offer3.setCustomer(customerRepo.findById(3L).orElse(null)); // Assuming customer with ID 3 exists
		offer3.setOfferStatus(OfferStatus.REJECTED);
		offer3.setOfferPrice(18000.0);
		offer3.setOfferDate(LocalDateTime.now().minusDays(3)); // Offer made 3 days ago
		offer3.setProperty(propertyRepo.findById(3L).orElse(null)); // Assuming property with ID 3 exists
// Offer 4
		Offer offer4 = new Offer();
		offer4.setCustomer(customerRepo.findById(3L).orElse(null)); // Assuming customer with ID 4 exists
		offer4.setOfferStatus(OfferStatus.PENDING);
		offer4.setOfferPrice(16000.0);
		offer4.setOfferDate(LocalDateTime.now().minusDays(2)); // Offer made 2 days ago
		offer4.setProperty(propertyRepo.findById(1L).orElse(null)); // Assuming property with ID 1 exists

		// Offer 5
		Offer offer5 = new Offer();
		offer5.setCustomer(customerRepo.findById(2L).orElse(null)); // Assuming customer with ID 5 exists
		offer5.setOfferStatus(OfferStatus.PENDING);
		offer5.setOfferPrice(17500.0);
		offer5.setOfferDate(LocalDateTime.now().minusDays(5)); // Offer made 5 days ago
		offer5.setProperty(propertyRepo.findById(2L).orElse(null)); // Assuming property with ID 2 exists

		// Offer 6
		Offer offer6 = new Offer();
		offer6.setCustomer(customerRepo.findById(2L).orElse(null)); // Assuming customer with ID 6 exists
		offer6.setOfferStatus(OfferStatus.ACCEPTED);
		offer6.setOfferPrice(22000.0);
		offer6.setOfferDate(LocalDateTime.now().minusDays(10)); // Offer made 10 days ago
		offer6.setProperty(propertyRepo.findById(3L).orElse(null)); // Assuming property with ID 3 exists

		// Offer 7
		Offer offer7 = new Offer();
		offer7.setCustomer(customerRepo.findById(3L).orElse(null)); // Assuming customer with ID 7 exists
		offer7.setOfferStatus(OfferStatus.PENDING);
		offer7.setOfferPrice(19000.0);
		offer7.setOfferDate(LocalDateTime.now().minusDays(3)); // Offer made 3 days ago
		offer7.setProperty(propertyRepo.findById(4L).orElse(null)); // Assuming property with ID 4 exists
		// Save the offers
		offerRepo.saveAll(Arrays.asList(offer1, offer2, offer3,offer4,offer5,offer6,offer7));






	}




}
