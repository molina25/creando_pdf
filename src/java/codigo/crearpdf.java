/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( urlPatterns = {"/crearPDF"})
public class crearpdf extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();
        try {
            
            try{
            Connection conn = null;
            Statement st = null;
            ResultSet rs = null;
            
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_inventario","root" ,"" );
            st = (Statement) conn.createStatement();
            rs = st.executeQuery("SELECT * FROM tb_categoria");
            
            if(conn!=null){
             try{
            Document documento = new Document();
            PdfWriter.getInstance(documento, out);
            
            documento.open();
            
            /*Image imagenes = Image.getInstance("C:\\Users\\Estefany\\Documents\\NetBeansProjects\\CrearPDF\\itca.png");
            imagenes.setAlignment(Element.ALIGN_LEFT);
            imagenes.scaleToFit(100, 100);
            documento.add(imagenes);*/
            
            
            Paragraph par1 = new Paragraph();
            Font fontitulo = new Font(Font.FontFamily.TIMES_ROMAN,16,Font.BOLD,BaseColor.BLUE);
            par1.add( new Phrase("Creación de Archivos PDF",fontitulo));
            par1.setAlignment(Element.ALIGN_CENTER);
            par1.add(new Phrase(Chunk.NEWLINE));
            par1.add(new Phrase(Chunk.NEWLINE));
            documento.add(par1);
            
             Paragraph par2 = new Paragraph();
            Font fondescrip = new Font(Font.FontFamily.TIMES_ROMAN,12,Font.NORMAL,BaseColor.BLACK);
            par2.add( new Phrase("Creando un reporte PDF a partir de una consulta de la base de datos bd_Inventario",fondescrip));
            par2.setAlignment(Element.ALIGN_JUSTIFIED);
            par2.add(new Phrase(Chunk.NEWLINE));
            par2.add(new Phrase(Chunk.NEWLINE));
            documento.add(par2);
            
        PdfPTable tabla = new PdfPTable(3);
        PdfPCell celda1 = new PdfPCell(new Paragraph("Id Categoria",FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLACK)));
        PdfPCell celda2 = new PdfPCell(new Paragraph("Nombre Categoria",FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLACK)));
        PdfPCell celda3 = new PdfPCell(new Paragraph("Estado Categoria",FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLACK)));
        
        tabla.addCell(celda1);
        tabla.addCell(celda2);
        tabla.addCell(celda3);
        
        while(rs.next()){
        tabla.addCell(rs.getString(1));
         tabla.addCell(rs.getString(2));
          tabla.addCell(rs.getString(3));
        }
        
        documento.add(tabla);
        
            documento.close();
            
            }catch(Exception ex){ex.getMessage();}
            }
            
            }catch(Exception ex ){
            ex.getMessage();
            }
          
           
            
        }finally{
        
        out.close();
                }
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

