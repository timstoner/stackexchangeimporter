package com.example.stackexchange.handler;

import org.apache.solr.common.SolrInputDocument;
import org.xml.sax.Attributes;

import com.example.stackexchange.entity.Comment;

public class CommentHandler extends BaseHandler {

	@Override
	public void handleRow(Attributes attributes) {
		Integer id = getInt(attributes, "Id");
		Integer postId = getInt(attributes, "PostId");
		Integer score = getInt(attributes, "Score");
		String text = getString(attributes, "Text");
		String creationDate = getCreationDate(attributes);
		Integer userId = getInt(attributes, "UserId");

		Comment comment = new Comment();
		comment.setId(id);
		comment.setPostId(postId);
		comment.setScore(score);
		comment.setText(text);
		comment.setCreationDate(creationDate);
		comment.setUserId(userId);
		persistRow(comment);

		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("type_s", "comment");
		doc.addField("postId_i", postId);
		doc.addField("score_i", score);
		doc.addField("text", text, 1.2f);
		doc.addField("creationDate_dt", creationDate);
		doc.addField("userId_i", userId);

		String url = "http://localhost:8080/stackexchange/comments/" + id;
		doc.addField("url", url);
		doc.addField("id", buildId(id));

		indexRow(doc);
	}
}
