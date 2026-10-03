## Lotto App

A Java Swing lottery game. Enter three lines of five distinct numbers from 1 to 40. The app draws five unique winning numbers and displays the number of matches on each line.

### Requirements

- Java 8 or newer
- Maven 3.6 or newer

### Run

From the repository root, run:

```text
mvn package
java -cp target/classes LottoApp
```

The app accepts only whole numbers from 1 to 40, rejects duplicates within a line, and exits cleanly if input is cancelled.

### Test

```text
mvn test
```
