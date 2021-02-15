package net.guides.springboot2.springboot2jpacrudexample.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2jpacrudexample.model.Country;
import net.guides.springboot2.springboot2jpacrudexample.repository.CountryRepository;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")

public class CounrtyController {
	
	 @Autowired
	    private CountryRepository countryRepository;

	    @GetMapping("/countries")
	    public List<Country> getAllCountries() {
	        return countryRepository.findAll();
	    }

	    @GetMapping("/countries/{id}")
	    public ResponseEntity<Country> getEmployeeById(@PathVariable(value = "id") Long countryId)
	        throws ResourceNotFoundException {
	        Country country = countryRepository.findById(countryId)
	          .orElseThrow(() -> new ResourceNotFoundException("Country not found for this id :: " + countryId));
	        return ResponseEntity.ok().body(country);
	    }
	    
	    @PostMapping("/countries")
	    public Country createCountries(@Valid @RequestBody Country country) {
	        return countryRepository.save(country);
	    }

	    @PutMapping("/countries/{id}")
	    public ResponseEntity<Country> updateCountry(@PathVariable(value = "id") Long countryId,
	         @Valid @RequestBody Country countryDetails) throws ResourceNotFoundException {
	    	Country country = countryRepository.findById(countryId)
	        .orElseThrow(() -> new ResourceNotFoundException("Country not found for this id :: " + countryId));

	    	country.setDescription(countryDetails.getDescription());
	    	country.setPresidentName(countryDetails.getPresidentName());
	    	country.setCountryName(countryDetails.getCountryName());
	        final Country updatedCountry = countryRepository.save(country);
	        return ResponseEntity.ok(updatedCountry);
	    }

	    @DeleteMapping("/countries/{id}")
	    public Map<String, Boolean> deleteCountry(@PathVariable(value = "id") Long countryId)
	         throws ResourceNotFoundException {
	    	Country country = countryRepository.findById(countryId)
	       .orElseThrow(() -> new ResourceNotFoundException("Country not found for this id :: " + countryId));

	    	countryRepository.delete(country);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }

}
