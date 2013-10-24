package com.pan.evms.sl.interaction.config;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import com.pan.evms.sl.interaction.base.HttpsServlet;

@WebServlet(name = "OperationConfig", urlPatterns = "/OperationConfig")
public class OperationConfig extends HttpsServlet
{
	private static final long serialVersionUID = -5024296200146110489L;

	@Override
	protected String getResponse(HttpServletRequest request)
	{
		return null;
	}

}
