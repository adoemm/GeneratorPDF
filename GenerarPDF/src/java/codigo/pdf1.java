/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 *
 * @author emmanuel
 */
public class pdf1 {
    
    
    
    public static void main(String[] args) {
        new pdf1().creaPDF();
}
    
    
    public void creaPDF ()
    {
         try {
        OutputStream file = new FileOutputStream(new File(
                "LimitedAccess.pdf"));
        
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, file);
 
      
 
        document.open();
        document.add(new Paragraph("Limited Access File !!"));
        document.close();
        file.close();
 
    } catch (Exception e) {
        e.printStackTrace();
    }
    }


}
