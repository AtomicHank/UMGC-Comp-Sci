// import the required packages
import java.io.*;
import java.security.*;
import javax.crypto.*;
import java.util.*;
import javax.swing.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import sun.misc;

// Class for Encryption and Decryption of data
public class Filedirectoryprcssng
{
   // Declare the required variables
   Cipher enecipher;
   Cipher endcipher;
  
   // 8-byte
   byte[] ensalt = {
   (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
   (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03
   };
  
   // Iteration count
   int itrcnt = 19;
  
   // Constructor
   public Filedirectoryprcssng() {}
  
   // Method to encrypt the data
   public String EDencrypt(String ensecretKey, String enplainText)
   throws InvalidKeySpecException,UnsupportedEncodingException,InvalidKeyException,
   NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
   NoSuchAlgorithmException,BadPaddingException
   {
       //Code for encryption and decryption key generation
       KeySpec EDkeySpec = new PBEKeySpec(ensecretKey.toCharArray(), ensalt, itrcnt);
       SecretKey EDkey = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(EDkeySpec);
      
       // Parameter to the ciphers
       AlgorithmParameterSpec EDparamSpec = new PBEParameterSpec(ensalt, itrcnt);
      
       //Encryption process
       enecipher = Cipher.getInstance(EDkey.getAlgorithm());
       enecipher.init(Cipher.ENCRYPT_MODE, EDkey, EDparamSpec);
       String EDcharSet="UTF-8";
       byte[] EDin = enplainText.getBytes(EDcharSet);
       byte[] EDout = enecipher.doFinal(EDin);
       String encStr=new sun.misc.BASE64Encoder().encode(EDout);
       return encStr;
   }
   // Method to decrypt the data
   public String decrypt(String ensecretKey, String EencryptedText)
   throws NoSuchAlgorithmException,InvalidKeyException,UnsupportedEncodingException,
   NoSuchPaddingException, BadPaddingException,InvalidKeySpecException, InvalidAlgorithmParameterException,
   IllegalBlockSizeException, IOException
   {
       //Code for encryption and decryption key generation
       KeySpec EDkeySpec = new PBEKeySpec(ensecretKey.toCharArray(), ensalt, itrcnt);
       SecretKey EDkey = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(EDkeySpec);
      
       // Parameter preparation for the ciphers
       AlgorithmParameterSpec EDparamSpec = new PBEParameterSpec(ensalt, itrcnt);
      
       // Code for decryption process
       endcipher=Cipher.getInstance(EDkey.getAlgorithm());
       endcipher.init(Cipher.DECRYPT_MODE, EDkey,EDparamSpec);
       byte[] EDenc = new sun.misc.BASE64Decoder().decodeBuffer(EencryptedText);
       byte[] EDutf8 = endcipher.doFinal(EDenc);
       String EDcharSet="UTF-8";
       String EDplainStr = new String(EDutf8, EDcharSet);
       return EDplainStr;
   }

   // Method to get the list of sub directories
   public static List<File> EDgetSubdirs(File EDfile)
   {
       List<File> subdirs = Arrays.asList(EDfile.listFiles(new FileFilter()
       {
           public boolean accept(File f)
           {
               return f.isDirectory();
           }
       }));
       subdirs = new ArrayList<File>(subdirs);

       List<File> deepSubdirs = new ArrayList<File>();
       for(File subdir : subdirs)
       {
           deepSubdirs.addAll(EDgetSubdirs(subdir));
       }
       subdirs.addAll(deepSubdirs);
       return subdirs;
   }
  
   // Main method
   public static void main(String[] args) throws Exception
   {
       BufferedReader object = new BufferedReader(new InputStreamReader(System.in));
       Filedirectoryprcssng cryptoUtil=new Filedirectoryprcssng();
       String EDkey="ezeon8547";
       String EDplain="Important Message read it before you proceed";
       String EDenc=cryptoUtil.EDencrypt(EDkey, EDplain);

       try
       {
       System.out.println("Choose option: ");
       File EDfile = new File("C:\\");
       while(true)
       {
       System.out.println("0 Exit \n"
       + "1 Select directory \n"
       + "2 List directory content (first level)\n"
       + "3 List directory content (all levels) \n"
       + "4 Delete file \n"
       + "5 Display file \n"
       + "6 Encrypt file (XOR with password)\n"
       + "7 Decrypt file (XOR with password)\n");
       System.out.println("\nChoose next option: ");
       int opt= Integer.parseInt(object.readLine());
       switch(opt)
       {
           case 0:
                   System.exit(0);
                   break;

           // Case to tests the file is a directory or not.
           case 1:
               System.out.println(EDfile.isDirectory());
               continue;

           // Case to return an array of strings for the files and directories
           case 2:
               File EDdir1 = new File( "C:\\" );
               String[] EDfile1 = EDdir1.list();
               System.out.println( "Files in this directory are:" );
               for ( String tknafile : EDfile1 )
               {
                   System.out.println( tknafile );
               }
               continue;
          
           //   Case for list the content of directory in all level
           case 3:
               File EDdir = new File( "C:\\" );
               File[] EDselectedFiles = EDdir.listFiles();
               for (File EDselectedFile : EDselectedFiles)
               {
                   System.out.println(EDselectedFile.getAbsolutePath());
               }
               continue;
              
           // Case to delete all the file files in a specific directory
           case 4:
               File EDf1 = new File("C:\\Test.txt");
               System.out.println(EDf1.delete());
               continue;

           // Case to test whether the application displays file
           case 5:
               try
               {
                   Scanner scn = new Scanner(new File("C:\\Test.txt"));
                   while (scn.hasNext())
                   {
                       System.out.println(scn.next());
                   }
                   scn.close();
               } catch (FileNotFoundException exc)
               {
                   exc.printStackTrace();
               }
               continue;

           // Case to Encrypt the file
           case 6:             
               System.out.println("Original text: "+EDplain);
               System.out.println("Encrypted text: "+EDenc);
               continue;
          
           // Case to Decrypt the file          
           case 7:
               String EDplainAfter=cryptoUtil.decrypt(EDkey, EDenc);
               System.out.println("Original content after decryption: "+EDplainAfter);
               continue;  
                  
       }
       }
       } catch(IOException ENexcep){
       } catch (NoSuchPaddingException ENexcep) {
       } catch (InvalidAlgorithmParameterException ENexcep) {
       } catch (NumberFormatException ENexcep) {
       } catch (InvalidKeyException ENexcep) {
       } catch (NoSuchAlgorithmException ENexcep) {
       } catch (BadPaddingException ENexcep) {
       } catch (InvalidKeySpecException ENexcep) {
       } catch (IllegalBlockSizeException ENexcep) {
       }
   }
}