import com.nautilus.logger.LogMessageStore;
import com.nautilus.logger.LogReader;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by michaeldunnegan on 4/28/18.
 */
public class LogReaderTest {

    @Test
    public void testLogsInOrder() {
        LogMessageStore.log(3, "3a", null);
        LogMessageStore.log(1, "1a", null);
        LogMessageStore.log(2, "2a", null);
        LogMessageStore.log(3, "3b", null);

        List<String> expectedMessages = Arrays.asList("3b", "3a", "2a", "1a");
        List<String> actualMessages = new ArrayList<>();

        LogReader logReader = new LogReader();

        actualMessages.add(logReader.get());
        actualMessages.add(logReader.get());
        actualMessages.add(logReader.get());
        actualMessages.add(logReader.get());

        assert (actualMessages.equals(expectedMessages));
    }
}
