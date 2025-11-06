package com.cfyusacapraz.agiletool.domain.base;

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
public abstract class BaseEntity<ID extends Serializable> implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private ID id;

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
}

