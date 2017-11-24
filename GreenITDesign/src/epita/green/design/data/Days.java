package epita.green.design.data;

public class Days {
	private String day;
	private String open;
	private String close;
	private String timings;
	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}
	/**
	 * @param day
	 *            the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}
	/**
	 * @return the open
	 */
	public String getOpen() {
		return open;
	}
	/**
	 * @param open
	 *            the open to set
	 */
	public void setOpen(String open) {
		this.open = open;
	}
	/**
	 * @return the close
	 */
	public String getClose() {
		return close;
	}
	/**
	 * @param close
	 *            the close to set
	 */
	public void setClose(String close) {
		this.close = close;
	}
	/**
	 * @return the timings
	 */
	public String getTimings() {
		return timings;
	}
	/**
	 * @param timings
	 *            the timings to set
	 */
	public void setTimings(String timings) {
		this.timings = timings;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Days [day=" + day + ", open=" + open + ", closeTime=" + close
				+ "]";
	}

}
