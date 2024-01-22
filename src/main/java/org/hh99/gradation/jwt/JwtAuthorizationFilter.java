package org.hh99.gradation.jwt;

import java.io.IOException;

import org.hh99.gradation.message.ErrorMessage;
import org.hh99.gradation.message.JwtErrorMessage;
import org.hh99.gradation.security.UserDetailsServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "JWT 검증 및 인가")
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private final UserDetailsServiceImpl userDetailsService;

	public JwtAuthorizationFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws
		IOException,
		ServletException {
		res.setContentType("text/html; charset=utf-8");

		String path = req.getRequestURI();

		// 회원가입과 로그인 경로에 대해서는 JWT 검증 로직을 건너뜁니다.
		if (path.contains("css") || "/".equals(path) || "/api/login".equals(path) || "/api/toSignup".equals(path)) {
			filterChain.doFilter(req, res);
			return;
		}

		String tokenValue = jwtUtil.getTokenFromRequest(req);

		if (tokenValue == null) {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			res.getWriter().write(JwtErrorMessage.NULL_JWT_ERROR_MESSAGE.getErrorMessage());
			return;
		}

		if (StringUtils.hasText(tokenValue)) {
			tokenValue = jwtUtil.substringToken(tokenValue);
			log.info(tokenValue);

			try {
				Jwts.parserBuilder().setSigningKey(jwtUtil.getKey()).build().parseClaimsJws(tokenValue);
			} catch (SecurityException | MalformedJwtException e) {
				res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				res.getWriter().write(JwtErrorMessage.INVALID_JWT_ERROR_MESSAGE.getErrorMessage());
				return;
			} catch (ExpiredJwtException e) {
				res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				res.getWriter().write(JwtErrorMessage.EXPIRED_JWT_ERROR_MESSAGE.getErrorMessage());
				return;
			} catch (UnsupportedJwtException e) {
				res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				res.getWriter().write(JwtErrorMessage.UNSUPPORTED_JWT_ERROR_MESSAGE.getErrorMessage());
				return;
			} catch (IllegalArgumentException e) {
				res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				res.getWriter().write(JwtErrorMessage.EMPTY_JWT_ERROR_MESSAGE.getErrorMessage());
				return;
			}

			Claims info = jwtUtil.getUserInfoFromToken(tokenValue);

			try {
				setAuthentication(info.getSubject());
			} catch (Exception e) {
				log.error(e.getMessage());
				return;
			}
		}

		filterChain.doFilter(req, res);
	}

	public void setAuthentication(String username) {
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		Authentication authentication = createAuthentication(username);
		context.setAuthentication(authentication);

		SecurityContextHolder.setContext(context);
	}

	private Authentication createAuthentication(String email) {
		UserDetails staffDetails = userDetailsService.loadUserByUsername(email);
		return new UsernamePasswordAuthenticationToken(staffDetails, null, staffDetails.getAuthorities());
	}
}
