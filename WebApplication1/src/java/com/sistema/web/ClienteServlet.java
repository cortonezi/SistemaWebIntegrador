package com.sistema.web;

import DatabaseConnection.ClienteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String endereco = request.getParameter("endereco");
        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");
        String placa = request.getParameter("placa");
        String observacao = request.getParameter("observacao");

        try (ClienteDAO dao = new ClienteDAO()) {
            dao.adicionarCliente(nome, email, telefone, endereco, marca, modelo, placa, observacao);
            // Redireciona para a página de sucesso, limpando os dados
            response.sendRedirect("cadastroClientes.html?success=true");
        } catch (SQLException ex) {
            String errorMessage = "";
            if (ex.getMessage().contains("Duplicate entry")) {
                if (ex.getMessage().contains("email")) {
                    errorMessage = "Email já cadastrado";
                } else if (ex.getMessage().contains("telefone")) {
                    errorMessage = "Telefone já cadastrado";
                } else if (ex.getMessage().contains("placa")) {
                    errorMessage = "Placa já cadastrada";
                }
            } else {
                errorMessage = "Erro desconhecido ao cadastrar cliente";
            }

            // Redireciona para a página de erro, mantendo os dados no formulário
            response.sendRedirect(String.format(
                    "cadastroClientes.html?success=false&error=%s&nome=%s&email=%s&telefone=%s&endereco=%s&marca=%s&modelo=%s&placa=%s&observacao=%s",
                    errorMessage,
                    nome, email, telefone, endereco, marca, modelo, placa, observacao
            ));
        }
    }
}
