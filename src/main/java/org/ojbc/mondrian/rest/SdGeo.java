package org.ojbc.mondrian.rest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="sdgeo")
@Entity
public class SdGeo{

    @Id
    Integer id;

    @Column(name="sd_eng_nam")
    String sdEnglishName;

    String geom;

    @Column(name="sd_kor_nam")
    String sdKorName;

    public String getSdEnglishName() {
        return sdEnglishName;
    }

    public void setSdEnglishName(String sdEnglishName) {
        this.sdEnglishName = sdEnglishName;
    }

    public String getSdKorName() {
        return sdKorName;
    }

    public void setSdKorName(String sdKorName) {
        this.sdKorName = sdKorName;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }
}