package com.registerstudent.exceptionhandler;

public class NegocioExcepition extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public NegocioExcepition(String mensagem) {
		super(mensagem);
	}

}
