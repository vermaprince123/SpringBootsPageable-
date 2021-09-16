package com.example.demo.controller;

import com.example.demo.model.Model;
import com.example.demo.repo.MyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {

    @Autowired
    MyRepo myRepo;

    @PostMapping("/insert")
    public Model insertValue(@RequestBody Model model){
        myRepo.save(model);
        return model;
    }

    @GetMapping("/getValue")
    public ResponseEntity<List<Model>> getValue(@RequestParam(defaultValue = "0") Integer pageNo,
                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                @RequestParam(defaultValue = "firstName") String sortBy){
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Model> pageResult = myRepo.findAll(paging);
        List<Model> pageContentValue = pageResult.getContent();
        return new ResponseEntity<>(pageContentValue, HttpStatus.OK);

    }


}
