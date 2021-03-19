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

import javax.xml.ws.Response;
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
    public Cliente saveCliente(Cliente cliente){
        return clienteService.saveClientes(cliente);
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Cliente cliente = clienteService.clienteById(id);
        if (cliente == null){
            response.put("error", "No se encuentra el dato con el id: ".concat(id.toString()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        try{
            cliente = clienteService.clienteById(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Hubo un error en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Tu id fue encontrado");
        response.put("cliente", cliente);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/cliente/{id}")
    public void deleteCliente(@PathVariable Long id){
        clienteService.deleteCliente(id);
    }
}
