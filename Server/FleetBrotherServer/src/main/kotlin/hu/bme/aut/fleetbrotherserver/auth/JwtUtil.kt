package hu.bme.aut.fleetbrotherserver.auth

import hu.bme.aut.fleetbrotherserver.data.entities.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.TimeUnit


@Component
class JwtUtil {
    private val secret_key = "mysecretkey"
    private val accessTokenValidity = (60 * 60 * 1000).toLong()
    private val jwtParser = Jwts.parser().setSigningKey(secret_key)
    private val TOKEN_HEADER = "Authorization"
    private val TOKEN_PREFIX = "Bearer "

    fun createToken(user: User): String {
        val claims = Jwts.claims().setSubject(user.email)

        val tokenCreateTime = Date()
        val tokenValidity = Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity))

        return Jwts.builder()
            .setClaims(claims)
            .setExpiration(tokenValidity)
            .signWith(SignatureAlgorithm.HS256, secret_key)
            .compact()
    }

    private fun parseJwtClaims(token: String): Claims {
        return jwtParser.parseClaimsJws(token).body
    }

    fun resolveClaims(req: HttpServletRequest): Claims? {
        return try {
            val token = resolveToken(req)
            token?.let { parseJwtClaims(it) }
        } catch (ex: ExpiredJwtException) {
            req.setAttribute("expired", ex.message)
            throw ex
        } catch (ex: Exception) {
            req.setAttribute("invalid", ex.message)
            throw ex
        }
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(TOKEN_HEADER)
        return if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            bearerToken.substring(TOKEN_PREFIX.length)
        } else null
    }

    fun validateClaims(claims: Claims): Boolean {
        return try {
            claims.expiration.after(Date())
        } catch (e: Exception) {
            throw e
        }
    }

    fun getEmail(claims: Claims): String {
        return claims.subject
    }

    private fun getRoles(claims: Claims): List<String>? {
        return claims["roles"] as List<String>?
    }
}