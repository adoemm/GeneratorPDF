package codigo;

import static Test.testEncryption.OWNER;
import Test.testOutputStream;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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
    
    public static byte[] OWNER = "Cecytem".getBytes();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LinkedList listaGeneral, datosPlantel, datosAlumno, datosAcademico, modulosCarrera, autenticidad;
        listaGeneral = new LinkedList();
        datosPlantel = new LinkedList();
        datosAlumno = new LinkedList();
        datosAcademico = new LinkedList();
        modulosCarrera = new LinkedList();
        autenticidad = new LinkedList();
        datosPlantel.add("Aculco");
        datosPlantel.add("Aculco");
        datosPlantel.add("México");
        datosPlantel.add("15BBE5112F");
        datosAlumno.add("Adolfo Emmanuel");
        datosAlumno.add("Arriaga");
        datosAlumno.add("Vargas");
        datosAlumno.add("AIVA930214HMCRRD06");
        datosAlumno.add("13415082270240");
        datosAcademico.add("Administración de Empresas");
        datosAcademico.add("10280564");
        datosAcademico.add("20 de Agosto de 2007");
        datosAcademico.add("20 de Julio de 2010");
        datosAcademico.add("360");
        datosAcademico.add("9.5");
        datosAcademico.add("CTED51923");
        String modulo1 = "Aplicar el proceso enfermero en el cuidado del adulto mayoy y en los programas de salud a la comunida";
        String firmaD = "CECYTEMFirmaElectronica";
        String selloD = "CECYTEMSelloElectronico";
        autenticidad.add(firmaD);
        autenticidad.add(selloD);
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
        listaGeneral.add(autenticidad);
        String fileName = getServletContext().getRealPath("CE-" + datosAcademico.get(1));
        createPDF(listaGeneral);
        openPDF(request, response, fileName);
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

    private void openPDF(HttpServletRequest request, HttpServletResponse response, String file) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfReader reader = new PdfReader(file, OWNER);
        PdfStamper stamper = null;
        try {
            stamper = new PdfStamper(reader, baos);
            stamper.close();
        } catch (DocumentException e) {
        }

        // set some response headers
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setContentType("application/pdf");
        response.setContentLength(baos.size());

        OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
        os.close();

    }

    private void createPDF(LinkedList datos) throws IOException {

        //Clasificando Listas.
        LinkedList datosPlantel = (LinkedList) datos.get(0);
        LinkedList datosAlumno = (LinkedList) datos.get(1);
        LinkedList datosAcademico = (LinkedList) datos.get(2);
        LinkedList modulos = (LinkedList) datos.get(3);
        LinkedList autenticidad = (LinkedList) datos.get(4);
        
        //Archivo a Abrir.
        String fileName = getServletContext().getRealPath("CE-" + datosAcademico.get(1));
        OutputStream file = new FileOutputStream(fileName);
        //Creando el documento.
        Document documento = new Document(PageSize.LETTER, 38, 38, 38, 38);
        
        try {
            //Imagenes y Figuras.
            Image escudo = Image.getInstance("/home/v10x/NetBeansProjects/GeneratorPDF/GenerarPDF/web/resources/img/EscudoNacional.png");
            Image sepLogo = Image.getInstance("/home/v10x/NetBeansProjects/GeneratorPDF/GenerarPDF/web/resources/img/sep-logo.png");
            Image sisBachLogo = Image.getInstance("/home/v10x/NetBeansProjects/GeneratorPDF/GenerarPDF/web/resources/img/sistemaNacBach.png");

            //alineación y tamaño de imagenes
            escudo.scaleAbsolute(345, 345);
            escudo.setAbsolutePosition(145, 225);
            sepLogo.scaleAbsolute(133, 52);
            sepLogo.setAbsolutePosition(25, 719);
            sisBachLogo.scaleAbsolute(115, 35);
            sisBachLogo.setAbsolutePosition(430, 250);
            //Asignando Instancia de documento a PDF.   
            PdfWriter pdf = PdfWriter.getInstance(documento, file);
            pdf.setEncryption(null, OWNER, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
            documento.open();
            //Asignando Metadatos.
            documento.addTitle("CECYTEM-CE-" + datosAcademico.get(1));
            documento.addAuthor("Colegio de Estudios Científicos y Tecnológicos del Estado de México.");
            documento.addSubject("Certificado Electronico del Alumno.");
            //Agregando elementos al Documento.
            documento.add(escudo);
            documento.add(sepLogo);
            Paragraph encabezado = new Paragraph();
            encabezado.setAlignment(Element.ALIGN_CENTER);
            encabezado.setLeading(10);
            //fuentes
            BaseFont base = BaseFont.createFont("/home/v10x/NetBeansProjects/GeneratorPDF/GenerarPDF/web/resources/fonts/SoberanaTitular-Regular.otf", "Cp1252", BaseFont.EMBEDDED);
            BaseFont base1 = BaseFont.createFont("/home/v10x/NetBeansProjects/GeneratorPDF/GenerarPDF/web/resources/fonts/SoberanaSans-Regular.otf", "Cp1252", BaseFont.EMBEDDED);
            BaseFont base2 = BaseFont.createFont("/home/v10x/NetBeansProjects/GeneratorPDF/GenerarPDF/web/resources/fonts/SoberanaSans-Bold.otf", "Cp1252", BaseFont.EMBEDDED);
            BaseFont base3 = BaseFont.createFont("/home/v10x/NetBeansProjects/GeneratorPDF/GenerarPDF/web/resources/fonts/SoberanaSans-Light.otf", "Cp1252", BaseFont.EMBEDDED);

            Font fontencabezado1 = new Font(base, 10, Font.NORMAL);
            Font fontencabezado2 = new Font(base, 8, Font.NORMAL);
            Font fontencabezado3 = new Font(base, 6, Font.NORMAL);
            Font fontTextoPlantel = new Font(base1, (float) 9.5, Font.NORMAL);
            Font fontTextoAlumno = new Font(base1, 12, Font.NORMAL);
            Font fontTexCal1 = new Font(base2, 9, Font.NORMAL);
            Font fontTexCal2 = new Font(base3, 9, Font.NORMAL);
            Font fontTexCal3 = new Font(base3, 8, Font.NORMAL);
            Font fontTexCompeProfesional = new Font(base2, 9.5f, Font.NORMAL);
            Font fontTextoModulos = new Font(base1, 7, Font.NORMAL);
            Font fontTextoQR = new Font(base1, 7, Font.NORMAL);
            Font fontTextoCom = new Font(base2, 8, Font.NORMAL);
            //
            encabezado.add("\n");
            encabezado.add(new Phrase("Sistema Educativo Nacional\n ", fontencabezado1));
            encabezado.add(new Phrase("Subsecretaría de Educación Media Superior\n ", fontencabezado2));
            encabezado.add(new Phrase("Colegio de Estudios Científicos y Tecnológicos del Estado de México\n ", fontencabezado3));
            encabezado.add(new Phrase("Certificado de Terminación de Estudios\n ", fontencabezado3));
            documento.add(encabezado);
            Paragraph parrafoPlantel = new Paragraph();
            parrafoPlantel.setAlignment(Element.ALIGN_CENTER);
            parrafoPlantel.add("\n\n");
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
            parrafodatos.add(new Phrase("con Clave Única de Registro de Población " + datosAlumno.get(3).toString().toUpperCase() + " y número de control " + datosAlumno.get(4).toString()
                    + ", acreditó totalmente el plan de estudios del bachillerato tecnológico en la carrera de " + datosAcademico.get(0)
                    + ", clave " + datosAcademico.get(1) + ", en el periodo del " + datosAcademico.get(2).toString() + " al " + datosAcademico.get(3).toString()
                    + ", con " + datosAcademico.get(4) + " créditos, de un total de 360.\n\n\n", fontTextoPlantel));
            documento.add(parrafodatos);

            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidths(new float[]{7, 2, 1, 8});
            tabla.setWidthPercentage(60);

            PdfPCell celda1 = new PdfPCell(new Paragraph("PROMEDIO GENERAL            DE APROVECHAMIENTO:", fontTexCal1));
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
            compeProfesional.add(new Phrase(" Conforme a los módulos de formación profesional acreditados:\n\n", fontTexCal2));
            documento.add(compeProfesional);
            //Módulos
            PdfPTable tablaModulos = new PdfPTable(3);
            tablaModulos.setWidths(new float[]{5, 100, 5});
            tablaModulos.setHorizontalAlignment(0);
            tablaModulos.setWidthPercentage(100);
            PdfPCell celdaMC = new PdfPCell();

            PdfPCell celdaM1 = new PdfPCell(new Paragraph("Calif.\n", fontTexCal3));

            celdaMC.setColspan(2);
            celdaMC.setBorder(0);
            celdaM1.setBorder(0);
            celdaM1.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaM1.setVerticalAlignment(Element.ALIGN_CENTER);
            celdaM1.setPaddingBottom(10);
            tablaModulos.addCell(celdaMC);
            tablaModulos.addCell(celdaM1);
            LinkedList modulo;
            for (int i = 0; i < modulos.size(); i++) {
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
            documento.add(new Phrase("\n\n\n\n\n\n"));
            documento.add(new Phrase("         QR", fontTextoQR));
            BufferedImage qrbuffer = new QR().createQR(datosAlumno.get(0) + " " + datosAlumno.get(1) + " " + datosAlumno.get(2) + ", " + datosAcademico.get(0));
            Image qr = Image.getInstance(qrbuffer, null);
            qr.scaleAbsolute(124, 124);
            qr.setAbsolutePosition(50, 140);
            documento.add(qr);
            //tabla de autenticidad
            PdfPTable tablaAutenticidad = new PdfPTable(1);
            tablaAutenticidad.setWidthPercentage(77);
            tablaAutenticidad.setHorizontalAlignment(2);
            PdfPCell celdaFirma1 = new PdfPCell(new Phrase("Firma Electrónica\n", fontTexCal1));
            PdfPCell celdaFirma2 = new PdfPCell(new Phrase(autenticidad.get(0).toString(), fontTextoModulos));
            PdfPCell celdaSello1 = new PdfPCell(new Phrase("Sello Electrónico", fontTexCal1));
            PdfPCell celdaSello2 = new PdfPCell(new Phrase(autenticidad.get(1).toString(), fontTextoModulos));
            celdaFirma1.setPaddingBottom(5);
            celdaFirma2.setPaddingBottom(5);
            celdaSello1.setPaddingBottom(5);
            celdaFirma1.setBorder(0);
            celdaFirma2.setBorder(0);
            celdaSello1.setBorder(0);
            celdaSello2.setBorder(0);
            tablaAutenticidad.addCell(celdaFirma1);
            tablaAutenticidad.addCell(celdaFirma2);
            tablaAutenticidad.addCell(celdaSello1);
            tablaAutenticidad.addCell(celdaSello2);

            documento.add(tablaAutenticidad);

            documento.add(new Phrase("\n\n\nFOLIO   ", fontTexCal1));
            documento.add(new Phrase(datosAcademico.get(6).toString(), fontTextoQR));
            documento.add(new Phrase("\n\nEl presente documento se imprime en " + datosPlantel.get(1) + ", " + datosPlantel.get(2) + " a los fecha.", fontTextoQR));

            documento.newPage(); //se agrega nueva pagina
            Paragraph pFolio = new Paragraph();
            pFolio.setAlignment(2);
            pFolio.add(new Phrase("FOLIO   ", fontTexCal1));
            pFolio.add(new Phrase(datosAcademico.get(6).toString(), fontTextoQR));
            documento.add(pFolio);
            Paragraph perfil = new Paragraph();
            perfil.setAlignment(1);
            perfil.add(new Phrase("Perfil del egreso\n", fontTextoPlantel));
            documento.add(perfil);
            perfil = new Paragraph();
            perfil.setAlignment(Element.ALIGN_JUSTIFIED);

            perfil.add(new Phrase("En el Acuerdo número 653, por el que se establece el plan de estudios del bachillerato tecnológico, publicado en el Diario Oficial de la Federación "
                    + "el 4 de septiembre de 2012, se indica que se deben articular para la formación integral de los alumnos, mediante los elementos y actores del proceso "
                    + "educativo, las competencias genéricas, disciplinares y profesionales del perfil de egreso referido en el Acuerdo 444, que determina el Marco Curricular Común "
                    + "del Sistema Nacional de Bachillerato, publicado en el Diario Oficial de la Federación el 21 de octubre de 2008. El desarrollo de las competencias del alumno se "
                    + "justifica con la acreditación del plan de estudios.\n\n", fontTextoQR));
            perfil.add(new Phrase("Competencias genéricas. ", fontTextoCom));
            perfil.add(new Phrase("Conforme a los artículos 2, 3 y 4 del Acuerdo 444, constituyen el perfil del egresado de EMS, porque le permiten comprender el mundo e influir"
                    + "en él, lo capacitan para continuar aprendiendo de forma autónoma a lo largo de su vida, y para desarrollar relaciones armónicas con quienes lo rodean, así "
                    + "como para participar eficazmente en los ámbitos social, profesional y político.\n\n", fontTextoQR));
            perfil.add(new Phrase("Competencias disciplinares básicas. ", fontTextoCom));
            perfil.add(new Phrase("Conforme a los artículos, 5, 6 y 7 del Acuerdo 444, constituyen el perfil del egresado de EMS, porque expresan conocimientos, habilidades y actitudes que consideran"
                    + "los mínimos necesarios de cada campo disciplinar para que se desarrolle de manera eficaz en diferentes contextos y situaciónes a lo largo de la vida, y dan sustento a su formación "
                    + "en las competencias genéricas.\n\n", fontTextoQR));
            perfil.add(new Phrase("Competencias disciplinares extendidas. ", fontTextoCom));
            perfil.add(new Phrase("Conforme a los artículos 2, 3, 4 y 6 del Acuerdo 653, y al artículo 7 del Acuerdo 656, las asignaturas acreditadas del componente propedéutico, "
                    + "del campo disciplinar correspondiente, constituyen el perfil bivalente del bachillerato tecnológico.\n\n", fontTextoQR));
            documento.add(perfil);

            //campo disciplinar
            PdfPTable tablaDisciplinar = new PdfPTable(2);
            tablaDisciplinar.setWidths(new float[]{2.4f, 8});
            tablaDisciplinar.setWidthPercentage(95);
            tablaDisciplinar.setHorizontalAlignment(0);
            PdfPCell celdaCampo = new PdfPCell(new Phrase("Campo disciplinar\n", fontTextoPlantel));
            PdfPCell celdaAsignatura = new PdfPCell(new Phrase("Asignatura\n", fontTextoPlantel));
            celdaCampo.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaCampo.setVerticalAlignment(Element.ALIGN_CENTER);
            celdaAsignatura.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaAsignatura.setVerticalAlignment(Element.ALIGN_CENTER);
            celdaCampo.setBorder(0);
            celdaAsignatura.setBorder(0);
            celdaCampo.setPaddingBottom(10);
            tablaDisciplinar.addCell(celdaCampo);
            tablaDisciplinar.addCell(celdaAsignatura);
            PdfPCell celdaCD = new PdfPCell(new Phrase("Matemáticas", fontTextoModulos));
            celdaCD.setBorder(0);
            tablaDisciplinar.addCell(celdaCD);
            celdaCD = new PdfPCell(new Phrase("Cálculo integral, Probabilidad y Estadística, Temas de Física, Dibujo Técnico y Matemáticas aplicadas.", fontTextoModulos));
            celdaCD.setBorder(0);
            tablaDisciplinar.addCell(celdaCD);
            celdaCD = new PdfPCell(new Phrase("Ciencias experimentales", fontTextoModulos));
            celdaCD.setBorder(0);
            tablaDisciplinar.addCell(celdaCD);
            celdaCD = new PdfPCell(new Phrase("Introducción a la Bioquímica, Temas de Biología contemporánea y Temas de Ciencias de la Salud.", fontTextoModulos));
            celdaCD.setBorder(0);
            tablaDisciplinar.addCell(celdaCD);
            celdaCD = new PdfPCell(new Phrase("Ciencias sociales", fontTextoModulos));
            celdaCD.setBorder(0);
            tablaDisciplinar.addCell(celdaCD);
            celdaCD = new PdfPCell(new Phrase("Temas de Ciencias Sociales, Historia, Temas de Administración, Introducción a la Economía e Introducción al Derecho.", fontTextoModulos));
            celdaCD.setBorder(0);
            tablaDisciplinar.addCell(celdaCD);
            celdaCD = new PdfPCell(new Phrase("Humanidades", fontTextoModulos));
            celdaCD.setBorder(0);
            tablaDisciplinar.addCell(celdaCD);
            celdaCD = new PdfPCell(new Phrase("Literatura y Temas de Filosofía.", fontTextoModulos));
            celdaCD.setBorder(0);
            tablaDisciplinar.addCell(celdaCD);
            celdaCD = new PdfPCell(new Phrase("Comunicación", fontTextoModulos));
            celdaCD.setBorder(0);
            tablaDisciplinar.addCell(celdaCD);
            celdaCD = new PdfPCell(new Phrase("Inglés V.", fontTextoModulos));
            celdaCD.setBorder(0);
            celdaCD.setPaddingBottom(10);
            tablaDisciplinar.addCell(celdaCD);
            documento.add(tablaDisciplinar);
            perfil = new Paragraph();
            perfil.add(new Phrase("Competencias profesionales. ", fontTextoCom));
            perfil.add(new Phrase("Conforme a los artículos 2, 3, 4 y 6 del Acuerdo 653, los módulos de formación profesional acreditados constituyen el perfil específico del"
                    + "bachillerato tecnológico.", fontTextoQR));
            documento.add(perfil);

            Paragraph cicloCompetencia = new Paragraph();
            cicloCompetencia.add(new Phrase("El perfil de competencias aplica a partir del ciclo escolar CICLO de la RIEMS.\n", fontTextoQR));
            cicloCompetencia.add(new Phrase("Consulte el QR para el despliegue de las competencias.\n\n\n\n\n\n\n\n\n\n", fontTextoQR));
            cicloCompetencia.setAlignment(Element.ALIGN_RIGHT);
            documento.add(cicloCompetencia);
            documento.add(sisBachLogo);
            Paragraph footAdv = new Paragraph();
            footAdv.add(new Phrase("Con fundamento en lo dispuesto en el artículo 60 de la Ley General de Educación, los certificados de estudios expedidos por instituciones"
                    + " del Sistema Educativo Nacional tienen validez en la República Mexicana sin necesidad de trámites adicionales de autenticación o legalización, lo cual"
                    + " permite el transito del educando por el Sistema Educativo Nacional.\n\n", fontTextoQR));
            footAdv.add(new Phrase("La versión electronica del presente documento, su integridad y autoría se podrán comprobar en la página electrónica de CECYTEM, en"
                    + " la siguiente liga: http://deo.cecytem.mx . De igual manera, se podrá verificar el documento electrónico por medio del código QR.", fontTextoQR));
            documento.add(footAdv);

        } catch (DocumentException ex) {
            Logger.getLogger(pdf.class.getName()).log(Level.SEVERE, null, ex);
        }

        documento.close();
        file.close();

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
