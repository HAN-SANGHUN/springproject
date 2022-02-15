/**
 * 
 */
package com.kona.kms.token.dao;

import java.util.List;
import com.kona.kms.token.model.LHSMModel;

/**
 * @author bizais
 *
 */
public interface TokenMapDao {
	List<LHSMModel>			getTokenMapInfo();
}
