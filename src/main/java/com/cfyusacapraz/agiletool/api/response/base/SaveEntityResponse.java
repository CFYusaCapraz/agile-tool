package com.cfyusacapraz.agiletool.api.response.base;

import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveEntityResponse<ID extends Serializable> extends BaseApiResponse implements Serializable {

    private ID entityId;
}
