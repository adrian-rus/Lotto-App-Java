import javax.swing.JOptionPane;

public class LottoApp {
	private static final int LINE_COUNT = 3;
	private static final int NUMBERS_PER_LINE = 5;

	public static void main(String[] args) {
		int[][] guesses = new int[LINE_COUNT][NUMBERS_PER_LINE];
		for (int line = 0; line < LINE_COUNT; line++) {
			JOptionPane.showMessageDialog(null, "Enter five unique numbers for line " + (line + 1));
			boolean lineComplete = false;
			while (!lineComplete) {
				for (int numberIndex = 0; numberIndex < NUMBERS_PER_LINE; numberIndex++) {
					Integer number = promptForNumber();
					if (number == null) {
						return;
					}

					boolean duplicate = false;
					for (int previous = 0; previous < numberIndex; previous++) {
						if (guesses[line][previous] == number) {
							duplicate = true;
							break;
						}
					}
					if (duplicate) {
						JOptionPane.showMessageDialog(null,
								"Each number on a line must be unique. Please re-enter this line.");
						break;
					}
					guesses[line][numberIndex] = number;
					if (numberIndex == NUMBERS_PER_LINE - 1) {
						lineComplete = true;
					}
				}
			}
		}

		Lotto lotto = new Lotto();
		lotto.setGuess(guesses);
		lotto.compute();

		StringBuilder result = new StringBuilder("Your numbers are:\n");
		for (int[] line : guesses) {
			for (int number : line) {
				result.append(number).append(' ');
			}
			result.append('\n');
		}

		result.append("\nThe winning numbers are:\n");
		for (int number : lotto.getLotteryNumbers()) {
			result.append(number).append(' ');
		}
		result.append("\n\nIn the first line you guessed: ").append(lotto.getCount1()).append(" numbers")
				.append("\nIn the second line you guessed: ").append(lotto.getCount2()).append(" numbers")
				.append("\nIn the third line you guessed: ").append(lotto.getCount3()).append(" numbers");

		JOptionPane.showMessageDialog(null, result.toString());
	}

	private static Integer promptForNumber() {
		while (true) {
			String input = JOptionPane.showInputDialog(null, "Please enter a number from 1 to 40:");
			if (input == null) {
				return null;
			}

			try {
				int number = Integer.parseInt(input.trim());
				if (number >= 1 && number <= 40) {
					return number;
				}
			} catch (NumberFormatException ignored) {
				// Show the same validation message for non-numeric and out-of-range input.
			}
			JOptionPane.showMessageDialog(null, "Enter a whole number between 1 and 40.");
		}
	}
}