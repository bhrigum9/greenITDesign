package epita.green.design.data;

import java.util.List;

/**
 * @author Bhrigu Mahajan
 *
 */
public class Openings {

	private List<String> days;

	/**
	 * @return the days
	 */
	public List<String> getDays() {
		return days;
	}

	/**
	 * @param days
	 *            the days to set
	 */
	public void setDays(List<String> days) {
		this.days = days;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Openings [days=" + days + "]";
	}

}
