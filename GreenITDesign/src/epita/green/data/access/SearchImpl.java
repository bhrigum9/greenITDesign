package epita.green.data.access;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import epita.green.design.data.Doctor;
import epita.green.design.data.SearchHandler;

/**
 * @author Bhrigu Mahajan
 *
 */
public class SearchImpl {

	public List<Doctor> compareById(String firstName,String lastName,String address,String phone,String email,String speciality) {
		ReadJson readJson = new ReadJson();
		Map<Long, Doctor> docList = readJson.getListById();
		List<Long> ids=null;
		SearchImpl searchImpl = new SearchImpl();
		if (!firstName.isEmpty()) {
			 ids = searchImpl.compareFirstName(firstName);
		}
		else if (!lastName.isEmpty()) {
			 ids = searchImpl.compareLastName(lastName);
		}
		else if (!address.isEmpty()) {
			 ids = searchImpl.compareAddress(address);
		}
		else if (!email.isEmpty()) {
			 ids = searchImpl.compareEmail(email);
		}
		else if (!phone.isEmpty()) {
			 ids = searchImpl.comparePhone(phone);
		}
		else if (!speciality.isEmpty()) {
			 ids = searchImpl.compareSpeciality(speciality);
		}
		List<Doctor> doctors= new ArrayList<>();
		for (Long nameId : ids) {
			docList.get(nameId);
			System.out.println(docList.get(nameId));
			doctors.add(docList.get(nameId));
		}

		return doctors;
	}

	public List<Long> compareFirstName(String firstName) {
		SearchHandler handler = new SearchHandler();
		Map<Long, String> firstNameMap = handler.getByFirstName();
		List<Long> ids = new ArrayList<>();
		for (Entry<Long, String> firstNames : firstNameMap.entrySet()) {
			if (firstNames.getValue().contains(firstName)) {
				Long id = firstNames.getKey();
				System.out.println(id);
				ids.add(id);
			}
		}

		return ids;
	}

	public List<Long> compareLastName(String lastName) {
		SearchHandler handler = new SearchHandler();
		Map<Long, String> firstNameMap = handler.getByLastName();
		List<Long> ids = new ArrayList<>();
		for (Entry<Long, String> firstNames : firstNameMap.entrySet()) {
			if (firstNames.getValue().contains(lastName)) {
				Long id = firstNames.getKey();
				System.out.println(id);
				ids.add(id);
			}
		}

		return ids;
	}

	public List<Long> compareAddress(String address) {
		SearchHandler handler = new SearchHandler();
		Map<Long, String> firstNameMap = handler.getByAddress();
		List<Long> ids = new ArrayList<>();
		for (Entry<Long, String> firstNames : firstNameMap.entrySet()) {
			if (firstNames.getValue().contains(address)) {
				Long id = firstNames.getKey();
				System.out.println(id);
				ids.add(id);
			}
		}

		return ids;
	}

	public List<Long> comparePhone(String phone) {
		SearchHandler handler = new SearchHandler();
		Map<Long, String> firstNameMap = handler.getByPhone();
		List<Long> ids = new ArrayList<>();
		for (Entry<Long, String> firstNames : firstNameMap.entrySet()) {
			if (firstNames.getValue().contains(phone)) {
				Long id = firstNames.getKey();
				System.out.println(id);
				ids.add(id);
			}
		}

		return ids;
	}

	public List<Long> compareEmail(String email) {
		SearchHandler handler = new SearchHandler();
		Map<Long, String> firstNameMap = handler.getByEmail();
		List<Long> ids = new ArrayList<>();
		for (Entry<Long, String> firstNames : firstNameMap.entrySet()) {
			if (firstNames.getValue().contains(email)) {
				Long id = firstNames.getKey();
				System.out.println(id);
				ids.add(id);
			}
		}

		return ids;
	}

	public List<Long> compareSpeciality(String speciality) {
		SearchHandler handler = new SearchHandler();
		Map<Long, String> firstNameMap = handler.getBySpeciality();
		List<Long> ids = new ArrayList<>();
		for (Entry<Long, String> firstNames : firstNameMap.entrySet()) {
			if (firstNames.getValue().contains(speciality)) {
				Long id = firstNames.getKey();
				System.out.println(id);
				ids.add(id);
			}
		}

		return ids;
	}

}
