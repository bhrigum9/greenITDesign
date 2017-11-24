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

				Openings opening = new Openings();
				if (!rec.get("openings").toString().isEmpty()) {
					JSONArray jsonOpenings = (JSONArray) rec.get("openings");

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

				}
				Doctor doctor = transformDoctor(id, firstName, lastName, email,
						gender, address, city, phone, speciality, image,
						opening);
				doctorList.add(doctor);
			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return doctorList;
	}
	/**
	 * 
	 * @return Map
	 */
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
			System.out.println(e);
			e.printStackTrace();
		}
		return listById;

	}
	/**
	 * 
	 * @return Map
	 */
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
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return firstNameMap;

	}
	/**
	 * 
	 * @return Map
	 */
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
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return lastNameMap;

	}
	/**
	 * 
	 * @return Map
	 */
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
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return addressMap;

	}
	/**
	 * 
	 * @return Map
	 */
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
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return emailMap;

	}
	/**
	 * 
	 * @return Map
	 */
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
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return phoneMap;

	}
	/**
	 * 
	 * @return Map
	 */
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
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			System.out.println(e);
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
	 * @return Object
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
	/**
	 * 
	 * @param array
	 * @param getDay
	 * @return Object
	 */
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
	/**
	 * 
	 * @param value
	 * @return String
	 */
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

	/**
	 * 
	 * @return Object
	 */
	private Reader getReaderFromFile() {
		InputStream stream = this.getClass()
				.getResourceAsStream("/designgreendata.json");
		Reader reader = new InputStreamReader(stream);
		return reader;
	}
}
