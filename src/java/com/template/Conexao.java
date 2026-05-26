package com.template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    static String conexao = "jdbc:postgresql://localhost:5432/saude_consulta";
    static String usuario = "postgres";
    static String senha = "postgres";


    public Connection conectaBD(){

        //Estabelecer conexao e capturar erro possivel pelo catch
        try{
            return DriverManager.getConnection(conexao,usuario,senha);
        }catch (SQLException e){
            System.out.println("Erro!");
            throw new RuntimeException(e.getMessage());

        }
    }

}
