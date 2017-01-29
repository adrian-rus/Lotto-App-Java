/*
*Lotto.java
*@authors Adrian Rus, Allan Williamson, Brian McDonell
*23/11/16
*/

//---------------Brian McDonell ----------------//
public class Lotto{

	//declaring data members
	private int guess[][], check[][];
	private int lotteryNumbers[];
	private int count1, count2, count3;
	private boolean duplicates;

	//constructor
	public Lotto(){
		guess = new int[3][5];
		check = new int[3][5];
		lotteryNumbers= new int[5];
		count1=0;
		count2=0;
		count3=0;
	}

	//setter
	public void setGuess(int guess[][]){
		this.guess = guess;
	}

	//----------------Adrian Rus--------------------//
	//compute method
	public void compute(){

	//generate random numbers and put them in an array using Math class and random() method
	for (int i=0;i<5;i++){
		lotteryNumbers[i]=(int)(Math.random()*40+1);//need to generate numbers in between 1 and 40 therefore we multiply it with 40 and we add 1
	}

	//checking if there are duplicates in the lotteryNumbers
	duplicates=false;
	for (int i=0;i<lotteryNumbers.length;i++){
		for (int j=i+1;j<lotteryNumbers.length;j++){
			if ((i!=j) && (lotteryNumbers[i] == lotteryNumbers[j])){
				duplicates=true;
			}
			if (duplicates) {
				lotteryNumbers[i]=(int)(Math.random()*40+1);
			}
		}
	}

	//iterating through the guess 2D array
	//and comparing each value in the guess array with each value from the lotteryNumber

	for (int i=0;i<guess.length;i++){//using this loop to run through each line of the guess array
		for(int j=0;j<guess[i].length;j++){//using this loop to run through each column of the guess array
			for(int k=0;k<lotteryNumbers.length;k++){//using this loop to run through each element of the lotteryNumber array
				if(lotteryNumbers[k]==guess[i][j]){//we check if each value in lotteryNumber matches with a value in the guess array - elements that the user has inputted
					check[i][j]=guess[i][j]; //and if it matches then we store it in the check array

					//NOTE1: check array is an alternate array that remembers only the common elements from guess array and lotteryNumbers array
					//NOTE2: all other elements from check array will be assigned to 0, 0 is the integer default value
				}
			}
		}
	}
	//NOTE3: We iterate through the elements of check array and on each line and we count how many elements are different than 0
	for (int i=0;i<check.length;i++){
		for (int j=0;j<check[i].length;j++){
			if ((i==0)&&(check[i][j]!=0)){
				count1++;
			}
			if ((i==1)&&(check[i][j]!=0)){
				count2++;
			}
			if ((i==2)&&(check[i][j]!=0)){
				count3++;
			}
		}
	}

	}

	//-----------------Allan Williamson-----------//
	//getters
	//getting the lotteryNumbers
	public int[] getLotteryNumbers(){
		return lotteryNumbers;
	}

	//getting the first counter
	public int getCount1(){
		return count1;
	}

	//getting the second counter
	public int getCount2(){
			return count2;
	}

	//getting the third counter
	public int getCount3(){
			return count3;
	}

}