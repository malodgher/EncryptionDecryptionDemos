public class P1_MyAns {
  private static final String cipherText = "GWN GANBDJAN HWNDG ZD EJAZNK WNAN:\n" +
    "DBZI QARL DFNINGRO ZDIBOK GR GWN NBDG-DRJGWNBDG.\n" +
    "BQGNA GPR KBVD, VRJ PZII QZOK B KNDNAGNK ZDIBOK.\n" +
    "HIZLE GR GWN WZTWNDG URZOG RQ GWN ZDIBOK BOK IRRF QRA B IBATN HIZQQ GR GWN ORAGW.\n" +
    "PBIF B WJOKANK VBAKD GRPBAKD GWN HIZQQ BOK DGRU BG GWN GBII GANN.\n" +
    "PBIF GNO QNNG GR GWN NBDG, BOK VRJ PZII QZOK B ARHF PZGW BO S UBZOGNK RO ZG.\n" +
    "GWN GANBDJAN ZD EJAZNK RON QRRG ENIRP GWN ARHF.\n";
  
  static final int alphaIndex = (int) 'A';
  static final int alphaLength = (int) 'Z' + 1 - (int) 'A';
  
  // needs to be filled out automatically based on the ciphertext
  static int[] frequency = new int[alphaLength];
  
  private static int decrypt(int index) {
    /* 
     * Figured out that the k1 and k2 were 3 and 1 respectively by finding the lone letter B and translating it to
     * the plaintext character A to find k2. Then found the most common word, GWN, and translated each letter to form THE,
     * finding k1. Then found out the inverse of k1 equals 9 by the Euclidean Algorithm
     */

    index = (index-1)*9; // (c-k2)*k1
    if(index < 0) // since -9%26 = -9 in Java and not 17, this line checks to see if index is less than 0...
      index += 26; // and adds 26 to it so that the modulus is a positive number between 0 and 25
    index = index%26; // ((c-k2)*k1) % 26
    return index;
  }
  
  public static void main(String [] args) {
    for (char cipherChar : cipherText.toCharArray())
      if (Character.isLetter(cipherChar)) { // only letters are encrypted, punctuation marks and whitespace are not
        // following line converts letters to numbers between 0 and 25
        int cipher = (int) cipherChar - alphaIndex;
        frequency[cipher]++; // This line adds to the element of the corresponding ciphertext letter.
        //This will show the frequency of each letter in the ciphertext

        int plain = decrypt(cipher);
        // following line coverts numbers between 0 and 25 to letters
        char plainChar = (char) (plain + alphaIndex);
        System.out.print(plainChar);
      }
      else
        System.out.print(cipherChar);
    
    System.out.println("\nTwo pairs of plaintext letters and ciphertext letters (P,C) are: (A,B) & (E,N)");
  }  
}
