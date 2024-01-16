package org.hh99.gradation.jwt;

import java.io.IOException;

import org.hh99.gradation.domain.UserAuthEnum;
import org.hh99.gradation.domain.dto.UserDto;
import org.hh99.gradation.message.ErrorMessage;
import org.hh99.gradation.message.SuccessMessage;
import org.hh99.gradation.security.UserDetailsImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static final String LOGIN_ATTEMPT_LOG = "로그인 시도";
	private static final String LOGIN_SUCCESS_AND_JWT_TOKEN_CREATION_LOG = "로그인 성공 및 JWT 생성";
	private static final String LOGIN_FAIL_LOG = "로그인 실패";
	private final JwtUtil jwtUtil;

	public JwtAuthenticationFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
		setFilterProcessesUrl("/api/login");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws
		AuthenticationException {
		log.info(LOGIN_ATTEMPT_LOG);
		try {
			UserDto requestDto = new ObjectMapper().readValue(request.getInputStream(), UserDto.class);

			return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
					requestDto.getEmail(),
					requestDto.getPassword(),
					null
				)
			);
		} catch (IOException e) {
			log.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
		Authentication authResult) throws IOException, ServletException {
		log.info(LOGIN_SUCCESS_AND_JWT_TOKEN_CREATION_LOG);

		Long id = ((UserDetailsImpl)authResult.getPrincipal()).getUserId();
		String email = ((UserDetailsImpl)authResult.getPrincipal()).getUsername();
		UserAuthEnum auth = ((UserDetailsImpl)authResult.getPrincipal()).getUser().getAuthor();

		String token = jwtUtil.createToken(id, email, auth);
		jwtUtil.addJwtToCookie(token, response);

		response.getWriter().write(SuccessMessage.LOGIN_SUCCESS_MESSAGE.getSuccessMessage());
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException failed) throws IOException, ServletException {
		log.info(LOGIN_FAIL_LOG);
		response.setStatus(401);
		response.getWriter().write(ErrorMessage.PASSWORD_MISMATCH_ERROR_MESSAGE.getErrorMessage());
	}
}