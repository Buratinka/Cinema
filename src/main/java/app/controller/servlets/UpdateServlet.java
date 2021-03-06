package app.controller.servlets;

import app.controller.service.UserService;
import app.controller.service.impl.UserServiceImpl;
import app.controller.utils.ValidationData;
import app.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdateServlet extends HttpServlet
{
    private static ValidationData validationData;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/view/update.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String wrongData = "Empty some fields !";
        String oldMail = req.getParameter("mail");
        String oldPass = req.getParameter("pass");
        String newMail = req.getParameter("newMail");
        String newPass = req.getParameter("newPass");

        if(validationData.isValidParam(oldMail,oldPass,newMail,newPass)) {
        User oldUser = new User(oldMail,oldPass);
        User newUser = new User(newMail,newPass);

            List<User> users = new ArrayList<>();
            users.add(oldUser);
            users.add(newUser);

            UserService userService = new UserServiceImpl();
            userService.update(users);

            req.setAttribute("oldMail", oldMail);
            doGet(req, resp);
        }
        else
            {
                req.setAttribute("wrongData", wrongData);
                doGet(req, resp);
            }
    }
}
