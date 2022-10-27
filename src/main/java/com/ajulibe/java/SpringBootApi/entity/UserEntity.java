package com.ajulibe.java.SpringBootApi.entity;

import com.ajulibe.java.SpringBootApi.config.permissions.ERole;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private String uuid = UUID.randomUUID().toString();

    @Column
    private String username;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
//    @Enumerated(EnumType.STRING)
//    @ElementCollection(fetch = FetchType.EAGER)
    //basically links this field to the role table and references the id column
//    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "id"))
    private int role;

    @Column
    @Builder.Default
    private boolean enabled = false;

    @Column(name = "joindate")
    private Date joindate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserEntity userEntity = (UserEntity) o;
        return id != null && Objects.equals(id, userEntity.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        var sga = new SimpleGrantedAuthority(String.valueOf(this.role));
        authorities.add(sga);
//        this.role
//        for (var r : this.role) {
//            var sga = new SimpleGrantedAuthority(r.name());
//            authorities.add(sga);
//        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
}

/**
 * mysql> select * from role;
 * +-------------+--------------+
 * |          id | names |
 * +-------------+--------------+
 * |           1 | role_admin   |
 * |           1 | role_moderat |
 * |           1 | role_user    |
 * +-------------+--------------+
 * 3 rows in set (0.00 sec)
 */