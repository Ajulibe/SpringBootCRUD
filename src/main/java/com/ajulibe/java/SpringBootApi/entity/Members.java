package com.ajulibe.java.SpringBootApi.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "members")
public class Members {
    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int memid;

    @Column(name = "sur_name")
    private String surname;

    @Column(name = "first_name")
    private String firstname;


    @Column(name = "address")
    private String address;

    @Column(name = "zip_code")
    private int zipcode;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "join_date")
    private Date joindate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recommendedby", referencedColumnName = "id")
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
