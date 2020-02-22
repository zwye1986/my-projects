<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertAttribute name="Header" />
	<div id="cntwrapper"> 
		<div id="content">
			<tiles:insertAttribute name="Content" />
		</div>
	</div>
<tiles:insertAttribute name="Footer" />
		

