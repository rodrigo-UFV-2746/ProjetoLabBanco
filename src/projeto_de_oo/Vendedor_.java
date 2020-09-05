/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_de_oo;

/**
 *
 * @author Matheus VR
 */


import java.io.PrintStream;




public class Vendedor_  extends Pessoa implements VendedorSalarioCalculado, Comparable {
    private float comisao;
    private String nome;

    public Vendedor_() {
        super();
    }

    public float getComisao() {
        return comisao;
    }

    public void setComisao(float comisao) {
        this.comisao = comisao;
    }
    
    public float calculaComicao(float valorTotaldeVenda){
        float valorComisao = this.comisao*valorTotaldeVenda;
        return valorComisao;  
    }
    public int compareTo(Object obj) {
        if(obj instanceof Cliente){
            Vendedor_ em=(Vendedor_)obj;
            return em.nome.compareTo(this.nome);
        }
        return 0;
    }
  

    
}

