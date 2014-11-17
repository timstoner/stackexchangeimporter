package com.example.stackexchange.handler;

import org.xml.sax.Attributes;

import com.example.stackexchange.entity.Vote;

public class VotesHandler extends BaseHandler {

	@Override
	public void handleRow(Attributes attributes) {
		Integer id = getId(attributes);
		Integer postId = getPostId(attributes);
		Integer voteTypeId = getInt(attributes, "VoteTypeId");
		String creationDate = getCreationDate(attributes);
		Integer userId = getUserId(attributes);
		Integer bountyAmount = getInt(attributes, "BountyAmount");

		Vote vote = new Vote();
		vote.setId(id);
		vote.setPostId(postId);
		vote.setVoteTypeId(voteTypeId);
		vote.setCreationDate(creationDate);
		vote.setUserId(userId);
		vote.setBountyAmount(bountyAmount);

		fireOnRowEvent(vote);
	}

}
