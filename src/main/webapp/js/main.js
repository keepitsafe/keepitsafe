var leftContainerCount = 0;
var rightContainerCount = 0;

function addKeep(keep) {
	container = "#keep-container-1";
	if (leftContainerCount > rightContainerCount) {
		container = "#keep-container-2";
	}

	$(container).append(
			"<h2>" + keep["name"] + "</h2><p>" + keep["description"] + "</p>");

	$(container)
			.append(
					'<table id="tb-keep-'
							+ keep["id"]
							+ '" class="table table-bordered"><thead><tr><th>Secret</th><th>Description</th></tr></thead><tbody></tbody></table>');

	$(container).append(
			"<a class=\"btn\" href=\"#\">Manage Permissions</a>&nbsp;");
	$(container).append("<a class=\"btn\" href=\"#\">New Secret</a>");
	$(container).append("<hr>");

	secret_count = 0;
	$.each(keep["secrets"], function(index, secret) {
		$("#tb-keep-" + keep["id"] + " tbody").append(
				"<tr><td><a href=\"#\" id=\"secret-" + secret["id"] + "\">"
						+ secret["name"] + "</a></td><td>"
						+ secret["description"] + "</td></tr>")

		$("#secret-" + secret["id"]).click(
				function() {
					$.ajax({
						type : "GET",
						dataType : "json",
						url : "http://localhost:8080/keepitsafe/secret/"
								+ secret["id"],
						success : function(sc) {
							$("#secret-id").val(sc["id"]);
							$("#secret-name").val(sc["name"]);
							$("#secret-description").val(sc["description"]);
							$("#secret-login").val(sc["login"]);
							$("#secret-password").val(sc["password"]);
							$("#secret-password-tx").val(sc["password"]);
							$("#secret-dialog").modal("show");
						}
					});

				});

		secret_count++;
	});

	if (leftContainerCount > rightContainerCount) {
		rightContainerCount += secret_count + 2;
	} else {
		leftContainerCount += secret_count + 2;
	}

}

$(document).ready(
		function() {
			$.ajax({
				type : "GET",
				dataType : "json",
				url : "http://localhost:8080/keepitsafe/keep",
				success : function(data) {
					jQuery.each(data, function(index, value) {
						addKeep(value);
					});
				}
			});

			$("#show-password").click(function() {

				if ($("#secret-password-tx").hasClass("hide")) {
					$("#secret-password").addClass("hide");
					$("#secret-password-tx").removeClass("hide");
					$("#show-password").html("Hide Password");
				} else {
					$("#secret-password-tx").addClass("hide");
					$("#secret-password").removeClass("hide");
					$("#show-password").html("Show Password");
				}
			});

			$("#secret-password-tx").keyup(function() {
				$("#secret-password").val($(this).val());
			});

			$("#secret-password").keyup(function() {
				$("#secret-password-tx").val($(this).val());
			});

			$("#btn-save-secret").click(
					function() {
						$.ajax({
							type : "PUT",
							dataType : "json",
							contentType : "application/json",
							url : "http://localhost:8080/keepitsafe/secret/"
									+ $("#secret-id").val(),
							data : $.toJSON({
								id : $("#secret-id").val(),
								name : $("#secret-name").val(),
								description : $("#secret-description").val(),
								login : $("#secret-login").val(),
								password : $("#secret-password-tx").val()
							}),
							success : function(data) {
								$("#secret-dialog").modal("hide");
							}
						});
					});
		});