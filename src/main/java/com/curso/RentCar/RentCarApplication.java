package com.curso.RentCar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.curso.RentCar.model.entity.CarEntity;
import com.curso.RentCar.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RentCarApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(RentCarApplication.class, args);
	}

	@Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }
	
	@Autowired 	CarService carService;
	
	@Override
	public void run(String... args) throws Exception {
			carService.save(new CarEntity(null,"A0","opel","astra",25.0,null));
			carService.save(new CarEntity(null,"A1","seat","ibiza",30.0,null));
			carService.save(new CarEntity(null,"A2","mercedes","benz",80.0,null));
	}
}
