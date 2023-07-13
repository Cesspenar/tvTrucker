package com.romans.TvTrucker.controller;

import com.romans.TvTrucker.repository.model.Animal;
import com.romans.TvTrucker.service.AnimalService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnimalController {

    @Autowired
    private AnimalService animalService;


    @GetMapping("/hello")
    public String welcome() {
        return "Hello World";
    }

    @PostMapping("/addAnimal")
    public Animal addAnimal(@RequestBody Animal animal) {
        return animalService.saveAnimal(animal);
    }

    @PostMapping("/addAnimals")
    public List<Animal> addAnimals(@RequestBody List<Animal> animals) {
        return animalService.saveAnimals(animals);
    }

    @GetMapping("/animals")
    public List<Animal> findAllAnimals() {
        return animalService.getAnimals();
    }

    @GetMapping("/animalById/{id}")
    public Animal findAnimalById(@PathVariable int id) {
        return animalService.getAnimalById(id);
    }

    @GetMapping("/animalByName/{name}")
    public Animal findAnimalByNme(@PathVariable String name) {
        return animalService.getAnimalByName(name);
    }

    @PutMapping("/update")
    public Animal updateAnimal(@RequestBody Animal animal) {
        return animalService.updateAnimal(animal);
    }

    @DeleteMapping("/deleteAnimal/{id}")
    public void deleteAnimal(@PathVariable int id) {
        animalService.deleteAnimal(id);
    }
}


