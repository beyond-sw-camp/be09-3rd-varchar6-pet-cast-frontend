package com.varchar6.petcast.servicegateway.filter;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    private final Environment environment;
    Environment env;

    public AuthorizationHeaderFilter(Environment env, Environment environment) {
        super(Config.class);
        this.env = env;
        this.environment = environment;
    }

    public static class Config {
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest();

            if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
            }

            String BearerToken = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String jwt = BearerToken.substring(7);

            if(!isJwtValid(jwt)){
                return onError(exchange, "Invalid JWT token", HttpStatus.UNAUTHORIZED);
            }

            // JWT에서 사용자 정보 추출
            Claims claims = getClaims(jwt);

            if (claims == null) {
                return onError(exchange, "Invalid JWT claims", HttpStatus.UNAUTHORIZED);
            }

            // 필요한 정보 추출
            String memberId = claims.get("memberId", String.class);
            String memberLoginId = claims.get("memberLoginId", String.class);
            String memberName = claims.get("memberName", String.class);
            String memberPhone = claims.get("memberPhone", String.class);
            String memberNickname = claims.get("memberNickname", String.class);
            String image = claims.get("image", String.class);
            String created = claims.get("created", String.class);
            String updated = claims.get("updated", String.class);
            String active = claims.get("active", String.class);
            String introduction = claims.get("introduction", String.class);
            List<String> authorities = claims.get("authorities", List.class);

            String authoritiesHeader = authorities.stream().collect(Collectors.joining(","));
            // 기존 헤더에 값 추가
            HttpHeaders headers = new HttpHeaders();
            headers.addAll(request.getHeaders()); // 기존 헤더 복사
            headers.add("X-Member-Id", memberId);
            headers.add("X-Member-Login-Id", memberLoginId);
            headers.add("X-Member-Name", memberName);
            headers.add("X-Member-Phone", memberPhone);
            headers.add("X-Member-Nickname", memberNickname);
            headers.add("X-image", image);
            headers.add("X-created", created);
            headers.add("X-updated", updated);
            headers.add("X-active", active);
            headers.add("X-introduction", introduction);
            headers.add("X-authorities", authoritiesHeader);

            ServerHttpRequest modifiedRequest = request.mutate().headers(httpHeaders -> {
                httpHeaders.addAll(headers);
            }).build();

            ServerWebExchange modifiedExchange = exchange.mutate().request(modifiedRequest).build();


            return chain.filter(modifiedExchange);


        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String errorMessage, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private boolean isJwtValid(String jwt) {
        boolean returnValue = true;

        String subject = null;
        try{
            subject = Jwts.parser()
                    .setSigningKey(env.getProperty("token.secret"))
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();
        }catch (Exception e){
            e.printStackTrace();
            returnValue = false;
        }

        if(subject == null || subject.isEmpty()){
            returnValue = false;
        }

        return returnValue;
    }

    private Claims getClaims(String jwt) {
        try {
            return Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(jwt)
                .getBody();
        } catch (Exception e) {
            log.error("Could not parse claims from JWT: {}", e.getMessage());
            return null;
        }
    }
}
