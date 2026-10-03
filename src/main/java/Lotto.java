import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Lotto {
	private static final int LINE_COUNT = 3;
	private static final int NUMBERS_PER_LINE = 5;
	private static final int MAX_NUMBER = 40;

	private final Random random;
	private int[][] guess = new int[LINE_COUNT][NUMBERS_PER_LINE];
	private final int[] lotteryNumbers = new int[NUMBERS_PER_LINE];
	private final int[] matchCounts = new int[LINE_COUNT];

	public Lotto() {
		this(new Random());
	}

	public Lotto(Random random) {
		this.random = Objects.requireNonNull(random, "random");
	}

	public void setGuess(int[][] guess) {
		if (guess == null || guess.length != LINE_COUNT) {
			throw new IllegalArgumentException("Exactly three lines are required.");
		}

		int[][] copy = new int[LINE_COUNT][NUMBERS_PER_LINE];
		for (int line = 0; line < LINE_COUNT; line++) {
			if (guess[line] == null || guess[line].length != NUMBERS_PER_LINE) {
				throw new IllegalArgumentException("Each line must contain exactly five numbers.");
			}

			for (int numberIndex = 0; numberIndex < NUMBERS_PER_LINE; numberIndex++) {
				int number = guess[line][numberIndex];
				if (number < 1 || number > MAX_NUMBER) {
					throw new IllegalArgumentException("Numbers must be between 1 and 40.");
				}
				for (int previous = 0; previous < numberIndex; previous++) {
					if (copy[line][previous] == number) {
						throw new IllegalArgumentException("Numbers on a line must be unique.");
					}
				}
				copy[line][numberIndex] = number;
			}
		}
		this.guess = copy;
	}

	public void compute() {
		List<Integer> availableNumbers = new ArrayList<Integer>(MAX_NUMBER);
		for (int number = 1; number <= MAX_NUMBER; number++) {
			availableNumbers.add(number);
		}
		Collections.shuffle(availableNumbers, random);
		for (int index = 0; index < NUMBERS_PER_LINE; index++) {
			lotteryNumbers[index] = availableNumbers.get(index);
		}

		Arrays.fill(matchCounts, 0);
		for (int line = 0; line < LINE_COUNT; line++) {
			for (int guessedNumber : guess[line]) {
				for (int lotteryNumber : lotteryNumbers) {
					if (guessedNumber == lotteryNumber) {
						matchCounts[line]++;
						break;
					}
				}
			}
		}
	}

	public int[] getLotteryNumbers() {
		return Arrays.copyOf(lotteryNumbers, lotteryNumbers.length);
	}

	public int getCount1() {
		return matchCounts[0];
	}

	public int getCount2() {
		return matchCounts[1];
	}

	public int getCount3() {
		return matchCounts[2];
	}
}