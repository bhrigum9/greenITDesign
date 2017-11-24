package epita.green.design.data;

import java.util.List;
import java.util.Map;

import epita.data.access.interfaces.IReadJsonInterface;
import epita.green.data.access.ReadJson;

/**
 * @author Bhrigu Mahajan ----Handler class to handle methods
 *
 */
public class SearchHandler {
	public IReadJsonInterface readJsonInterface;

	/**
	 * 
	 * @return List of Object
	 */
	public List<Doctor> getSearchResult() {
		List<Doctor> doctorList = null;
		try {
			readJsonInterface = new ReadJson();
			doctorList = readJsonInterface.getListOfDoctors();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return doctorList;

	}
	/**
	 * 
	 * @return Map
	 */
	public Map<Long, String> getByFirstName() {

		readJsonInterface = new ReadJson();
		Map<Long, String> firstNameMap = readJsonInterface
				.getListofFirstNames();
		return firstNameMap;

	}
	/**
	 * 
	 * @return Map
	 */
	public Map<Long, String> getByLastName() {

		readJsonInterface = new ReadJson();
		Map<Long, String> lastNameMap = readJsonInterface.getListofLastNames();
		return lastNameMap;

	}
	/**
	 * 
	 * @return Map
	 */
	public Map<Long, String> getByAddress() {

		readJsonInterface = new ReadJson();
		Map<Long, String> addressMap = readJsonInterface.getListofAddress();
		return addressMap;

	}
	/**
	 * 
	 * @return Map
	 */
	public Map<Long, String> getByEmail() {

		readJsonInterface = new ReadJson();
		Map<Long, String> emailMap = readJsonInterface.getListofEmails();
		return emailMap;

	}
	/**
	 * 
	 * @return Map
	 */
	public Map<Long, String> getByPhone() {

		readJsonInterface = new ReadJson();
		Map<Long, String> phoneMap = readJsonInterface.getListofPhones();
		return phoneMap;

	}
	/**
	 * 
	 * @return Map
	 */
	public Map<Long, String> getBySpeciality() {

		readJsonInterface = new ReadJson();
		Map<Long, String> specialityMap = readJsonInterface
				.getListofSpeciality();
		return specialityMap;

	}

}
