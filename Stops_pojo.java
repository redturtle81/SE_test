package model;

public class Stops_pojo {
	private String stop_id;
	private String stop_code;
	private String stop_name;
	private String stop_desc;
	private double stop_lat;
	private double stop_lon;
	private String stop_elevation;
	private String zone_id;
	private String stop_url;
	private String location_type;
	private String parent_station;
	private String platform_code;
	private String ch_station_long_name;
	private String ch_station_synonym1;
	private String ch_station_synonym2;
	private String ch_station_synonym3;
	private String ch_station_synonym4;

	public String getStop_id() {
		return stop_id;
	}

	public void setStop_id(String stop_id) {
		this.stop_id = stop_id;
	}

	public String getStop_code() {
		return stop_code;
	}

	public void setStop_code(String stop_code) {
		this.stop_code = stop_code;
	}

	public String getStop_name() {
		return stop_name;
	}

	public void setStop_name(String stop_name) {
		this.stop_name = stop_name;
	}

	public String getStop_desc() {
		return stop_desc;
	}

	public void setStop_desc(String stop_desc) {
		this.stop_desc = stop_desc;
	}

	public double getStop_lat() {
		return stop_lat;
	}

	public void setStop_lat(double stop_lat) {
		this.stop_lat = stop_lat;
	}

	public void setStop_lat(String stop_lat) {
		this.stop_lat = Double.parseDouble(stop_lat);
	}

	public double getStop_lon() {
		return stop_lon;
	}

	public void setStop_lon(double stop_lon) {
		this.stop_lon = stop_lon;
	}

	public void setStop_lon(String stop_lon) {
		this.stop_lon = Double.parseDouble(stop_lon);
	}

	public String getStop_elevation() {
		return stop_elevation;
	}

	public void setStop_elevation(String stop_elevation) {
		this.stop_elevation = stop_elevation;
	}

	public String getZone_id() {
		return zone_id;
	}

	public void setZone_id(String one_id) {
		this.zone_id = one_id;
	}

	public String getStop_url() {
		return stop_url;
	}

	public void setStop_url(String stop_url) {
		this.stop_url = stop_url;
	}

	public String getLocation_type() {
		return location_type;
	}

	public void setLocation_type(String location_type) {
		this.location_type = location_type;
	}

	public String getParent_station() {
		return parent_station;
	}

	public void setParent_station(String parent_station) {
		this.parent_station = parent_station;
	}

	public String getPlatform_code() {
		return platform_code;
	}

	public void setPlatform_code(String platform_code) {
		this.platform_code = platform_code;
	}

	public String getCh_station_long_name() {
		return ch_station_long_name;
	}

	public void setCh_station_long_name(String ch_station_long_name) {
		this.ch_station_long_name = ch_station_long_name;
	}

	public String getCh_station_synonym1() {
		return ch_station_synonym1;
	}

	public void setCh_station_synonym1(String ch_station_synonym1) {
		this.ch_station_synonym1 = ch_station_synonym1;
	}

	public String getCh_station_synonym2() {
		return ch_station_synonym2;
	}

	public void setCh_station_synonym2(String ch_station_synonym2) {
		this.ch_station_synonym2 = ch_station_synonym2;
	}

	public String getCh_station_synonym3() {
		return ch_station_synonym3;
	}

	public void setCh_station_synonym3(String ch_station_synonym3) {
		this.ch_station_synonym3 = ch_station_synonym3;
	}

	public String getCh_station_synonym4() {
		return ch_station_synonym4;
	}

	public void setCh_station_synonym4(String ch_station_synonym4) {
		this.ch_station_synonym4 = ch_station_synonym4;
	}

	@Override
	public String toString() {
		return stop_id;
	}
}
