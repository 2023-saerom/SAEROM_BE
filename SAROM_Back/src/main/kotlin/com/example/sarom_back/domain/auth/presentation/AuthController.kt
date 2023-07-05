import com.example.sarom_back.domain.auth.TokenResponse
import com.example.sarom_back.domain.auth.exception.InvalidPasswordException
import com.example.sarom_back.domain.auth.presentation.dto.response.SignInRequest
import com.example.sarom_back.domain.auth.usecase.ReissueTokenService
import com.example.sarom_back.domain.auth.usecase.SignInService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val signInService: SignInService,
    private val reissueTokenService: ReissueTokenService
) {

    @PostMapping("/signin")
    fun signIn(@RequestBody request: SignInRequest): ResponseEntity<TokenResponse> {
        return try {
            val tokenResponse = signInService.execute(request)
            ResponseEntity.ok(tokenResponse)
        } catch (ex: InvalidPasswordException) {
            ResponseEntity.badRequest().build()
        } catch (ex: UsernameNotFoundException) {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/reissue-token")
    fun reissueToken(@RequestBody refreshToken: String): ResponseEntity<TokenResponse> {
        return try {
            val tokenResponse = reissueTokenService.execute(refreshToken)
            ResponseEntity.ok(tokenResponse)
        } catch (ex: Exception) {
            ResponseEntity.badRequest().build()
        }
    }
}
