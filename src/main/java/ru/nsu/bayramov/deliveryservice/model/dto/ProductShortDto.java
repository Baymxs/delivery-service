package ru.nsu.bayramov.deliveryservice.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Товар")
public class ProductShortDto {
    @ApiModelProperty(value = "Id", required = true, example = "1")
    private Integer id;

    @ApiModelProperty(value = "Наименование товара", required = true, example = "Java. Полное руководство | Шилдт Герберт")
    private String name;

    @ApiModelProperty(value = "Описание товара", required = true, example = "В этом десятом издании справочного пособия, полностью обновленном с учетом последней версии Java SE 9, поясняется, как разрабатывать, компилировать, отлаживать и выполнять программы на языке программирования Java")
    private String description;

    @ApiModelProperty(value = "Цена товара", required = true, example = "4045")
    private Integer cost;
}
