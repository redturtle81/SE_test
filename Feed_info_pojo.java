package model;

public class Feed_info_pojo {
	private String feed_publisher_name;
	private String feed_publisher_url;
	private String feed_lang;
	private String feed_start_date;
	private String feed_end_date;
	private String feed_version;

	public String getFeed_publisher_name() {
		return feed_publisher_name;
	}

	public void setFeed_publisher_name(String feed_publisher_name) {
		this.feed_publisher_name = feed_publisher_name;
	}

	public String getFeed_publisher_url() {
		return feed_publisher_url;
	}

	public void setFeed_publisher_url(String feed_publisher_url) {
		this.feed_publisher_url = feed_publisher_url;
	}

	public String getFeed_lang() {
		return feed_lang;
	}

	public void setFeed_lang(String feed_lang) {
		this.feed_lang = feed_lang;
	}

	public String getFeed_start_date() {
		return feed_start_date;
	}

	public void setFeed_start_date(String feed_start_date) {
		this.feed_start_date = feed_start_date;
	}

	public String getFeed_end_date() {
		return feed_end_date;
	}

	public void setFeed_end_date(String feed_end_date) {
		this.feed_end_date = feed_end_date;
	}

	public String getFeed_version() {
		return feed_version;
	}

	public void setFeed_version(String feed_version) {
		this.feed_version = feed_version;
	}

	@Override
	public String toString() {
		String output = "Feed_publisher_name: " + getFeed_publisher_name() + " - Feed_publisher_url: "
				+ getFeed_publisher_url() + " -  Feed_lang:" + getFeed_lang() + " - Feed_start_date: "
				+ getFeed_start_date() + " - Feed_end_date:" + getFeed_end_date() + " - Feed_version: "
				+ getFeed_version();
		return output;
	}
}
