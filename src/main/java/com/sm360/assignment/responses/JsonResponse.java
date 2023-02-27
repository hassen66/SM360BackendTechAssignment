package com.sm360.assignment.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class JsonResponse  {

    private int statusCode;
    private String status;
    private String message;
    private Object data;

}
