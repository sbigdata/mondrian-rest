package org.ojbc.mondrian.rest;

import javax.persistence.*;
import java.util.Date;

@Table(name="Data")
@Entity
public class Data {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name="카드번호")
    String cardNumber;

    @Column(name="교통수단코드")
    Integer trafficCode;

    @Column(name="트랜잭션id")
    Integer transactionId;

    @Column(name="노선id")
    Integer routeId;

    @Column(name="노선명")
    String routeName;

    @Column(name="교통사업자ID")
    Integer routeManagerId;

    @Column(name="교통사업자명")
    String routeManagerName;

    @Column(name="차량id")
    Integer carId;

    @Column(name="차량등록번호")
    String carRegistNumber;

    @Column(name="운행출발날짜")
    Date driveStartTime;

    @Column(name="운행종료일시")
    Date driveEndDate;

    @Column(name="승차일시")
    Date driveStartDate;

    @Column(name="승차정류장id")
    Integer startId;

    @Column(name="승차정류장명")
    String startName;

    @Column(name="하차일시")
    Date endTime;

    @Column(name="하차정류장id")
    Integer stopId;

    @Column(name="하차정류장명")
    String stopName;

    @Column(name="환승횟수")
    Integer transferCount;

    @Column(name="사용자구분코드")
    String userCode;

    @Column(name="이용객수")
    Integer userCount;

    @Column(name="승하차금액")
    Integer busFee;

    @Column(name="승하차위반금액")
    Integer busFineFee;

    @Column(name="총이용거리")
    Integer distance;

    @Column(name="총탑승시간")
    Integer resultTime;

    @Column(name="연도")
    Integer year;

    @Column(name="월")
    Integer month;

    @Column(name="일")
    Integer day;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getTrafficCode() {
        return trafficCode;
    }

    public void setTrafficCode(Integer trafficCode) {
        this.trafficCode = trafficCode;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Integer getRouteManagerId() {
        return routeManagerId;
    }

    public void setRouteManagerId(Integer routeManagerId) {
        this.routeManagerId = routeManagerId;
    }

    public String getRouteManagerName() {
        return routeManagerName;
    }

    public void setRouteManagerName(String routeManagerName) {
        this.routeManagerName = routeManagerName;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarRegistNumber() {
        return carRegistNumber;
    }

    public void setCarRegistNumber(String carRegistNumber) {
        this.carRegistNumber = carRegistNumber;
    }

    public Date getDriveStartTime() {
        return driveStartTime;
    }

    public void setDriveStartTime(Date driveStartTime) {
        this.driveStartTime = driveStartTime;
    }

    public Date getDriveEndDate() {
        return driveEndDate;
    }

    public void setDriveEndDate(Date driveEndDate) {
        this.driveEndDate = driveEndDate;
    }

    public Date getDriveStartDate() {
        return driveStartDate;
    }

    public void setDriveStartDate(Date driveStartDate) {
        this.driveStartDate = driveStartDate;
    }

    public Integer getStartId() {
        return startId;
    }

    public void setStartId(Integer startId) {
        this.startId = startId;
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStopId() {
        return stopId;
    }

    public void setStopId(Integer stopId) {
        this.stopId = stopId;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public Integer getTransferCount() {
        return transferCount;
    }

    public void setTransferCount(Integer transferCount) {
        this.transferCount = transferCount;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getBusFee() {
        return busFee;
    }

    public void setBusFee(Integer busFee) {
        this.busFee = busFee;
    }

    public Integer getBusFineFee() {
        return busFineFee;
    }

    public void setBusFineFee(Integer busFineFee) {
        this.busFineFee = busFineFee;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getResultTime() {
        return resultTime;
    }

    public void setResultTime(Integer resultTime) {
        this.resultTime = resultTime;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}