package com.adoptmate.controllers;

import com.adoptmate.enumerations.Issuer;
import com.adoptmate.models.*;
import com.adoptmate.repositories.UserRepository;
import com.adoptmate.tools.RESTConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(RESTConfiguration.USER.REQUEST_MAPPING)
public class UserController {

    @Autowired
    private UserRepository repo;

    //CRUD
    @PostMapping(RESTConfiguration.SAVE)
    public void save(@RequestBody User user){
        repo.save(user);
    }

    @PutMapping(RESTConfiguration.UPDATE)
    public void update(@PathVariable long id, @RequestBody User user){
        try{
        User updateUser = repo.findById(id).get();
        updateUser.setName(user.getName());
        updateUser.setPhone(user.getPhone());
        updateUser.setPassword(user.getPassword());
        //TODO preguntar a isma si esto es necesario
        updateUser.setDescription(user.getDescription());
        updateUser.setHome(user.getHome());
        updateUser.setKids(user.getKids());
        updateUser.setLikedPets(user.getLikedPets());
        updateUser.setMatchedShelters(user.getMatchedShelters());
        repo.save(updateUser);
        }catch(NoSuchElementException ignored){}
    }

    @DeleteMapping(RESTConfiguration.DELETE)
    public void delete(@PathVariable long id){
        try {
            repo.delete(repo.findById(id).get());
        }catch (NoSuchElementException ignored){}
    }

    @GetMapping(RESTConfiguration.GET)
    public User get(@PathVariable long id){
        try {
            return repo.findById(id).get();
        }catch (NoSuchElementException nse){
            return null;
        }
    }

    //GET
    @GetMapping(RESTConfiguration.ALL)
    public List<User> findAll(){
        return repo.findAll();
    }

    @GetMapping(RESTConfiguration.USER.GET_PHONE)
    public int getPhone(@PathVariable long id){ //try
        try {
            return repo.findById(id).get().getPhone();
        }catch(NoSuchElementException nse){
            return -1;
        }
    }

    @GetMapping(RESTConfiguration.USER.GET_NAME)
    public String getName(@PathVariable long id){ //try
        try {
            return repo.findById(id).get().getName();
        }catch(NoSuchElementException nse){
            return null;
        }
    }

    @GetMapping(RESTConfiguration.USER.GET_BY_NAME)
    public List<User> getByName(@PathVariable String name){
        try {
            return repo.findByName(name);
        }catch(NoSuchElementException ignored){
            return null;
        }
    }

    @GetMapping(RESTConfiguration.USER.GET_INCOMES)
    public List<IncomeStatement> findIncomes(@PathVariable long id){
        try{
            return repo.findByStatements(id);
        }catch (NoSuchElementException ignored){
            return null;
        }
    }

    @GetMapping(RESTConfiguration.USER.GET_PETS)
    public List<Pet> findMatchedPets(@PathVariable long id){
        try{
            return repo.findByMatchedPets(id);
        }catch (NoSuchElementException ignored){
            return null;
        }
    }

    @GetMapping(RESTConfiguration.USER.GET_SHELTERS)
    public List<Shelter> findLikedShelters(@PathVariable long id){
        try{
            return repo.findLikedShelters(id);
        }catch (NoSuchElementException ignored){
            return null;
        }
    }

    @GetMapping(RESTConfiguration.RANDOM)
    public User findRandom(){
        long random;
        List<User> users = repo.findAll();
        try {
                random = (long) (Math.random() * (repo.count() + 1));
                return users.get((int)random);
        }catch (NoSuchElementException | IndexOutOfBoundsException ignored){
            return null;
        }
    }

    @GetMapping(RESTConfiguration.FIND_RANDOM)
    public List<User> findRandoms(@RequestParam(name = RESTConfiguration.PARAMS.FIND_RANDOM_NAME) long number){
        final List<User> total = repo.findAll();
        List<User> users = new ArrayList<>();
        long random;
        User user;
            for (int i = 0; i < number; i++) {
                random = (long) (Math.random() * (total.size()));
                user = total.get((int)random);
                if (!users.contains(user)) {
                    users.add(user);
                } else {
                    i--;
                }
            }
        return users;
    }

    @GetMapping(RESTConfiguration.USER.LOGIN)
    public User login(@RequestParam User user){
        String password = new String(user.getPassword());
        try {
            return repo.login(user.getName(), user.getPassword());
        }catch (NoSuchElementException ignored){
            return null;
        }
    }

    //POST

    //PUT
    @PutMapping(RESTConfiguration.USER.LIKE)
    public void like(@RequestParam(name = RESTConfiguration.USER.PARAMS.USER_ID) long userId,
                     @RequestParam(name = RESTConfiguration.USER.PARAMS.PET_ID) long petId){
        PetController controller = new PetController();
        User user = repo.findById(userId).get();
        Pet pet = controller.get(petId);
        user.getLikedPets().add(pet);
        repo.save(user);
        if (pet.getLikedUsers().contains(user)){
            match(user, pet);
        }
    }
    private void match(User user, Pet pet){
        user.getMatchedShelters().add(pet.getShelterFinal());
        pet.getShelterFinal().getMatchedUsers().add(user);
        repo.save(user);
    }

    @PutMapping(RESTConfiguration.USER.SEND_MESSAGE)
    public void sendMessage(@RequestBody Message message,
                            @RequestParam(name = RESTConfiguration.USER.PARAMS.USER_NAME) String userName,
                            @RequestParam(name = RESTConfiguration.USER.PARAMS.SHELTER_NAME) String shelterName){
        ShelterController controller = new ShelterController();
        User user = repo.findByName(userName).get(0);
        Shelter shelter = controller.findByName(shelterName);
        if (message.getIssuer() == Issuer.USER){
            user.getMessages().add(message);
        }else if (message.getIssuer() == Issuer.SHELTER){
            shelter.getMessages().add(message);
        }
        repo.save(user);
    }

}
