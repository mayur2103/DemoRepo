package EncrptionAndDecryption;

import java.util.Base64;

public class decryption {
	
	public static void main(String[] args) {

		//String encrptData= "Password";
		String decrptData  ="UGFzc3dvcmRAMQ==";
				
		byte[] decodeBytes = Base64.getDecoder().decode(decrptData.getBytes());
				
		System.out.println("decodedBytes --------------->" + new String(decodeBytes));

			}

}
