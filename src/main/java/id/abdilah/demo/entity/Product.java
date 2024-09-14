package id.abdilah.demo.entity;

import id.abdilah.demo.entity.enums.Unit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {
    @Id
    @JdbcTypeCode(Types.VARCHAR)
    @GeneratedValue
    private UUID id;

    @Column(name = "ean_code", unique = true)
    private String eanCode;

    @Column(unique = true)
    private String name;

    @Column(name = "base_price")
    private double basePrice;

    private String description;
    private double price;
    private int stock;

    @Enumerated(EnumType.STRING)
    private Unit unit;
}
