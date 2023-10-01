package uz.ilmnajot.college.Entity.component;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import uz.ilmnajot.college.Entity.User;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@MappedSuperclass
@NoArgsConstructor
@Data
public abstract class BaseUUIDEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private boolean deleted = false;

    private boolean blocked =false;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @CreatedBy
    @ManyToOne
    private User createdBy;

    @LastModifiedBy
    @ManyToOne
    private User updatedBy;
}
