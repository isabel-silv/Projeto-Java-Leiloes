/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean cadastrarProduto (ProdutosDTO produto){
      String sql = "INSERT INTO produto (nome, valor, status) VALUES (?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) { 
           st.setString(1, produto.getNome());
            st.setInt(2, produto.getValor());
             st.setString(3, produto.getStatus());
             int inserido = st.executeUpdate();
            if (inserido > 0) {
            System.out.println("Produto cadastrado com sucesso!");
            return true;
        } else {
            System.out.println("Erro ao cadastrar o produto.");
            return false;
        }
    } catch (SQLException ex) {
        System.err.println("Erro ao cadastrar o produto: " + ex.getMessage());
        return false;
    }

        
        //conn = new conectaDAO().connectDB();
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

