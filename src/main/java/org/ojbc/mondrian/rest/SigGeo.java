package org.ojbc.mondrian.rest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="siggeo")
@Entity
public class SigGeo{

    @Id
    Integer id;

    public String getSig_code() {
        return sig_code;
    }

    public void setSig_code(String sig_code) {
        this.sig_code = sig_code;
    }

    @Column(name="sig_eng_na", nullable = true)
    String sigEnglishName;

    @Column(name="sig_kor_na", nullable = true)
    String sig_kor_na;

    @Column(name="geom")
    String geom;

    @Column(name="sd_code", nullable = true)
    String sd_code;

    @Column(name="sig_code", nullable = true)
    String sig_code;

    public String getSig_kor_na() {
        return sig_kor_na;
    }

    public void setSig_kor_na(String sig_kor_na) {
        this.sig_kor_na = sig_kor_na;
    }

    public String getSd_code() {
        return sd_code;
    }

    public void setSd_code(String sd_code) {
        this.sd_code = sd_code;
    }


    public String getSigEnglishName() {
        return sigEnglishName;
    }

    public void setSigEnglishName(String sigEnglishName) {
        this.sigEnglishName = sigEnglishName;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }
}