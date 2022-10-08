package com.ajulibe.java.SpringBootApi.entity;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "members")
public class Members {
    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memid")
    private int memid;

    @NotNull
    @Column(name = "surname")
    private String surname;

    @NotNull
    @Column(name = "firstname")
    private String firstname;


    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "zipcode")
    private int zipcode;

    @NotNull
    @Column(name = "telephone")
    private String telephone;

    @NotNull
    @Column(name = "joindate")
    private Date joindate;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recommendedby", referencedColumnName = "memid")
    private Members recommendedby;

    public Members() {
    }

    public Members(String surname, String firstname, String address, int zipcode, String telephone, Date joindate, Members recommendedby) {
        this.surname = surname;
        this.firstname = firstname;
        this.address = address;
        this.zipcode = zipcode;
        this.telephone = telephone;
        this.joindate = joindate;
        this.recommendedby = recommendedby;
    }

    // define getter/setter


    public int getMemid() {
        return memid;
    }

    public void setMemid(int memid) {
        this.memid = memid;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getJoindate() {
        return joindate;
    }

    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    public Members getRecommendedby() {
        return recommendedby;
    }

    public void setRecommendedby(Members recommendedby) {
        this.recommendedby = recommendedby;
    }

    @Override
    public String toString() {
        return "Members{" +
                "memid=" + memid +
                ", surname='" + surname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", address='" + address + '\'' +
                ", zipcode=" + zipcode +
                ", telephone='" + telephone + '\'' +
                ", joindate=" + joindate +
                ", recommendedby=" + recommendedby +
                '}';
    }


}
