package com.websystique.springboot.service;


import java.util.List;

import com.websystique.springboot.model.Cat;

public interface CatService {
	
	Cat findById(long id);
	
	Cat findByName(String name);
	
	void saveCat(Cat cat);
	
	void updateCat(Cat cat);
	
	void deleteCatById(long id);

	List<Cat> findAllCats();
	
	void deleteAllCats();
	
	boolean isCatExist(Cat cat);
	
}
