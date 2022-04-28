package com.unikom.projectservice.controller;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "partner-manage-service")
public class PartnerController {
}
