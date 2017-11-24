/**
 * 
 */
package epita.data.access.interfaces;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import epita.green.design.data.Doctor;

/**
 * @author Bhrigu Mahajan
 *
 */
public interface IReadJsonInterface {
	/**
	 * 
	 * @return Map
	 */
	public Map<Long, Doctor> getListById();
	/**
	 * 
	 * @return Map
	 */
	public Map<Long, String> getListofLastNames();
	/**
	 * 
	 * @return Map
	 */
	public Map<Long, String> getListofEmails();
	/**
	 * 
	 * @return Map
	 */
	public Map<Long, String> getListofAddress();
	/**
	 * 
	 * @return Map
	 */
	public Map<Long, String> getListofPhones();
	/**
	 * 
	 * @return Map
	 */
	public Map<Long, String> getListofSpeciality();
	/**
	 * 
	 * @return
	 */
	public Map<Long, String> getListofFirstNames();
	List<Doctor> getListOfDoctors()
			throws ParseException, org.json.simple.parser.ParseException;

}
