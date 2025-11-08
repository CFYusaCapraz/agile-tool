package com.cfyusacapraz.agiletool.domain.base;

import com.cfyusacapraz.agiletool.dto.base.BaseEntityDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Base entity class with generic ID type and embedded audit metadata.
 * All domain entities can extend this class to inherit common fields.
 *
 * @param <ID> The type of the entity's identifier (e.g., Long, UUID)
 */
@MappedSuperclass
@Getter
@Setter
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
            auditMetadata = new AuditMetadata();
        }
    }

    public abstract D toDto();

    public abstract BaseEntity<ID, D> fromDto(D dto);
}

