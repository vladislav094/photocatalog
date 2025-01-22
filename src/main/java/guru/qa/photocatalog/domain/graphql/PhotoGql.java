package guru.qa.photocatalog.domain.graphql;

import guru.qa.photocatalog.data.PhotoEntity;

import java.util.Date;
import java.util.UUID;

public record PhotoGql(UUID id,
                       String description,
                       Date lastModifyDate,
                       String content) {

    public static PhotoGql fromEntity(PhotoEntity pe) {
        return new PhotoGql(
                pe.getId(),
                pe.getDescription(),
                pe.getLastModifyDate(),
                "");
    }
}
