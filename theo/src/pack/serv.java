package pack;


import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hsqldb.lib.Iterator;

/**
 * Servlet implementation class serv
 */
@WebServlet("/serv")
public class serv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private Facade facade = new Facade();

    /**
     * Default constructor. 
     */
    public serv() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op = request.getParameter("op");


	/*	
		if(op.equals("ajouterpersonne")){
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			if(nom.length()*prenom.length()>0){
			facade.ajoutPersonne(nom,prenom);
			response.getWriter().println("Personne ajoutée");
			}
			request.getRequestDispatcher("ajouterpersonne.html").forward(request,response);
		}*/
		

		
		if(op.equals("lister")){
				ArrayList<Integer> listid = facade.ListeIdFilms(0);
				ArrayList<String> listPochettes = new ArrayList<String>();
				
				Iterator iter = (Iterator) listid.iterator();
				while (iter.hasNext()) {
					listPochettes.add(facade.GetPochette((Integer) iter.next()));
				}
				request.setAttribute("listepersonnes", listPochettes);
				request.getRequestDispatcher("lister.jsp").forward(request,response);
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
