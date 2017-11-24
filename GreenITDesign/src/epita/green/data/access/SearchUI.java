package epita.green.data.access;

import java.text.ParseException;
import java.util.List;

import epita.data.access.interfaces.ISearchImplInterface;
import epita.data.access.interfaces.ISearchUIInterface;
import epita.green.design.data.Doctor;

public class SearchUI implements ISearchUIInterface {

	public ISearchImplInterface searchImplInterface;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * epita.data.access.interfaces.ISearchUIInterface#getDataByFirstName(java.
	 * lang.String)
	 */
	@Override
	public List<Doctor> getDataByFirstName(String firstName)
			throws ParseException, org.json.simple.parser.ParseException {
		searchImplInterface = new SearchImpl();
		List<Doctor> doctorsSearched = searchImplInterface
				.compareById(firstName, "", "", "", "", "");
		return doctorsSearched;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * epita.data.access.interfaces.ISearchUIInterface#getDataByLastName(java.
	 * lang.String)
	 */
	@Override
	public List<Doctor> getDataByLastName(String lastName)
			throws ParseException, org.json.simple.parser.ParseException {
		SearchImpl searchImpl = new SearchImpl();
		List<Doctor> doctorsSearched = searchImpl.compareById("", lastName, "",
				"", "", "");
		return doctorsSearched;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * epita.data.access.interfaces.ISearchUIInterface#getDataByAddress(java.
	 * lang.String)
	 */
	@Override
	public List<Doctor> getDataByAddress(String address)
			throws ParseException, org.json.simple.parser.ParseException {
		SearchImpl searchImpl = new SearchImpl();
		List<Doctor> doctorsSearched = searchImpl.compareById("", "", address,
				"", "", "");
		return doctorsSearched;

	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * epita.data.access.interfaces.ISearchUIInterface#getDataByPhone(java.lang.
	 * String)
	 */
	@Override
	public List<Doctor> getDataByPhone(String phone)
			throws ParseException, org.json.simple.parser.ParseException {
		SearchImpl searchImpl = new SearchImpl();
		List<Doctor> doctorsSearched = searchImpl.compareById("", "", "", phone,
				"", "");
		return doctorsSearched;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * epita.data.access.interfaces.ISearchUIInterface#getDataByEmail(java.lang.
	 * String)
	 */
	@Override
	public List<Doctor> getDataByEmail(String email)
			throws ParseException, org.json.simple.parser.ParseException {
		SearchImpl searchImpl = new SearchImpl();
		List<Doctor> doctorsSearched = searchImpl.compareById("", "", "", "",
				email, "");
		return doctorsSearched;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * epita.data.access.interfaces.ISearchUIInterface#getDataBySpeciality(java.
	 * lang.String)
	 */
	@Override
	public List<Doctor> getDataBySpeciality(String speciality)
			throws ParseException, org.json.simple.parser.ParseException {
		SearchImpl searchImpl = new SearchImpl();
		List<Doctor> doctorsSearched = searchImpl.compareById("", "", "", "",
				"", speciality);
		return doctorsSearched;
	}
	/**
	 * @return the searchImplInterface
	 */
	public ISearchImplInterface getSearchImplInterface() {
		return searchImplInterface;
	}
	/**
	 * @param searchImplInterface
	 *            the searchImplInterface to set
	 */
	public void setSearchImplInterface(
			ISearchImplInterface searchImplInterface) {
		this.searchImplInterface = searchImplInterface;
	}
}
