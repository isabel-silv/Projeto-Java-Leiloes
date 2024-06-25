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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
     private String url = "jdbc:mysql://localhost:3306/uc11";
    private String user = "root";
    private String password = "Jkic25032004@";  
    
   public boolean cadastrarProduto(ProdutosDTO produto) {
     conectaDAO conecta = new conectaDAO();
    Connection conn = conectaDAO.connectDB();
     if (conn != null) { 
       String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
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
     }  
        return false;
}
public ArrayList<ProdutosDTO> listarProdutos() {
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    try {
       conectaDAO conecta = new conectaDAO();
        Connection conn = conectaDAO.connectDB(); 
        String sql = "SELECT * FROM produtos";
        PreparedStatement st = conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
       
         
        while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(rs.getInt("id")); 
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));
            listagem.add(produto);
        }
        return listagem;
    } catch (SQLException ex) {
        return null;
    }
}
public boolean venderProduto(int id) {
        try {
            conectaDAO conecta = new conectaDAO();
            Connection conn = conectaDAO.connectDB();

            if (conn != null) {
                String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, id);
                    int atualizado = st.executeUpdate();

                    if (atualizado > 0) {
                        System.out.println("Produto marcado como vendido!");
                        return true;
                    } else {
                        System.out.println("Erro ao atualizar o status do produto.");
                        return false;
                    }
                }
            } else {
                System.out.println("Erro na conexão com o banco de dados.");
                return false;
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar o status do produto: " + ex.getMessage());
            return false;
        }
    }



}

