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
public class Venda {
     private Cliente cliente;
     private Vendedor_ vendedor;
     private Produto produto;
     private float valor_Total;

    public Venda() {
        
    }

    public Venda(Cliente cliente, Vendedor_ vendedor, Produto produto) {
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.produto = produto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor_ getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor_ vendedor) {
        this.vendedor = vendedor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public float getValor_Total() {
        return valor_Total;
    }

    public void setValor_Total(float valor_Total) {
        this.valor_Total = valor_Total;
    }
    
}
