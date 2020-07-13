package ru.nsu.bayramov.deliveryservice.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel(description = "Фильтр для списка заданий")
public class TasksFilterDto {
    @ApiModelProperty(value = "Дата начала диапазона", required = true, example = "01.01.2020")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startDate;

    @ApiModelProperty(value = "Дата конца диапазона", required = true, example = "09.01.2020")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date endDate;

    @ApiModelProperty(value = "Номер заказа", example = "1")
    private Integer orderId;
}
