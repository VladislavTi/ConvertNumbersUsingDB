package main;

import java.sql.SQLException;
import bd.BDClass;
import java.util.*;
import java.util.concurrent.TimeUnit;

import parsing.*;
import bd.OperationWithBD;


public class Main {
	

	private static Scanner scanner;

	private static class WorkTask extends Thread {
		
		//we use field msg to exit from thread
		//in method run we compare msg with keyword "exit"
		private String msg;
		
		WorkTask(String msg) {
			this.msg = msg;
		}
		
		public void setMessage(String s){
			this.msg = s;
		}
		
		public String getMessage() {
			return this.msg;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (!getMessage().equals("exit")) {
				
				//waiting for 10 seconds
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//read and delete min value from DB
				try {
					BDClass.ReadDB();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			System.out.println("Thread closed");
		}
	   
	}
	
	
	
	

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		scanner = new Scanner(System.in);

		BDClass.create();

		//create variable dbThread and set message "start"
		Runnable dbThread = new WorkTask("start");
		
		//starting thread
		((Thread) dbThread).start();
		
		
		out: while (true) {
			        System.out.println("Input number");
			        String inputString = scanner.nextLine().toLowerCase();
		
			        //check for exit from program
			        if (inputString.equals("exit")) {
			        	System.out.println("Please wait for a while....");
			        	
			        	//set message "exit" to exit from thread
			        	((WorkTask) dbThread).setMessage("exit");
			        	
			        	//waiting until thread is closing
			        	try {
							((Thread) dbThread).join();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        	
			        	//close DB connection
			        	BDClass.CloseDB();
			        	System.out.println("Good bye!");
			        	break out;
			        }
			        
			        //waiting for a number from console and change it from string to number
			        //after that, fill DB with new record
			        StrToInt s = new StrToInt(inputString);
			        int p = s.StartParsing();
			        if ((p != 0) || ((p == 0) && (inputString.equals("zero")))) {
				        BDClass.WriteDB(inputString, s.StartParsing());
				        System.out.println(p);
			        } else {
			        	System.err.println("Wrong number. Please enter correct string");
			        }
   		}
		
	}

}
