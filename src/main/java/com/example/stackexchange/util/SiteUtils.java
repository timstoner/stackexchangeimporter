package com.example.stackexchange.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SiteUtils {

	private static String siteName;

	public static String getSiteName(String dir) {
		if (siteName == null) {
			Path path = Paths.get(dir);
			Path parent = path.getParent();

			Path name = parent.getName(parent.getNameCount() - 1);
			siteName = name.toString();
		}
		return siteName;
	}

	public static void setSiteName(String siteName) {
		SiteUtils.siteName = siteName;
	}

}
