import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CardNumberTest {

	@Test
	void notNumberCardHaveValue() {
		assertEquals(10, CardNumber.JACK.getMinValue());
		assertEquals(10, CardNumber.QUEEN.getMinValue());
		assertEquals(10, CardNumber.KING.getMinValue());
	}

	@Test
	void aceCanBeEither1Or11() {
		assertEquals(1, CardNumber.ACE.getMinValue());
		assertEquals(11, CardNumber.ACE.getMaxValue());
	}
}
