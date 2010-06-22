package br.usp.labes.memoriavirtual.busca;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.usp.labes.memoriavirtual.util.*;

/**
 * Servlet implementation class BuscaSimplesServlet
 */
public class BuscaSimplesServlet extends HttpServlet implements ConfigServlet{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscaSimplesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				
		ResultSet rs = null;		
		int operation = Integer.parseInt(request.getParameter("operation"));
		BuscarSimples buscarSimples = null;
		
		
		switch (operation) {
			case SEARCH:
				buscarSimples = new BuscarSimples();				
				String busca = request.getParameter("busca");
				String test = request.getParameter("busca");
				
				try {
					Interceptor.filter(busca);
				} catch (Exception e) {
					//response.sendRedirect("jsp/message.jsp?id=1");
				}
				
				buscarSimples.setBusca(busca);
				
				try {
					rs = buscarSimples.Buscar();
				} catch (Exception e) {
					//response.sendRedirect("jsp/message.jsp?id=1");
				}
				
				request.setAttribute("busca",rs);	
				request.setAttribute("Mensagem",test);
				
				break;
							
		}
		
		try {
			request.getRequestDispatcher("jsp/displayResultList.jsp").forward(request, response);
		} 
		catch (Exception e) {
			//response.sendRedirect("jsp/message.jsp?id=3");
		}
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
