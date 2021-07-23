package br.com.totvs.pedido.dto;

import br.com.totvs.pedido.Pedido;
import br.com.totvs.pedido.PedidoSituacao;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PedidoDto {

    private Long id;
    private LocalDateTime data;
    private PedidoSituacao situacao;
    private String razaoSocial;
    private String cnpj;
    private Integer quantidadeProdutos;
    private BigDecimal quantidadeItens;
    private BigDecimal totalPedido;


    public PedidoDto(Pedido pedido) {
        this.id = pedido.getId();
        this.data = pedido.getData();
        this.situacao = pedido.getSituacao();
        this.razaoSocial = pedido.getRazaoSocial();
        this.cnpj = pedido.getCnpj();
        this.quantidadeProdutos = pedido.getQuantidadeProdutos();
        this.quantidadeItens = pedido.getQuantidadeItens();
        this.totalPedido = pedido.getTotalPedido();
    }

    public static List<PedidoDto> converter(List<Pedido> pedidos) {
        return pedidos.stream().map(PedidoDto::new).collect(Collectors.toList());
    }
}
