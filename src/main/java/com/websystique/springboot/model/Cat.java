package com.websystique.springboot.model;
	
public class Cat {

	private long id;
	
	private String name;
	
	private int age;
	
	private String breed;

	public Cat(){
		id=0;
	}
	
	public Cat(long id, String name, int age, String breed){
		this.id = id;
		this.name = name;
		this.age = age;
		this.breed = breed;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cat other = (Cat) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "cat [id=" + id + ", name=" + name + ", age=" + age
				+ ", breed=" + breed + "]";
	}


}
