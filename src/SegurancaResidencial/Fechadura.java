/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SegurancaResidencial;

import java.io.Serializable;

public class Fechadura implements Serializable {
    private String id;
    private String localizacao;
    private String pin;
    private int tentativasFalhas;

    public Fechadura(String id, String localizacao, String pin) {
        this.id = id;
        this.localizacao = localizacao;
        this.pin = pin;
        this.tentativasFalhas = 0;
    }

    public String getId() {
        return id;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void registrarTentativa(boolean sucesso) {
        if (!sucesso) {
            tentativasFalhas++;
        } else {
            tentativasFalhas = 0;
        }
    }

    public int getTentativasFalhas() {
        return tentativasFalhas;
    }
}

