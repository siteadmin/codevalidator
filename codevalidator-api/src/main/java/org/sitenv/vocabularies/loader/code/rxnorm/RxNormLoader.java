package org.sitenv.vocabularies.loader.code.rxnorm;

import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.sitenv.vocabularies.constants.VocabularyConstants;
import org.sitenv.vocabularies.loader.DelimitedTextVocabularyLoader;
import org.sitenv.vocabularies.model.impl.RxNormModel;

public class RxNormLoader extends DelimitedTextVocabularyLoader<RxNormModel> {
	public RxNormLoader() {
		super(RxNormModel.class, 0);
	}
	
	@Override
	protected boolean processLine(OObjectDatabaseTx dbConnection, ODocument doc, Map<String, String> baseFields, int lineIndex, String line) {
		String[] lineParts = StringUtils.splitPreserveAllTokens(line, "|", 16);
		
		Map<String, String> fields = new LinkedHashMap<String, String>();
		fields.put("code", lineParts[0]);
		fields.put("displayName", lineParts[14]);
		fields.putAll(baseFields);
		
		this.loadDocument(doc, fields);
		
		return true;
	}
	
	@Override
	protected Map<String, String> buildBaseFields() {
		Map<String, String> baseFields = super.buildBaseFields();
		baseFields.put("codeSystemId", VocabularyConstants.RXNORM_CODE_SYSTEM_ID);
		baseFields.put("codeSystemName", VocabularyConstants.RXNORM_CODE_SYSTEM_NAME);
		
		return baseFields;
	}
}