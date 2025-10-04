package ru.itis.servlet;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
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
import java.util.Scanner;

@NoArgsConstructor
@AllArgsConstructor
@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private UserRepositoryJdbcImpl userRepositoryJdbc;

    @Override
    public void init() throws ServletException {
        Scanner scan  = new Scanner(System.in);
        System.out.print("Введите количество пользователей: ");
        int usersCount = scan.nextInt();
        getServletContext().setAttribute("usersCount", usersCount);

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int usersCount = (int) getServletContext().getAttribute("usersCount");
        request.setAttribute("usersCount", usersCount);
        request.getRequestDispatcher("/jsp/addUsers.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int usersCount = (int) getServletContext().getAttribute("usersCount");

        for (int i = 1; i <= usersCount; i++) {
            String firstName = request.getParameter("firstName" + i);
            String lastName = request.getParameter("lastName" + i);
            int age = Integer.parseInt(request.getParameter("age" + i));

            userRepositoryJdbc.saveUserByFullInfo(firstName, lastName, age);
        }

    }

}