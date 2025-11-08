package com.cfyusacapraz.agiletool.api.response.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class SaveEntityResponse<ID extends Serializable> extends BaseApiResponse implements Serializable {

    private ID entityId;

    @Override
    public boolean isSuccess() {
        return true;
    }
}
