package com.adoptmate.controllers;

import com.adoptmate.models.Pet;
import com.adoptmate.models.Shelter;
import com.adoptmate.repositories.ShelterRepository;
import com.adoptmate.tools.RESTConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(RESTConfiguration.SHELTER.REQUEST_MAPPING)
public class ShelterController {
    @Autowired
    private ShelterRepository repo;

    //CRUD
    @PostMapping(RESTConfiguration.SAVE) //OK
    public void save(@RequestBody Shelter shelter){
        repo.save(shelter);
    }

    @PutMapping(RESTConfiguration.UPDATE) //OK
    public void update(@PathVariable long id, @RequestBody Shelter shelter){
        try {
            Shelter uShelter = repo.findById(id).get();
            uShelter.setName(shelter.getName());
            uShelter.setCif(shelter.getCif());
            uShelter.setPhone(shelter.getPhone());
            //uShelter.setDescription(shelter.getDescription());
            uShelter.setLocation(shelter.getLocation());
            //uShelter.setMatchedUsers(shelter.getMatchedUsers());
            //uShelter.setMessages(shelter.getMessages());
            uShelter.setPassword(shelter.getPassword());
            //uShelter.setPets(shelter.getPets());
            //uShelter.setReachedUsers(shelter.getReachedUsers());
            repo.save(uShelter);
        }catch (NoSuchElementException ignored){}


    }

    @DeleteMapping(RESTConfiguration.DELETE) //OK
    public void delete(@PathVariable long id){
        try {
            repo.delete(repo.findById(id).get());
        }catch (NoSuchElementException ignored){}
    }

    @GetMapping(RESTConfiguration.GET) //OK
    public Shelter get(@PathVariable long id){
        try {
            return repo.findById(id).get();
        }catch (NoSuchElementException ignored){return null;}
    }

    //GET
    @GetMapping(RESTConfiguration.ALL) //OK
    public List<Shelter> findAll(){
        return repo.findAll();
    }

    @GetMapping(RESTConfiguration.SHELTER.FIND_BY_NAME) //OK
    public Shelter findByName(@PathVariable String name){
        try{
            return repo.findByName(name);
        }catch (NoSuchElementException ignored){
            return null;
        }
    }

    //PUT
    @PutMapping(RESTConfiguration.SHELTER.ADD_PET) //KO
    public void addPet(@PathVariable long id, @RequestBody Pet pet){
        Shelter shelter = repo.findById(id).get();
        shelter.getPets().add(pet);
        pet.setShelterFinal(shelter);
        repo.save(shelter);
    }

    @PutMapping(RESTConfiguration.SHELTER.REMOVE_PET)
    public void removePet(@PathVariable long id, @RequestBody Pet pet){
        Shelter shelter = repo.findById(id).get();
        shelter.getPets().remove(pet);
        repo.save(shelter);
    }


}
