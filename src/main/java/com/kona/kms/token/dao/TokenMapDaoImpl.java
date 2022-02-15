/**
 * 
 */
/**
 * 
 */
package com.kona.kms.token.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kona.kms.token.model.LHSMModel;

/**
 * @author bizais
 *
 */
@Repository
public class TokenMapDaoImpl implements TokenMapDao {

	/* (non-Javadoc)
	 * @see com.kona.kms.token.dao.TokenMapDao#getTokenMapInfo()
	 */
	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<LHSMModel> getTokenMapInfo() {
		// TODO Auto-generated method stub
		return  sqlSessionTemplate.selectList("tokenmap.getTokenMapInfo");
	}

}
