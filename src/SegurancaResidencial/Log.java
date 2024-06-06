/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SegurancaResidencial;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable{
    private Date dataHora;
    private boolean sucesso;
    private String idFechadura;

    public Log(Date dataHora, boolean sucesso, String idFechadura) {
        this.dataHora = dataHora;
        this.sucesso = sucesso;
        this.idFechadura = idFechadura;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public boolean getSucesso() {
        return sucesso;
    }

    public String getIdFechadura() {
        return idFechadura;
    }
}

