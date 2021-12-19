package lt.codeacademy.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name ="Products")
public class Product {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "varchar(36)",updatable = false)
    @Type(type = "uuid-char")
    private UUID id;
    @NotBlank
    @Size(min=4,max = 50, message = "{validation.Size.product.name}")
    private String name;
    @NotBlank
    private String category;
    @NotBlank
    @Size(max=300)
    private String description;
    @PositiveOrZero
    @Max(1000)
    private int quantity;
    @Positive
    private BigDecimal price;
}