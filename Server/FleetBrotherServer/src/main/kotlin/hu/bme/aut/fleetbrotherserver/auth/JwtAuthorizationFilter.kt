package hu.bme.aut.fleetbrotherserver.auth

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter


@Component
class JwtAuthorizationFilter(
    private val jwtUtil: JwtUtil,
    private val mapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val errorDetails: MutableMap<String, Any?> = HashMap()

        try {
            val accessToken = jwtUtil.resolveToken(request)

            if (accessToken == null) {
                filterChain.doFilter(request, response)
                return
            }

            println("token : $accessToken")

            val claims = jwtUtil.resolveClaims(request)

            if (claims != null && jwtUtil.validateClaims(claims)) {
                val email = claims.subject
                val roleStr = claims["role"].toString()

                val role = SimpleGrantedAuthority("ROLE_$roleStr")

                println("email : $email")

                val authentication: Authentication = UsernamePasswordAuthenticationToken(email, "", listOf(role))
                SecurityContextHolder.getContext().authentication = authentication
            }

        } catch (e: Exception) {
            errorDetails["message"] = "Authentication Error"
            errorDetails["details"] = e.message
            response.status = HttpStatus.FORBIDDEN.value()
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            mapper.writeValue(response.writer, errorDetails)
        }

        filterChain.doFilter(request, response)
    }
}