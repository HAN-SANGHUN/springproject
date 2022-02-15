/**
 * 
 */
package com.kona.kms.token.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kona.kms.token.model.KeyInfo;

@Repository
public class KeyInfoDaoImpl implements KeyInfoDao {
	
	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<KeyInfo> getKeyInfoAll(){
		return sqlSessionTemplate.selectList("keyInfo.getKeyInfoAll");
	}
	
	@Override
	public int getTotalRecord(){
		return sqlSessionTemplate.selectOne("keyInfo.getTotalRecord");
	}
	
}
