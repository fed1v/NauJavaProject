package ru.fed1v.NauJava.entity;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Класс, описывающий роль пользователя
 */
@Entity
public class AppUserRole {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     * Роль пользователя
     */
    @Column(nullable = false, unique = true)
    private String role;
    

    public AppUserRole(String role) {
        this.role = role;
    }

    public AppUserRole() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUserRole role1 = (AppUserRole) o;
        return Objects.equals(role, role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(role);
    }
}
