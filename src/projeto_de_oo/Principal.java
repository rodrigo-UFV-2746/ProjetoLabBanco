package projeto_de_oo;
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.swing.JOptionPane;
import projeto_de_oo.ConectaBanco;

public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        // coleções
        //-------------------------------------------------

        //-------------------------------------------------
        
        // objetos
        //-------------------------------------------------
        Cliente novoCliente = new Cliente();
        Cliente AlteraCliente = new Cliente();
        Vendedor_ novoVendedor = new Vendedor_();
        Vendedor_ alteraVendedor = new Vendedor_();
        Endereco novoEnd = new Endereco();
        Produto novoProduto = new Produto();
        Venda novaVenda = new Venda();
        Connection con = null;
        con = ConectaBanco.conectabanco();
        PreparedStatement pst;
        PreparedStatement pst1;
        ResultSet rs;
        
        // scanners
        //-------------------------------------------------
        Scanner imput = new Scanner(System.in); // para inteiros
        Scanner input2 = new Scanner(System.in); // para caracteres
        Scanner input3 = new Scanner(System.in); // para float
        
        
        int controlador = 0;
        
        do{
            System.out.println(""
                    + "+++++++++++++++++++++++++\n"
                    + "+ Digite opcao de Menu: +\n"
                    + "+-----------------------+\n"
                    + "+0 - Sair               +\n"
                    + "+1 - Cliente            +\n"
                    + "+2 - Vendedor           +\n"
                    + "+3 - Produto            +\n"
                    + "+4 - PDV                +\n"
                    + "+++++++++++++++++++++++++\n");
            controlador = imput.nextInt();
            switch(controlador) {
                case 0:
                    System.out.println("Sair"); // encerra o programa
                    con.close();
                break;
                case 1:
               
                // definindo opções para cliente
                
                    int controladorcli = 0;   
                    
                    do{
                        System.out.println(""
                        + "+++++++++++++++++++++++++\n"
                        + "+         Cliente       +\n"
                        + "+-----------------------+\n"
                        + "+0 - Sair               +\n"
                        + "+1 - Inclusao           +\n"
                        + "+2 - Alteracao          +\n"
                        + "+3 - Exclusao           +\n"
                        + "+4 - Listar             +\n"
                        + "+++++++++++++++++++++++++\n");
                        controladorcli = imput.nextInt();
                        String buscaNome = null;
                    
                        switch(controladorcli) {
                            case 0:
                                System.out.println("Sair"); // sai do programa
                                break;
                            case 1:
                                // incluindo novos clientes
                                System.out.println("incluir");
                                
                                // adicionando novos clientes
                                System.out.println("---------------------------");
                                System.out.println("Insira os dados do cliente");
                                System.out.println("---------------------------");
                                
                                System.out.print("nome:");
                                novoCliente.setNome(input2.nextLine());
                                
                                System.out.println("Enderço");                            
                                
                                System.out.print("Estado:");
                                novoEnd.setEstado(input2.nextLine());                               
                                
                                System.out.print("Cidade:");
                                novoEnd.setCidade(input2.nextLine());                                                               
                                
                                System.out.print("Cep:");
                                novoEnd.setCep(input2.nextLine());
                               
                                System.out.print("Bairro:");
                                novoEnd.setBairro(input2.nextLine());                                
                                
                                System.out.print("Rua:");
                                novoEnd.setRua(input2.nextLine());
                                
                                System.out.print("Número:");
                                novoEnd.setNumero(input2.nextLine());
                                
                                System.out.print("observações:");                            
                                novoCliente.setDados_adicionais(input2.nextLine());
                                
                                
                                novoCliente.setEnd(novoEnd);

                                try{
                                    String sql ="INSERT INTO cliente(NOMECLIENTE,RUA,NUMERO,BAIRRO,CEP,CIDADE,ESTADO,DADOS_ADICIONAIS) VALUES (?,?,?,?,?,?,?,?);";
                                         
                                    pst = con.prepareStatement(sql);
                                    
                                    pst.setString(1, novoCliente.getNome());
                                    pst.setString(2, novoCliente.getEnd().getRua());
                                    pst.setInt(3, Integer.parseInt(novoCliente.getEnd().getNumero()));
                                    pst.setString(4, novoCliente.getEnd().getBairro());
                                    pst.setString(5, novoCliente.getEnd().getCep());
                                    pst.setString(6, novoCliente.getEnd().getCidade());
                                    pst.setString(7, novoCliente.getEnd().getEstado());
                                    pst.setString(8, novoCliente.getDados_adicionais());
                                    pst.execute();
                            }catch(SQLException error){
                                    JOptionPane.showMessageDialog(null,error);
                                }
                               
                                System.out.println("Operação realizada com sucesso!");
                                break;
                            case 2:
                                // alterando informações

                                    // percorrendo o treeSet
                                    // e procurando o nome especificado
                                    System.out.println("Insira o nome do cliente:");
                                    buscaNome = input2.nextLine();
                                    String sql1 = "select CODIGOCLIENTE,NOMECLIENTE,RUA,NUMERO,BAIRRO,CEP,CIDADE,ESTADO,DADOS_ADICIONAIS  from cliente where NOMECLIENTE = ?";
                                System.out.println("Listar");
                                System.out.println("CODIGOCLIENTE,NOMECLIENTE,RUA,NUMERO,BAIRRO,CEP,CIDADE,ESTADO,DADOS_ADICIONAIS");
                                try{
                                        
                                    pst = con.prepareStatement(sql1);
                                    pst.setString(1, buscaNome);
                                    rs = pst.executeQuery();
                                    while(rs.next()){
                                        System.out.printf("%d - %s - %s - %d - %s - %s - %s - %s - %s\n",rs.getInt("CODIGOCLIENTE"),rs.getString("NOMECLIENTE"),rs.getString("RUA"),rs.getInt("NUMERO"),rs.getString("BAIRRO"),rs.getString("CEP"),rs.getString("CIDADE"),rs.getString("ESTADO"),rs.getString("DADOS_ADICIONAIS"));
                                    }
                                }catch(SQLException error){
                                    JOptionPane.showMessageDialog(null,error);
                                }
                                System.out.println("----------------------------------------------");
                                System.out.println("Insira os dados que deseja alterar do cliente");
                                System.out.println("---------------------------------------------");
                                
                                System.out.print("nome:");
                                AlteraCliente.setNome(input2.nextLine());
                                
                                System.out.println("Enderço");                            
                                
                                System.out.print("Estado:");
                                novoEnd.setEstado(input2.nextLine());                               
                                
                                System.out.print("Cidade:");
                                novoEnd.setCidade(input2.nextLine());                                                               
                                
                                System.out.print("Cep:");
                                novoEnd.setCep(input2.nextLine());
                               
                                System.out.print("Bairro:");
                                novoEnd.setBairro(input2.nextLine());                                
                                
                                System.out.print("Rua:");
                                novoEnd.setRua(input2.nextLine());
                                
                                System.out.print("Número:");
                                novoEnd.setNumero(input2.nextLine());
                                
                                System.out.print("observações:");                            
                                AlteraCliente.setDados_adicionais(input2.nextLine());
                                AlteraCliente.setEnd(novoEnd);
                                try{
                                    String sql ="UPDATE cliente SET NOMECLIENTE =?,RUA =?,NUMERO=?,BAIRRO=?,CEP=?,CIDADE=?,ESTADO=?,DADOS_ADICIONAIS=? where NOMECLIENTE = ?;";
                                         
                                    pst = con.prepareStatement(sql);
                                    
                                    pst.setString(1, AlteraCliente.getNome());
                                    pst.setString(2, AlteraCliente.getEnd().getRua());
                                    pst.setInt(3, Integer.parseInt(AlteraCliente.getEnd().getNumero()));
                                    pst.setString(4, AlteraCliente.getEnd().getBairro());
                                    pst.setString(5, AlteraCliente.getEnd().getCep());
                                    pst.setString(6, AlteraCliente.getEnd().getCidade());
                                    pst.setString(7, AlteraCliente.getEnd().getEstado());
                                    pst.setString(8, AlteraCliente.getDados_adicionais());
                                    pst.setString(9,buscaNome);
                                    pst.execute();
                                         System.out.println("Operação realizada com sucesso!");
                            }catch(SQLException error){
                                    JOptionPane.showMessageDialog(null,error);
                            }
                                break;   
                            case 3:
                                
                                 System.out.println("Insira o nome do cliente deseja excluir:");
                                 buscaNome = input2.nextLine();
                                 String sql2 = "delete from cliente where NOMECLIENTE = ?";
                                try{
                                        
                                    pst = con.prepareStatement(sql2);
                                    pst.setString(1, buscaNome);
                                    pst.execute();
                                     System.out.println("cliente removido!");
                                }catch(SQLException error){
                                    JOptionPane.showMessageDialog(null,error);
                                }
                                
                                break;
                            case 4:
                                // listando os clientes cadastrados
                                String sql = "select CODIGOCLIENTE,NOMECLIENTE,RUA,NUMERO,BAIRRO,CEP,CIDADE,ESTADO,DADOS_ADICIONAIS  from cliente";
                                System.out.println("Listar");
                                System.out.println("CODIGOCLIENTE,NOMECLIENTE,RUA,NUMERO,BAIRRO,CEP,CIDADE,ESTADO,DADOS_ADICIONAIS");
                                try{

                                    pst = con.prepareStatement(sql);
                                    rs = pst.executeQuery();
                                    while(rs.next()){
                                        System.out.printf("%d - %s - %s - %d - %s - %s - %s - %s - %s\n",rs.getInt("CODIGOCLIENTE"),rs.getString("NOMECLIENTE"),rs.getString("RUA"),rs.getInt("NUMERO"),rs.getString("BAIRRO"),rs.getString("CEP"),rs.getString("CIDADE"),rs.getString("ESTADO"),rs.getString("DADOS_ADICIONAIS"));
                                    }

                                }

                                catch(SQLException error){

                                    JOptionPane.showMessageDialog(null, error);
                                }
            
                            break;
                        default:
                            System.out.println("entrada invalida");
                    }
                    
                }while(controladorcli != 0);
        
               break;
            case 2:        
                int controladorVendedor = 0;
                String buscaNome;
               do{       
                    System.out.println(""
                    + "+++++++++++++++++++++++++\n"
                    + "+        Vendedor       +\n"
                    + "+-----------------------+\n"
                    + "+0 - Sair               +\n"
                    + "+1 - Inclusao           +\n"
                    + "+2 - Alteracao          +\n"
                    + "+3 - Exclusao           +\n"
                    + "+4 - Listar             +\n"
                    + "+++++++++++++++++++++++++\n");
                    controladorVendedor = imput.nextInt();
                    switch(controladorVendedor) {
                    case 0:
                        System.out.println("Sair");
                    break;
                    case 1:
                        System.out.println("incluir");
                                
                                // adicionando novos clientes
                                System.out.println("---------------------------");
                                System.out.println("Insira os dados do Vendedor");
                                System.out.println("---------------------------");
                                
                                System.out.print("nome:");
                                novoVendedor.setNome(input2.nextLine());
                                
                                System.out.println("Enderço");                            
                                
                                System.out.print("Estado:");
                                novoEnd.setEstado(input2.nextLine());                               
                                
                                System.out.print("Cidade:");
                                novoEnd.setCidade(input2.nextLine());                                                               
                                
                                System.out.print("Cep:");
                                novoEnd.setCep(input2.nextLine());
                               
                                System.out.print("Bairro:");
                                novoEnd.setBairro(input2.nextLine());                                
                                
                                System.out.print("Rua:");
                                novoEnd.setRua(input2.nextLine());
                                
                                System.out.print("Número:");
                                novoEnd.setNumero(input2.nextLine());
                                
                              
                                
                                novoVendedor.setEnd(novoEnd);
                                  try{
                                    String sql ="INSERT INTO VENDEDOR (NOMEVENDEDOR,RUA,NUMERO,BAIRRO,CEP,CIDADE,ESTADO) VALUES (?,?,?,?,?,?,?);";
                                         
                                    pst = con.prepareStatement(sql);
                                    
                                    pst.setString(1, novoVendedor.getNome());
                                    pst.setString(2, novoVendedor.getEnd().getRua());
                                    pst.setInt(3, Integer.parseInt(novoVendedor.getEnd().getNumero()));
                                    pst.setString(4, novoVendedor.getEnd().getBairro());
                                    pst.setString(5, novoVendedor.getEnd().getCep());
                                    pst.setString(6, novoVendedor.getEnd().getCidade());
                                    pst.setString(7, novoVendedor.getEnd().getEstado());
                                    pst.execute();
                            }catch(SQLException error){
                                    JOptionPane.showMessageDialog(null,error);
                                }
                               
                                System.out.println("Operação realizada com sucesso!");
                                break;
                    
                    case 2:
     
                            System.out.println("Insira o nome do Vendedor que deseja Alterar:");
                            buscaNome = input2.nextLine();
                            String sql = "select CODIGOVENDEDOR,NOMEVENDEDOR,RUA,NUMERO,BAIRRO,CEP,CIDADE,ESTADO from VENDEDOR where NOMEVENDEDOR =?";
                            System.out.println("Listar");
                            System.out.println("CODIGOVENDEDOR,NOMEVENDEDOR,RUA,NUMERO,BAIRRO,CEP,CIDADE,ESTADO");
                         try{

                                pst = con.prepareStatement(sql);
                                pst.setString(1, buscaNome);
                                rs = pst.executeQuery();
                                while(rs.next()){
                                    System.out.printf("%d - %s - %s - %d - %s - %s - %s - %s \n",rs.getInt("CODIGOVENDEDOR"),rs.getString("NOMEVENDEDOR"),rs.getString("RUA"),rs.getInt("NUMERO"),rs.getString("BAIRRO"),rs.getString("CEP"),rs.getString("CIDADE"),rs.getString("ESTADO"));
                                    }

                            }catch(SQLException error){
                                    JOptionPane.showMessageDialog(null,error);
                            }
                                
                                System.out.println("----------------------------------------------");
                                System.out.println("Insira os dados que deseja alterar do vededor");
                                System.out.println("---------------------------------------------");
                                
                                System.out.print("nome:");
                                alteraVendedor.setNome(input2.nextLine());
                                
                                System.out.println("Enderço");                            
                                
                                System.out.print("Estado:");
                                novoEnd.setEstado(input2.nextLine());                               
                                
                                System.out.print("Cidade:");
                                novoEnd.setCidade(input2.nextLine());                                                               
                                
                                System.out.print("Cep:");
                                novoEnd.setCep(input2.nextLine());
                               
                                System.out.print("Bairro:");
                                novoEnd.setBairro(input2.nextLine());                                
                                
                                System.out.print("Rua:");
                                novoEnd.setRua(input2.nextLine());
                                
                                System.out.print("Número:");
                                novoEnd.setNumero(input2.nextLine());
                                alteraVendedor.setEnd(novoEnd);
                                try{
                                    String sql7="UPDATE VENDEDOR SET NOMEVENDEDOR =?,RUA =?,NUMERO=?,BAIRRO=?,CEP=?,CIDADE=?,ESTADO=? where NOMEVENDEDOR = ?;";
                                         
                                    pst = con.prepareStatement(sql7);
                                    
                                    pst.setString(1, alteraVendedor.getNome());
                                    pst.setString(2, alteraVendedor.getEnd().getRua());
                                    pst.setInt(3, Integer.parseInt(alteraVendedor.getEnd().getNumero()));
                               
                                    pst.setString(4, alteraVendedor.getEnd().getBairro());
                                    pst.setString(5, alteraVendedor.getEnd().getCep());
                                    pst.setString(6, alteraVendedor.getEnd().getCidade());
                                    pst.setString(7, alteraVendedor.getEnd().getEstado());
                                    pst.setString(8,buscaNome);
                                    pst.execute();
                                         System.out.println("Operação realizada com sucesso!");
                            }catch(SQLException error){
                                    JOptionPane.showMessageDialog(null,error);
                            }
                       
                                break;   
                    case 3:
                            System.out.println("Insira o nome do vendedor deseja excluir:");
                                 buscaNome = input2.nextLine();
                                 String sq45 = "delete from vendedor where NOMEVENDEDOR = ?";
                                try{
                                        
                                    pst = con.prepareStatement(sq45);
                                    pst.setString(1, buscaNome);
                                    pst.execute();
                                    System.out.println("Vendedor removido!");
                                }catch(SQLException error){
                                    JOptionPane.showMessageDialog(null,error);
                                }
                                
                                
                                break;
                    
                    case 4:
                        System.out.println("Listar");
                        String sql2 = "select CODIGOVENDEDOR,NOMEVENDEDOR,RUA,NUMERO,BAIRRO,CEP,CIDADE,ESTADO from VENDEDOR";
                        System.out.println("Listar");
                         System.out.println("CODIGOVENDEDOR,NOMEVENDEDOR,RUA,NUMERO,BAIRRO,CEP,CIDADE,ESTADO");
                         try{

                                pst = con.prepareStatement(sql2);
                                rs = pst.executeQuery();
                                while(rs.next()){
                                    System.out.printf("%d - %s - %s - %d - %s - %s - %s - %s \n",rs.getInt("CODIGOVENDEDOR"),rs.getString("NOMEVENDEDOR"),rs.getString("RUA"),rs.getInt("NUMERO"),rs.getString("BAIRRO"),rs.getString("CEP"),rs.getString("CIDADE"),rs.getString("ESTADO"));
                                    }

                                }

                        catch(SQLException error){

                                    JOptionPane.showMessageDialog(null, error);
                                }
            
                    break;
                    default:
                        System.out.println("entrada invalida");
                    }
               }while(controladorVendedor != 0);

            break;   
            case 3:
                int controladorProduto = 0;
               do{
                    System.out.println(""
                    + "+++++++++++++++++++++++++\n"
                    + "+        Produto        +\n"
                    + "+-----------------------+\n"
                    + "+0 - Sair               +\n"
                    + "+1 - Inclusao           +\n"
                    + "+2 - Alteracao          +\n"
                    + "+3 - Exclusao           +\n"
                    + "+4 - Listar             +\n"
                    + "+++++++++++++++++++++++++\n");
                    controladorProduto = imput.nextInt();
                    switch(controladorProduto) {
                    case 0:
                        System.out.println("Sair");
                    break;
                    case 1:
                        System.out.println("incluir");
                                
                                // adicionando novos clientes
                                System.out.println("---------------------------");
                                System.out.println("Insira os dados do Produto");
                                System.out.println("---------------------------");
     
                                System.out.print("Codigo:");
                                novoProduto.setCodigo(input2.nextLine());
                                
                                System.out.print("Unidade:");
                                novoProduto.setUnidade(input2.nextLine());

                                System.out.print("Descrição:");
                                novoProduto.setDescricaoproduto(input2.nextLine());                               
                                
                                System.out.print("Preço unitário:");
                                novoProduto.setPrecounitario(input2.nextFloat());                                                               
                               try{
                                    String sql89 ="INSERT INTO PRODUTO(DESCRICAOPRODUTO,UNIDADE,PRECOUNITARIO,CODIGOPRODUTO) VALUES (?,?,?,?);";
                                    pst = con.prepareStatement(sql89);
                                    pst.setString(1,novoProduto.getDescricaoproduto());
                                    pst.setString(2,novoProduto.getUnidade());
                                    pst.setFloat(3,novoProduto.getPrecounitario());
                                    pst.setInt(4,Integer.parseInt(novoProduto.getCodigo()));
                                    pst.execute();
                            }catch(SQLException error){
                                    JOptionPane.showMessageDialog(null,error);
                                }
                              System.out.println("Operação realizada com sucesso!");
                                
                        
                    break;
                    case 2:
                      System.out.println("ALTERAR");
                            System.out.println("Insira o DESCRICAO PRODUTO do Produto que deseja Alterar:");
                            buscaNome = input2.nextLine();
                         String sql50 ="SELECT CODIGOPRODUTO,DESCRICAOPRODUTO,UNIDADE,PRECOUNITARIO FROM PRODUTO WHERE DESCRICAOPRODUTO = ?;";
                         try{

                                pst = con.prepareStatement(sql50);
                                pst.setString(1,buscaNome);
                                rs = pst.executeQuery();
                                while(rs.next()){
                                    System.out.printf("%d - %s - %s - %.2f\n",rs.getInt("CODIGOPRODUTO"),rs.getString("DESCRICAOPRODUTO"),rs.getString("UNIDADE"),rs.getFloat("PRECOUNITARIO"));
                                    }

                                }

                        catch(SQLException error){

                                    JOptionPane.showMessageDialog(null, error);
                                }
                        System.out.println("---------------------------");
                        System.out.println("Insira os dados Que deseja Alterar do Produto");
                        System.out.println("---------------------------");
                        
                        System.out.print("Descrição:");
                        novoProduto.setDescricaoproduto(input2.nextLine()); 
                        
                        System.out.print("Unidade:");
                         novoProduto.setUnidade(input2.nextLine());                             
                                
                       System.out.print("Preço unitário:");
                        novoProduto.setPrecounitario(input2.nextFloat());
                        try{
                                    String sql200 ="UPDATE PRODUTO SET DESCRICAOPRODUTO =?,UNIDADE =?,PRECOUNITARIO=?;";
                                    pst = con.prepareStatement(sql200);
                                    pst.setString(1,novoProduto.getDescricaoproduto());
                                    pst.setString(2,novoProduto.getUnidade());
                                    pst.setFloat(3,novoProduto.getPrecounitario());
                                    pst.execute();
                            }catch(SQLException error){
                                    JOptionPane.showMessageDialog(null,error);
                                }
                              System.out.println("Operação realizada com sucesso!");
                       break;   
                   
                    case 3:
                        System.out.println("excluir");
                        System.out.println("Insira o DESCRICAO PRODUTO do Produto que deseja excluir:");
                        buscaNome = input2.nextLine();
                        String sql88 ="DELETE FROM PRODUTO WHERE DESCRICAOPRODUTO = ?;";
                        try{
                                pst = con.prepareStatement(sql88);
                                pst.setString(1,buscaNome);
                               pst.execute();
                                }

                        catch(SQLException error){

                                    JOptionPane.showMessageDialog(null, error);
                                }
                        System.out.println("produto removido!");
                                
                                break;
                 
                    case 4:
                        System.out.println("Listar");
                        String sql100 ="SELECT CODIGOPRODUTO,DESCRICAOPRODUTO,UNIDADE,PRECOUNITARIO FROM PRODUTO;";
                        try{
                                pst = con.prepareStatement(sql100);
                                rs = pst.executeQuery();
                                while(rs.next()){
                                    System.out.printf("%d - %s - %s - %.2f\n",rs.getInt("CODIGOPRODUTO"),rs.getString("DESCRICAOPRODUTO"),rs.getString("UNIDADE"),rs.getFloat("PRECOUNITARIO"));
                                    }
                                }
                        catch(SQLException error){

                                    JOptionPane.showMessageDialog(null, error);
                                }
                    break;
                    default:
                        System.out.println("entrada invalida");
                    }
               }while(controladorProduto != 0);
                
            break;
            case 4:
              
                System.out.println("PDV");               
                int controladorPdv= 0;
                String buscaN;

               do{
                                       System.out.println(""
                    + "+++++++++++++++++++++++++\n"
                    + "+          PDV          +\n"
                    + "+-----------------------+\n"
                    + "+0 - Sair               +\n"
                    + "+1 - Inclusao           +\n"
                    + "+2 - Alteracao          +\n"
                    + "+3 - Exclusao           +\n"
                    + "+4 - Listar             +\n"
                    + "+++++++++++++++++++++++++\n");
                    controladorPdv = imput.nextInt();
                    
                    switch(controladorPdv) {
                    case 0:
                        System.out.println("Sair");
                        break;
                    case 1:
                            String buscanome1;
                            System.out.println("BEM VINDO AO PDV:");
                            System.out.println("Insira o numero da venda:");
                            int temp_num_venda;
                            temp_num_venda = input3.nextInt();
                            System.out.println("Insira o nome do cliente:");
                            buscanome1 = input2.nextLine();
                            String sql20 = "select CODIGOCLIENTE from cliente where NOMECLIENTE = ?";
                            int tempcodcli = 0;
                            try{
                                    pst = con.prepareStatement(sql20);
                                    pst.setString(1, buscanome1);
                                    rs = pst.executeQuery();
                                    while(rs.next()){
                                        tempcodcli = rs.getInt("CODIGOCLIENTE");
                                    }
                                }catch(SQLException error){
                                    JOptionPane.showMessageDialog(null,error);
                                }
                            System.out.println("Insira o nome do Vendendor:");
                            buscaNome = input2.nextLine();
                            String sql21 = "select CODIGOVENDEDOR from VENDEDOR where NOMEVENDEDOR = ?";
                            int tempcodvend = 0;
                            try{
                                    pst = con.prepareStatement(sql21);
                                    pst.setString(1, buscaNome);
                                    rs = pst.executeQuery();
                                    while(rs.next()){
                                        tempcodvend = rs.getInt("CODIGOVENDEDOR");
                                    }
                                }catch(SQLException error){
                                    JOptionPane.showMessageDialog(null,error);
                                }
                            System.out.println("Insira o DESCRICAO do PRODUTO:");
                            buscaNome = input2.nextLine();
                            System.out.println("Insira o QUANTIDADE do PRODUTO:");
                            int tempquant = input3.nextInt();
                            String sql22 = "select CODIGOPRODUTO,PRECOUNITARIO from PRODUTO where DESCRICAOPRODUTO = ?";
                            int tempcodprod = 0;
                            float temppreco = 0;
                            try{
                                    pst = con.prepareStatement(sql22);
                                    pst.setString(1, buscaNome);
                                    rs = pst.executeQuery();
                                    while(rs.next()){
                                        tempcodprod = rs.getInt("CODIGOPRODUTO");
                                        temppreco = rs.getFloat("PRECOUNITARIO");
                                    }
                                }catch(SQLException error){
                                    JOptionPane.showMessageDialog(null,error);
                                }
                             try{
                                    String sql9 ="INSERT INTO PEDIDO(CODIGOCLIENTE,CODIGOVENDEDOR,TOTALVENDA,NUMVENDA) VALUES (?,?,?,?);";
                                    pst = con.prepareStatement(sql9);
                                   float totalvenda;
                                   totalvenda = tempquant * temppreco;
                                    pst.setInt(1,(tempcodcli));
                                    pst.setInt(2,(tempcodvend));
                                    pst.setFloat(3, totalvenda);
                                    pst.setInt(4,temp_num_venda);
                                    pst.execute();
                            }catch(SQLException error){
                                    JOptionPane.showMessageDialog(null,error);
                                }
                              try{
                                    String sql8 ="INSERT INTO ITEMVENDA(NUMVENDA,CODIGOPRODUTO,QUANTIDADE,PRECOUNITARIO) VALUES (?,?,?,?);";
                  
                                   pst = con.prepareStatement(sql8);
                                    pst.setInt(1,temp_num_venda);
                                    pst.setInt(2,tempcodprod);
                                    pst.setInt(3,tempquant);
                                    pst.setFloat(4, temppreco);
                                    pst.execute();
                            }catch(SQLException error){
                                    JOptionPane.showMessageDialog(null,error);
                                }
                                
                        break;
                        
                       
                        
                    
                    case 2:
                        System.out.println("alterar");
                    break;   
                    case 3:
                        System.out.println("excluir");
                    break;
                    case 4:
                        System.out.println("Listar");
                    break;
                    default:
                        System.out.println("entrada inválida");
                    }
                    // o usuario vai ser redirecionado para a tela de cadastrar           
                    
               }while(controladorPdv != 0);
               break;
            default:
                System.out.println("entrada inválida");
            }
             
             
        }while(controlador != 0);
 
        
        
        
    }
    
}
