package org.ojbc.mondrian.rest;

import net.minidev.json.annotate.JsonIgnore;


import javax.persistence.*;
/**
 * sdgeo Table Connection
 */
@Table(name = "sd")
@Entity
public class Sd {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    @Embedded
//    Long Id;

    @Column(name = "NAME")
    String name;

    @Id
    @Column(name = "sdcode")
    String sdCode;


//    public Long getId() {
//        return Id;
//    }
//
//    public void setId(Long id) {
//        Id = id;
//    }

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
