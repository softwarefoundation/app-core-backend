package br.com.devchampions.backendapp.controller;

import br.com.devchampions.backendapp.dto.CpfDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@Slf4j
@RestController
@RequestMapping("/cpf")
public class CpfController {

    @PostMapping("validar")
    public ResponseEntity<?> validarCpf(@RequestBody CpfDto cpfDto) {

        log.info("CPF: {}", cpfDto.getCpf());

        if (cpfDto.getCpf() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Número de CPF obrigatório");
        }

        if (cpfDto.getCpf().trim().length() != 11) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O CPF deve conter 11 dígitos");
        }

        // Somente números
        Pattern p = Pattern.compile("[^0-9]+");

        if (p.matcher(cpfDto.getCpf()).find()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Informe somente os números do CPF");
        }

        return ResponseEntity.ok(cpfDto.getCpf());
    }



}
