package br.com.devchampions.backendapp.controller;

import br.com.devchampions.backendapp.dto.CpfDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cpf")
public class CpfController {

    @PostMapping
    public ResponseEntity<?> cpf(@RequestBody CpfDto cpfDto){

        if(cpfDto.getCpf() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O CPF deve conter 11 dígitos");
        }

        return ResponseEntity.ok(cpfDto);
    }


}
