public class P2_MyAns {
  private static byte[] cipherText = new byte[] { -119, 119, 48, -18, 29, 23, -85, 81, 22, -85, 70, 74, -66, 90, 20, -15, 66, 5, -67, 65, 19, -95, 64, 0, -13, 83, 5, -68, 86, 18, -81, 64, 15, -18, 122, 48, -102, 98, 75, -1, 28, 85, -60 };
  
  public static void main(String [] args) {
    /*
     * Figured out that the keys were such by brute-forcing. I knew that the plaintext was an HTTP GET request, 
     * so I XORed the character 'G' with the first byte in the cipher text, like so: System.out.print((byte) (cipherText[0] ^ (byte)('G')));
     * I repeated this process two more times with the letters 'E' and 'T'. When I XORed the ' ' character with the
     * cipherText[3] byte, I had got the same key as the first byte, meaning that I had found all of the partial keys
     * in the entire key array.
     */

    byte[] key = { -50, 50, 100 };

    // These lines are just to show how I brute-forced my way to finding the keys
    System.out.println((byte) (cipherText[0] ^ (byte)('G')));
    System.out.println((byte) (cipherText[1] ^ (byte)('E')));
    System.out.println((byte) (cipherText[2] ^ (byte)('T')));
    System.out.println(((byte) (cipherText[3] ^ (byte)(' '))) + "\n");


    // Loop that decrypts cipherText    
    for (int i = 0; i < cipherText.length; i++) 
      System.out.print((char) (cipherText[i] ^ key[i % key.length]));
  }  
}
