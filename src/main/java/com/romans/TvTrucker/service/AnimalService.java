package com.romans.TvTrucker.service;

import com.romans.TvTrucker.repository.AnimalRepository;
import com.romans.TvTrucker.repository.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public Animal saveAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public List<Animal> saveAnimals(List<Animal> animals) {
        return animalRepository.saveAll(animals);
    }

    public List<Animal> getAnimals() {
        return animalRepository.findAll();
    }

    public Animal getAnimalById(int id) {
        return animalRepository.findById(id).orElse(null);
    }

    public Animal getAnimalByName(String name) {
        return animalRepository.findByName(name).orElse(null);
    }

    public void deleteAnimal(int id) {
        animalRepository.deleteById(id);
    }

    public Animal updateAnimal(Animal animal) {
        return animalRepository.save(animal);
    }
}
