package br.com.totvs.pedido.form;

import br.com.totvs.pedido.Pedido;
import br.com.totvs.pedido.PedidoRepository;
import br.com.totvs.pedido_produto.PedidoProduto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PedidoForm {

    private LocalDateTime data;
    @Length(max = 255)
    private String razaoSocial;
    @Length(max = 14)
    private String cnpj;
    @Length(max = 11)
    private String telefone;
    @Length(max = 255)
    private String email;
    private List<PedidoProduto> pedidoProdutos = new ArrayList<>();

    public Pedido converter() {
        return new Pedido(data, razaoSocial, cnpj, telefone, email, pedidoProdutos);
    }

}
