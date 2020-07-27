package io.laidani.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import io.laidani.dtos.WellDTO;
import io.laidani.models.Well;

@Mapper(componentModel = "Spring")
public interface IWellMapper {
	
	IWellMapper INSTANCE = Mappers.getMapper(IWellMapper.class);
	
	@Mapping(source = "latitude", target = "gps_latitude")
	WellDTO wellToWellDTO (Well well);
	
	List<WellDTO> wellsToWellDTOs (List<Well> well);
}
