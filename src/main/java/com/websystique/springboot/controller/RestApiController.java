package com.websystique.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.websystique.springboot.model.Cat;
import com.websystique.springboot.service.CatService;
import com.websystique.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	CatService catService; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All Cats---------------------------------------------

	@RequestMapping(value = "/cat/", method = RequestMethod.GET)
	public ResponseEntity<List<Cat>> listAllCats() {
		List<Cat> cats = catService.findAllCats();
		if (cats.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Cat>>(cats, HttpStatus.OK);
	}

	// -------------------Retrieve Single Cat------------------------------------------

	@RequestMapping(value = "/cat/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCat(@PathVariable("id") long id) {
		logger.info("Fetching cat with id {}", id);
		Cat cat = catService.findById(id);
		if (cat == null) {
			logger.error("cat with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("cat with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cat>(cat, HttpStatus.OK);
	}

	// -------------------Create a Cat-------------------------------------------

	@RequestMapping(value = "/cat/", method = RequestMethod.POST)
	public ResponseEntity<?> createCat(@RequestBody Cat cat, UriComponentsBuilder ucBuilder) {
		logger.info("Creating cat : {}", cat);

		if (catService.isCatExist(cat)) {
			logger.error("Unable to create. A cat with name {} already exist", cat.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A cat with name " + 
			cat.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		catService.saveCat(cat);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/cat/{id}").buildAndExpand(cat.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Cat ------------------------------------------------

	@RequestMapping(value = "/cat/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCat(@PathVariable("id") long id, @RequestBody Cat cat) {
		logger.info("Updating cat with id {}", id);

		Cat currentCat = catService.findById(id);

		if (currentCat == null) {
			logger.error("Unable to update. Cat with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. cat with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentCat.setName(cat.getName());
		currentCat.setAge(cat.getAge());
		currentCat.setBreed(cat.getBreed());

		catService.updateCat(currentCat);
		return new ResponseEntity<Cat>(currentCat, HttpStatus.OK);
	}

	// ------------------- Delete a Cat-----------------------------------------

	@RequestMapping(value = "/cat/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCat(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Cat with id {}", id);

		Cat cat = catService.findById(id);
		if (cat == null) {
			logger.error("Unable to delete. Cat with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Cat with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		catService.deleteCatById(id);
		return new ResponseEntity<Cat>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All cats-----------------------------

	@RequestMapping(value = "/cat/", method = RequestMethod.DELETE)
	public ResponseEntity<Cat> deleteAllCats() {
		logger.info("Deleting All Cats");

		catService.deleteAllCats();
		return new ResponseEntity<Cat>(HttpStatus.NO_CONTENT);
	}

}