package com.example.stackexchange.handler;

import org.apache.solr.common.SolrInputDocument;
import org.xml.sax.Attributes;

import com.example.stackexchange.entity.PostHistory;

public class PostHistoryHandler extends BaseHandler {

	@Override
	public void handleRow(Attributes attributes) {
		Integer id = getId(attributes);
		Integer postHistoryTypeId = getInt(attributes, "PostHistoryTypeId");
		Integer postId = getPostId(attributes);
		String revisionGUID = getString(attributes, "RevisionGUID");
		String creationDate = getCreationDate(attributes);
		Integer userId = getUserId(attributes);
		String userDisplayName = getUserDisplayName(attributes);
		String comment = getString(attributes, "Comment");
		String text = getText(attributes);
		Integer closeReasonId = getInt(attributes, "CloseReasonId");

		PostHistory postHistory = new PostHistory();
		postHistory.setId(id);
		postHistory.setPostHistoryTypeId(postHistoryTypeId);
		postHistory.setPostId(postId);
		postHistory.setRevisionGUID(revisionGUID);
		postHistory.setCreationDate(creationDate);
		postHistory.setUserId(userId);
		postHistory.setUserDisplayName(userDisplayName);
		postHistory.setComment(comment);
		postHistory.setText(text);
		postHistory.setCloseReasonId(closeReasonId);

		persistRow(postHistory);

		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("type_s", "postHistory");
		doc.addField("postHistoryTypeId_i", postHistoryTypeId);
		doc.addField("postId_i", postId);
		doc.addField("revisionGuid_s", revisionGUID);
		doc.addField("creationDate_dt", creationDate);
		doc.addField("userId_i", userId);
		doc.addField("userDisplayName_s", userDisplayName);
		doc.addField("comment_t", comment);
		doc.addField("text", text);
		doc.addField("closeReasonId_i", closeReasonId);
		doc.addField("id", buildId(id));

		indexRow(doc);
	}
}
