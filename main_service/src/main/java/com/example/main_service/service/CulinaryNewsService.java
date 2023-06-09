package com.example.main_service.service;

import com.example.data.dto.request.AddCulinaryNewRequest;
import com.example.data.model.extended.CulinaryNews;
import com.example.data.repository.extended.CulinaryNewsRepository;
import com.example.data.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
public class CulinaryNewsService {
    private final CulinaryNewsRepository culinaryNewsRepository;

    private final UserService userService;


    public CulinaryNewsService(CulinaryNewsRepository culinaryNewsRepository, UserService userService) {
        this.culinaryNewsRepository = culinaryNewsRepository;
        this.userService = userService;
    }


    @Transactional(transactionManager = "transactionManager")
    public CulinaryNews saveCulinaryNew(String user_login, AddCulinaryNewRequest addCulinaryNewRequest) {
        CulinaryNews culinaryNews = new CulinaryNews(addCulinaryNewRequest.getName(), user_login, addCulinaryNewRequest.getDescription(), new Timestamp(System.currentTimeMillis()));
        culinaryNewsRepository.save(culinaryNews);
        userService.incrementCulinaryNewsCounter(user_login);
        return culinaryNews;
    }

    public Page<CulinaryNews> getAllCulinaryNews(int page, int size, String sortOrder) {
        Sort.Direction direction = Sort.Direction.fromString(sortOrder);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "publicationDate"));
        return culinaryNewsRepository.findAll(pageable);
    }

    public CulinaryNews findCulinaryNewById(Long id) {
        return culinaryNewsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Кулинарная новость с номером " + id + " не найдена в базе!"));
    }
}
