package parsing;

public class StrToInt {
String [] inputStringArray;
	
	public StrToInt(String str) {
		this.inputStringArray = str.toLowerCase().split(" |-");
	}
	
	public int StartParsing() {
		
		int summ = 0;
		int previousNumber = 0, number = 0; 
		String identifier = null;

		if (this.inputStringArray.length > 1) {
			for (int i = this.inputStringArray.length-1; i >= 0; i--) {
				
				
				if ((this.inputStringArray[i].equals("thousand")) || (this.inputStringArray[i].equals("hundred"))) {
					identifier = this.inputStringArray[i];
					continue;
				}
				
				
				if ((i == this.inputStringArray.length-1) && (identifier == null) ) {
					summ += countOneWord(i);
					continue;
				}
				
				number = units(this.inputStringArray[i]);
				if (number == 0) {														//not 1-9
					number = tenths(this.inputStringArray[i]);
					if (number == 0) {													//not 11-19
						number = decade(this.inputStringArray[i]);
						summ += number;
						identifier = null;
					} else {
						summ += number;
						identifier = null;
					}
				} else {
					previousNumber = number;
				}
				
				
				if (identifier != null) {
					switch(identifier) {
					case "thousand":
						summ += previousNumber * 1000;
						break;
					case "hundred":
						summ += previousNumber * 100;
					}
				} 
				
			
			
			}
		} else if (this.inputStringArray.length == 1){
			summ = countOneWord(this.inputStringArray.length - 1);
			
		}
		
		
		return summ;
	}
	

	private int countOneWord(int position) {
		int summ = 0;
		
		summ = units(this.inputStringArray[position]);
		if (summ == 0) {														//не 1-9
			summ = tenths(this.inputStringArray[position]);
			if (summ == 0) {													//не 11-19
				summ = decade(this.inputStringArray[position]);
			} 
		} 
		
		return summ;
		
	}
	
	
	
	
		
	private int units(String str) {
		switch (str){
		case "one":
			return 1;
		case "two":
			return 2;
		case "three":
			return 3;
		case "four":
			return 4;
		case "five":
			return 5;
		case "six":
			return 6;
		case "seven":
			return 7;
		case "eight":
			return 8;
		case "nine":
			return 9;
		}
		return 0;
	}
	
	private int tenths(String str) {
		switch (str){
			case "eleven":
				return 11;
			case "twelve":
				return 12;
			case "thirteen":
				return 13;
			case "fourteen":
				return 14;
			case "fifteen":
				return 15;
			case "sixteen":
				return 16;
			case "seventeen":
				return 17;
			case "eighteen":
				return 18;
			case "nineteen":
				return 19;
		}
		return 0;
	}
	
	private int decade(String str) {
		switch (str){
		case "ten":
			return 10;
		case "twenty":
			return 20;
		case "thirty":
			return 30;
		case "forty":
			return 40;
		case "fifty":
			return 50;
		case "Sixty":
			return 60;
		case "seventy":
			return 70;
		case "eighty":
			return 80;
		case "ninety":
			return 90;
		}
		return 0;
	}
	
	
}
