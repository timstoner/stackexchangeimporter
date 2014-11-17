package com.example.stackexchange.handler;

import org.apache.solr.common.SolrInputDocument;
import org.xml.sax.Attributes;

import com.example.stackexchange.entity.Post;

public class PostHandler extends BaseHandler {

	@Override
	public void handleRow(Attributes a) {
		Integer id = getId(a);
		Integer postTypeId = getInt(a, "PostTypeId");
		Integer parentId = getInt(a, "ParentId");
		Integer acceptedAnswerId = getInt(a, "AcceptedAnswerId");
		String creationDate = getDate(a, "CreationDate");
		Integer score = getInt(a, "Score");
		Integer viewCount = getInt(a, "ViewCount");
		String body = getString(a, "Body");
		Integer ownerUserId = getInt(a, "OwnerUserId");
		String ownerDisplayName = getString(a, "OwnerDisplayName");
		Integer lastEditorUserId = getInt(a, "LastEditorUserId");
		String lastEditorDisplayName = getString(a, "LastEditorDisplayName");
		String lastEditDate = getDate(a, "LastEditDate");
		String lastActivityDate = getDate(a, "LastActivityDate");
		String communityOwnedDate = getDate(a, "CommunityOwnedDate");
		String closedDate = getDate(a, "ClosedDate");
		String title = getString(a, "Title");
		String tags = getString(a, "Tags");
		Integer answerCount = getInt(a, "AnswerCount");
		Integer commentCount = getInt(a, "CommentCount");
		Integer favoriteCount = getInt(a, "FavoriteCount");

		Post post = new Post();
		post.setId(id);
		post.setAcceptedAnswerId(acceptedAnswerId);
		post.setAnswerCount(answerCount);
		post.setBody(body);
		post.setClosedDate(closedDate);
		post.setCommentCount(commentCount);
		post.setCommunityOwnedDate(communityOwnedDate);
		post.setCreationDate(creationDate);
		post.setFavoriteCount(favoriteCount);
		post.setLastActivityDate(lastActivityDate);
		post.setLastEditDate(lastEditDate);
		post.setLastEditorDisplayName(lastEditorDisplayName);
		post.setLastEditorUserId(lastEditorUserId);
		post.setOwnerDisplayName(ownerDisplayName);
		post.setOwnerUserId(ownerUserId);
		post.setParentId(parentId);
		post.setPostTypeId(postTypeId);
		post.setScore(score);
		post.setTags(tags);
		post.setTitle(title);
		post.setViewCount(viewCount);

		persistRow(post);

		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("type_s", "post");
		doc.addField("acceptedAnswerId_i", acceptedAnswerId);
		doc.addField("answerCount_i", answerCount);
		doc.addField("body_t", body);
		doc.addField("closedDate_dt", closedDate);
		doc.addField("commentCount_i", commentCount);
		doc.addField("communityOwnedDate_dt", communityOwnedDate);
		doc.addField("creationDate_dt", creationDate);
		doc.addField("favoriteCount_i", favoriteCount);
		doc.addField("lastActivityDate_dt", lastActivityDate);
		doc.addField("lastEditDate_dt", lastEditDate);
		doc.addField("lastEditorDisplayName_s", lastEditorDisplayName);
		doc.addField("lastEditorUserId_i", lastEditorUserId);
		doc.addField("ownerDisplayName_s", ownerDisplayName);
		doc.addField("ownerUserId_i", ownerUserId);
		doc.addField("parentId_i", parentId);
		doc.addField("postTypeId_i", postTypeId);
		doc.addField("score_i", score);

		if (tags != null) {
			if (tags.contains("><")) {
				tags = tags.substring(1, tags.length() - 1);
			}

			String[] split = tags.split("><");
			
			for (String tag : split) {
				LOG.info("");
				doc.addField("tags_ts", tag, 1.2f);
			}
		}

		doc.addField("title_t", title, 1.5f);
		doc.addField("viewCount_i", viewCount);

		String url = "http://localhost:8080/stackexchange/posts/" + id;
		doc.addField("url", url);
		doc.addField("id", buildId(id));

		indexRow(doc);
	}

}
