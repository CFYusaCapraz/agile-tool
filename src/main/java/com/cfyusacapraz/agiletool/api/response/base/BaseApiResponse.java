package com.cfyusacapraz.agiletool.api.response.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseApiResponse implements Serializable {

    private String id;

    private long timestamp = System.currentTimeMillis();

    private boolean success;

    private String correlationId;

    public BaseApiResponse(String id, boolean success) {
        this.id = id;
        this.success = success;
    }
}
