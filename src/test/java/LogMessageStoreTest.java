import com.nautilus.logger.LogMessageStore;
import com.nautilus.logger.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by michaeldunnegan on 4/28/18.
 */

public class LogMessageStoreTest {

    @Test
    public void testBasicLogMessage() {
        LogMessageStore.log(3, "3a", null);
        int counter = 0;
        while (LogMessageStore.get() != null) {
            counter++;
        }
        assert(counter == 1);
    }

    @Test
    public void testMultipleLogMessage() {
        LogMessageStore.log(3, "3a", null);
        LogMessageStore.log(2, "2a", null);
        LogMessageStore.log(1, "1a", null);
        int counter = 0;
        while (LogMessageStore.get() != null) {
            counter++;
        }
        assert(counter == 3);
    }

    @Test
    public void testLogsInOrder() {
        LogMessageStore.log(3, "3a", null);
        LogMessageStore.log(1, "1a", null);
        LogMessageStore.log(2, "2a", null);
        LogMessageStore.log(3, "3b", null);

        List<String> expectedMessages = Arrays.asList("3b", "3a", "2a", "1a");
        List<String> actualMessages = new ArrayList<>();

        actualMessages.add(LogMessageStore.get());
        actualMessages.add(LogMessageStore.get());
        actualMessages.add(LogMessageStore.get());
        actualMessages.add(LogMessageStore.get());

        assert (actualMessages.equals(expectedMessages));
    }

    @Test
    public void testAppendModeSimple() {
        LogMessageStore.beginAppend();
        LogMessageStore.log(3, "3a", null);
        LogMessageStore.log(1, "1a", null);
        LogMessageStore.log(2, "2a", null);
        LogMessageStore.log(3, "3b", null);
        LogMessageStore.endAppend();

        List<String> expectedMessages = Arrays.asList("3a3b", "2a", "1a");
        List<String> actualMessages = new ArrayList<>();

        actualMessages.add(LogMessageStore.get());
        actualMessages.add(LogMessageStore.get());
        actualMessages.add(LogMessageStore.get());

        assert (actualMessages.equals(expectedMessages));
    }

    @Test
    public void testAppendModeComplex() {
        LogMessageStore.beginAppend();
        LogMessageStore.log(3, "3a", null);
        LogMessageStore.log(1, "1a", null);
        LogMessageStore.log(2, "2a", null);
        LogMessageStore.log(3, "3b", null);
        LogMessageStore.endAppend();
        LogMessageStore.log(3, "3c", null);
        LogMessageStore.log(1, "1b", null);
        LogMessageStore.log(2, "2b", null);
        LogMessageStore.log(3, "3d", null);

        List<String> expectedMessages = Arrays.asList("3d", "3c", "3a3b", "2b", "2a", "1b", "1a");
        List<String> actualMessages = new ArrayList<>();

        actualMessages.add(LogMessageStore.get());
        actualMessages.add(LogMessageStore.get());
        actualMessages.add(LogMessageStore.get());
        actualMessages.add(LogMessageStore.get());
        actualMessages.add(LogMessageStore.get());
        actualMessages.add(LogMessageStore.get());
        actualMessages.add(LogMessageStore.get());

        assert (actualMessages.equals(expectedMessages));
    }

}
