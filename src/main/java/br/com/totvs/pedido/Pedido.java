package br.com.totvs.pedido;

import br.com.totvs.pedido_produto.PedidoProduto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long id;
    @Enumerated(EnumType.ORDINAL)
    private PedidoSituacao situacao = PedidoSituacao.ABERTO;
    private LocalDateTime data;
    @Column(name = "razao_social")
    private String razaoSocial;
    private String cnpj;
    private String telefone;
    private String email;
    @JsonManagedReference(value = "pedidoProduto")
    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<PedidoProduto> pedidoProdutos = new ArrayList<>();

    public Pedido(LocalDateTime data, String razaoSocial, String cnpj,
                  String telefone, String email, List<PedidoProduto> pedidoProdutos) {
        super();
        this.data = data;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.email = email;
        this.pedidoProdutos = pedidoProdutos;
    }

    public Integer getQuantidadeProdutos() {
        return pedidoProdutos.size();
    }

    public BigDecimal getQuantidadeItens() {
        return pedidoProdutos.stream().map(PedidoProduto::getQuantidade).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalPedido() {
        return pedidoProdutos.stream()
                .map(p -> p.getQuantidade().multiply(p.getPreco(), MathContext.DECIMAL32))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
