package com.formatoweb.pruebasgit218032021.controllers;

import com.formatoweb.pruebasgit218032021.entity.Cliente;
import com.formatoweb.pruebasgit218032021.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.formatoweb.pruebasgit218032021.entity.Cliente;
import com.formatoweb.pruebasgit218032021.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> saveCliente(Cliente cliente){
        Map<String, Object> response = new HashMap<>();
        Cliente newCliente = new Cliente();
        try{
            newCliente = clienteService.saveClientes(cliente);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al cargar dato en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Tu dato ha sido cargado satisfactoriamente");
        response.put("dato", newCliente);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/cliente/{id}")
    public Cliente getClienteById(@PathVariable Long id){
        return clienteService.clienteById(id);
    }

    @DeleteMapping("/cliente/{id}")
    public void deleteCliente(@PathVariable Long id){
        clienteService.deleteCliente(id);
    }
}
