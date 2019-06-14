<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>touk demo</title>
    <style>

        @media screen and (min-width: 768px){
            .rwd-break { display: none; }
        }

    </style>
</head>
<body>
<div class="container-fluid" style="border: #C1C1C1 solid 1px; border-radius: 10px; margin:5px;">
    <!-- Body Page -->
    <div class="col-md-12" style="min-height:500px;">
        <tiles:insertAttribute name="body" />
    </div>
</div>

</body>
</html>