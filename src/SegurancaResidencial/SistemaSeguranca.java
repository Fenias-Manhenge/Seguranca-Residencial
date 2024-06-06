/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SegurancaResidencial;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SistemaSeguranca implements Serializable {
    private List<Log> logTentativas;
    private List<Proprietario> proprietario;
    private List<Fechadura> fechaduras;
    private List<Usuario> usuarios;

    public SistemaSeguranca(ArrayList<Proprietario> proprietario) {
        this.proprietario = proprietario;
        this.logTentativas = new ArrayList<>();
        this.fechaduras = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public boolean autenticarProprietario(String nome, String adminCode) {
        boolean exist = false;
        
        for (int i = 0; i < proprietario.size(); i++) {
            if(proprietario.get(i).getNome().equalsIgnoreCase(nome) && proprietario.get(i).getAdminCode().equalsIgnoreCase(adminCode))
                exist = true;
        }
        
        return exist;
    }

    public void adicionarFechadura(Fechadura fechadura) {
        fechaduras.add(fechadura);
    }

    public void registrarTentativa(String idFechadura, boolean sucesso) {
        Fechadura fechadura = getFechaduraById(idFechadura);
        if (fechadura != null) {
            fechadura.registrarTentativa(sucesso);
            logTentativas.add(new Log(new Date(), sucesso, idFechadura));
            if (fechadura.getTentativasFalhas() >= 3) {
                System.out.println("Alarme ativado!");
            }
        }
    }

    public Fechadura getFechaduraById(String id) {
        for (Fechadura fechadura : fechaduras) {
            if (fechadura.getId().equals(id)) {
                return fechadura;
            }
        }
        return null;
    }

    public List<Log> getLogTentativas() {
        return logTentativas;
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void removerUsuario(String nome) {
        usuarios.removeIf(usuario -> usuario.getNome().equalsIgnoreCase(nome));
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}

