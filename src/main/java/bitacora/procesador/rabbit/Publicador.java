package bitacora.procesador.rabbit;

import bitacora.procesador.domain.Revision;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@EnableRabbit
@AllArgsConstructor
@Component
public class Publicador {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange exchange;

    public String publicar(Revision revision) {
        System.out.println("EL SISTEMA VA A PUBLICAR EL EQUIPO : " + revision.getEquipo().getMarca());
        rabbitTemplate.convertAndSend(exchange.getName(), "routing.B" , revision);
        return "Revision enviada";
    }


}
