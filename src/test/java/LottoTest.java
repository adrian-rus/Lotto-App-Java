import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;

class LottoTest {
	@Test
	void generatesFiveDistinctNumbersInRange() {
		Lotto lotto = new Lotto(new Random(17));

		lotto.compute();

		int[] numbers = lotto.getLotteryNumbers();
		Set<Integer> distinctNumbers = new HashSet<Integer>();
		for (int number : numbers) {
			assertTrue(number >= 1 && number <= 40);
			distinctNumbers.add(number);
		}
		assertEquals(5, distinctNumbers.size());
	}

	@Test
	void countsMatchesByLine() {
		Random random = new Random(29);
		Lotto expectedDraw = new Lotto(new Random(29));
		expectedDraw.compute();
		int[] winningNumbers = expectedDraw.getLotteryNumbers();

		int[][] guesses = {
			Arrays.copyOf(winningNumbers, 5),
			{1, 2, 3, 4, 5},
			{36, 37, 38, 39, 40}
		};
		Lotto lotto = new Lotto(random);
		lotto.setGuess(guesses);
		lotto.compute();

		assertArrayEquals(winningNumbers, lotto.getLotteryNumbers());
		assertEquals(5, lotto.getCount1());
		assertEquals(countMatches(guesses[1], winningNumbers), lotto.getCount2());
		assertEquals(countMatches(guesses[2], winningNumbers), lotto.getCount3());
	}

	@Test
	void resetsMatchCountsWhenComputedAgain() {
		Random random = new Random(43);
		Random expectedRandom = new Random(43);
		int[][] guesses = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}};
		Lotto lotto = new Lotto(random);
		lotto.setGuess(guesses);
		lotto.compute();
		lotto.compute();

		Lotto expectedSecondDraw = new Lotto(expectedRandom);
		expectedSecondDraw.compute();
		expectedSecondDraw.compute();
		int[] winningNumbers = expectedSecondDraw.getLotteryNumbers();

		assertArrayEquals(winningNumbers, lotto.getLotteryNumbers());
		assertEquals(countMatches(guesses[0], winningNumbers), lotto.getCount1());
		assertEquals(countMatches(guesses[1], winningNumbers), lotto.getCount2());
		assertEquals(countMatches(guesses[2], winningNumbers), lotto.getCount3());
	}

	@Test
	void copiesGuessesAndReturnedWinningNumbers() {
		int[][] guesses = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}};
		Lotto lotto = new Lotto(new Random(7));
		lotto.setGuess(guesses);
		guesses[0][0] = 40;
		lotto.compute();

		int[] winningNumbers = lotto.getLotteryNumbers();
		int originalFirstNumber = winningNumbers[0];
		int expectedMatches = countMatches(new int[]{1, 2, 3, 4, 5}, winningNumbers);
		winningNumbers[0] = -1;

		assertNotEquals(-1, lotto.getLotteryNumbers()[0]);
		assertEquals(expectedMatches, lotto.getCount1());
		assertTrue(originalFirstNumber >= 1 && originalFirstNumber <= 40);
	}

	@Test
	void rejectsMalformedOrInvalidGuesses() {
		Lotto lotto = new Lotto(new Random(1));

		assertThrows(IllegalArgumentException.class, () -> lotto.setGuess(null));
		assertThrows(IllegalArgumentException.class, () -> lotto.setGuess(new int[][]{{1, 2, 3, 4, 5}}));
		assertThrows(IllegalArgumentException.class, () -> lotto.setGuess(
				new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14}}));
		assertThrows(IllegalArgumentException.class, () -> lotto.setGuess(
				new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 41}}));
		assertThrows(IllegalArgumentException.class, () -> lotto.setGuess(
				new int[][]{{1, 2, 3, 4, 4}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}}));
	}

	private int countMatches(int[] guesses, int[] winningNumbers) {
		int matches = 0;
		for (int guess : guesses) {
			for (int winningNumber : winningNumbers) {
				if (guess == winningNumber) {
					matches++;
					break;
				}
			}
		}
		return matches;
	}
}
