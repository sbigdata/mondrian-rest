package org.ojbc.mondrian.rest;

import net.minidev.json.annotate.JsonIgnore;


import javax.persistence.*;
/**
 * sigcode Table Connection
 */
@Table(name = "sig")
@Entity
public class SigCode {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    @Embedded
//    Long Id;

    @Column(name = "NAME")
    String name;

    @Column(name = "sdcode")
    String sdCode;

    @Id
    @Column(name = "sigcode")
    String sigCode;

//    public Long getId() {
//        return Id;
//    }
//
//    public void setId(Long id) {
//        Id = id;
//    }

    public String getSigCode() {
        return sigCode;
    }

    public void setSigCode(String sigCode) {
        this.sigCode = sigCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdCode() {
        return sdCode;
    }

    @JsonIgnore
    public void setSdCode(String sdCode) {
        this.sdCode = sdCode;
    }
    //    public String getSdCode() {
//        return sdCode;
//    }
//
//    public void setSdCode(String sdCode) {
//        this.sdCode = sdCode;
//    }
}
