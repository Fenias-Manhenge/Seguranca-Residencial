/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SegurancaResidencial;

import java.io.Serializable;

public class Proprietario extends Usuario implements Serializable {
    private String adminCode;

    public Proprietario(String nome, String telefone, String email, String adminCode) {
        super(nome, telefone, email);
        this.adminCode = adminCode;
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }
}

