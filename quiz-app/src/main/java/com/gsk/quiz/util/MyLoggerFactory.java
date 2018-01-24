package com.gsk.quiz.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLoggerFactory {

	public static Logger getLogger() {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

		String callersClassName = stackTrace[2].getClassName();

		return LoggerFactory.getLogger(callersClassName);
	}

}
