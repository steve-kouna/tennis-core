/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koona.tennis.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Steve KOUNA
 */
@Entity
@Table(name="score_vainqueur")
public class ScoreVainqueur {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="set_1")
    private Byte set1;
    
    @Column(name="set_2")
    private Byte set2;
    
    @Column(name="set_3", nullable = true)
    private Byte set3;
    
    @Column(name="set_4", nullable = true)
    private Byte set4;
    
    @Column(name="set_5", nullable = true)
    private Byte set5;
    
    @Transient
    private MatchTennis match;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getSet1() {
        return set1;
    }

    public void setSet1(Byte set1) {
        this.set1 = set1;
    }

    public Byte getSet2() {
        return set2;
    }

    public void setSet2(Byte set2) {
        this.set2 = set2;
    }

    public Byte getSet3() {
        return set3;
    }

    public void setSet3(Byte set3) {
        this.set3 = set3;
    }

    public Byte getSet4() {
        return set4;
    }

    public void setSet4(Byte set4) {
        this.set4 = set4;
    }

    public Byte getSet5() {
        return set5;
    }

    public void setSet5(Byte set5) {
        this.set5 = set5;
    }

    public MatchTennis getMatch() {
        return match;
    }

    public void setMatch(MatchTennis match) {
        this.match = match;
    }
    
    
}
