package lt.codeacademy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "Files")
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "varchar(36)", updatable = false)
    @Type(type = "uuid-char")
    private UUID id;

    private String fileName;
    private long size;
    private String mediaType;
    @Lob
    private byte[] bytes;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    @JsonBackReference
    private Product product;

    @CreationTimestamp
    private final LocalDateTime timestamp = LocalDateTime.now();

    public File(String fileName, long size, String mediaType, byte[] bytes) {
        this.fileName = fileName;
        this.size = size;
        this.mediaType = mediaType;
        this.bytes = bytes;
    }
}
