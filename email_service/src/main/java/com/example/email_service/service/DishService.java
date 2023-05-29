package com.example.email_service.service;

import com.example.data.exception.ResourceNotFoundException;
import com.example.data.model.basic.Dish;
import com.example.data.repository.basic.DishRepository;
import org.springframework.stereotype.Service;

@Service

public class DishService {
    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }


    public Dish findDishByName(String name) {
        return dishRepository.findDishByName(name).orElseThrow(() -> new ResourceNotFoundException("Блюдо " + name + " не найдено"));
    }
}
