package com.ecommerce.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    @NotNull
    @Positive
    private Double price;

    @PositiveOrZero
    @Max(100)
    private Double discount;

    @Positive
    private Double specialPrice;

    @Min(0)
    private Integer quantity;

    @Size(max = 255)
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Category category;
}