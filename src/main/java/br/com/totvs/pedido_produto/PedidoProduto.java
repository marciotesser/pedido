package br.com.totvs.pedido_produto;

import br.com.totvs.pedido.Pedido;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.MathContext;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "produto"})
public class PedidoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido_produto")
    private Long id;
    @JsonBackReference(value = "pedidoProduto")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;
    @Size(max = 255)
    private String produto;
    @Column(precision = 14, scale = 10)
    private BigDecimal quantidade;
    @Column(precision = 14, scale = 10)
    private BigDecimal preco;

    public BigDecimal getTotal() {
        return this.quantidade.multiply(this.preco, MathContext.DECIMAL32);
    }

}
