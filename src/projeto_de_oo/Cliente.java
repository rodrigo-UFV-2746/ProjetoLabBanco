/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projeto_de_oo;

/**
 *
 * @author RODRIGO
 */
import java.util.Date;
public class Cliente extends Pessoa implements Comparable { 
    private  String dados_adicionais;
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
   
    public Cliente() {
       super();
    }
    public Cliente(String rua,String bairro,String cidade,String cep,String estado){
        super();
 
        
    }
    public Cliente(String dados_adicionais) {
        this.dados_adicionais = dados_adicionais;
    }

    public String getDados_adicionais() {
        return dados_adicionais;
    }

    public void setDados_adicionais(String dados_adicionais) {
        this.dados_adicionais = dados_adicionais;
    }
   
    public int compareTo(Object obj) {
        if(obj instanceof Cliente){
            Cliente em=(Cliente)obj;
            return em.nome.compareTo(this.nome);
        }
        return 0;
    }
    
}
