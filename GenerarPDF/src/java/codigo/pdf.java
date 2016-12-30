package codigo;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author emmanuel
 */
@WebServlet(name = "pdf", urlPatterns = {"/pdf"})
public class pdf extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        generarPDF(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void generarPDF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();

        try {
            try {

                //tamaño de pixel 37.795275591
                
                Document documento = new Document(PageSize.LETTER, 38, 38, 38, 38);
                Image escudo = Image.getInstance("/home/v10x/NetBeansProjects/GeneratorPDF/GenerarPDF/web/resources/img/EscudoNacional.png");
                Image sepLogo = Image.getInstance("/home/v10x/NetBeansProjects/GeneratorPDF/GenerarPDF/web/resources/img/sep-logo.png");
                Image sisBachLogo = Image.getInstance("/home/v10x/NetBeansProjects/GeneratorPDF/GenerarPDF/web/resources/img/sistemaNacBach.png");
                
                
                escudo.scaleAbsolute(345,345);
                escudo.setAbsolutePosition(145, 225);
                sepLogo.scaleAbsolute(163, 49);
                sepLogo.setAbsolutePosition(38,700);
                sisBachLogo.scaleAbsolute(151,45);
                sisBachLogo.setAbsolutePosition(400, 700);
                        

                PdfWriter.getInstance(documento, out);
                documento.open();
                documento.add(escudo);
                documento.add(sepLogo);
                documento.add(sisBachLogo);
                
                Paragraph encabezado = new Paragraph();
                
                //fuentes
                Font fontencabezado1 = new Font();
                BaseFont base= BaseFont.createFont("");
                fontencabezado1.setSize(10);
                fontencabezado1.setFamily("SOBERANA");
                //
                
                
                
                
                
                encabezado.add(new Phrase("Sistema Educativo Nacional", fontencabezado1));
                documento.add(encabezado);
//                Paragraph par1 = new Paragraph();
//                Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);
//                par1.add(new Phrase("Certificado Electronico CECYTEM", fontTitulo));
//                par1.setAlignment(Element.ALIGN_CENTER);
//                par1.add(new Phrase(Chunk.NEWLINE));
//                par1.add(new Phrase(Chunk.NEWLINE));
//                
//                //documento.add(par1);
//               
//                for (int i = 0; i < 100; i++) {
//                    //documento.add(new Phrase("Text "));
//                }
//              
//                for (int i = 0; i < 150; i++) {
//                    //documento.add(new Phrase("Text "));
//                   
//                    }
//                
//                  
//                for (int i = 0; i < 50; i++) {
//                    //documento.add(new Phrase("Text "));
//                }
               
              

                documento.newPage(); //se agrega nueva pagina
                Paragraph par2 = new Paragraph();
                Font fontDescription = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.NORMAL, BaseColor.BLACK);
                par2.add(new Phrase("Este es un p Entradas del certificado Electronico:\n"
                        + "	-Datos del Alumno\n"
                        + "	-Plantel\n"
                        + "	-Datos de la carrera.", fontDescription));
                par2.setAlignment(Element.ALIGN_JUSTIFIED);
                par2.add(new Phrase(Chunk.NEWLINE));
                par2.add(new Phrase(Chunk.NEWLINE));
                documento.add(par2);

                documento.close();

            } catch (Exception ex) {
                ex.getMessage();
            }
        } finally {
            out.close();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
