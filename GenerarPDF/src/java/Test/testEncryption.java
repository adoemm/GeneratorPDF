/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.Certificate;

/**
 *
 * @author emmanuel
 */
public class testEncryption {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DocumentException, FileNotFoundException {
        String filename = "/home/v10x/Desktop/file.pdf";
        new testEncryption().createPDF(filename);
    }

    public static byte[] USER = "Hello".getBytes();
    public static byte[] OWNER = "Cecytem".getBytes();

    public void createPDF(String filename) throws DocumentException, FileNotFoundException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        writer.setEncryption(null, OWNER, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
        document.open();
        document.add(new Paragraph("Hellos word"));
        document.close();
    }

}
