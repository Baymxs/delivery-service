package ru.nsu.bayramov.deliveryservice.dtotransformservice;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.nsu.bayramov.deliveryservice.model.domain.Task;
import ru.nsu.bayramov.deliveryservice.model.dto.TaskDto;

@Service
@RequiredArgsConstructor
public class DefaultDtoTransformService implements DtoTransformService {
    private final ModelMapper modelMapper;

    public TaskDto convertToTaskDto(Task task) {
        return modelMapper.map(task, TaskDto.class);
    }
}
