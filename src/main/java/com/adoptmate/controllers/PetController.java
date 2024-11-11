package com.adoptmate.controllers;

import com.adoptmate.enumerations.Specie;
import com.adoptmate.models.Breed;
import com.adoptmate.models.Pet;
import com.adoptmate.repositories.BreedRepository;
import com.adoptmate.repositories.PetRepository;
import com.adoptmate.repositories.ShelterRepository;
import com.adoptmate.tools.RESTConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(RESTConfiguration.PET.REQUEST_MAPPING)
public class PetController {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private BreedRepository breedRepository;
    @Autowired
    private ShelterRepository shelterRepository;


    //CRUD
    @PostMapping(RESTConfiguration.SAVE)
    public void save(@RequestBody Pet pet){
        pet.setBreedFinal(breedRepository.findByName(pet.getBreed()));
        pet.setShelterFinal(shelterRepository.findByName(pet.getShelter()));
        petRepository.save(pet);
    }

    @PutMapping(RESTConfiguration.UPDATE)
    public void update(@PathVariable long id, @RequestBody Pet pet){
        try{
            Pet uPet = petRepository.findById(id).get();
            uPet.setName(pet.getName());
            uPet.setSex(pet.getSex());
            uPet.setAge(pet.getAge());
            uPet.setDescription(pet.getDescription());
            uPet.setBreed(pet.getBreed());
            uPet.setShelter(pet.getShelter());
            uPet.setBreedFinal(pet.getBreedFinal());
            uPet.setShelterFinal(pet.getShelterFinal());
            uPet.setImages(pet.getImages());
            uPet.setLikedUsers(pet.getLikedUsers());
            petRepository.save(uPet);
        }catch (NoSuchElementException ignored){}
    }

    @DeleteMapping(RESTConfiguration.DELETE)
    public void delete(@PathVariable long id){
        try{
            petRepository.delete(petRepository.findById(id).get());
        }catch (NoSuchElementException ignored){}
    }

    @GetMapping(RESTConfiguration.GET)
    public Pet get(@PathVariable long id){
        try{
            return petRepository.findById(id).get();
        }catch (NoSuchElementException ignored){return null;}
    }

    //GET
    @GetMapping(RESTConfiguration.ALL)
    public List<Pet> findAll(){
        return petRepository.findAll();
    }

    @GetMapping(RESTConfiguration.PET.FIND_BY_SHELTER)
    public List<Pet> findByShelter(@RequestParam(name = RESTConfiguration.PET.PARAMS.FIND_BY_SHELTER_NAME) String shelter){
        return petRepository.findByShelter(shelter);
    }

    @GetMapping(RESTConfiguration.RANDOM)
    public Pet findRandom(){
        List<Pet> total = petRepository.findAll();
        long random;
        try {
            random = (long) (Math.random() * (petRepository.count()));
            return total.get((int)random);
        }catch (NoSuchElementException ignored){
            return null;
        }
    }

    @GetMapping(RESTConfiguration.FIND_RANDOM)
    public List<Pet> findRandom(@RequestParam(name = RESTConfiguration.PARAMS.FIND_RANDOM_NAME) long number){
        List<Pet> total = petRepository.findAll();
        List<Pet> pets = new ArrayList<>();
        Pet pet;
        long random;
        for (long i = 0; i < number; i++){
            random = (long) (Math.random() * (petRepository.count()));
            pet = total.get((int)random);
            if (!pets.contains(pet)){
                pets.add(pet);
            }else{
                i--;
            }
        }
        return pets;
    }

    @GetMapping(RESTConfiguration.PET.FIND_DOGS)
    public List<Pet> findDogs(){
        return petRepository.findBySpecie(Specie.DOG);
    }

    @GetMapping(RESTConfiguration.PET.FIND_DOGS_BREEDS)
    public List<Breed> findDogsBreeds(){
        return breedRepository.findBySpecie(Specie.DOG);
    }

    @GetMapping(RESTConfiguration.PET.BREEDS)
    public List<Breed> findBreedsByName(@RequestParam(name = RESTConfiguration.PET.PARAMS.BREEDS_NAME) String name){
        return breedRepository.findByNameLike(name);
    }

    @GetMapping(RESTConfiguration.PET.FIND_CATS)
    public List<Pet> findCats(){ //OK
        return petRepository.findBySpecie(Specie.CAT);
    }

    @GetMapping(RESTConfiguration.PET.FIND_CATS_BREEDS)
    public List<Breed> findCatsBreeds(){
        return breedRepository.findBySpecie(Specie.CAT);
    }

    @GetMapping(RESTConfiguration.PET.TYPE)
    public List<Pet> findByType(@PathVariable String type){
        return petRepository.findByType(type);
    }





}
