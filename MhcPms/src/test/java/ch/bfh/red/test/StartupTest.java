package ch.bfh.red.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ch.bfh.red.TestApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class StartupTest {

    @Test
    public void startupTest() {
//    	TestApplication.main(new String[]{});
    }

}
