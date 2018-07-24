/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.site.util;

public class Helper {

	private Helper() {

	}

	public static String generateUniqueSiteId(String utilityName) {
		Long siteId = SiteConstants.SITE_STARTING_ID;
		String uniqueSiteId = utilityName.substring(0, 1);
		return uniqueSiteId + "-" + SiteConstants.SITE_STARTING_KEY + String.format("%05d", siteId);
	}

	public static String generateNextSiteId(String siteId, String utilityName) {
		siteId = siteId.substring(3);
		Long uniqueId = Long.valueOf(siteId);
		uniqueId++;
		utilityName = utilityName.substring(0, 1);

		return utilityName + "-" + SiteConstants.SITE_STARTING_KEY + String.format("%05d", uniqueId);
	}

	public static String generateNextUserId(String userId) {
		userId = userId.substring(1);
		Long uniqueId = Long.valueOf(userId);
		uniqueId++;
		return SiteConstants.SITE_USER_STARTING + String.format("%05d", uniqueId);

	}

	public static String generateUniqueUserId(Character siteUserStarting) {
		Long siteId = SiteConstants.SITE_USER_STARTING_ID;
		return SiteConstants.SITE_USER_STARTING + String.format("%05d", siteId);
	}

}
