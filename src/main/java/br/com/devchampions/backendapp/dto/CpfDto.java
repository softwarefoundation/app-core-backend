package br.com.devchampions.backendapp.dto;


public class CpfDto {

    private String cpf;

    public CpfDto() {
    }

    public CpfDto(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
