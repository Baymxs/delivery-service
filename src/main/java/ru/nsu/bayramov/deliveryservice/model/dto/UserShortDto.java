package ru.nsu.bayramov.deliveryservice.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Информация о пользователе")
public class UserShortDto {
    @ApiModelProperty(value = "Имя", required = true, example = "Иван")
    private String name;

    @ApiModelProperty(value = "Фамилия", required = true, example = "Иванов")
    private String surname;

    @ApiModelProperty(value = "Отчество", required = true, example = "Иванович")
    private String patronymic;

    @ApiModelProperty(value = "Электронная почта", required = true, example = "ivan_ivanich1337@gmail.com")
    private String email;

    @ApiModelProperty(value = "Телефон", required = true, example = "+79241709215")
    private String phone;
}
