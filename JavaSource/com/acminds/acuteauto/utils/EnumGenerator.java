/**
 * 
 */
package com.acminds.acuteauto.utils;

import java.io.File;
import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.acminds.acuteauto.persistence.BaseDAO;
import com.acminds.acuteauto.persistence.dto.Enum;
import com.acminds.acuteauto.persistence.dto.EnumType;
import com.sun.xml.internal.ws.util.StringUtils;

/**
 * Layman kind of implementation.
 * @author Mansur
 *
 */
public class EnumGenerator {
	
	private static final String CLASS_DECL = "public static class {0}";
	private static final String BRAC_OPEN = "{";
	private static final String BRAC_CLOSE = "}";
	private static final String FIELD_DECL = "public static final int {0} = {1};";
	private static final String FILE_EXT = ".java";
	
	public static void main(String[] args) throws Exception {
		EnumGenerator eg = new EnumGenerator();
		String javaPath = "JavaSource\\"+EnumConstants.class.getName().replace(".", "\\")+FILE_EXT;
		StringBuilder sb = new StringBuilder();
		sb.append("package com.acminds.acuteauto.utils;")
		.append("\n")
		.append("/** AUTO GENERATED **/")
		.append("\n")
		.append("public class EnumConstants {")
		.append("\n");
		List<EnumType> types = eg.getEnumTypes();
		for(EnumType et:types) {
			sb.append(eg.getClassAsString(et)).append("\n");			
		}
		sb.append(BRAC_CLOSE);
		System.out.println(sb.toString());
		FileUtils.writeStringToFile(new File(javaPath), sb.toString());
	}
	
	private String getClassAsString(EnumType type) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t").append(MessageFormat.format(CLASS_DECL, new Object[]{dbToJavaName(type.getCodeName())})).append(BRAC_OPEN);
		for(Enum e:type.getEnums()) {
			sb.append("\n")
			.append("\t\t")
			.append(MessageFormat.format(FIELD_DECL, new Object[]{e.getEnumValue().toUpperCase().replace(" ", "_"), e.getEnumId()}));
		}		
		sb.append("\n\t").append(BRAC_CLOSE);
		return sb.toString();
	}
	
	private List<EnumType> getEnumTypes() {
		BaseDAO dao = new BaseDAO();
		List<EnumType> types = dao.createQuery("from EnumType", EnumType.class).getResultList();
		return types;
	}
	
	private String dbToJavaName(String tableName) {
		String name = "";
		String names[] = tableName.split("_");
		for(String n:names) {
			name = name+StringUtils.capitalize(n.toLowerCase());
		}
		return name;
	}

}
