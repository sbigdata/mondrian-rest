package org.ojbc.mondrian.rest;

import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;

/**
 * Emdgeo Table Connection
 */

@Table(name = "emdgeo")
@Entity
public class EmdGeo {

    @Column(name = "NAME")
    String name;

    @ColumnTransformer(read="st_astext(geom)")
    String geom;

    @Id
    @Column(name = "emdcode")
    String emdCode;

    @Column(name = "sigcode")
    String sigCode;

    @Column(name = "sdcode")
    String sdCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    public String getEmdCode() {
        return emdCode;
    }

    public void setEmdCode(String emdCode) {
        this.emdCode = emdCode;
    }

    public String getSigCode() {
        return sigCode;
    }

    public void setSigCode(String sigCode) {
        this.sigCode = sigCode;
    }

    public String getSdCode() {
        return sdCode;
    }

    public void setSdCode(String sdCode) {
        this.sdCode = sdCode;
    }
}
