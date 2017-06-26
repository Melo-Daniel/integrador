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
public class Quadro {
    private int id,ps,lr,recebidops,recebidolr,total;
    private String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getLr() {
        return lr;
    }

    public void setLr(int lr) {
        this.lr = lr;
    }

    public int getRecebidops() {
        return recebidops;
    }

    public void setRecebidops(int recebidops) {
        this.recebidops = recebidops;
    }

    public int getRecebidolr() {
        return recebidolr;
    }

    public void setRecebidolr(int recebidolr) {
        this.recebidolr = recebidolr;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
