/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emmanuel
 */
public class testOutputStream {
    
    public static void main(String[] args) throws IOException, DocumentException {
         new testOutputStream().writeInOutPutStream();
         
        
    }
 

  

     
  
    
    public void writeInOutPutStream()
    {
        String content="Hello";
        byte [] bytes= content.getBytes();
        try {
            FileOutputStream file= new FileOutputStream("test.txt");
            OutputStream out= file;
            out.write(bytes);
            out.write(bytes[0]);
           
            out.write(bytes, 1, 4);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(testOutputStream.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(testOutputStream.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        }
    
}
