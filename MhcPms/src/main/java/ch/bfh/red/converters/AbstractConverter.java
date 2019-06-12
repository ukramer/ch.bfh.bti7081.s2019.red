package ch.bfh.red.converters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public abstract class AbstractConverter<MODEL, DTO> {
	
	public abstract MODEL toModel(DTO dto);
	
	public abstract DTO toDTO(MODEL model);
	
	public ArrayList<DTO> toDTOList(Collection<MODEL> models) {
		return toDTO(models, new ArrayList<>());
	}
	
	public ArrayList<MODEL> toModelList(Collection<DTO> dtos) {
		return toModel(dtos, new ArrayList<>());
	}
	
	public HashSet<DTO> toDTOSet(Collection<MODEL> models) {
		return toDTO(models, new HashSet<>());
	}
	
	public HashSet<MODEL> toModelSet(Collection<DTO> dtos) {
		return toModel(dtos, new HashSet<>());
	}
	
	public <T extends Collection<DTO>> T toDTO(Collection<MODEL> models, T dtos) {
		for (MODEL model : models)
			dtos.add(toDTO(model));
		return dtos;
	}
	
	public <T extends Collection<MODEL>> T toModel(Collection<DTO> dtos, T models) {
		for (DTO dto : dtos)
			models.add(toModel(dto));
		return models;
	}
	
}
