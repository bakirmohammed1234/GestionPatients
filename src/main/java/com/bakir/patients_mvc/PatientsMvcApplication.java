package com.bakir.patients_mvc;

import com.bakir.patients_mvc.entities.Patient;
import com.bakir.patients_mvc.repositories.PatientRepository;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

	public static void main(String[] args) {

		SpringApplication.run(PatientsMvcApplication.class, args);

	}
	@Bean
	CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
		return args -> {
//			patientRepository.save(new Patient(0,"bakir",new Date(),false,120));
//			patientRepository.save(new Patient(0,"bahafid",new Date(),true,112));
//			patientRepository.save(new Patient(0,"khadija",new Date(),false,118));
//			patientRepository.save(new Patient(0,"imane",new Date(),true,140));
//
//			patientRepository.findAll().forEach(p->{
//				System.out.println(p.getNom());
//			});
		};
	}

}
