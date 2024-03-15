package com.projetofinal.projetofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.projetofinal.model.MetasFinanceiras;
import com.projetofinal.projetofinal.service.MetasFinanceirasService;

@RestController
@RequestMapping("/metasfinanceiras")
public class ControllerMetasFinanceiras {

    @Autowired
    private MetasFinanceirasService service;

}
