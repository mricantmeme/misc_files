package com.dicv.truck.token;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dicv.truck.dto.StatusMessageDto;
import com.dicv.truck.utility.DicvConstants;
import com.google.gson.Gson;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(TokenInterceptor.class);

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		String authToken = httpReq.getHeader("authorization");
		response.addHeader("Access-Control-Expose-Headers", DicvConstants.HEADER_AUTHORIZATION);
		String origin = request.getHeader("Origin");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Origin", origin);
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type,X-Requested-With,Authorization,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Access-Control-Allow-Credentials");
		String httpURI = httpReq.getRequestURI();
		if (httpURI.contains("login") || httpURI.contains("forgotPassword")) {
			return true;
		}

		if (authToken == null) {
			LOGGER.warn("Faled in validating Token,Either token is null or empty...");
			StatusMessageDto statusMessage = new StatusMessageDto();
			statusMessage.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			statusMessage.setMessage("Token Not Available");
			Gson gson = new Gson();
			String gsonObject = gson.toJson(statusMessage);
			response.getWriter().print(gsonObject);
			response.flushBuffer();
			return false;
		}

		// Currently JWT token is not properly handled from UI side, token validate
		// method is only way to logout from ui

		if (!isValidateToken(authToken)) {
			StatusMessageDto statusMessage = new StatusMessageDto();
			statusMessage.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			statusMessage.setMessage("Token Invalid");
			Gson gson = new Gson();
			String gsonObject = gson.toJson(statusMessage);
			response.getWriter().print(gsonObject);
			response.flushBuffer();
			return false;
		}

		return true;
	}

	/**
	 * 
	 * @param tokenDetails
	 * @return TokenDetails
	 * @throws DicvTruckException
	 */
	public String generateToken(Integer userId) {
		String token = null;
		try {
			Algorithm algorithm = Algorithm.HMAC256("Dicv0=MCZaqj6J5/a2DIMKdPwig==");
			long nowMillis = System.currentTimeMillis();
			Date issueAt = new Date(nowMillis);
			Date eexpiresAt = new Date(nowMillis + 1380 * 60 * 1000);
			token = JWT.create().withSubject(userId.toString()).withIssuedAt(issueAt).withExpiresAt(eexpiresAt)
					.withIssuer("auth0").sign(algorithm);

		} catch (UnsupportedEncodingException exception) {
			LOGGER.warn("Failed in generateToken...");
		} catch (JWTCreationException exception) {
			LOGGER.warn("Failed in generateToken...");
		} catch (Exception ex) {
			LOGGER.error("Exception in JWT");
		}
		return token;
	}

	public boolean isValidateToken(String token) {
		if (token == null || token.isEmpty()) {
			LOGGER.info("Faled in validating Token,Either token is null or empty...");
			return false;
		}
		try {
			long nowMillis = System.currentTimeMillis();
			Date currentDate = new Date(nowMillis);
			DecodedJWT jwt = JWT.decode(token);
			if (jwt != null && jwt.getSubject() != null && jwt.getAlgorithm().equals("HS256")
					&& (currentDate.before(jwt.getExpiresAt()))) {
				return true;
			}
		} catch (IllegalArgumentException e) {
			LOGGER.error("IllegalArgumentException  ", e);
		} catch (JWTDecodeException exception) {
			LOGGER.error("JWTDecodeException  ", exception);
		} catch (Exception ex) {
			LOGGER.error("Exception in JWT ", ex);
		}
		return false;
	}

}
