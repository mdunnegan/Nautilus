import com.nautilus.logger.LogMessageStore;
import com.nautilus.logger.Logger;
import org.junit.Test;

import static junit.framework.TestCase.fail;

/**
 * Created by michaeldunnegan on 4/28/18.
 */
public class LoggerTest {

    @Test
    public void testNoArgLoggerWithValidCall() {

        final String myMessage = "hello test";

        Logger l = new Logger();
        l.log(1, myMessage);

        assert(LogMessageStore.get().equals(myMessage));
    }

    @Test
    public void testNoArgLoggerWithInvalidCall() {

        final String myMessage = "hello test";

        Logger l = new Logger();

        try {
            l.log(myMessage);
            fail();
        } catch (Exception e) {
            assert(LogMessageStore.get() == null);
        }
    }

    @Test
    public void testOneArgLoggerWithValidCall() {
        final String myMessage = "hello test";

        Logger l = new Logger(1);
        l.log(myMessage);

        assert(LogMessageStore.get().equals(myMessage));
    }

    @Test
    public void testOneArgLoggerWithLogLevelOverriddenCall() {
        final String myMessage = "hello test";

        Logger l = new Logger(1);
        l.log(3, myMessage);

        assert(LogMessageStore.get().equals(myMessage));
    }

}
