package epita.green.data.access;

import java.text.ParseException;
import java.util.List;

import epita.green.design.data.Doctor;

public class SearchUI {

	public List<Doctor> getDataByFirstName(String firstName) throws ParseException, org.json.simple.parser.ParseException {
		SearchImpl searchImpl = new SearchImpl();
		List<Doctor> doctorsSearched = searchImpl.compareById(firstName,"","","","","");
		return doctorsSearched;

	}
	public List<Doctor> getDataByLastName(String lastName) throws ParseException, org.json.simple.parser.ParseException {
		SearchImpl searchImpl = new SearchImpl();
		List<Doctor> doctorsSearched = searchImpl.compareById("",lastName,"","","","");
		return doctorsSearched;

	}
	
	public List<Doctor> getDataByAddress(String address) throws ParseException, org.json.simple.parser.ParseException {
		SearchImpl searchImpl = new SearchImpl();
		List<Doctor> doctorsSearched = searchImpl.compareById("","",address,"","","");
		return doctorsSearched;

	}
	
	public List<Doctor> getDataByPhone(String phone) throws ParseException, org.json.simple.parser.ParseException {
		SearchImpl searchImpl = new SearchImpl();
		List<Doctor> doctorsSearched = searchImpl.compareById("","","",phone,"","");
		return doctorsSearched;
	}

	public List<Doctor> getDataByEmail(String email) throws ParseException, org.json.simple.parser.ParseException {
		SearchImpl searchImpl = new SearchImpl();
		List<Doctor> doctorsSearched = searchImpl.compareById("","","","",email,"");
		return doctorsSearched;
	}

	public List<Doctor> getDataBySpeciality(String speciality) throws ParseException, org.json.simple.parser.ParseException {
		SearchImpl searchImpl = new SearchImpl();
		List<Doctor> doctorsSearched = searchImpl.compareById("","","","","",speciality);
		return doctorsSearched;
	}

	
	public static void main(String a[]) throws ParseException, org.json.simple.parser.ParseException {

		SearchUI searchUI = new SearchUI();
		try {
			//List<Doctor> docByFirstName=searchUI.getDataByFirstName("A");
			//List<Doctor> docBySecondName=searchUI.getDataByLastName("A");
			//List<Doctor> docByAddress=searchUI.getDataByAddress("96");
			List<Doctor> docByPhone=searchUI.getDataByPhone("538");
			//List<Doctor> docBySpeciality=searchUI.getDataBySpeciality("A");
			//List<Doctor> docByEmail=searchUI.getDataByEmail("e");

			
			
			System.out.println(docByPhone.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
