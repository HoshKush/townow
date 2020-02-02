package spring.sts.townow;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import spring.model.board.BoardDAO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class Boardmapper {

	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

	@Autowired
	private BoardDAO dao;

	@Test
	@Ignore
	public void testMapper() {
		logger.info("mapper:" + dao.getClass().getName());

	}

}