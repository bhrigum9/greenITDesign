package epita.green.data.access;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import epita.green.design.data.Days;
import epita.green.design.data.Doctor;
import epita.green.design.data.Openings;

/**
 * @author Bhrigu Mahajan
 *
 */
public class ReadJson {
	/**
	 * 
	 * @param args
	 * @throws ParseException
	 * @throws org.json.simple.parser.ParseException
	 */
	@SuppressWarnings("unchecked")
	public List<Doctor> getListOfDoctors()
			throws ParseException, org.json.simple.parser.ParseException {

		JSONParser parser = new JSONParser();
		List<Doctor> doctorList = new ArrayList<>();
		Map<Long, String> firstNameMap = new HashMap<>();
		Map<Long, String> lastNameMap = new HashMap<>();
		Map<Long, String> addressMap = new HashMap<>();
		Map<Long, String> emailMap = new HashMap<>();
		Map<Long, String> phoneMap = new HashMap<>();
		Map<Long, String> specialityMap = new HashMap<>();

		/*
		 * List<String> firstnameList= new ArrayList<>(); List<String>
		 * lastnameList= new ArrayList<>(); List<String> addressList= new
		 * ArrayList<>(); List<String> emailList= new ArrayList<>();
		 * List<String> phoneList= new ArrayList<>(); List<String>
		 * specialityList= new ArrayList<>();
		 */

		try {
			Object obj = parser.parse(getReaderFromFile());
			List<Object> doc = (List<Object>) (obj);
			for (int i = 0; i < doc.size(); ++i) {
				JSONObject rec = (JSONObject) doc.get(i);
				Long id = (Long) rec.get("id");
				String firstName = (String) rec.get("first_name");
				String lastName = (String) rec.get("last_name");
				String email = (String) rec.get("email");
				String gender = (String) rec.get("gender");
				String address = (String) rec.get("address");
				String city = (String) rec.get("city");
				String phone = (String) rec.get("phone");
				String speciality = (String) rec.get("specialty");
				String image = (String) rec.get("image");

				// System.out.println(rec.get("openings").toString());
				Openings opening = new Openings();
				if (!rec.get("openings").toString().isEmpty()) {
					// Object jsonOpenings = null;
					JSONArray jsonOpenings = (JSONArray) rec.get("openings");
					// System.out.println(jsonOpenings.toString());

					if (!(jsonOpenings.equals(null))) {
						for (int k = 0; k < jsonOpenings.size(); ++k) {
							JSONObject objects = (JSONObject) jsonOpenings
									.get(0);
							for (int j = 0; j < objects.size(); j++) {
								List<Days> day = new ArrayList<>();
								for (int m = 1; m < 8; m++) {
									List<Days> daysList = new ArrayList<>();
									JSONArray array = new JSONArray();
									String getDay = getDayOfWeek(m);
									array.add(objects.get(getDay));
									daysList.add(setDayList(array, getDay));
									// System.out.println(daysList.toString());
									day.addAll(daysList);
								}
								opening.setDays(day);
							}
						}
					}
					firstNameMap.put(id, firstName);
					lastNameMap.put(id, lastName);
					addressMap.put(id, address);
					emailMap.put(id, email);
					phoneMap.put(id, phone);
					specialityMap.put(id, speciality);

					/*
					 * lastnameList.add(lastName); addressList.add(address);
					 * emailList.add(email); phoneList.add(phone);
					 * specialityList.add(speciality)
					 */;
					// System.out.println(opening.toString());
				}
				Doctor doctor = transformDoctor(id, firstName, lastName, email,
						gender, address, city, phone, speciality, image,
						opening);
				doctorList.add(doctor);
			}
			// System.out.print(Arrays.asList(doctorList).toString() );
			// System.out.print(Arrays.asList(firstNameMap).toString());
			// System.out.print(Arrays.asList(lastNameMap).toString());
			// System.out.print(Arrays.asList(addressMap).toString());
			// System.out.print(Arrays.asList(emailMap).toString());
			//
			// System.out.print("\n\n\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doctorList;
	}

	@SuppressWarnings("unchecked")
	public Map<Long, Doctor> getListById() {

		JSONParser parser = new JSONParser();
		Map<Long, Doctor> listById = new HashMap<>();
		Object obj;
		try {
			obj = parser.parse(getReaderFromFile());
			List<Object> doc = (List<Object>) (obj);
			for (int i = 0; i < doc.size(); ++i) {
				JSONObject rec = (JSONObject) doc.get(i);
				Long id = (Long) rec.get("id");
				String firstName = (String) rec.get("first_name");
				String lastName = (String) rec.get("last_name");
				String email = (String) rec.get("email");
				String gender = (String) rec.get("gender");
				String address = (String) rec.get("address");
				String city = (String) rec.get("city");
				String phone = (String) rec.get("phone");
				String speciality = (String) rec.get("specialty");

				String image = null;
				Openings opening = null;
				Doctor doctor = transformDoctor(id, firstName, lastName, email,
						gender, address, city, phone, speciality, image,
						opening);
				listById.put(id, doctor);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listById;

	}

	@SuppressWarnings("unchecked")
	public Map<Long, String> getListofFirstNames() {

		JSONParser parser = new JSONParser();
		Map<Long, String> firstNameMap = new HashMap<>();
		Object obj;
		try {

			obj = parser.parse(getReaderFromFile());
			List<Object> doc = (List<Object>) (obj);
			for (int i = 0; i < doc.size(); ++i) {
				JSONObject rec = (JSONObject) doc.get(i);
				Long id = (Long) rec.get("id");
				String firstName = (String) rec.get("first_name");
				firstNameMap.put(id, firstName);

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return firstNameMap;

	}

	@SuppressWarnings("unchecked")
	public Map<Long, String> getListofLastNames() {

		JSONParser parser = new JSONParser();
		Map<Long, String> lastNameMap = new HashMap<>();
		Object obj;
		try {
			obj = parser.parse(getReaderFromFile());
			List<Object> doc = (List<Object>) (obj);
			for (int i = 0; i < doc.size(); ++i) {
				JSONObject rec = (JSONObject) doc.get(i);
				Long id = (Long) rec.get("id");
				String lastName = (String) rec.get("last_name");
				lastNameMap.put(id, lastName);

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lastNameMap;

	}

	@SuppressWarnings("unchecked")
	public Map<Long, String> getListofAddress() {

		JSONParser parser = new JSONParser();
		Map<Long, String> addressMap = new HashMap<>();
		Object obj;
		try {
			obj = parser.parse(getReaderFromFile());
			List<Object> doc = (List<Object>) (obj);
			for (int i = 0; i < doc.size(); ++i) {
				JSONObject rec = (JSONObject) doc.get(i);
				Long id = (Long) rec.get("id");
				String address = (String) rec.get("address");
				addressMap.put(id, address);

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addressMap;

	}

	@SuppressWarnings("unchecked")
	public Map<Long, String> getListofEmails() {

		JSONParser parser = new JSONParser();
		Map<Long, String> emailMap = new HashMap<>();
		Object obj;
		try {
			obj = parser.parse(getReaderFromFile());
			List<Object> doc = (List<Object>) (obj);
			for (int i = 0; i < doc.size(); ++i) {
				JSONObject rec = (JSONObject) doc.get(i);
				Long id = (Long) rec.get("id");
				String email = (String) rec.get("email");
				emailMap.put(id, email);

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emailMap;

	}

	@SuppressWarnings("unchecked")
	public Map<Long, String> getListofPhones() {

		JSONParser parser = new JSONParser();
		Map<Long, String> phoneMap = new HashMap<>();
		Object obj;
		try {
			obj = parser.parse(getReaderFromFile());
			List<Object> doc = (List<Object>) (obj);
			for (int i = 0; i < doc.size(); ++i) {
				JSONObject rec = (JSONObject) doc.get(i);
				Long id = (Long) rec.get("id");
				String phone = (String) rec.get("phone");
				phoneMap.put(id, phone);

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phoneMap;

	}

	@SuppressWarnings("unchecked")
	public Map<Long, String> getListofSpeciality() {

		JSONParser parser = new JSONParser();
		Map<Long, String> specialityMap = new HashMap<>();
		Object obj;
		try {
			obj = parser.parse(getReaderFromFile());
			List<Object> doc = (List<Object>) (obj);
			for (int i = 0; i < doc.size(); ++i) {
				JSONObject rec = (JSONObject) doc.get(i);
				Long id = (Long) rec.get("id");
				String speciality = (String) rec.get("specialty");
				specialityMap.put(id, speciality);

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return specialityMap;

	}
	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param gender
	 * @param address
	 * @param city
	 * @param phone
	 * @param speciality
	 * @param image
	 * @param opening
	 * @return
	 */
	private static Doctor transformDoctor(Long id, String firstName,
			String lastName, String email, String gender, String address,
			String city, String phone, String speciality, String image,
			Openings opening) {
		Doctor doctor = new Doctor();
		doctor.setId(id);
		doctor.setFirstName(firstName);
		doctor.setLastName(lastName);
		doctor.setEmailId(email);
		doctor.setGender(gender);
		doctor.setAddress(address);
		doctor.setCity(city);
		doctor.setPhone(phone);
		doctor.setSpeciality(speciality);
		doctor.setOpenings(opening);
		doctor.setImage(image);
		return doctor;
	}

	private static Days setDayList(JSONArray array, String getDay) {
		Days days = new Days();
		days.setDay(getDay);
		JSONObject jsonValue = (JSONObject) array.get(0);
		if (jsonValue != null) {
			String closeTime = (String) jsonValue.get("close");
			String openTime = (String) jsonValue.get("open");
			if (!closeTime.isEmpty()) {
				days.setCloseTime(closeTime);
			}

			if (!openTime.isEmpty()) {
				days.setOpen(openTime);
			}
		} else {
			days.setOpen("N/A");
			days.setCloseTime("N/A");
		}
		return days;
	}

	private static String getDayOfWeek(int value) {
		String day = "";
		switch (value) {
			case 1 :
				day = "mon";
				break;
			case 2 :
				day = "tue";
				break;
			case 3 :
				day = "wed";
				break;
			case 4 :
				day = "thu";
				break;
			case 5 :
				day = "fri";
				break;
			case 6 :
				day = "sat";
				break;
			case 7 :
				day = "sun";
				break;
		}
		return day;
	}
	private Reader getReaderFromFile() {
		InputStream stream = this.getClass()
				.getResourceAsStream("/designgreendata.json");
		Reader reader = new InputStreamReader(stream);
		return reader;
	}
}
