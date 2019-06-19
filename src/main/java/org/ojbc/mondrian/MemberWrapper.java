/*
 * Unless explicitly acquired and licensed from Licensor under another license, the contents of
 * this file are subject to the Reciprocal Public License ("RPL") Version 1.5, or subsequent
 * versions as allowed by the RPL, and You may not copy or use this file in either source code
 * or executable form, except in compliance with the terms and conditions of the RPL
 *
 * All software distributed under the RPL is provided strictly on an "AS IS" basis, WITHOUT
 * WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, AND LICENSOR HEREBY DISCLAIMS ALL SUCH
 * WARRANTIES, INCLUDING WITHOUT LIMITATION, ANY WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, QUIET ENJOYMENT, OR NON-INFRINGEMENT. See the RPL for specific language
 * governing rights and limitations under the RPL.
 *
 * http://opensource.org/licenses/RPL-1.5
 *
 * Copyright 2018 Open Justice Broker Consortium and Cascadia Analytics LLC
 */
package org.ojbc.mondrian;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.olap4j.OlapException;
import org.olap4j.metadata.Member;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A wrapper around olap4j Member objects, suitable for serialization via json.
 *
 */
public class MemberWrapper {

	private String name;
	private String caption;
	private boolean isAll;
	private int childMemberCount;
	private List<MemberWrapper> childMembers;
	private boolean childMembersPopulated;

	MemberWrapper() {
	}

	public MemberWrapper(Member member) throws OlapException {
		this.name = member.getName();
		this.caption = member.getCaption();
		this.isAll = member.isAll();
		this.childMemberCount = member.getChildMemberCount();
		childMembers = new ArrayList<>();
		if (childMemberCount <= LevelWrapper.CARDINALITY_LIMIT) {
			childMembersPopulated = true;
			for(Member child : member.getChildMembers()) {
				childMembers.add(new MemberWrapper(child));
			}
		} else {
			childMembersPopulated = false;
		}
	}

	public String getName() {
		return name;
	}

	public String getCaption() {
		return caption;
	}

	@JsonProperty("isAll")
	public boolean isAll() {
		return isAll;
	}

	public int getChildMemberCount() {
		return childMemberCount;
	}

	public List<MemberWrapper> getChildMembers() {
		return Collections.unmodifiableList(childMembers);
	}

	public boolean isChildMembersPopulated() {
		return childMembersPopulated;
	}

	void setName(String name) {
		this.name = name;
	}

	void setCaption(String caption) {
		this.caption = caption;
	}

	void setAll(boolean isAll) {
		this.isAll = isAll;
	}

	void setChildMemberCount(int childMemberCount) {
		this.childMemberCount = childMemberCount;
	}

	void setChildMembers(List<MemberWrapper> childMembers) {
		this.childMembers = childMembers;
	}

	void setChildMembersPopulated(boolean childMembersPopulated) {
		this.childMembersPopulated = childMembersPopulated;
	}

}
