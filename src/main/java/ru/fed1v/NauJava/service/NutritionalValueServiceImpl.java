package ru.fed1v.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fed1v.NauJava.entity.NutritionalValue;
import ru.fed1v.NauJava.repository.NutritionalValueRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class NutritionalValueServiceImpl implements NutritionalValueService {

    private final NutritionalValueRepository repository;

    @Autowired
    public NutritionalValueServiceImpl(NutritionalValueRepository repository) {
        this.repository = repository;
    }


    @Override
    public void createNutritionalValue(NutritionalValue nutritionalValue) {
        repository.save(nutritionalValue);
    }

    @Override
    public NutritionalValue findById(Long id) {
        return repository
                .findById(id)
                .orElse(null);
    }

    @Override
    public List<NutritionalValue> getAll() {
        List<NutritionalValue> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public void updateNutritionalValue(NutritionalValue nutritionalValue) {
        repository.save(nutritionalValue);
    }

    @Override
    public void deleteNutritionalValue(Long id) {
        repository.deleteById(id);
    }
}
