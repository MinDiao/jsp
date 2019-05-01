package com.hpe.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.hpe.dao.IFriendLinkDao;
import com.hpe.dao.impl.FriendLinkDaoImpl;
import com.hpe.po.FriendLink;

public class TestFriendLink {
	
	IFriendLinkDao friendLinkDao = new FriendLinkDaoImpl();

	@Test
	public void testSelectName() {

		FriendLink selectById = friendLinkDao.selectById(23);
		System.out.println(selectById);
		
	}

}
