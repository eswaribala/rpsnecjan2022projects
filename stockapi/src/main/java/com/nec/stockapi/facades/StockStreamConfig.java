package com.nec.stockapi.facades;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(StockFacade.class)
public class StockStreamConfig {

}
