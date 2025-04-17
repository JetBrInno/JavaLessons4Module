import org.junit.jupiter.api.Test;
import ru.inno.StringUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringUtilsTest {

    @Test
    public void testIsBlank() {
        StringUtils stringUtils = new StringUtils();
        assertTrue(stringUtils.isBLank(""));
    }

    @Test
    public void testIsBlank2() {
        StringUtils stringUtils = new StringUtils();
        assertTrue(stringUtils.isBLank(" "));
    }

    @Test
    public void testIsBlank3() {
        StringUtils stringUtils = new StringUtils();
        assertTrue(stringUtils.isBLank("      "));
    }
}
