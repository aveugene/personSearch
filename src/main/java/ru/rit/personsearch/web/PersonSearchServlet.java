package ru.rit.personsearch.web;

import ru.rit.personsearch.Config;
import ru.rit.personsearch.repository.PersonRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class PersonSearchServlet extends HttpServlet {

    private PersonRepository personRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        personRepository = Config.get().getRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action == null ? "" : action) {
            case "search":
                Map<String, String> requestParams = new TreeMap<>();
                String firstName = req.getParameter("first_name");
                String lastName = req.getParameter("last_name");
                String patronymic = req.getParameter("patronymic");
                requestParams.put("first_name", firstName);
                requestParams.put("last_name", lastName);
                requestParams.put("patronymic", patronymic);
                req.setAttribute("persons", personRepository.get(requestParams));
                req.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(req, resp);
                break;
            case "all":
                req.setAttribute("persons", personRepository.getAll());
                req.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(req, resp);
                break;
            default:
                req.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}