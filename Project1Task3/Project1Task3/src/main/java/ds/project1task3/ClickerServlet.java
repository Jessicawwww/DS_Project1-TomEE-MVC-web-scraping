package ds.project1task3;

import java.io.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "helloServlet", urlPatterns = {"/submit", "/getResults"})
public class ClickerServlet extends HttpServlet {
    private ClickerModel clickerModel = null;
    @Override
    public void init() {
         clickerModel = new ClickerModel();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        System.out.println(request.getServletPath());
        //submit page
        if(request.getServletPath().equals("/submit")) {
            String answer = request.getParameter("question");
            clickerModel.updateResults(answer);
            request.setAttribute("answer",answer);
            RequestDispatcher view = request.getRequestDispatcher("index2.jsp");
            view.forward(request, response);
        }
        //getResults page
        if(request.getServletPath().equals("/getResults")) {
            HashMap<String, Integer> results = clickerModel.getResults();
            clickerModel.clear();
            request.setAttribute("result", results);
            RequestDispatcher view = request.getRequestDispatcher("result.jsp");
            view.forward(request, response);
        }
    }

}