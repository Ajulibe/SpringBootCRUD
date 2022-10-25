package com.ajulibe.java.SpringBootApi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacilitiesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int facid;
    private String firstname;
    private Long membercost;
    private Long guestcost;
    private Long intialoutlay;
    private Long monthlymaintenance;
}
