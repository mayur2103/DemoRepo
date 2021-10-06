package EncrptionAndDecryption;

import java.util.Base64;

public class encrption {

	public static void main(String[] args) {

		String encrptData= "Password@121";
		
		byte[] encodedBytes = Base64.getEncoder().encode(encrptData.getBytes());
		
		System.out.println("encodedBytes --------------->" + new String(encodedBytes));

	}
	
	
}