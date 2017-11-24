package epita.data.access.interfaces;

import java.text.ParseException;
import java.util.List;

import epita.green.design.data.Doctor;

public interface ISearchUIInterface {
	/**
	 * 
	 * @param firstName
	 * @return
	 * @throws ParseException
	 * @throws org.json.simple.parser.ParseException
	 */
	public List<Doctor> getDataByFirstName(String firstName)
			throws ParseException, org.json.simple.parser.ParseException;
	/**
	 * 
	 * @param lastName
	 * @return
	 * @throws ParseException
	 * @throws org.json.simple.parser.ParseException
	 */
	public List<Doctor> getDataByLastName(String lastName)
			throws ParseException, org.json.simple.parser.ParseException;
	/**
	 * 
	 * @param address
	 * @return
	 * @throws ParseException
	 * @throws org.json.simple.parser.ParseException
	 */
	public List<Doctor> getDataByAddress(String address)
			throws ParseException, org.json.simple.parser.ParseException;
	/**
	 * 
	 * @param phone
	 * @return
	 * @throws ParseException
	 * @throws org.json.simple.parser.ParseException
	 */
	public List<Doctor> getDataByPhone(String phone)
			throws ParseException, org.json.simple.parser.ParseException;
	/**
	 * 
	 * @param email
	 * @return
	 * @throws ParseException
	 * @throws org.json.simple.parser.ParseException
	 */
	public List<Doctor> getDataByEmail(String email)
			throws ParseException, org.json.simple.parser.ParseException;
	/**
	 * 
	 * @param speciality
	 * @return
	 * @throws ParseException
	 * @throws org.json.simple.parser.ParseException
	 */
	public List<Doctor> getDataBySpeciality(String speciality)
			throws ParseException, org.json.simple.parser.ParseException;

}
