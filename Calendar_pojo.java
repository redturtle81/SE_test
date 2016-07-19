package model;

public class Calendar_pojo {
	private String service_id;
	private int monday;
	private int tuesday;
	private int wednesday;
	private int thursday;
	private int friday;
	private int saturday;
	private int sunday;
	private String start_date;
	private String end_date;

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public int getMonday() {
		return monday;
	}

	public void setMonday(int monday) {
		this.monday = monday;
	}

	public void setMonday(String monday) {
		this.monday = Integer.parseInt(monday);
	}

	public int getTuesday() {
		return tuesday;
	}

	public void setTuesday(int tuesday) {
		this.tuesday = tuesday;
	}

	public void setTuesday(String tuesday) {
		this.tuesday = Integer.parseInt(tuesday);
	}

	public int getWednesday() {
		return wednesday;
	}

	public void setWednesday(int wednesday) {
		this.wednesday = wednesday;
	}

	public void setWednesday(String wednesday) {
		this.wednesday = Integer.parseInt(wednesday);
	}

	public int getThursday() {
		return thursday;
	}

	public void setThursday(int thursday) {
		this.thursday = thursday;
	}

	public void setThursday(String thursday) {
		this.thursday = Integer.parseInt(thursday);
	}

	public int getFriday() {
		return friday;
	}

	public void setFriday(int friday) {
		this.friday = friday;
	}

	public void setFriday(String friday) {
		this.friday = Integer.parseInt(friday);
	}

	public int getSaturday() {
		return saturday;
	}

	public void setSaturday(int saturday) {
		this.saturday = saturday;
	}

	public void setSaturday(String saturday) {
		this.saturday = Integer.parseInt(saturday);
	}

	public int getSunday() {
		return sunday;
	}

	public void setSunday(int sunday) {
		this.sunday = sunday;
	}

	public void setSunday(String sunday) {
		this.sunday = Integer.parseInt(sunday);
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	@Override
	public String toString() {
		String output = "Service_id: " + getService_id() + " - Monday: " + getMonday() + " - Tuesday:" + getTuesday()
				+ " - Thursday: " + getThursday() + " - Friday:" + getFriday() + " - Saturday: " + getSaturday()
				+ " - Sunday:" + getSunday() + " - Start_date: " + getStart_date() + " - End_date:" + getEnd_date();
		return output;
	}
}
