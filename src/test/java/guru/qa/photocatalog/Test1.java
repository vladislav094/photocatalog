package guru.qa.photocatalog;

import guru.qa.photocatalog.aspects.MyAnnotation;
import org.junit.jupiter.api.Test;

import static guru.qa.photocatalog.controller.PhotoController.myCall;

public class Test1 {

    @Test
    @MyAnnotation
    public void debugTest() {
        myDebug("a", 1, 2, new Object[]{"q"});
        myCall();
    }

    @MyAnnotation
    public void myDebug(String abc, int i, int c, Object[] o) {
    }
}
