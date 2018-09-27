package org.ojbc.mondrian.rest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="emdgeo")
@Entity
public class EmdGeo{

    @Id
    Integer id;

    @Column(name="emd_eng_na")
    String emdEnglishName;

    @Column(name="emd_kor_na")
    String emdKorName;

    String geom;

    public String getEmdEnglishName() {
        return emdEnglishName;
    }

    public void setEmdEnglishName(String emdEnglishName) {
        this.emdEnglishName = emdEnglishName;
    }

    public String getEmdKorName() {
        return emdKorName;
    }

    public void setEmdKorName(String emdKorName) {
        this.emdKorName = emdKorName;
    }

    public String emdEnglishName() {
        return emdEnglishName;
    }

    public void setemdEnglishName(String emdEnglishName) {
        this.emdEnglishName = emdEnglishName;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }
}