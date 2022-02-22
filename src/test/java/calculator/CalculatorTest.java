package calculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
public class CalculatorTest {

    @Test
    public void splitAndSum_null() {
        int result = Calculator.splitAndSum(null);

        assertThat(result).isEqualTo(0);
    }

    @Test
    public void splitAndSum_empty() {
        int result = Calculator.splitAndSum("");

        assertThat(result).isEqualTo(0);
    }

    @Test
    public void splitAndSum_숫자하나() {
        int result = Calculator.splitAndSum("1");
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void splitAndSum_쉼표구분자() {
        int result = Calculator.splitAndSum("1,2");
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void splitAndSum_쉼표_또는_콜론_구분자() {
        int result = Calculator.splitAndSum("1,2:3");
        assertThat(result).isEqualTo(6);
    }

    @Test
    public void splitAndSum_custom_구분자() {
        int result = Calculator.splitAndSum("//;\n1;2;3");
        assertThat(result).isEqualTo(6);
    }

    @Test
    public void splitAndSum_negative() {
        assertThatThrownBy(() -> Calculator.splitAndSum("-1,2,3"))
                .isInstanceOf(RuntimeException.class);
    }
}
