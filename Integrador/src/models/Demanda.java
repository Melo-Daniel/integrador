/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author daniel.freitas
 */
public class Demanda {
    private int id,cod,prioridadeContabil, prioridadeRelacionamento,status;
    private String empresa,regime,colaboradorContabil,colaboradorRelacionamento,email,responsavel;

    public Demanda(int cod, int prioridadeContabil, int prioridadeRelacionamento, int status, String empresa, String regime, String colaboradorContabil, String colaboradorRelacionamento, String email, String responsavel) {
        this.cod = cod;
        this.prioridadeContabil = prioridadeContabil;
        this.prioridadeRelacionamento = prioridadeRelacionamento;
        this.status = status;
        this.empresa = empresa;
        this.regime = regime;
        this.colaboradorContabil = colaboradorContabil;
        this.colaboradorRelacionamento = colaboradorRelacionamento;
        this.email = email;
        this.responsavel = responsavel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
    
    public Demanda() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getPrioridadeContabil() {
        return prioridadeContabil;
    }

    public void setPrioridadeContabil(int prioridadeContabil) {
        this.prioridadeContabil = prioridadeContabil;
    }

    public int getPrioridadeRelacionamento() {
        return prioridadeRelacionamento;
    }

    public void setPrioridadeRelacionamento(int prioridadeRelacionamento) {
        this.prioridadeRelacionamento = prioridadeRelacionamento;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public String getColaboradorContabil() {
        return colaboradorContabil;
    }

    public void setColaboradorContabil(String colaboradorContabil) {
        this.colaboradorContabil = colaboradorContabil;
    }

    public String getColaboradorRelacionamento() {
        return colaboradorRelacionamento;
    }

    public void setColaboradorRelacionamento(String colaboradorRelacionamento) {
        this.colaboradorRelacionamento = colaboradorRelacionamento;
    }
    
    
}
