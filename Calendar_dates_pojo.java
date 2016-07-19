package model;

public class Calendar_dates_pojo

{
	public String service_id;
	public String date;
	public int exception_type;

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getException_type() {
		return exception_type;
	}

	public void setException_type(int exception_type) {
		this.exception_type = exception_type;
	}

	public void setException_type(String exception_type) {
		this.exception_type = Integer.parseInt(exception_type);
	}

	@Override
	public String toString() {
		String output = "Service_id: " + getService_id() + " - Date: " + getDate() + " - Exception_type:"
				+ getException_type();
		return output;
	}
}
