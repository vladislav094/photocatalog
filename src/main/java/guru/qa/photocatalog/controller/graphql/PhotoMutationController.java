package guru.qa.photocatalog.controller.graphql;

import guru.qa.photocatalog.domain.graphql.PhotoGql;
import guru.qa.photocatalog.domain.graphql.PhotoInputGql;
import guru.qa.photocatalog.service.PhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PhotoMutationController {

    private static final Logger LOG = LoggerFactory.getLogger(PhotoMutationController.class);
    private final PhotoService photoService;

    @Autowired
    public PhotoMutationController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @MutationMapping
    public PhotoGql addPhoto(@Argument PhotoInputGql input) {
        return photoService.addPhotoGql(input);
    }
}
