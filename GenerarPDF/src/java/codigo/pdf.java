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
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.LinkedList;
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
        LinkedList listaGeneral, datosPlantel, datosAlumno, datosAcademico, modulosCarrera;
        listaGeneral = new LinkedList();
        datosPlantel = new LinkedList();
        datosAlumno = new LinkedList();
        datosAcademico = new LinkedList();
        modulosCarrera = new LinkedList();

        datosPlantel.add("Aculco");
        datosPlantel.add("Aculco");
        datosPlantel.add("México");
        datosPlantel.add("15BBE5112F");
        datosAlumno.add("Adolfo Emmanuel");
        datosAlumno.add("Arriaga");
        datosAlumno.add("Vargas");
        datosAlumno.add("AIVA930214HMCRRD06");
        datosAlumno.add("10280564");
        datosAcademico.add("Administración de Empresas");
        datosAcademico.add("10280564");
        datosAcademico.add("20 de Agosto de 2007");
        datosAcademico.add("20 de Julio de 2010");
        datosAcademico.add("360");
        datosAcademico.add("9.5");
        datosAcademico.add("CTED51923");
        String modulo1 = "APLICAR EL PROCESO ENFERMERO EN EL CUIDADO DEL ADULTO MAYOR Y EN LOS PROGRAMAS DE SALUD A LA COMUNIDAD";
        String modulo2 = "APLICA CUIDADOS DE BAJA Y MEDIANA COMPLEJIDAD PARA LA RECUPERACIÓN DE LA SALUD O LIMITAR EL DAÑO EN EL ADULTO, CON BASES ÉTICAS Y LEGALES";
        String modulo3 = "REALIZA ANÁLISIS FÍSICO-QUÍMICOS A MUESTRAS DE FÁRMACOS, COSMÉTICOS, ACEITES, GRASAS COMESTIBLES Y SUELOS CON BASE EN LAS NORMAS";
        for (int i = 0; i < 6; i++) {
            LinkedList modulo = new LinkedList();
            modulo.add("I.");
            modulo.add(modulo1);
            modulo.add("9.5");
            modulosCarrera.add(modulo);

        }
        listaGeneral.add(datosPlantel);
        listaGeneral.add(datosAlumno);
        listaGeneral.add(datosAcademico);
        listaGeneral.add(modulosCarrera);

        generarPDF(request, response, listaGeneral);
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

    private void generarPDF(HttpServletRequest request, HttpServletResponse response, LinkedList datos) throws ServletException, IOException {
        response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();

        try {
            try {
                LinkedList datosPlantel = (LinkedList) datos.get(0);
                LinkedList datosAlumno = (LinkedList) datos.get(1);
                LinkedList datosAcademico = (LinkedList) datos.get(2);
                LinkedList modulos = (LinkedList) datos.get(3);
                //tamaño de pixel 37.795275591
                Document documento = new Document(PageSize.LETTER, 38, 38, 38, 38);
                Image escudo = Image.getInstance("/home/v10x/NetBeansProjects/GeneratorPDF/GenerarPDF/web/resources/img/EscudoNacional.png");
                Image sepLogo = Image.getInstance("/home/v10x/NetBeansProjects/GeneratorPDF/GenerarPDF/web/resources/img/sep-logo.png");
                Image sisBachLogo = Image.getInstance("/home/v10x/NetBeansProjects/GeneratorPDF/GenerarPDF/web/resources/img/sistemaNacBach.png");

                //alineación y tamaño de imagenes
                escudo.scaleAbsolute(345, 345);
                escudo.setAbsolutePosition(145, 225);
                sepLogo.scaleAbsolute(163, 49);
                sepLogo.setAbsolutePosition(38, 700);
                sisBachLogo.scaleAbsolute(151, 45);
                sisBachLogo.setAbsolutePosition(400, 700);

                PdfWriter.getInstance(documento, out);
                documento.open();
                documento.add(escudo);
                documento.add(sepLogo);
                documento.add(sisBachLogo);

                Paragraph encabezado = new Paragraph();
                encabezado.setAlignment(Element.ALIGN_CENTER);
                //fuentes
                BaseFont base = BaseFont.createFont("/home/v10x/NetBeansProjects/GeneratorPDF/GenerarPDF/web/resources/fonts/SoberanaTitular-Regular.otf", "Cp1252", BaseFont.EMBEDDED);
                BaseFont base1 = BaseFont.createFont("/home/v10x/NetBeansProjects/GeneratorPDF/GenerarPDF/web/resources/fonts/SoberanaSans-Regular.otf", "Cp1252", BaseFont.EMBEDDED);
                BaseFont base2 = BaseFont.createFont("/home/v10x/NetBeansProjects/GeneratorPDF/GenerarPDF/web/resources/fonts/SoberanaSans-Bold.otf", "Cp1252", BaseFont.EMBEDDED);
                BaseFont base3 = BaseFont.createFont("/home/v10x/NetBeansProjects/GeneratorPDF/GenerarPDF/web/resources/fonts/SoberanaSans-Light.otf", "Cp1252", BaseFont.EMBEDDED);

                Font fontencabezado1 = new Font(base, 10, Font.NORMAL);
                Font fontencabezado2 = new Font(base, 8, Font.NORMAL);
                Font fontencabezado3 = new Font(base, 6, Font.NORMAL);
                Font fontTextoPlantel = new Font(base1, (float) 8.5, Font.NORMAL);
                Font fontTextoAlumno = new Font(base1, 12, Font.NORMAL);
                Font fontTexCal1 = new Font(base2, 8, Font.NORMAL);
                Font fontTexCal2 = new Font(base3, 8, Font.NORMAL);
                Font fontTexCompeProfesional = new Font(base2, 8.5f, Font.NORMAL);
                Font fontTextoModulos = new Font(base1, 7, Font.NORMAL);
                //
                encabezado.add("\n\n\n\n");
                encabezado.add(new Phrase("Sistema Educativo Nacional\n ", fontencabezado1));
                encabezado.add(new Phrase("Subsecretaría de Educación Media Superior\n ", fontencabezado2));
                encabezado.add(new Phrase("Colegio de Estudios Científicos y Tecnológicos del Estado de México\n ", fontencabezado3));
                encabezado.add(new Phrase("Certificado de Terminación de Estudios\n ", fontencabezado3));
                documento.add(encabezado);

                Paragraph parrafoPlantel = new Paragraph();
                parrafoPlantel.setAlignment(Element.ALIGN_CENTER);

                parrafoPlantel.add(new Phrase("El Colegio de Estudios Científicos y Tecnológicos " + datosPlantel.get(0) + ", de " + datosPlantel.get(1)
                        + ", " + datosPlantel.get(2) + ",\n", fontTextoPlantel));
                parrafoPlantel.add(new Phrase("con Clave de Centro de Trabajo " + datosPlantel.get(3) + ", certifica que\n\n", fontTextoPlantel));
                documento.add(parrafoPlantel);

                Paragraph parrafoAlumo = new Paragraph();
                parrafoAlumo.setAlignment(Element.ALIGN_CENTER);

                parrafoAlumo.add(new Phrase(datosAlumno.get(0).toString().toUpperCase() + " " + datosAlumno.get(1).toString().toUpperCase() + " " + datosAlumno.get(2).toString().toUpperCase() + "\n\n", fontTextoAlumno));
                documento.add(parrafoAlumo);

                Paragraph parrafodatos = new Paragraph();
                parrafodatos.setAlignment(Element.ALIGN_JUSTIFIED);
//                parrafodatos.add(new Phrase("con Clave Única de Registro de Población AAAA999999AAAAAA99 y número de control 00000000000000, acreditó\n", fontTextoPlantel));
//                parrafodatos.add(new Phrase("totalmente el plan de estudios del bachillerato tecnológico en la carrera de Técnico en Programación, clave 000000000-00,\n", fontTextoPlantel));
//                parrafodatos.add(new Phrase("en el periodo del 01 de Enero de 0000 al 31 de Diciembre de 0000, con 360 créditos, de un total de 360.\n", fontTextoPlantel));
//                parrafodatos.add(new Phrase("con Clave de Centro de Trabajo CLAVE, certifica que\n", fontTextoPlantel));
                parrafodatos.add(new Phrase("con Clave Única de Registro de Población " + datosAlumno.get(3).toString().toUpperCase() + " y número de control " + datosAlumno.get(4).toString()
                        + ", acreditó totalmente el plan de estudios del bachillerato tecnológico en la carrera de " + datosAcademico.get(0)
                        + ", clave " + datosAcademico.get(1) + ", en el periodo del " + datosAcademico.get(2).toString() + " al " + datosAcademico.get(3).toString()
                        + ", con " + datosAcademico.get(4) + " créditos, de un total de 360.\n\n\n", fontTextoPlantel));
                documento.add(parrafodatos);

                PdfPTable tabla = new PdfPTable(4);
                tabla.setWidths(new float[]{7, 2, 1, 8});
                tabla.setWidthPercentage(55);

                PdfPCell celda1 = new PdfPCell(new Paragraph("PROMEDIO GENERAL            DE APROVECHAMIENTO", fontTexCal1));
                PdfPCell celda2 = new PdfPCell(new Paragraph(datosAcademico.get(5).toString(), fontTexCal2));
                PdfPCell celda3 = new PdfPCell(new Paragraph("CERO PUNTO CERO", fontTexCal2));
                PdfPCell celda4 = new PdfPCell(new Paragraph(" ", fontTexCal2));
                celda1.setBorder(0);
                celda1.setRightIndent(7);
                celda4.setBorder(0);
                celda1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda3.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda3.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tabla.addCell(celda1);
                tabla.addCell(celda2);
                tabla.addCell(celda4);
                tabla.addCell(celda3);
                documento.add(tabla);

                //competencias profesionales
                Paragraph compeProfesional = new Paragraph();
                compeProfesional.setAlignment(Element.ALIGN_JUSTIFIED);

                compeProfesional.add(new Phrase("\nCompetencias profesionales extendidas.", fontTexCompeProfesional));
                compeProfesional.add(new Phrase(" Conforme a los módulos de formación profesional acreditados.\n\n", fontTexCal2));
                documento.add(compeProfesional);
                //Módulos
                PdfPTable tablaModulos = new PdfPTable(3);
                tablaModulos.setWidths(new float[]{1, 10, 1});
                PdfPCell celdaM1 = new PdfPCell(new Paragraph("Calificación\n", fontTexCal2));

                celdaM1.setColspan(3);
                celdaM1.setBorder(0);
                celdaM1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                celdaM1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celdaM1.setPaddingBottom(10);
                tablaModulos.addCell(celdaM1);
                LinkedList modulo;
                for (int i = 0; i < 6; i++) {
                    modulo = (LinkedList) modulos.get(i);
                    PdfPCell celdaM2 = new PdfPCell(new Paragraph(modulo.get(0).toString(), fontTextoModulos));
                    PdfPCell celdaM3 = new PdfPCell(new Paragraph(modulo.get(1).toString(), fontTextoModulos));
                    PdfPCell celdaM4 = new PdfPCell(new Paragraph(modulo.get(2).toString(), fontTextoModulos));
                    celdaM2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celdaM2.setVerticalAlignment(Element.ALIGN_CENTER);
                    celdaM3.setHorizontalAlignment(Element.ALIGN_LEFT);
                    celdaM3.setVerticalAlignment(Element.ALIGN_CENTER);
                    celdaM4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celdaM4.setVerticalAlignment(Element.ALIGN_CENTER);
                    celdaM2.setBorder(0);
                    celdaM3.setBorder(0);
                    celdaM4.setBorder(0);
                    tablaModulos.addCell(celdaM2);
                    tablaModulos.addCell(celdaM3);
                    tablaModulos.addCell(celdaM4);

                }

                documento.add(tablaModulos);

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
