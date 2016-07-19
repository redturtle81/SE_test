package model;

public class Frequencies_pojo {
	private String trip_id;
	private String start_time;
	private String end_time;
	private int headway_secs;

	public String getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public int getHeadway_secs() {
		return headway_secs;
	}

	public void setHeadway_secs(int headway_secs) {
		this.headway_secs = headway_secs;
	}

	public void setHeadway_secs(String headway_secs) {
		this.headway_secs = Integer.parseInt(headway_secs);
	}

	@Override
	public String toString() {
		return trip_id;
	}
}
