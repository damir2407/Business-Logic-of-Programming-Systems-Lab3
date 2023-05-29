package com.example.email_service.service;

import com.example.data.exception.ResourceNotFoundException;
import com.example.data.model.basic.NationalCuisine;
import com.example.data.repository.basic.NationalCuisineRepository;
import org.springframework.stereotype.Service;

@Service
public class NationalCuisineService {

    private final NationalCuisineRepository nationalCuisineRepository;

    public NationalCuisineService(NationalCuisineRepository nationalCuisineRepository) {
        this.nationalCuisineRepository = nationalCuisineRepository;
    }

    public NationalCuisine findNationalCuisineByName(String cuisine) {
        return nationalCuisineRepository.findNationalCuisineByCuisine(cuisine).orElseThrow(() -> new ResourceNotFoundException("Нац. кухня " + cuisine + " не существует!"));
    }
}
