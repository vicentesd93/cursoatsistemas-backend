package com.curso.RentCar.rent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.curso.RentCar.model.entity.CarEntity;
import com.curso.RentCar.model.entity.RentEntity;
import com.curso.RentCar.repository.RentRepository;
import com.curso.RentCar.service.RentService;
import com.curso.RentCar.service.RentServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class RentSerivceTest {

	private final RentEntity rent = new RentEntity();
	
	@InjectMocks
	private RentService rentService = new RentServiceImpl();
	@Mock
	private RentRepository rentRepository;
	
	@Test
	public void testFindAll() {
		// Given 
		final Pageable pageable = PageRequest.of(0, 10);
		final List<RentEntity> list = new ArrayList<RentEntity>();
		list.add(rent);
		final Page<RentEntity> pages = new PageImpl<RentEntity>(list);
		// When
		Mockito.when(rentRepository.findAll(pageable)).thenReturn(pages);
		// Then
		final Page<RentEntity> books = rentService.findAll(pageable);
		Assert.assertNotNull(books);
		Assert.assertEquals(books, pages);
	}
	
	@Test
	public void testFindByIdFound() {
		// Given 
		final Double price = 25.0;
		rent.setPrice(price);
		// When
		Mockito.when(rentRepository.findById(1)).thenReturn(Optional.ofNullable(rent));
		// Then
		final Optional<RentEntity> rent = rentService.findById(1);
		Assert.assertNotNull(rent);
		Assert.assertNotNull(rent.get());
		Assert.assertEquals(rent.get().getPrice(), price);
	}
	
	@Test
	public void testFindByIdNotFound() {
		// Given 
		// When
		Mockito.when(rentRepository.findById(1234)).thenReturn(Optional.empty());
		// Then
		final Optional<RentEntity> rent = rentRepository.findById(1234);
		Assert.assertNotNull(rent);
		Assert.assertEquals(rent, Optional.empty());
	}
	
	@Test
	public void testCarAviable() {
		// Given
		LocalDate fini = LocalDate.parse("2019-10-01");
		LocalDate ffin = LocalDate.parse("2019-10-05");
		CarEntity car = new CarEntity();
		car.setId(1);
		//When
		Mockito.when(rentRepository.findAll()
				.stream()
				.filter(x -> !(fini.isAfter(x.getEndD()) &&
						ffin.isAfter(x.getEndD())) && 
						!(fini.isBefore(x.getInitD()) &&
								ffin.isBefore(x.getInitD())) && 
						x.getCar().equals(car) ? true : false)
				.findFirst()).thenReturn(Optional.empty());
		//Then
		Optional<RentEntity> rent = rentRepository.findAll()
				.stream()
				.filter(x -> !(fini.isAfter(x.getEndD()) &&
						ffin.isAfter(x.getEndD())) && 
						!(fini.isBefore(x.getInitD()) &&
								ffin.isBefore(x.getInitD())) && 
						x.getCar().equals(car) ? true : false)
				.findFirst();
		Assert.assertNotNull(rent);
		Assert.assertEquals(rent, Optional.empty());
		
	}
}
