package com.websystique.springboot.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.websystique.springboot.model.Cat;



@Service("catService")
public class CatServiceImpl implements CatService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<Cat> cats;
	
	static{
		cats= populateDummycats();
	}

	public List<Cat> findAllCats() {
		return cats;
	}
	
	public Cat findById(long id) {
		for(Cat cat : cats){
			if(cat.getId() == id){
				return cat;
			}
		}
		return null;
	}
	
	public Cat findByName(String name) {
		for(Cat cat : cats){
			if(cat.getName().equalsIgnoreCase(name)){
				return cat;
			}
		}
		return null;
	}
	
	public void saveCat(Cat cat) {
		cat.setId(counter.incrementAndGet());
		cats.add(cat);
	}

	public void updateCat(Cat cat) {
		int index = cats.indexOf(cat);
		cats.set(index, cat);
	}

	public void deleteCatById(long id) {
		
		for (Iterator<Cat> iterator = cats.iterator(); iterator.hasNext(); ) {
		    Cat cat = iterator.next();
		    if (cat.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isCatExist(Cat cat) {
		return findByName(cat.getName())!=null;
	}
	
	public void deleteAllCats(){
		cats.clear();
	}

	private static List<Cat> populateDummycats(){
		List<Cat> cats = new ArrayList<Cat>();
		cats.add(new Cat(counter.incrementAndGet(),"Sam",3, "Bengal"));
		cats.add(new Cat(counter.incrementAndGet(),"Tom",4, "Cymric"));
		cats.add(new Cat(counter.incrementAndGet(),"Jerome",5, "Devon Rex"));
		cats.add(new Cat(counter.incrementAndGet(),"Silvia",4, "Korat"));
		return cats;
	}

}
