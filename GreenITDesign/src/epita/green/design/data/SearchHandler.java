package epita.green.design.data;

import java.util.List;
import java.util.Map;

import epita.green.data.access.ReadJson;

/**
 * @author Bhrigu Mahajan
 *
 */
public class SearchHandler {

	public List<Doctor> getSearchResult() {
		List<Doctor> doctorList = null;
		try {
			ReadJson readJson = new ReadJson();
			doctorList = readJson.getListOfDoctors();
			doctorList.get(0);
			// System.out.println(doctorList.get(0).toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return doctorList;

	}

	public Map<Long, String> getByFirstName() {

		ReadJson readJson = new ReadJson();
		Map<Long, String> firstNameMap = readJson.getListofFirstNames();
		System.out.println(firstNameMap.toString());
		return firstNameMap;

	}

	public Map<Long, String> getByLastName() {

		ReadJson readJson = new ReadJson();
		Map<Long, String> lastNameMap = readJson.getListofLastNames();
		System.out.println(lastNameMap.toString());
		return lastNameMap;

	}
	
	public Map<Long, String> getByAddress() {

		ReadJson readJson = new ReadJson();
		Map<Long, String> addressMap = readJson.getListofAddress();
		System.out.println(addressMap.toString());
		return addressMap;

	}
	public Map<Long, String> getByEmail() {

		ReadJson readJson = new ReadJson();
		Map<Long, String> emailMap = readJson.getListofEmails();
		System.out.println(emailMap.toString());
		return emailMap;

	}
	public Map<Long, String> getByPhone() {

		ReadJson readJson = new ReadJson();
		Map<Long, String> phoneMap = readJson.getListofPhones();
		System.out.println(phoneMap.toString());
		return phoneMap;

	}
	public Map<Long, String> getBySpeciality() {

		ReadJson readJson = new ReadJson();
		Map<Long, String> specialityMap = readJson.getListofSpeciality();
		System.out.println(specialityMap.toString());
		return specialityMap;

	}

	
}
