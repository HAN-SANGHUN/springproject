/**
 * 
 */
package com.kona.kms.token.dao;

import java.util.List;

import com.kona.kms.token.model.KeyInfo;


public interface KeyInfoDao {

	List<KeyInfo> getKeyInfoAll();
	
	int getTotalRecord();

}
