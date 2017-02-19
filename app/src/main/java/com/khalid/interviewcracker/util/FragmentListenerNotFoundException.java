package com.khalid.interviewcracker.util;

public class FragmentListenerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FragmentListenerNotFoundException() {
		super();
	}

	public FragmentListenerNotFoundException(String detailMessage) {
		super(detailMessage);
	}

	public FragmentListenerNotFoundException(Throwable throwable) {
		super(throwable);
	}

	public FragmentListenerNotFoundException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

}
