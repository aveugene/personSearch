package ru.rit.personsearch.web;

import com.google.gson.Gson;
import org.slf4j.Logger;
import ru.rit.personsearch.Config;
import ru.rit.personsearch.repository.JdbcPersonRepository;
import ru.rit.personsearch.repository.PersonRepository;
import ru.rit.personsearch.to.PersonTo;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.slf4j.LoggerFactory.getLogger;

public class PersonSearchServlet extends HttpServlet {
    private static final Logger log = getLogger(JdbcPersonRepository.class);
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
                req.getParameterMap().forEach((paramName, paramValueArray) -> {
                    if (!paramValueArray[0].equals("") && !paramName.equals("action")) {
                        requestParams.put(paramName, paramValueArray[0]);
                    }
                });
                sendResponse(resp, personRepository.get(requestParams));
                break;
            case "all":
                sendResponse(resp, personRepository.getAll());
                break;
        }
    }

    private void sendResponse(HttpServletResponse resp, List<PersonTo> personTos) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(personTos);
        resp.setContentType("application/json; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
    }
}