package com.formatoweb.pruebasgit218032021.controllers;

import com.formatoweb.pruebasgit218032021.entity.Cliente;
import com.formatoweb.pruebasgit218032021.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.formatoweb.pruebasgit218032021.entity.Cliente;
import com.formatoweb.pruebasgit218032021.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @GetMapping("/cliente")
    public List<Cliente> getCliente(){
        return clienteService.getClientes();
    }

    @PostMapping("/cliente")
    public Cliente saveCliente(Cliente cliente){
        return clienteService.saveClientes(cliente);
    }

    @GetMapping("/cliente/{id}")
    public Cliente getClienteById(@PathVariable Long id){
        return clienteService.clienteById(id);
    }
}
