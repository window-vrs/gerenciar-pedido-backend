package br.com.pedido.controller.excepton.commum;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.TokenExpiredException;

import br.com.pedido.controller.excepton.CampoErro;
import br.com.pedido.controller.excepton.ErroResposta;
import br.com.pedido.controller.excepton.RegistroNotFoundExcepton;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
	public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		List<FieldError> fieldErrors = ex.getFieldErrors();
		List<CampoErro> listaErros = fieldErrors.stream().map(fe -> new CampoErro(fe.getField(), fe.getDefaultMessage())).collect(Collectors.toList());

		return new ErroResposta(HttpStatus.UNPROCESSABLE_CONTENT.value(), "Erro de validação", listaErros);
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErroResposta handleErrosNaoTratados(RuntimeException ex) {
		return new ErroResposta(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Ocorreu um erro inesperado, entre em contato com o administrador do sistema ", List.of());
	}

	@ExceptionHandler(IllegalAccessException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
	public ErroResposta handleIllegalAccessException(IllegalAccessException ex) {
		return new ErroResposta(HttpStatus.UNPROCESSABLE_CONTENT.value(),"Ocorreu um erro inesperado, entre em contato com o administrador do sistema ", List.of());
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErroResposta handleErrosNaoTratados(DataIntegrityViolationException ex) {
		String mensagemErro = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();
		return new ErroResposta(HttpStatus.BAD_REQUEST.value(), mensagemErro, List.of());
	}	
	
	@ExceptionHandler(RegistroNotFoundExcepton.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
	public ErroResposta handleRegistroNotFoundExcepton(RegistroNotFoundExcepton ex) {
		String mensagemErro = ex.getMessage() != null ? ex.getMessage() : "Registro não encontrado";
		return new ErroResposta(HttpStatus.UNPROCESSABLE_CONTENT.value(), mensagemErro, List.of());
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErroResposta handleInternalUsernameNotFoundException(UsernameNotFoundException ex) {
		return new ErroResposta(HttpStatus.UNAUTHORIZED.value(),"Usuário/senha inválidos, check seu usuário/senha", List.of());
	}

	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErroResposta handleInternalUsernameNotFoundException(BadCredentialsException ex) {
		return new ErroResposta(HttpStatus.UNAUTHORIZED.value(),"Usuário/senha inválidos, check seu usuário/senha", List.of());
	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ErroResposta handleAccessDeniedException(AccessDeniedException ex) {
		return new ErroResposta(HttpStatus.FORBIDDEN.value(),"Usuário sem permissão, tente logar novamente", List.of());
	}

	@ExceptionHandler(AuthorizationDeniedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErroResposta handleAuthorizationDeniedException(AuthorizationDeniedException ex) {
		return new ErroResposta(HttpStatus.UNAUTHORIZED.value(),"Usuário não autorizado, check seu usuário/senha", List.of());
	}

	@ExceptionHandler(TokenExpiredException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErroResposta handleTokenExpiredException(TokenExpiredException ex) {
		return new ErroResposta(HttpStatus.UNAUTHORIZED.value(),"Seu token expirou, tente logar novamente", List.of());
	}

}
