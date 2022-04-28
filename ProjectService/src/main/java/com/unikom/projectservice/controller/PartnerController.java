package com.unikom.projectservice.controller;

import com.unikom.projectservice.dto.PartneDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project")
public class PartnerController {

    @Autowired
    private IPartnerController partnerController;

    @GetMapping("/partner")
    public ResponseEntity<?> findAllParner() {
        return new ResponseEntity<>(partnerController.findAll(), HttpStatus.OK);
    }


}
