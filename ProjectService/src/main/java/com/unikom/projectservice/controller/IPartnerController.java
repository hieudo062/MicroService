package com.unikom.projectservice.controller;

import org.springframework.cloud.openfeign.FeignClient;
import com.unikom.projectservice.dto.PartneDTO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "partner-manage-service")
public interface IPartnerController {

    @GetMapping("/partner/findAll")
    List<PartneDTO> findAll();

    @GetMapping("/partner/1")
    PartneDTO findById(Long id);
}
