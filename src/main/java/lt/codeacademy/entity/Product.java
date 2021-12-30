package lt.codeacademy.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "varchar(36)", updatable = false)
    @Type(type = "uuid-char")
    private UUID id;
    @NotBlank
    @Size(min = 4, max = 100, message = "{validation.Size.product.name}")
    private String name;
    @NotBlank
    private String category;
    @NotBlank
    @Size(max = 1000)
    private String description;
    @PositiveOrZero
    @Max(1000)
    private int quantity;
    @Positive
    private BigDecimal price;
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private Set<File> images;
}