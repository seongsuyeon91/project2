package com.study.jsp.fileupdown;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/file_upload")
@MultipartConfig
public class FileUpload extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
        String jdbcUser = "scott";
        String jdbcPassword = "tiger";

        Connection con = null;
        PreparedStatement pstmt = null;
        String query;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

            String fName = request.getParameter("bName");
            String fTitle = request.getParameter("bTitle");
            String fContent = request.getParameter("bContent");
            Part filePart = request.getPart("files");
            String fileName = filePart.getSubmittedFileName();
            InputStream fileInputStream = filePart.getInputStream();
            
            if(fileName != null || fileName != ""){
            query = "insert into filesave (cnum, fname, contents) values (filesave_seq.nextval, ?, ?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, fileName);
            pstmt.setBinaryStream(2, fileInputStream);
            pstmt.executeUpdate();

            }
            query = "insert into archive "
               + " (bId, bName, bTitle, bContent, filename, bHit, bGroup, bStep, bIndent) "
               + " values "
               + " (archive_seq.nextval, ?, ?, ?, ?, 0, archive_seq.currval, 0, 0 ) ";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, fName);
            pstmt.setString(2, fTitle);
            pstmt.setString(3, fContent);
            pstmt.setString(4, fileName);
            pstmt.executeUpdate();

            response.sendRedirect("archive_list.do");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
             if(pstmt != null) pstmt.close();
             if(con != null) con.close();
             } catch(Exception e2) {
                e2.printStackTrace();
             }
             
        } 
    }
}