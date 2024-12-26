package guru.qa.photocatalog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PhotocatalogApplicationTests {

	public String abc(String a) {
		System.out.println("a");
		return "";
	}

	@Test
	void contextLoads() {
		abc(abc("a"));

	}

}
