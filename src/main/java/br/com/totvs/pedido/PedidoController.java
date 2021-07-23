package br.com.totvs.pedido;

import br.com.totvs.pedido.dto.PedidoDto;
import br.com.totvs.pedido.form.PedidoForm;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedido")
@AllArgsConstructor
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final PedidoService pedidoService;

    @GetMapping
    public List<PedidoDto> retornarPedidos() {
        return pedidoService.retornarPedidos();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Pedido> gravarPedido(@RequestBody @Valid PedidoForm pedidoForm) {
        return pedidoService.salvarPedido(pedidoForm);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long id, @RequestBody @Valid Pedido pedido) {
        return pedidoService.atualizarPedido(id, pedido);
    }

    @PutMapping("/finaliza-pedido/{id}")
    @Transactional
    public ResponseEntity<Pedido> finalizarPedido(@PathVariable Long id, @RequestBody @Valid Pedido pedido) {
        return pedidoService.finalizarPedido(id, pedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> carregarPedido(@PathVariable Long id) {
        return pedidoService.carregarPedido(id);
    }

    @PostMapping("/atualiza-resumo")
    public ResponseEntity<Pedido> atualizaResumo(@RequestBody Pedido pedido) {
        return ResponseEntity.ok().body(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirPedido(@PathVariable Long id){
        return pedidoService.excluirPedido(id);
    }
}
