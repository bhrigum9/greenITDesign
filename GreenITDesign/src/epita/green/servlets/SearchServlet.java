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

import epita.data.access.interfaces.ISearchUIInterface;
import epita.green.data.access.SearchUI;
import epita.green.design.data.Doctor;

/**
 * @author Vanessa--- Servlet implementation class SearchServlet
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ISearchUIInterface searchUIInterface;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ")
				.append(request.getContextPath());
		String search = request.getParameter("search");
		String field = request.getParameter("field");
		String msg = request.getParameter("msg");

		List<Doctor> results = new ArrayList<Doctor>();

		if (search == null || field == null || search.isEmpty()
				|| field.isEmpty()) {
			results = new ArrayList<Doctor>();
		} else {
			try {
				results = searchByField(search, field);
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		if (results.isEmpty()) {
			msg = "No doctors found.";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("identities", results);

		// Forward to list.jsp
		try {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @param search
	 * @param field
	 * @return
	 * @throws ParseException
	 * @throws org.json.simple.parser.ParseException
	 */
	private List<Doctor> searchByField(String search, String field)
			throws ParseException, org.json.simple.parser.ParseException {
		List<Doctor> results;
		searchUIInterface = new SearchUI();

		switch (field) {
			case "firstName" :
				results = searchUIInterface.getDataByFirstName(search);
				break;
			case "lasstName" :
				results = searchUIInterface.getDataByLastName(search);
				break;
			case "address" :
				results = searchUIInterface.getDataByAddress(search);;
				break;
			case "phoneNumber" :
				results = searchUIInterface.getDataByPhone(search);
				break;
			case "email" :
				results = searchUIInterface.getDataByEmail(search);
				break;
			case "speciality" :
				results = searchUIInterface.getDataBySpeciality(search);
				break;
			default :
				results = new ArrayList<Doctor>();
		}
		return results;
	}
	/**
	 * @return the searchUIInterface
	 */
	public ISearchUIInterface getSearchUIInterface() {
		return searchUIInterface;
	}
	/**
	 * @param searchUIInterface
	 *            the searchUIInterface to set
	 */
	public void setSearchUIInterface(ISearchUIInterface searchUIInterface) {
		this.searchUIInterface = searchUIInterface;
	}

}
