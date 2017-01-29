/*
*LottoApp.java
*@authors Adrian Rus, Allan Williamson, Brian McDonell
*23/11/16
*/

//--------------Allan Williamson---------------//
import javax.swing.JOptionPane;//importing JOptionPane

public class LottoApp{

	public static void main(String args[]){

		//variables
		int guess[][], check[][]; //2D integer arrays
		int lotteryNumbers[]; //1D integer array
		int count1,count2,count3; //counting variables
        String str = "";//collects all sytem prints into one JOptionPane
		//creating 2D arrays
		guess=new int[3][5];
		check=new int[3][5];

		//objects
		Lotto myLotto;
		myLotto = new Lotto();

		//------------------Brian McDonell---------//
		//setting the 2D guess array
		myLotto.setGuess(guess);


		//user inputs numbers
		for(int i=0;i<3;i++){
			for(int j=0;j<5;j++){

				if (j==0){ //checks if we are at the begining of the line and prints out a message to inform the user which line is he entering elements for
					JOptionPane.showMessageDialog(null,"Enter the numbers for line "+(i+1));//it is line i+1 because i starts at 0
				}

				guess[i][j]= Integer.parseInt(JOptionPane.showInputDialog(null,"Please enter your numbers:"));//parses the String entered and makes it an integer element which is added to the guess array

				//checking if the value entered is in between 1 and 40, shows an error message otherwise and asks the user to enter the number again
				if ((guess[i][j]<1)||(guess[i][j])>40){
					JOptionPane.showMessageDialog(null, "Number entered is not valid");
					guess[i][j]=Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the number again:"));
					}
			}

		}

		//computing
		myLotto.compute();

		//----------------Alan Williamson--------------------//
		//printing the numbers entered by the user to visualise them
		str +=("Your numbers are: "+"\n");
		for(int i=0;i<3;i++){
			for(int j=0;j<5;j++){
				str +=(guess[i][j]+" ");
			}
			str +=("\n");
		}

		str +=("\n");

		//getting the output

		str +=("The winning numbers are:"+"\n");

		//getting the lottery numbers
		lotteryNumbers=myLotto.getLotteryNumbers();

		//printing the lottery numbers
		for (int i=0;i<5;i++){
			str +=(lotteryNumbers[i]+" ");
		}

		str +=("\n");
		str +=("\n");

		//getting the first line count and printing it to screen
		count1=myLotto.getCount1();
		str +=("In the first line you guessed: "+count1+ " numbers"+"\n");

		//getting the second line count and printing it to screen
		count2=myLotto.getCount2();
		str +=("In the second line you guessed: "+count2+ " numbers"+"\n");

		//getting the third line count and printing it to screen
		count3=myLotto.getCount3();
		str +=("In the third line you guessed: "+count3+ " numbers");

        JOptionPane.showMessageDialog(null,str);

		//NOTE2: We print the user numbers, the winning numbers and how many numbers the user guessed to the screen for better visualisation
	}
}