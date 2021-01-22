package com.iteco.basetask;

import com.iteco.basetask.entities.Address;
import com.iteco.basetask.repositories.AddressRepository;
import com.iteco.basetask.services.AddressServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasetaskApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(BasetaskApplicationTests.class);

	@Autowired
	private AddressServiceImpl addressService;

	@MockBean
	private AddressRepository addressRepository;

	@Before
	public void before() {
		List<Address> addresses = new ArrayList<>();
		addresses.add(new Address(1L,"foo@gmail.ru"));
		addresses.add(new Address(2L, "foo_next@gmail.ru"));
		addresses.add(new Address(3L, "foo_next_afterNext@gmail.ru"));
	}

	private Address findCacheByEmail(String email) {
		final Address addresses = addressRepository.findCacheByEmail(email);
		return addresses;
	}

	@Test
	public void findCacheByEmail() {
		findCacheByEmail("foo@gmail.ru");
		findCacheByEmail("foo@gmail.ru");
	}


	@Test
	public void addAddress() {
		Address john = new Address(1L, "jhon@mail.ru");
		boolean isCreated = addressService.save(john);
		Assert.assertTrue(isCreated);

	}

	@Before
	public void setUp() {
		Address john = new Address("john@gmail.com");
		john.setId(11L);

		Address bob = new Address("bob@gmail.com");
		Address alex = new Address("alex@gmail.com");

		List<Address> allEmployees = Arrays.asList(john, bob, alex);

		Mockito.when(addressRepository.findAddressByEmail(john.getEmail())).thenReturn(john);
		Mockito.when(addressRepository.findAddressByEmail(alex.getEmail())).thenReturn(alex);
		Mockito.when(addressRepository.findAddressByEmail("wrong_name")).thenReturn(null);
		Mockito.when(addressRepository.findById(john.getId())).thenReturn(Optional.of(john));
		Mockito.when(addressRepository.findAll()).thenReturn(allEmployees);
		Mockito.when(addressRepository.findById(-99L)).thenReturn(Optional.empty());
	}
	@Test
	public void whenValidName_thenEmployeeShouldBeFound() {
		String email = "alex@mail.ru";
		addressService.save(new Address(1L, email));
		Address found = addressService.getAddressByEmail(email);

		assertThat(found.getEmail()).isEqualTo(email);
	}
	@Test
	public void whenInValidName_thenEmployeeShouldNotBeFound() {
		Address fromDb = addressService.getAddressByEmail("wrong_name");
		assertThat(fromDb).isNull();

		verifyFindByNameIsCalledOnce("wrong_name");
	}

	private void verifyFindByNameIsCalledOnce(String name) {
		Mockito.verify(addressRepository, VerificationModeFactory.times(1)).findAddressByEmail(name);
		Mockito.reset(addressRepository);
	}

	private void verifyFindByIdIsCalledOnce() {
		Mockito.verify(addressRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
		Mockito.reset(addressRepository);
	}

	private void verifyFindAllEmployeesIsCalledOnce() {
		Mockito.verify(addressRepository, VerificationModeFactory.times(1)).findAll();
		Mockito.reset(addressRepository);
	}




}

