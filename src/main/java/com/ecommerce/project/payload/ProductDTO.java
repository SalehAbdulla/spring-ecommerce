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
    private Integer quantity;
    @NotNull
    @Positive
    private Double price;
    @PositiveOrZero
    @Max(100)
    private Double discount;
    @Positive
    private Double specialPrice;
    @NotBlank
    @Size(min = 3, max = 255)
    private String description;
}
