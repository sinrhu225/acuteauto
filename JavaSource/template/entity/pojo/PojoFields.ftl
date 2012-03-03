<#-- // Fields -->
private static final long serialVersionUID = 1L;

<#foreach field in pojo.getAllPropertiesIterator()>
<#if pojo.getMetaAttribAsBool(field, "gen-property", true)> 
<#if pojo.hasMetaAttribute(field, "field-description")>    /**
     ${pojo.getFieldJavaDoc(field, 0)}
     */
 </#if>    
<#if c2h.isCollection(field)>
${pojo.getFieldModifiers(field)} ${pojo.getJavaTypeName(field, jdk5).replaceAll("Set", "List")} ${field.name}<#if pojo.hasFieldInitializor(field, jdk5)> = ${pojo.getFieldInitialization(field, jdk5).replaceAll("HashSet", "ArrayList")}</#if>;
<#else>
${pojo.getFieldModifiers(field)} ${pojo.getJavaTypeName(field, jdk5)} ${field.name}<#if pojo.hasFieldInitializor(field, jdk5)> = ${pojo.getFieldInitialization(field, jdk5)}</#if>;
</#if>
</#if>
</#foreach>
