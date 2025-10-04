package ru.itis.servlet;

import ru.itis.models.User;
import ru.itis.repository.User.UserRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/searchDrivers")
public class SearchDriverServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/oris_dz",
                    "postgres",
                    "AAA"
            );

            userRepositoryJdbc = new UserRepositoryJdbcImpl(connection);

        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException(e);
        }
    }

    private UserRepositoryJdbcImpl userRepositoryJdbc;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("param");
        List<User> drivers;

        if ("salary".equals(param)) {
            drivers = userRepositoryJdbc.findBySalary();
        } else if ("car".equals(param)) {
            drivers = userRepositoryJdbc.findByCar();
        } else if ("country".equals(param)) {
            drivers = userRepositoryJdbc.findByCountry();
        } else {
            drivers = List.of();
        }

        req.setAttribute("users", drivers);
        req.getRequestDispatcher("/jsp/users.jsp").forward(req, resp);
    }
}
