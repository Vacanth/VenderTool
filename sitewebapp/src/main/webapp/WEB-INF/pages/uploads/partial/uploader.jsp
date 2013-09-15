<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div>
	
	<form method="post" action="upload-page.php" enctype="multipart/form-data">
		<input name="filesToUpload[]" id="filesToUpload" type="file" multiple="" />
	</form>

	<form method="post" action="uploads/upload" enctype="multipart/form-data">
 

		<input path="file" type="file" />
		<input type="submit" value="Upload" />
	</form>

</div>


