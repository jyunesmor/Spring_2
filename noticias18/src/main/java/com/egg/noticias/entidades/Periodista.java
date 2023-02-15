/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.noticias.entidades;

import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Periodista extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = true)
    private Integer id;
    private ArrayList<Noticia> cantNoticia;
    private Integer sueldoMensual;

    public Periodista() {
    }

    public ArrayList<Noticia> getCantNoticia() {
        return cantNoticia;
    }

    public void setCantNoticia(ArrayList<Noticia> cantNoticia) {
        this.cantNoticia = cantNoticia;
    }

    public Integer getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(Integer sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }

}
