package com.example.superheros.antiHero.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "anti_hero")
@AllArgsConstructor
@NoArgsConstructor
public class AntiHero implements Serializable {
    private static final long serialVersionUID = -4439114469417994311L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "should provide firstName")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String house;

    @Column(name = "known_as")
    private String knownAs;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = true, updatable = false)
    private Date createdAt;


    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    public static AntiHero merge(AntiHero original, AntiHero updated) {
        AntiHero result = new AntiHero();

        Field[] fields = AntiHero.class.getDeclaredFields();

        for (Field field : fields) {
            // Set the field to be accessible, as private fields may not be accessible by default
            field.setAccessible(true);

            try {
                
                if (field.get(updated) != null) {
                    field.set(result, field.get(updated));
                } else {
                    field.set(result, field.get(original));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;

    }
}
