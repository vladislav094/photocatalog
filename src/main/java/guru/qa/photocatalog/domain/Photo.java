package guru.qa.photocatalog.domain;

import guru.qa.photocatalog.data.PhotoEntity;

import java.util.Date;

public record Photo(String description,
                    Date lastModifyDate,
                    String content) {

    public static Photo fromEntity(PhotoEntity pe) {
        return new Photo(
                pe.getDescription(),
                pe.getLastModifyDate(),
                "");
    }
}
