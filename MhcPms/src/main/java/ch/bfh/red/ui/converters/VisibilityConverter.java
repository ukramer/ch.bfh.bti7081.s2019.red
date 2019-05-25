package ch.bfh.red.ui.converters;


import ch.bfh.red.backend.models.Visibility;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class VisibilityConverter implements AttributeConverter<Visibility, String> {

    @Override
    public String convertToDatabaseColumn(Visibility visibility) {
        if (visibility== null) {
            return null;
        }
        return visibility.getCode();
    }

    @Override
    public Visibility convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Visibility.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}