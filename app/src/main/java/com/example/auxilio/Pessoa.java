package com.example.auxilio;

import java.util.Date;

public class Pessoa {
    private long cpf;
    private Date nasc;
    private float renda;
    private String data;
    private float saldo;


    public Pessoa(long cpf, Date nasc, float renda, String data, float saldo) {
        this.cpf = cpf;
        this.nasc = nasc;
        this.renda = renda;
        this.data = data;
        this.saldo = saldo;
    }

    public long getCpf() {
        return cpf;
    }

    public Date getNasc() {
        return nasc;
    }

    public float getRenda() {
        return renda;
    }

    public String getData() {
        return data;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public void setNasc(Date nasc) {
        this.nasc = nasc;
    }

    public void setRenda(float renda) {
        this.renda = renda;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
