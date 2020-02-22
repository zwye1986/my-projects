<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertAttribute name="Header" />
	<div id="cntwrapper"> 
	
		<tiles:insertAttribute name="Sidebar"></tiles:insertAttribute>
		<tiles:insertAttribute name="Content"></tiles:insertAttribute>
		
	</div>
	<tiles:insertAttribute name="Footer"></tiles:insertAttribute>
		

