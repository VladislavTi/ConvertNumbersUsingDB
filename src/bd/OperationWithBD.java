package bd;

import java.sql.SQLException;
import java.util.TimerTask;

public class OperationWithBD extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				BDClass.ReadDB();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
