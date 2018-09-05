import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.BadPaddingException;
import java.security.MessageDigest;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
 
public class ImageDecrypt_SkellCode {    
  static void P1() throws Exception {
    byte[] iv = new byte[] { 0, 0, 0, 0, 
                             0, 0, 0, 0, 
                             0, 0, 0, 0, 
                             0, 0, 0, 0 };
    byte[] key = Files.readAllBytes(Paths.get("P1_key"));
    byte[] cipherText = Files.readAllBytes(Paths.get("P1_cipher.txt"));
    
    // BEGIN SOLUTION
    byte[] plainText = cipherText;    
    // END SOLUTION
    
    System.out.println(new String(plainText, StandardCharsets.UTF_8));
  }

  static void P2() throws Exception {
    byte[] cipherBMP = Files.readAllBytes(Paths.get("P2_cipher.bmp"));
    
    // BEGIN SOLUTION
    byte[] P1_plaintext = "plaintext from the first problem goes here".getBytes(StandardCharsets.UTF_8);
    byte[] plainBMP = cipherBMP;    
    // END SOLUTION
    
    Files.write(Paths.get("P2_plain.bmp"), plainBMP);
  }

  static void P3() throws Exception {
    byte[] cipherBMP = Files.readAllBytes(Paths.get("P3_cipher.bmp"));
    
    // BEGIN SOLUTION
    byte[] modifiedBMP = cipherBMP;    
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
    byte hour = 0;
    byte minute = 0;
    byte second = 0;
    byte[] key = new byte[] {   0,   0,    0,   0, 
                                0,   hour, minute, second,
                                0,   0,    0,   0,
                                0,   0,    0,   0 }; 
    byte[] plainBMP = cipherBMP;    
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
