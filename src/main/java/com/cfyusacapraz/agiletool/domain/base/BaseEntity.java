package com.cfyusacapraz.agiletool.domain.base;

import com.cfyusacapraz.agiletool.dto.base.BaseEntityDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Base entity class with generic ID type and embedded audit metadata.
 * All domain entities can extend this class to inherit common fields.
 *
 * @param <ID> The type of the entity's identifier (e.g., Long, UUID)
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseEntity<ID extends Serializable, D extends BaseEntityDto<ID>> implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private ID id;

    @Version
    private int version;

    @Embedded
    private AuditMetadata auditMetadata;

    /**
     * Initialize audit metadata if not already initialized
     */
    @PrePersist
    protected void initializeAudit() {
        if (auditMetadata == null) {
            OffsetDateTime now = OffsetDateTime.now();
            auditMetadata = new AuditMetadata(now, now, null, null);
        }
    }

    @PreUpdate
    protected void touchAudit() {
        if (auditMetadata == null) {
            auditMetadata = new AuditMetadata();
        }
        auditMetadata.setUpdatedAt(OffsetDateTime.now());
    }

    public abstract D toDto();

    public abstract BaseEntity<ID, D> fromDto(D dto);

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BaseEntity<?, ?> that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}

