package epita.green.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		List<Doctor> results;

		if (search == null || field == null || search.isEmpty()
				|| field.isEmpty()) {
			results = searchByField("", "");
		} else {
			results = searchByField(search, field);
		}

		if (results.isEmpty()) {
			msg = "No identities foud.";
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

	private List<Doctor> searchByField(String search, String field) {

		List<Doctor> results = new ArrayList<Doctor>();
		Doctor doc1 = new Doctor();
		doc1.setFirstName("Ambrose");
		doc1.setLastName("Skouling");
		doc1.setEmailId("askouling0@alexa.com");
		doc1.setGender("Male");
		doc1.setAddress("69071 Oak Avenue");
		doc1.setCity("Juan L. Lacaze");
		doc1.setPhone("909-881-4802");
		doc1.setImage("");
		doc1.setSpeciality("Pediatric Dentistry");

		results.add(doc1);

		doc1 = new Doctor();
		doc1.setFirstName("Lise");
		doc1.setLastName("Hazlewood");
		doc1.setEmailId("lhazlewood1@cmu.edu");
		doc1.setGender("Female");
		doc1.setAddress("41 Fairview Hill");
		doc1.setCity("Danghara");
		doc1.setPhone("532-324-9726");
		doc1.setImage(
				"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABGdBTUEAAK/INwWK6QAAABl0RVh0U29mdHdhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAAGMSURBVDjLY/j//z8DJZiggtx9Sasyd8Yxk21Axo7YSymbow4QZUDJ8QyHoiNpB/IPJP/P3pPwP3177P+mQ5X/6/aV/o9cFrATrwHFxzIcCg+nnplzacr/TbdW/19/c8X/tTeW/l91bdH/5Vfn/y/ZkvPfb7rbHZwGFBxKnTn9fN//jTdX/W8+XPU/cX34/5iVQf8rtuf/L9mc/d9nqutuvC7I2Zv4AOjf/0D//o9fG3YIJh4wy+OS9xTnQ2699kyO7VacRAUi0L/wUPea5LTGtceW9FgA+ncNyekgfJEfZ9AcTyagfw+59ztcgolbVBsdMi7V/a+Xr/lfK0v1AV4XAP27O2tl0v/UJbH/rRtM/5tVGf6PmB74v/dE0//khdH/VVMUZ+I0AOjflxnLE/5PP9v7f8rprv8TT7X/7zvZ8r/nRON/kLhKssIZxXhZB7wusGu22Bk3N+x/1Mzg//qFWv+1s9X+q6cp/1dOUjigEIeqGWcgAv17AOjfS2RnJt08DWbNTNVVVMmNhDAANau2t3wToKQAAAAASUVORK5CYII=");
		doc1.setSpeciality("Pediatric Dentistry");

		results.add(doc1);

		return results;
	}

}
