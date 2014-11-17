package com.example.stackexchange.handler;

import org.apache.solr.common.SolrInputDocument;
import org.xml.sax.Attributes;

import com.example.stackexchange.entity.User;

public class UserHandler extends BaseHandler {

	@Override
	public void handleRow(Attributes attributes) {
		Integer id = getInt(attributes, "Id");
		Integer reputation = getInt(attributes, "Reputation");
		String creationDate = getCreationDate(attributes);
		String displayName = getString(attributes, "DisplayName");
		String websiteUrl = getString(attributes, "WebsiteUrl");
		String location = getString(attributes, "Location");
		String aboutMe = getString(attributes, "AboutMe");
		Integer views = getInt(attributes, "Views");
		Integer upVotes = getInt(attributes, "UpVotes");
		Integer downVotes = getInt(attributes, "DownVotes");
		String profileImageUrl = getString(attributes, "ProfileImageUrl");
		String emailHash = getString(attributes, "EmailHash");
		Integer age = getInt(attributes, "Age");
		Integer accountId = getInt(attributes, "AccountId");

		User user = new User();
		user.setId(id);
		user.setReputation(reputation);
		user.setCreationDate(creationDate);
		user.setDisplayName(displayName);
		user.setWebsiteUrl(websiteUrl);
		user.setLocation(location);
		user.setAboutMe(aboutMe);
		user.setViews(views);
		user.setUpVotes(upVotes);
		user.setDownVotes(downVotes);
		user.setProfileImageUrl(profileImageUrl);
		user.setEmailHash(emailHash);
		user.setAge(age);
		user.setAccountId(accountId);

		persistRow(user);

		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("type_s", "user");
		doc.addField("reputation_i", reputation);
		doc.addField("creationDate_dt", creationDate);
		doc.addField("displayName_s", displayName);
		doc.addField("websiteUrl_s", websiteUrl);
		doc.addField("location_s", location);
		doc.addField("aboutMe_t", aboutMe);
		doc.addField("views_i", views);
		doc.addField("upVotes_i", upVotes);
		doc.addField("downVotes_i", downVotes);
		doc.addField("profileImageUrl_s", profileImageUrl);
		doc.addField("emailHash_s", emailHash);
		doc.addField("age_i", age);
		doc.addField("accountId_i", accountId);
		doc.addField("id", buildId(id));

		indexRow(doc);
	}
}
