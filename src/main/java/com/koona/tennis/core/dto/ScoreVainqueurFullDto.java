/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koona.tennis.core.dto;

/**
 *
 * @author Steve KOUNA
 */
public class ScoreVainqueurFullDto {
    private Long id;
    private Byte set1;
    private Byte set2;
    private Byte set3;
    private Byte set4;
    private Byte set5;
    private MatchTennisDto match;

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

    public MatchTennisDto getMatch() {
        return match;
    }

    public void setMatch(MatchTennisDto match) {
        this.match = match;
    }
    
}
