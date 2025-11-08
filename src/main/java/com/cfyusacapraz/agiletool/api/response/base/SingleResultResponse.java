package com.cfyusacapraz.agiletool.api.response.base;

import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SingleResultResponse<D> extends BaseApiResponse implements Serializable {

    private D result;

    @Override
    public boolean isSuccess() {
        return true;
    }
}
