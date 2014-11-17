package com.example.stackexchange.handler;

import org.apache.solr.common.SolrInputDocument;
import org.xml.sax.Attributes;

import com.example.stackexchange.entity.Badge;

public class BadgeHandler extends BaseHandler {

	@Override
	public void handleRow(Attributes attributes) {
		Integer id = getId(attributes);
		Integer userId = getUserId(attributes);
		String name = getName(attributes);
		String date = getDate(attributes, "Date");

		Badge badge = new Badge(id, userId, name, date);
		persistRow(badge);

		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("type_s", "badge");
		doc.addField("userId_i", userId);
		doc.addField("name_s", name);
		doc.addField("date_dt", date);
		doc.addField("id", buildId(id));
		indexRow(doc);
	}
}
