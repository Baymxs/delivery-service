package ru.nsu.bayramov.deliveryservice.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ApiModel(description = "Заказ")
public class OrderDto {
    @ApiModelProperty(value = "Id", required = true, example = "1")
    private Integer id;

    @ApiModelProperty(value = "Название заказа", required = true, example = "Доставка it литературы")
    private String name;

    @ApiModelProperty(value = "Адрес доставки", required = true, example = "ул. Николаева, 11/5")
    private String address;

    @ApiModelProperty(value = "Дата предполагаемой доставки", required = true, example = "2020-07-13 14:00:00.000000")
    private Date deliveryDate;

    @ApiModelProperty(value = "Доставлено/Не доставлено", required = true, example = "false")
    private Boolean state;

    @ApiModelProperty(value = "Пользователь, которому принадлежит данный заказ", required = true)
    private UserShortDto user;

    @ApiModelProperty(value = "Товары, которые входят в заказ", required = true)
    private Set<ProductShortDto> products;
}
