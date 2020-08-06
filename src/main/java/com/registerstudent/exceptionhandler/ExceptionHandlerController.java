package com.registerstudent.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.registerstudent.exceptionhandler.DetalhesErro.Campo;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;	
	
	@Autowired
	private DetalhesErro detalhesErro;
	
	@ExceptionHandler({ NegocioException.class })
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
		
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		
		detalhesErro.setStatus(httpStatus.value());
		detalhesErro.setTitulo(ex.getMessage());
		detalhesErro.setDataHora(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, detalhesErro, new HttpHeaders(), httpStatus, request);
	}
	
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> conflict(DataIntegrityViolationException ex, WebRequest request) {
		
		HttpStatus httpStatus = HttpStatus.CONFLICT;
		
		detalhesErro.setStatus(httpStatus.value());
		detalhesErro.setTitulo(ex.getRootCause().getMessage());
		detalhesErro.setDataHora(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, detalhesErro, new HttpHeaders(), httpStatus, request);
	}
	
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
			WebRequest request) {
		
		HttpStatus httpStatus = HttpStatus.NOT_ACCEPTABLE;
		
		detalhesErro.setStatus(httpStatus.value());
		detalhesErro.setTitulo(ex.getConstraintViolations().toString());
		detalhesErro.setDataHora(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, detalhesErro, new HttpHeaders(), httpStatus, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Campo> campos = new ArrayList<DetalhesErro.Campo>();
		
		for (ObjectError erro: ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) erro).getField();
			String mensagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
			
			campos.add(new DetalhesErro.Campo(nome,mensagem));
		}
		
		detalhesErro.setStatus(status.value());
		detalhesErro.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente!");
		detalhesErro.setDataHora(OffsetDateTime.now());
		detalhesErro.setCampos(campos);
		
		return super.handleExceptionInternal(ex, detalhesErro, headers, status, request);
	}
	
	@Override
	public ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		detalhesErro.setStatus(status.value());
		detalhesErro.setTitulo("Tipo de mídia HTTP não suportada");
		detalhesErro.setDataHora(OffsetDateTime.now());
		
		return super.handleExceptionInternal(ex, detalhesErro, headers, status, request);
	}

}
