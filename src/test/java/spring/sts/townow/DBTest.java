package spring.sts.townow;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/min/webapp/WEB-INF/spring/**/root-context" })

public class DBTest {

	@Inject
	private DataSource ds;

	@Test
	public void testConnection() throws Exception {
		try (Connection con = ds.getConnection()) {
			System.out.println("Connection : " + con + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
