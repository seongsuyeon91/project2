package com.study.jsp.command;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface BCommand {
	
	void execute(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
