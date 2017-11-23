package epita.green.data.access;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.soap.Text;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
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
	 * @param <K>
	 * @param <V>
	 * @param args
	 * @throws ParseException
	 * @throws org.json.simple.parser.ParseException
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> void main(String[] args) throws ParseException, org.json.simple.parser.ParseException {

		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader("C:\\Users\\Bhrigu Mahajan\\Desktop\\testfile.json"));
			List<Object> doc = (List<Object>) (obj);
			List<Doctor> doctorList = new ArrayList<>();
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

				//System.out.println(rec.get("openings").toString());
				Openings opening = new Openings();
				if (!rec.get("openings").toString().isEmpty()) {
					// Object jsonOpenings = null;
					JSONArray jsonOpenings = (JSONArray) rec.get("openings");
					//System.out.println(jsonOpenings.toString());

					if (!(jsonOpenings.equals(null))) {
						for (int k = 0; k < jsonOpenings.size(); ++k) {
							JSONObject objects = (JSONObject) jsonOpenings.get(0);
							for (int j = 0; j < objects.size(); j++) {
								List<Days> day = new ArrayList<>();
								for (int m = 1; m < 8; m++) {
									List<Days> daysList = new ArrayList<>();
									JSONArray array = new JSONArray();
									String getDay = getDayOfWeek(m);
									array.add(objects.get(getDay));
									daysList.add(setDayList(array, getDay));
									//System.out.println(daysList.toString());
									day.addAll(daysList);
								}
								opening.setDays(day);
							}
						}
					}
					System.out.println(opening.toString());
				}
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
				doctorList.add(doctor);
			}
			System.out.print(Arrays.asList(doctorList).toString() );
			System.out.print("\n\n\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Days setDayList(JSONArray array, String getDay) {
		Days days = new Days();
		days.setDay(getDay);
		JSONObject jsonValue = (JSONObject) array.get(0);
		if (jsonValue!=null) {
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
		case 1:
			day = "mon";
			break;
		case 2:
			day = "tue";
			break;
		case 3:
			day = "wed";
			break;
		case 4:
			day = "thu";
			break;
		case 5:
			day = "fri";
			break;
		case 6:
			day = "sat";
			break;
		case 7:
			day = "sun";
			break;
		}
		return day;
	}
}
