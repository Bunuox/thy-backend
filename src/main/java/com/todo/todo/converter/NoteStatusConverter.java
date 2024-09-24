package com.todo.todo.converter;

import com.todo.todo.model.enumaration.NoteStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class NoteStatusConverter implements AttributeConverter<NoteStatus, String> {
    @Override
    public String convertToDatabaseColumn(NoteStatus attribute) {
        return attribute.getCode();
    }

    @Override
    public NoteStatus convertToEntityAttribute(String dbData) {
        return NoteStatus.getByCode(dbData);
    }
}
