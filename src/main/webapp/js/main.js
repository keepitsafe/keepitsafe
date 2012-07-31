/*
 * Copyright 2012 Felipe C. do R. P.
 *
 * This file is part of Keep It Safe.
 * 
 * Keep It Safe is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Keep It Safe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Keep It Safe.  If not, see <http://www.gnu.org/licenses/>.
 */

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
	$(container).append(
			"<a class=\"btn\" href=\"#\" id=\"btn-new-secret-" + keep["id"]
					+ "\">New Secret</a>");
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

	$("#btn-new-secret-" + keep["id"]).click(function() {
		$("#secret-id").val("");
		$("#secret-keep-id").val(keep["id"]);
		$("#secret-name").val("");
		$("#secret-description").val("");
		$("#secret-login").val("");
		$("#secret-password").val("");
		$("#secret-password-tx").val("");
		$("#secret-dialog").modal("show");
	});

	if (leftContainerCount > rightContainerCount) {
		rightContainerCount += secret_count + 2;
	} else {
		leftContainerCount += secret_count + 2;
	}

}

$(document)
		.ready(
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

					$("#btn-save-secret")
							.click(
									function() {
										if ($("#secret-id").val() == "") {
											alert("http://localhost:8080/keepitsafe/keep/"
													+ $("#secret-keep-id")
															.val() + "/secret");
											$
													.ajax({
														type : "POST",
														dataType : "json",
														contentType : "application/json",
														url : "http://localhost:8080/keepitsafe/keep/"
																+ $(
																		"#secret-keep-id")
																		.val()
																+ "/secret",
														data : $
																.toJSON({
																	name : $(
																			"#secret-name")
																			.val(),
																	description : $(
																			"#secret-description")
																			.val(),
																	login : $(
																			"#secret-login")
																			.val(),
																	password : $(
																			"#secret-password-tx")
																			.val()
																}),
														success : function(data) {
															$("#secret-dialog")
																	.modal(
																			"hide");
														}
													});
										} else {
											$
													.ajax({
														type : "PUT",
														dataType : "json",
														contentType : "application/json",
														url : "http://localhost:8080/keepitsafe/secret/"
																+ $(
																		"#secret-id")
																		.val(),
														data : $
																.toJSON({
																	id : $(
																			"#secret-id")
																			.val(),
																	name : $(
																			"#secret-name")
																			.val(),
																	description : $(
																			"#secret-description")
																			.val(),
																	login : $(
																			"#secret-login")
																			.val(),
																	password : $(
																			"#secret-password-tx")
																			.val()
																}),
														success : function(data) {
															$("#secret-dialog")
																	.modal(
																			"hide");
														}
													});
										}
									});
				});