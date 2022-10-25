package com.ajulibe.java.SpringBootApi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookid;

    @OneToOne
    @JoinColumn(name = "facid", referencedColumnName = "facid")
    private FacilitiesEntity facilities;

    @OneToOne
    @JoinColumn(name = "memid", referencedColumnName = "memid")
    private MembersEntity members;

    private Date starttime;
    private int slots;
}
