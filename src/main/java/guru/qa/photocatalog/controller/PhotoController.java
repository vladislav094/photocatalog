package guru.qa.photocatalog.controller;

import guru.qa.photocatalog.domain.Photo;
import guru.qa.photocatalog.ex.PhotoNotFoundException;
import guru.qa.photocatalog.service.PhotoService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/photo")
public class PhotoController {

    private static final Logger LOG = LoggerFactory.getLogger(PhotoController.class);
    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/all")
    public List<Photo> all() {
        return photoService.allPhoto();
    }

    @GetMapping("/{id}")
    public Photo byId(@PathVariable("id") String id) {
        return photoService.byId(id);
    }
}
