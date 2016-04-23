package ru.javacourse.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// сервлеты создаются в единичном экземпляре!!
public class SourceServlet extends HttpServlet {


   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String  password = request.getParameter("password");

        // Выглядит как два запроса, видимых для пользователя
        // http://localhost:8080/app3/SourceServlet?param=test&redirect=yes (Status 302)
        // —> http://localhost:8080/app3/TargetServlet (Status 200)
        // при этом параметры реквеста не передаются TargetServlet'у
        // НО если мы хотим передать параметры, то можем конкатенировать параметры из request
        // response.sendRedirect("TargetServlet?param=test&redirect=yes");

        if (request.getParameter("redirect") != null) {
            response.sendRedirect("TargetServlet");
        }
        // Для клиента выглядит как один запрос
        // http://localhost:8080/app3/SourceServlet?param=test
        // параметр передаётся TargetServlet'у
        // пользователь не видит, что мы работаем с другим урлом
        else {
            getServletContext().getRequestDispatcher("/TargetServlet").forward(request, response);
        }

    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

}
