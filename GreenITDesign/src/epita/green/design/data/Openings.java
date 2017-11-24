package epita.green.design.data;

import java.util.List;

/**
 * @author Bhrigu Mahajan
 *
 */
public class Openings {

	private List<Days> days;

	/**
	 * @return the days
	 */
	public List<Days> getDays() {
		return days;
	}

	/**
	 * @param days
	 *            the days to set
	 */
	public void setDays(List<Days> days) {
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
