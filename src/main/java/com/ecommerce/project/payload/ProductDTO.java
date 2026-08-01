package com.ecommerce.project.payload;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    @NotBlank
    private String name;
    @Size(max = 255)
    private String image;
    @Min(0)
    private int quantity;
    @NotNull
    @Positive
    private double price;
    @PositiveOrZero
    @Max(100)
    private double discount;
    @Positive
    private double specialPrice;
    @NotBlank
    @Size(min = 3, max = 255)
    private String description;
}
