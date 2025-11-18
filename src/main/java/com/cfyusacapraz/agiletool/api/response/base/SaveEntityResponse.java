package com.cfyusacapraz.agiletool.api.response.base;

import com.cfyusacapraz.agiletool.dto.base.BaseEntityDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class SaveEntityResponse<ID extends Serializable> extends BaseApiResponse implements Serializable {

    private ID entityId;

    public SaveEntityResponse(@NotNull BaseEntityDto<ID> entityDto) {
        setSuccess(true);
        this.entityId = entityDto.getId();
    }

    @Override
    public boolean isSuccess() {
        return true;
    }
}
