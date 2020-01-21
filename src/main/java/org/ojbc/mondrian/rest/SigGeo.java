package org.ojbc.mondrian.rest;

import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
/**
 * siggeo Table Connection
 */
@Table(name = "siggeo")
@Entity
public class SigGeo {
    @Column(name = "NAME", nullable = true)
    String name;

    @ColumnTransformer(read = "st_astext(geom)")
    String geom;

    @Column(name = "sdcode", nullable = true)
    String sdCode;

    @Id
    @Column(name = "sigcode", nullable = true)
    String sigCode;

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

    public String getSdCode() {
        return sdCode;
    }

    public String getSigCode() {
        return sigCode;
    }

    public void setSigCode(String sigCode) {
        this.sigCode = sigCode;
    }

    public void setSdCode(String sdCode) {
        this.sdCode = sdCode;


    }
}
