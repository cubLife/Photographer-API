package com.gmail.serhiisemiv.dto.mappers;

import com.gmail.serhiisemiv.dto.CostumerFeedbackDto;
import com.gmail.serhiisemiv.modeles.CostumerFeedback;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CostumerFeedbackMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public CostumerFeedbackMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        TypeMap<CostumerFeedback, CostumerFeedbackDto> propertyMapperToDto = modelMapper.createTypeMap(CostumerFeedback.class, CostumerFeedbackDto.class);

        propertyMapperToDto.addMappings(mapper -> mapper.map(CostumerFeedback::getId,CostumerFeedbackDto::setId));
        propertyMapperToDto.addMappings(mapper -> mapper.map(CostumerFeedback::getEmail,CostumerFeedbackDto::setEmail));
        propertyMapperToDto.addMappings(mapper -> mapper.map(CostumerFeedback::getFirstName,CostumerFeedbackDto::setFirstName));
        propertyMapperToDto.addMappings(mapper -> mapper.map(CostumerFeedback::getLastName,CostumerFeedbackDto::setLastName));
        propertyMapperToDto.addMappings(mapper -> mapper.map(CostumerFeedback::getCreationDate, CostumerFeedbackDto::setCreationDate));
        propertyMapperToDto.addMappings(mapper -> mapper.map(CostumerFeedback::getGrade, CostumerFeedbackDto::setGrade));
        propertyMapperToDto.addMappings(mapper -> mapper.map(CostumerFeedback::getFeedback, CostumerFeedbackDto::setFeedback));
    }

    public CostumerFeedbackDto toDto(CostumerFeedback costumerFeedback) {
        return modelMapper.map(costumerFeedback, CostumerFeedbackDto.class);
    }
}
