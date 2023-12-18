package com.github.zeldaservice.infra.security;

import com.github.zeldaservice.exception.TokenInexistenteException;
import com.github.zeldaservice.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
// limpa os imports!

public class SecurityZeldaFilter extends OncePerRequestFilter {
    private TokenService tokenService = new TokenService(); // use um construtor (explícito ou allArgs) - marque como final pra não ser modificado em tempo de execução.

    @Override // põe o nonNull pra parâmetros do doFilterInternal. Coisa nova do spring 3. Além disso, quebre os params
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String tokenJWT = recoverToken(request); // var

        if (tokenJWT == null) {
            filterChain.doFilter(request, response);
        }


//        if (tokenJWT != null) { // inverte esse if. tá muito grande.

        // recomendo usar "var" pra não ficar verboso
//          var subject = tokenService.getSubject(tokenJWT); tipo assim
        /*var*/ String subject = tokenService.getSubject(tokenJWT);
//        /*var*/ MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();
        /*var*/ var bodyValues = new LinkedMultiValueMap<String, String>(); // assim!
        bodyValues.add("email", subject);

        // tenta nunca colocar url ao longo da aplicação. Crie uma constante no topo da classe (private static final String URL = "..."
        // OUUUU crie um arquivo de constantes, que fica melhor ainda.
        // uma última nota: Tenta sempre sempre sempre manter o código identado antes dessa linha >>>>>>>>>>>>>>>>
        /*var!*/ ResponseEntity<Boolean> responseEntity = WebClient.create("http://user-service-app-env-1.eba-zevpvgmk.us-east-1.elasticbeanstalk.com/login/authentication")
                .post()
                .body(BodyInserters.fromFormData(bodyValues))
                .retrieve()
//                .toEntity(Boolean.class).block(); // um ponto por linha!
                .toEntity(Boolean.class)
                .block();  // assim

//            Boolean emailExists = responseEntity.getBody(); // body pode ser null. como suas exceções não capturam isso, toma cuidado.
//
//            if (emailExists != null && emailExists) {
//                var authentication = new UsernamePasswordAuthenticationToken(subject, null, null);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }

        // vou te dar uma solução melhor:
        if (responseEntity != null && (responseEntity.getBody() != null ? responseEntity.getBody() : false)) {
            var authentication = new UsernamePasswordAuthenticationToken(subject, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
//        }

        filterChain.doFilter(request, response);
    }

    public String recoverToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization"); // var!

        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
// tira os espaços!
    }
}
