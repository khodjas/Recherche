package data;

import java.io.File;

public class Site {
	private File page;
	private String url;

	public Site(String url) {
		this.page = new File(url);
		this.url = url;

	}

	public File getPage() {
		return page;
	}

	@Override
	public String toString() {
		return "Site [page=" + url + "]";
	}

}
