package com.estuardo.wyss.hospital.controller.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @project payara.hackathon.jakartaEE10
 * @coder estuardo.wyss
 * @date 03/06/2023
 */
@Getter
@Setter
public class DataTreeUI extends StandardResponse{
    List<DataTreeNodeUI> nodes;
}
