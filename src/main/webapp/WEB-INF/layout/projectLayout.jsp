<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertAttribute name="Header" />
	<section> 
	
		<tiles:insertAttribute name="Sidebar"></tiles:insertAttribute>
		<tiles:insertAttribute name="Content"></tiles:insertAttribute>
		
	</section>
	<tiles:insertAttribute name="Footer"></tiles:insertAttribute>
		

