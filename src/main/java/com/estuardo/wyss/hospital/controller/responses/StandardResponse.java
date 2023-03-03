package com.estuardo.wyss.hospital.controller.responses;

import lombok.Getter;
import lombok.Setter;

/**
 * @company kappa.computacion
 * @coder estuardo.wyss
 * @date
 */
@Getter
@Setter
public class StandardResponse {
    private boolean success;
    private boolean operationStatus;
    private String message;
    private String status;
}
