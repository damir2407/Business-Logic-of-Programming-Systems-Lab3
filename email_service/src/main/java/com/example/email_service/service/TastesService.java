package com.example.email_service.service;

import com.example.data.exception.ResourceNotFoundException;
import com.example.data.model.basic.Tastes;
import com.example.data.repository.basic.TastesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TastesService {

    private final TastesRepository tastesRepository;

    public TastesService(TastesRepository tastesRepository) {
        this.tastesRepository = tastesRepository;
    }


    public Tastes findTasteByTasteName(String taste) {
        return tastesRepository.findTastesByTaste(taste).orElseThrow(() -> new ResourceNotFoundException("Вкус " + taste + " не найден"));
    }

    public List<Tastes> findAllTastesByTasteNames(List<String> tasteNames) {
        List<Tastes> tastesList = new ArrayList<>();
        for (String taste_name : tasteNames) {
            Tastes taste = findTasteByTasteName(taste_name);
            tastesList.add(taste);
        }
        return tastesList;
    }
}
