/**
 * 
 */
package epita.green.statics.design.methods;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import epita.green.design.data.Days;
import epita.green.design.data.Doctor;
import epita.green.design.data.Openings;

/**
 * @author Bhrigu Mahajan
 *
 */
public final class ReadJsonStaticMethods {

	/**
	 * 
	 * @param value
	 * @return String
	 */
	public static String getDayOfWeek(int value) {
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
	 * @param array
	 * @param getDay
	 * @return Object
	 */
	public static Days setDayList(JSONArray array, String getDay) {
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
	public static Doctor transformDoctor(Long id, String firstName,
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
}
