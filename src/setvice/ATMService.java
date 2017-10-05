package setvice;

import java.util.Random;

import dataBaseutil.DataBaseUtils;
import user.User;

public class ATMService {

	public static void addUser() {
		User user = new User();
		int balanece = 0;
		Random random = new Random();
		int num = random.nextInt(10000);
		user.setCarNum(num);
		user.setBalanece(balanece);
		
		int row = DataBaseUtils.addUser(user);
		if(1 != row) {
			System.out.println("开户失败");
		}
	}
	
	

	public static void addAmout(int cardNum) {
		 int amount = 1000;
		 int row  = DataBaseUtils.deposit(amount, cardNum);
		 if(1!= row) {
			 System.out.println("存钱失败");
		 }
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		ATMService.addUser();
	
		
	}
	
	
	
	
	
	
	
}
