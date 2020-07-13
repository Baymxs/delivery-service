package ru.nsu.bayramov.deliveryservice.dtotransformservice;

import ru.nsu.bayramov.deliveryservice.model.domain.Task;
import ru.nsu.bayramov.deliveryservice.model.dto.TaskDto;

public interface DtoTransformService {
    TaskDto convertToTaskDto(Task task);
}
