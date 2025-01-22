package guru.qa.photocatalog.domain.graphql;

import guru.qa.photocatalog.data.PhotoEntity;

public record PhotoInputGql(String description,
                            String content) {

    public static PhotoInputGql fromEntity(PhotoEntity pe) {
        return new PhotoInputGql(
                pe.getDescription(),
                "");
    }
}
