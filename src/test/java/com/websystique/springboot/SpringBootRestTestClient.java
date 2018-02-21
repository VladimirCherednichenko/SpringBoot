package com.websystique.springboot;
 
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import com.websystique.springboot.model.Cat;
import org.springframework.web.client.RestTemplate;
 

public class SpringBootRestTestClient {
 
    public static final String REST_SERVICE_URI = "http://localhost:8080/SpringBootRestApi/api";
     
    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllcats(){
        System.out.println("Testing listAllcats API-----------");
         
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> catsMap = restTemplate.getForObject(REST_SERVICE_URI+"/cat/", List.class);
         
        if(catsMap!=null){
            for(LinkedHashMap<String, Object> map : catsMap){
                System.out.println("cat : id="+map.get("id")+", Name="+map.get("name")+", Age="+map.get("age")+", Breed="+map.get("breed"));;
            }
        }else{
            System.out.println("No cat exist----------");
        }
    }
     
    /* GET */
    private static void getcat(){
        System.out.println("Testing getcat API----------");
        RestTemplate restTemplate = new RestTemplate();
        Cat cat = restTemplate.getForObject(REST_SERVICE_URI+"/cat/1", Cat.class);
        System.out.println(cat);
    }
     
    /* POST */
    private static void createcat() {
        System.out.println("Testing create cat API----------");
        RestTemplate restTemplate = new RestTemplate();
        Cat cat = new Cat(0,"Sarah",51,"134");
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/cat/", cat, Cat.class);
        System.out.println("Location : "+uri.toASCIIString());
    }
 
    /* PUT */
    private static void updatecat() {
        System.out.println("Testing update cat API----------");
        RestTemplate restTemplate = new RestTemplate();
        Cat cat  = new Cat(1,"Tomy",33, "70000");
        restTemplate.put(REST_SERVICE_URI+"/cat/1", cat);
        System.out.println(cat);
    }
 
    /* DELETE */
    private static void deletecat() {
        System.out.println("Testing delete cat API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/cat/3");
    }
 
 
    /* DELETE */
    private static void deleteAllcats() {
        System.out.println("Testing all delete cats API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/cat/");
    }
 
    public static void main(String args[]){
        listAllcats();
        getcat();
        createcat();
        listAllcats();
        updatecat();
        listAllcats();
        deletecat();
        listAllcats();
        deleteAllcats();
        listAllcats();
    }
}