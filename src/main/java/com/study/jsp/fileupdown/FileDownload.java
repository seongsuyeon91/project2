package com.study.jsp.fileupdown;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class FileDownload extends HttpServlet  {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
        String jdbcUser = "scott";
        String jdbcPassword = "tiger";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

            String fileName = request.getParameter("fname");
            String query = "select contents from filesave where fname = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, fileName);
            resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                inputStream = resultSet.getBinaryStream("contents");
                if (inputStream != null) {
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
                    response.setContentType("application/octet-stream");

                    outputStream = response.getOutputStream();
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (pstmt != null) pstmt.close();
                if (inputStream != null) inputStream.close();
                if (outputStream != null) outputStream.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}