package top.xmy.musicservice.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.LocalTime;

@Component
public class TimeBasedAccessFilter extends AbstractGatewayFilterFactory<TimeBasedAccessFilter.Config> {

    public TimeBasedAccessFilter() {
        super(Config.class);
    }

    public static class Config {
        // 配置类，可以添加更多配置参数
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            LocalTime now = LocalTime.now();
            LocalTime startTime = LocalTime.of(8, 0); // 8:00
            LocalTime endTime = LocalTime.of(20, 0);  // 20:00

            if (now.isBefore(startTime) || now.isAfter(endTime)) {
                byte[] bytes = "Service is not available at this time".getBytes();
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                exchange.getResponse().setStatusCode(HttpStatus.SERVICE_UNAVAILABLE);
                return exchange.getResponse().writeWith(Flux.just(buffer));
            }
            return chain.filter(exchange);
        };
    }
}
