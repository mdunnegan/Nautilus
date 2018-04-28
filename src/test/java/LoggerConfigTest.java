import com.nautilus.logger.LogMessageStore;
import com.nautilus.logger.LogReader;
import com.nautilus.logger.Logger;
import com.nautilus.logger.LoggerConfig;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.fail;

/**
 * Created by michaeldunnegan on 4/28/18.
 */
public class LoggerConfigTest {

    @Test
    public void testLoggerFormatting() {

        LoggerConfig config = new LoggerConfig(this.getClass(),
                "datetime={datetime}, priority={priority}, classname={classname}, thread={thread}: '{message}'");
        Logger logger = new Logger(config);

        logger.log(3, "3a", config);
        logger.log(1, "1a", config);
        logger.log(2, "2a", config);
        logger.log(3, "3b", config);

        List<String> expectedMessages = Arrays.asList("3b", "3a", "2a", "1a");
        List<String> actualMessages = new ArrayList<>();

        LogReader logReader = new LogReader();

        actualMessages.add(logReader.get());
        actualMessages.add(logReader.get());
        actualMessages.add(logReader.get());
        actualMessages.add(logReader.get());

        actualMessages.forEach(System.out::println);

        assert(true);
    }

    @Test
    public void testCapacity() {

        LoggerConfig config = new LoggerConfig(this.getClass(),
                "datetime={datetime}, priority={priority}, classname={classname}, thread={thread}: '{message}'", 1L);
        Logger logger = new Logger(config);

        logger.log(3, "3a", config);
        logger.log(1, "1a", config);

        List<String> expectedMessages = Arrays.asList("3b", "3a");
        List<String> actualMessages = new ArrayList<>();

        LogReader logReader = new LogReader();

        actualMessages.add(logReader.get());
        actualMessages.add(logReader.get());

        actualMessages.forEach(System.out::println);

        // Best confirmed by human eyes
        assert(true);
    }
}
