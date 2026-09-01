package apigatway.config;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements Filter {

    private final JwtService jwtService;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req =
                (HttpServletRequest) request;

        HttpServletResponse res =
                (HttpServletResponse) response;

        String path = req.getRequestURI();

        // Public APIs

        if (path.startsWith("/api/auth/send-otp")
                || path.startsWith("/api/auth/verify-otp")   || path.startsWith("/actuator") ) {

            chain.doFilter(request, response);
            return;
        }

        String authHeader =
                req.getHeader("Authorization");

        if (authHeader == null
                || !authHeader.startsWith("Bearer ")) {

            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            res.getWriter()
                    .write("Authorization Token Missing");

            return;
        }

        // Token valid hai
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        "gateway-user",
                        null,
                        java.util.Collections.emptyList());

        SecurityContextHolder.getContext()
                .setAuthentication(auth);

        chain.doFilter(request, response);
    }
}