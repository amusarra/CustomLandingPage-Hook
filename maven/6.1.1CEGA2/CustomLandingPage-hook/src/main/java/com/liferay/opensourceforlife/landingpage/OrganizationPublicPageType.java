/**
 * Returns custom landing path for organization which user belongs to
 */

package com.liferay.opensourceforlife.landingpage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.liferay.opensourceforlife.util.CustomLandingPageUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Tejas Kanani
 */
public class OrganizationPublicPageType implements LandingPageType
{

	/*
	 * (non-Javadoc)
	 * @see
	 * com.liferay.opensourceforlife.landingpage.LandingPageType#getLandingPagePath(javax.servlet
	 * .http.HttpServletRequest)
	 */
	public String getLandingPagePath(final HttpServletRequest request) throws PortalException,
			SystemException
	{

		String organizationPath = StringPool.BLANK;

		User currentUser = PortalUtil.getUser(request);

		List<Organization> userOrganizations = currentUser.getOrganizations();

		if (userOrganizations != null && !userOrganizations.isEmpty())
		{
			Organization organization = userOrganizations.get(0);
			// If user is member of more than one organization then it will take
			// first organization from list
			String organizationFriendlyURL = organization.getGroup().getFriendlyURL();

			organizationPath = CustomLandingPageUtil.getLanguage(request)
					+ PortalUtil.getPathFriendlyURLPublic()
					+ organizationFriendlyURL
					+ CustomLandingPageUtil.getLandingPageFriendlyURL(organization,
							PortalUtil.getCompanyId(request), Boolean.FALSE);
		}

		return organizationPath;
	}
}
