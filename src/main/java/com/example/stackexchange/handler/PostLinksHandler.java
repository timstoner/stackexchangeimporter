package com.example.stackexchange.handler;

import org.apache.solr.common.SolrInputDocument;
import org.xml.sax.Attributes;

import com.example.stackexchange.entity.PostLink;

public class PostLinksHandler extends BaseHandler {

	@Override
	public void handleRow(Attributes attributes) {
		Integer id = getId(attributes);
		String creationDate = getCreationDate(attributes);
		Integer postId = getPostId(attributes);
		Integer relatedPostId = getInt(attributes, "RelatedPostId");
		Integer linkTypeId = getInt(attributes, "LinkTypeId");

		PostLink link = new PostLink();
		link.setId(id);
		link.setCreationDate(creationDate);
		link.setPostId(postId);
		link.setRelatedPostId(relatedPostId);
		link.setLinkType(linkTypeId);

		persistRow(link);

		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("type_s", "postLink");
		doc.addField("creationDate_dt", creationDate);
		doc.addField("postId_i", postId);
		doc.addField("relatedPostId_i", relatedPostId);
		doc.addField("linkTypeId_i", linkTypeId);
		doc.addField("id", buildId(id));

		indexRow(doc);
	}

}
