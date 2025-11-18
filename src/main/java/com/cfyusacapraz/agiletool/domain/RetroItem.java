package com.cfyusacapraz.agiletool.domain;

import com.cfyusacapraz.agiletool.domain.base.BaseEntity;
import com.cfyusacapraz.agiletool.dto.RetroItemDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "retro_items")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RetroItem extends BaseEntity<Long, RetroItemDto> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "column_id", nullable = false)
    private RetroColumn column;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user_id", nullable = false)
    private User createdBy;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(name = "vote_count", nullable = false)
    private int voteCount;

    @Column(nullable = false)
    private boolean isVisible;
}
