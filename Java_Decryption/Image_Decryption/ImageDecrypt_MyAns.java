//Author: Murtaza Lodgher

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.lang.model.util.ElementScanner6;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.BadPaddingException;
import java.security.MessageDigest;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
 
public class ImageDecrypt_MyAns {    
  static void P1() throws Exception {
    byte[] iv = new byte[] { 0, 0, 0, 0, 
                             0, 0, 0, 0, 
                             0, 0, 0, 0, 
                             0, 0, 0, 0 };
    byte[] key = Files.readAllBytes(Paths.get("P1_key"));
    byte[] cipherText = Files.readAllBytes(Paths.get("P1_cipher.txt"));
    
    // BEGIN SOLUTION
    byte[] plainText = cipherText;
    Cipher cbc = Cipher.getInstance("AES/CBC/ISO10126Padding"); // Initialized cipher
    SecretKeySpec cbcKey = new SecretKeySpec(key, "AES"); // Initialized key
    IvParameterSpec cbcIV = new IvParameterSpec(iv); // Initialized IV
    cbc.init(Cipher.DECRYPT_MODE, cbcKey, cbcIV); // Set cipher to decrypt
    plainText = cbc.doFinal(cipherText); // Decrypted plainText Initialized
    System.out.println("Problem 1:");
    // END SOLUTION
    
    System.out.println(new String(plainText, StandardCharsets.UTF_8));
  }

  public static byte[] p3PlainHeader = new byte[54]; // Made byte array for header of the decrypted bmp on P2

  static void P2() throws Exception {
    byte[] cipherBMP = Files.readAllBytes(Paths.get("P2_cipher.bmp"));
    
    // BEGIN SOLUTION
    byte[] P1_plaintext = "The quick brown fox jumps over the lazy dog.".getBytes(StandardCharsets.UTF_8); // Copied P1 message from console to here
    byte[] plainBMP = cipherBMP;

    MessageDigest p21_MD = MessageDigest.getInstance("MD5"); // Initialized message digest    
    p21_MD.update(P1_plaintext); // Set message disgest to P1 plainText
    System.out.println("\nProblem 2-1:");
    byte[] p1PlainMDHash = p21_MD.digest(); // Put the message digest into a byte array
    for(int i = 0; i < p1PlainMDHash.length; i++){ // Prints out the Message digest
      if((i + 1) == p1PlainMDHash.length)
        System.out.print(p1PlainMDHash[i] + "\n");
      else
        System.out.print(p1PlainMDHash[i] + " ");
    }

    Cipher ecb = Cipher.getInstance("AES/ECB/ISO10126Padding"); // Initialized cipher
    SecretKeySpec ecbKey = new SecretKeySpec(p1PlainMDHash, "AES"); // Initialized key with p1PlainMDHash byte array
    ecb.init(Cipher.DECRYPT_MODE, ecbKey); // Set cipher to decrypt
    plainBMP = ecb.doFinal(cipherBMP);// Decrypted plainBMP initialized
    System.arraycopy(plainBMP, 0, p3PlainHeader, 0, 54); // Set header of plainBMP equal to p3PlainHeader for P3
    // END SOLUTION
    
    Files.write(Paths.get("P2_plain.bmp"), plainBMP);
  }

  static void P3() throws Exception {
    byte[] cipherBMP = Files.readAllBytes(Paths.get("P3_cipher.bmp"));
    
    // BEGIN SOLUTION
    byte[] modifiedBMP = cipherBMP;
    System.arraycopy(p3PlainHeader, 0, modifiedBMP, 0, 54); // Set header of modifiedBMP to p3PlainHeader
    // END SOLUTION
    
    Files.write(Paths.get("P3_cipher_modified.bmp"), modifiedBMP);
  }

  static void P4() throws Exception {
    byte[] iv = new byte[] { 0, 0, 0, 0, 
                             0, 0, 0, 0, 
                             0, 0, 0, 0, 
                             0, 0, 0, 0 };
    byte[] cipherBMP = Files.readAllBytes(Paths.get("P4_cipher.bmp"));
    
    // BEGIN SOLUTION

    // Found these 3 bytes through a brute force while loop which I have commented out below
    byte hour = 15;
    byte minute = 47;
    byte second = 16;

    /* while(true){
      if(hour == 24 && minute == 0 && second == 0)
        break;
      
      byte[] key = new byte[] {   65,   6,    39,   69, 
                                115,   hour, minute, second,
                                0,   0,    0,   0,
                                0,   0,    0,   0 }; 
      byte[] plainBMP = cipherBMP;
    
      Cipher cbc = Cipher.getInstance("AES/CBC/ISO10126Padding");
      SecretKeySpec cbcKey = new SecretKeySpec(key, "AES");
      IvParameterSpec cbcIV = new IvParameterSpec(iv);
      cbc.init(Cipher.DECRYPT_MODE, cbcKey, cbcIV);

      try{
        plainBMP = cbc.doFinal(cipherBMP);
      }

      catch(BadPaddingException e){
        if(minute == 59 && second == 59){
          second = 0;
          minute = 0;
          hour++;
          continue;
        }
        else if(second == 59)
        {
          second = 0;
          minute++;
          continue;
        }
        else{
          second++;
          continue;
        }
      }

      if(!(plainBMP[0] == 66 && plainBMP[1] == 77 && plainBMP[2] == -118 && plainBMP[3] == -60 && plainBMP[4] == 9 && plainBMP[5] == 0)){
        if (minute == 59 && second == 59) {
          second = 0;
          minute = 0;
          hour++;
          continue;
        } 
        else if (second == 59) {
          second = 0;
          minute++;
          continue;
        } 
        else {
          second++;
          continue;
        }
      }

      else{
        System.out.println("You found the right combo! Hour: " + hour + "; Minute: " + minute + "; Second: " + second);
        break;
      }

    } */

    byte[] key = new byte[] {   65,   6,    39,   69, 
                                115,   hour, minute, second,
                                0,   0,    0,   0,
                                0,   0,    0,   0 }; 
    byte[] plainBMP = cipherBMP;
    
    Cipher cbc = Cipher.getInstance("AES/CBC/ISO10126Padding"); // Initialized cipher
    SecretKeySpec cbcKey = new SecretKeySpec(key, "AES"); // Initialized key
    IvParameterSpec cbcIV = new IvParameterSpec(iv); // Initialized UV
    cbc.init(Cipher.DECRYPT_MODE, cbcKey, cbcIV); // Set cipher to decrypt

    plainBMP = cbc.doFinal(cipherBMP); // Decrypted byte[] initialized
    // END SOLUTION
    
    Files.write(Paths.get("P4_plain.bmp"), plainBMP);
  }

  public static void main(String [] args) {
    try {  
      P1();
      P2();
      P3();
      P4();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
