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
    private final PedidoServiceImpl pedidoService;

    @GetMapping
    public List<PedidoDto> retornaPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        var pedidosDto = PedidoDto.converter(pedidos);

        return pedidosDto;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Pedido> gravarPedido(@RequestBody @Valid PedidoForm pedidoForm) {
        var pedido = pedidoForm.converter();
        this.pedidoRepository.save(pedido);

        return ResponseEntity.ok().body(pedido);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long id, @RequestBody @Valid Pedido pedido) {
        var optional = pedidoRepository.findById(id);
        if (optional.isPresent()) {
            var pedidoRet = this.pedidoRepository.save(pedido);
            return ResponseEntity.ok().body(pedidoRet);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/finaliza-pedido/{id}")
    @Transactional
    public ResponseEntity<Pedido> finalizarPedido(@PathVariable Long id, @RequestBody @Valid Pedido pedido) {
        this.pedidoService.validaFinalizacaoPedido(pedido);
        pedido.setSituacao(PedidoSituacao.FINALIZADO);
        var optional = pedidoRepository.findById(id);
        if (optional.isPresent()) {
            var pedidoRet = this.pedidoRepository.save(pedido);
            return ResponseEntity.ok().body(pedidoRet);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> carregarPedido(@PathVariable Long id) {
        var optional = pedidoRepository.findById(id);
        return optional.map(pedido -> ResponseEntity.ok().body(pedido))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id){
        var optional = pedidoRepository.findById(id);
        if (optional.isPresent()) {
            pedidoRepository.delete(optional.get());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
