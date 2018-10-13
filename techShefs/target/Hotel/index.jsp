<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
<style>           
.blue-button{
	background: #25A6E1;
	filter: progid: DXImageTransform.Microsoft.gradient( startColorstr='#25A6E1',endColorstr='#188BC0',GradientType=0);
	padding:3px 5px;
	color:#fff;
	font-family:'Helvetica Neue',sans-serif;
	font-size:12px;
	border-radius:2px;
	-moz-border-radius:2px;
	-webkit-border-radius:4px;
	border:1px solid #1A87B9
}     
table {
  font-family: "Helvetica Neue", Helvetica, sans-serif;
   width: 50%;
}
th {
  background: SteelBlue;
  color: white;
}
 td,th{
                border: 1px solid gray;
                width: 25%;
                text-align: left;
                padding: 5px 10px;
            }
</style>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">

        
function RenderTable(data)
{
	
	var hotelRow = "";

	hotelRow += "<tr><th>Synonyms</th></tr>";



    for (var i = 0; i < data.length; i++)
    {	
    	
        var synonymName = data[i].words;

        hotelRow += "<tr><td>" + synonymName + "</td></tr>" ;
    }
    
    jQuery('#synonymTable').empty();
    jQuery('#synonymTable').append(hotelRow);
}  

function getAllSynonyms(){
	
	var word = jQuery('[name="synonymName"]').val();
	
	var url = "http://localhost:8080/Synonyms/getAllSynonyms/"+word;
	
	jQuery.ajax(
            {	            	
            	headers: { 
                    'Accept' : 'application/json',
                    'Content-Type': 'application/json'
                	},
            	type: 'GET',
                url: url,
                dataType: 'json',
                success: function (data) {
                	RenderTable(data);
                },
                error: function (jqxhr, textStatus, errorMessage) {
                alert("oops something went wrong..");
                }
            });
	
}
        
        
</script>
</head>
<body>


<table>
<tr>
	<th colspan="2">Search Synonyms</th>
</tr>
<tr>
   <td>Enter Synonym word:</td>
   <td><input type="text" name="synonymName" placeholder="Synonym word"></td>
</tr>
<tr>
	<td colspan="2"><input type="submit" class="blue-button" onclick="return getAllSynonyms();"/></td>
</tr>
</table>

</br>
<h3>Synonym List</h3>

<table class="tg" id="synonymTable">
</table>
</body>
</html>

