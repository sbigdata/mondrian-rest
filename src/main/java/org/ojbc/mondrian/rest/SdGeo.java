package org.ojbc.mondrian.rest;

import com.esri.hadoop.hive.ST_MultiPolygon;
import com.vividsolutions.jts.geom.CoordinateArrays;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Type;
import org.hibernate.spatial.*;
import org.postgis.Geometry;
import org.postgis.MultiPolygon;

import javax.persistence.*;
/**
 * sdgeo Table Connection
 */
@Table(name = "sdgeo")
@Entity
public class SdGeo {

    @ColumnTransformer(read="st_astext(geom)")
    String geom;

    @Column(name = "NAME")
    String name;

    @Id
    @Column(name = "sdcode")
    String sdCode;

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
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
