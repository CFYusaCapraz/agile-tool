package com.cfyusacapraz.agiletool.api.request;

import com.cfyusacapraz.agiletool.api.request.base.BasePagedApiRequest;
import com.cfyusacapraz.agiletool.domain.enums.TeamStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TeamFilterRequest extends BasePagedApiRequest implements Serializable {

    private String name;

    private Set<TeamStatus> statuses = Set.of(TeamStatus.ACTIVE, TeamStatus.INACTIVE, TeamStatus.ARCHIVED);
}
