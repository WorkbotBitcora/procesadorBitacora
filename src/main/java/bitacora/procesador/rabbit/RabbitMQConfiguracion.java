package bitacora.procesador.rabbit;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguracion {

    public static final String ROUTING_A = "routing.A";
    public static final String ROUTING_B = "routing.B";
    public static final String ROUTING_C = "routing.C";

    @Bean
    Queue queueA (){
        return new Queue("queue.A",false);
    }

    @Bean
    Queue queueB (){
        return new Queue("queue.B",false);
    }

    @Bean
    Queue queueC (){
        return new Queue("queue.C",false);
    }

    @Bean
    DirectExchange exchange(){
        return new DirectExchange("exchange.direct");
    }

    @Bean
    Binding bindingA (Queue queueA, DirectExchange exchange){
        return  BindingBuilder.bind(queueA).to(exchange).with(ROUTING_A);
    }
    @Bean
    Binding bindingB (Queue queueB, DirectExchange exchange){
        return  BindingBuilder.bind(queueB).to(exchange).with(ROUTING_B);
    }

    @Bean
    Binding bindingC(Queue queueC, DirectExchange exchange){
        return  BindingBuilder.bind(queueC).to(exchange).with(ROUTING_C);
    }


    @Bean
    MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate  (ConnectionFactory   connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
