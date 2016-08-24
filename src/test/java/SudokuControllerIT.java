import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import de.affinitas.sudoku.SudokuApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class SudokuControllerIT {
	
	private int port = 8080;

	private URL baseURL;
	private RestTemplate template;

	@Before
	public void setUp() throws Exception {
		this.baseURL = new URL("http://localhost:" + port + "/");
		template = new RestTemplate();
	}

	@Test
	public void testMainScreen() throws Exception {
		ResponseEntity<String> response = template.getForEntity(baseURL.toString(), String.class);
		assertThat(response.getBody(), equalTo("Welcome to Sudoku Affinitas"));
	}
}