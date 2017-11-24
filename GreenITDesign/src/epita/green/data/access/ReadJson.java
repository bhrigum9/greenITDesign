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

import epita.data.access.interfaces.IReadJsonInterface;
import epita.green.design.data.Doctor;
import epita.green.design.data.Openings;
import epita.green.statics.design.methods.ReadJsonStaticMethods;

/**
 * @author Bhrigu Mahajan --This class is used to prepare memory map(Database)
 *
 */
public class ReadJson implements IReadJsonInterface {
	/**
	 * 
	 * @param args
	 * @throws ParseException
	 * @throws org.json.simple.parser.ParseException
	 */
	@SuppressWarnings("unchecked")
	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see epita.data.access.interfaces.IReadJsonInterface#getListOfDoctors()
	 */
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

					if (!(rec.get("openings").equals(""))) {
						JSONArray openingss = (JSONArray) rec.get("openings");
						System.out.println(openingss);
						JSONObject size = (JSONObject) openingss.get(0);
						int k = size.keySet().size();
						String day = "";
						List<String> days = new ArrayList<>();
						for (int j = 1; j < 8; j++) {
							if (size.containsKey(
									ReadJsonStaticMethods.getDayOfWeek(j))) {
								day = ReadJsonStaticMethods.getDayOfWeek(j);
								JSONObject timings = (JSONObject) size.get(
										ReadJsonStaticMethods.getDayOfWeek(j));
								String timing = timings.toString();
								System.out.println(timings);
							}
							days.add(day);
						}
						opening.setDays(days);
					}
					firstNameMap.put(id, firstName);
					lastNameMap.put(id, lastName);
					addressMap.put(id, address);
					emailMap.put(id, email);
					phoneMap.put(id, phone);
					specialityMap.put(id, speciality);

				}
				Doctor doctor = ReadJsonStaticMethods.transformDoctor(id,
						firstName, lastName, email, gender, address, city,
						phone, speciality, image, opening);
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
	/*
	 * (non-Javadoc)
	 * 
	 * @see epita.data.access.interfaces.IReadJsonInterface#getListById()
	 */
	@SuppressWarnings("unchecked")
	@Override
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
				String image = (String) rec.get("image");
				Openings opening = new Openings();
				if (!(rec.get("openings").equals(""))) {
					JSONArray openingss = (JSONArray) rec.get("openings");
					System.out.println(openingss);
					JSONObject size = (JSONObject) openingss.get(0);
					String day = "";
					List<String> days = new ArrayList<>();
					StringBuffer sb = new StringBuffer();
					for (int j = 1; j < 8; j++) {
						if (size.containsKey(
								ReadJsonStaticMethods.getDayOfWeek(j))) {
							day = ReadJsonStaticMethods.getDayOfWeek(j);
							JSONObject timings = (JSONObject) size
									.get(ReadJsonStaticMethods.getDayOfWeek(j));
							String timing = timings.toString();
							sb.append(day);
							sb.append("-");
							sb.append(timing);
						}
					}
					days.add(sb.toString());
					opening.setDays(days);
				}
				Doctor doctor = ReadJsonStaticMethods.transformDoctor(id,
						firstName, lastName, email, gender, address, city,
						phone, speciality, image, opening);
				listById.put(id, doctor);

			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return listById;

	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * epita.data.access.interfaces.IReadJsonInterface#getListofFirstNames()
	 */
	@SuppressWarnings("unchecked")
	@Override
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
	/*
	 * (non-Javadoc)
	 * 
	 * @see epita.data.access.interfaces.IReadJsonInterface#getListofLastNames()
	 */
	@SuppressWarnings("unchecked")
	@Override
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
	/*
	 * (non-Javadoc)
	 * 
	 * @see epita.data.access.interfaces.IReadJsonInterface#getListofAddress()
	 */
	@SuppressWarnings("unchecked")
	@Override
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
	/*
	 * (non-Javadoc)
	 * 
	 * @see epita.data.access.interfaces.IReadJsonInterface#getListofEmails()
	 */
	@SuppressWarnings("unchecked")
	@Override
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
	/*
	 * (non-Javadoc)
	 * 
	 * @see epita.data.access.interfaces.IReadJsonInterface#getListofPhones()
	 */
	@SuppressWarnings("unchecked")
	@Override
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
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * epita.data.access.interfaces.IReadJsonInterface#getListofSpeciality()
	 */
	@SuppressWarnings("unchecked")
	@Override
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
