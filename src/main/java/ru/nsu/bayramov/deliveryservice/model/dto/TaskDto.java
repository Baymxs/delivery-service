package ru.nsu.bayramov.deliveryservice.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskDto {
    @ApiModelProperty(value = "Id", required = true, example = "1")
    private Integer id;

    @ApiModelProperty(value = "Заказ", required = true)
    private OrderDto order;

    @ApiModelProperty(value = "Дата добавления задания", required = true, example = "2020-07-13 12:00:00.000000")
    private Date date;

    @ApiModelProperty(value = "Признак выполненного задания", required = true, example = "false")
    private Boolean state;
}
