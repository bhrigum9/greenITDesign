package epita.green.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epita.green.data.access.SearchUI;
import epita.green.design.data.Doctor;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ")
				.append(request.getContextPath());
		String search = request.getParameter("search");
		String field = request.getParameter("field");
		String msg = request.getParameter("msg");

		// System.out.println(search + " " + field );

		List<Doctor> results = new ArrayList<Doctor>();

		if (search == null || field == null || search.isEmpty()
				|| field.isEmpty()) {
			results = new ArrayList<Doctor>();
		} else {
			try {
				results = searchByField(search, field);
			} catch (Exception e) {
				// TODO
			}
		}

		if (results.isEmpty()) {
			msg = "No doctors foud.";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("identities", results);

		// Forward to list.jsp
		try {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private List<Doctor> searchByField(String search, String field)
			throws ParseException, org.json.simple.parser.ParseException {
		List<Doctor> results;
		SearchUI searchUI = new SearchUI();

		switch (field) {
			case "firstName" :
				results = searchUI.getDataByFirstName(search);
				break;
			case "lasstName" :
				results = searchUI.getDataByLastName(search);
				break;
			case "address" :
				results = searchUI.getDataByAddress(search);;
				break;
			case "phoneNumber" :
				results = searchUI.getDataByPhone(search);
				break;
			case "email" :
				results = searchUI.getDataByEmail(search);
				break;
			case "speciality" :
				results = searchUI.getDataBySpeciality(search);
				break;
			default :
				results = new ArrayList<Doctor>();
		}
		return results;
	}

}
