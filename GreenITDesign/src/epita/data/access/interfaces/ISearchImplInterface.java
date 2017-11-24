/**
 * 
 */
package epita.data.access.interfaces;

import java.util.List;

import epita.green.design.data.Doctor;

/**
 * @author Bhrigu Mahajan
 *
 */
public interface ISearchImplInterface {
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param phone
	 * @param email
	 * @param speciality
	 * @return
	 */
	public List<Doctor> compareById(String firstName, String lastName,
			String address, String phone, String email, String speciality);
	/**
	 * 
	 * @param firstName
	 * @return
	 */
	public List<Long> compareFirstName(String firstName);
	/**
	 * 
	 * @param lastName
	 * @return
	 */
	public List<Long> compareLastName(String lastName);
	/**
	 * 
	 * @param address
	 * @return
	 */
	public List<Long> compareAddress(String address);
	/**
	 * 
	 * @param phone
	 * @return
	 */
	public List<Long> comparePhone(String phone);
	/**
	 * 
	 * @param email
	 * @return
	 */
	public List<Long> compareEmail(String email);
	/**
	 * 
	 * @param speciality
	 * @return
	 */
	public List<Long> compareSpeciality(String speciality);

}
