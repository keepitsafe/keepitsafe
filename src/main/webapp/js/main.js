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

/* Events */

$(document).ready(function() {

    /* Load Page */
    $.ajax({
        type : "GET",
        dataType : "json",
        url : "keep",
        success : function(data) {
            $.each(data, function(index, keep) {
                loadKeep(keep);
            });
        }
    });
    
    /* New Keep */
    $("#btn-keep-new").click(function() {
        $("#frm-keep-id").val("");
        $("#frm-keep-name").val("");
        $("#frm-keep-description").val("");
        $("#dlg-keep").modal("show");
    });
    
    /* Keep Dialog Save */
    $("#btn-keep-save").click(function() {
        if ($("#frm-keep-id").val() == "") {

            /* New Keep */
            $.ajax({
                type : "POST",
                dataType : "json",
                contentType : "application/json",
                url : "keep",
                data : $.toJSON({
                    name : $("#frm-keep-name").val(),
                    description : $("#frm-keep-description") .val(),
                }),
                success : function(data) {
                    $("#dlg-keep").modal("hide");
                    loadKeep(data);
                }
            });
        } else {
            /* Update Keep */
            
            var id = $("#frm-keep-id").val();
            $.ajax({
                type : "PUT",
                dataType : "json",
                contentType : "application/json",
                url : "keep/" + id,
                data : $.toJSON({
                    name : $("#frm-keep-name").val(),
                    description : $("#frm-keep-description") .val(),
                }),
                success : function(data) {
                    $("#dlg-keep").modal("hide");
                    $("#keep-" + id + " h2").html(data["name"]);
                    $("#keep-" + id + " p").html(data["description"]);
                }
            });
        }
    });
    
    /* Secret Dialog Save */
    $("#btn-secret-save").click(function() {
        if ($("#frm-secret-id").val() == "") {
            
            /* New Secret */
            var keepId = $("#frm-secret-keep-id").val();
            $.ajax({
                type : "POST",
                dataType : "json",
                contentType : "application/json",
                url : "keep/" + keepId + "/secret",
                data : $.toJSON({
                    name : $("#frm-secret-name").val(),
                    description : $("#frm-secret-description").val(),
                    login : $("#frm-secret-login").val(),
                    password : $("#frm-secret-password-tx").val()
                }),
                success : function(data) {
                    $("#dlg-secret").modal("hide");
                    //TODO Wrap on loadSecret function
                   
                    var tbody = $("#tb-keep-" + keepId + " tbody");
                    tbody.append(
                            "<tr><td><a href=\"#\" id=\"secret-" + data["id"] + "\">"
                                    + data["name"] + "</a></td><td id=\"secret-" + data["id"] + "-description\">"
                                    + data["description"] + "</td></tr>");

                    $("#secret-" + data["id"]).click(function() {
                        $.ajax({
                            type : "GET",
                            dataType : "json",
                            url : "secret/" + data["id"],
                            success : function(sc) {
                                $("#frm-secret-id").val(sc["id"]);
                                $("#frm-secret-name").val(sc["name"]);
                                $("#frm-secret-description").val(sc["description"]);
                                $("#frm-secret-login").val(sc["login"]);
                                $("#frm-secret-password").val(sc["password"]);
                                $("#frm-secret-password-tx").val(sc["password"]);
                                $("#dlg-secret").modal("show");
                            }
                        });
                    });
                }
            });
        } else {
            
            /* Update Secret */ 
            var id = $("#frm-secret-id").val();
            $.ajax({
                type : "PUT",
                dataType : "json",
                contentType : "application/json",
                url : "secret/" + id,
                data : $.toJSON({
                    id : $("#frm-secret-id").val(),
                    name : $("#frm-secret-name").val(),
                    description : $("#frm-secret-description").val(),
                    login : $("#frm-secret-login").val(),
                    password : $("#frm-secret-password-tx").val()
                }),
                success : function(data) {
                    $("#dlg-secret").modal("hide");
                    $("#secret-" + id).html($("#frm-secret-name").val());
                    $("#secret-" + id + "-description").html($("#frm-secret-description").val());
                }
            });
        }
    });
    
    /* Show/Hide Password */
    $("#btn-password-show").click(function() {
        if ($("#frm-secret-password-tx").hasClass("hide")) {
            $("#frm-secret-password").addClass("hide");
            $("#frm-secret-password-tx").removeClass("hide");
            $("#btn-password-show").html("Hide Password");
        } else {
            $("#frm-secret-password-tx").addClass("hide");
            $("#frm-secret-password").removeClass("hide");
            $("#btn-password-show").html("Show Password");
        }
    });
    
    /* Sync password text and hidden fields */
    $("#frm-secret-password-tx").keyup(function() {
        $("#frm-secret-password").val($(this).val());
    });

    $("#frm-secret-password").keyup(function() {
        $("#frm-secret-password-tx").val($(this).val());
    });

     
     
});

/* Globals */
var leftContainerCount = 0;
var rightContainerCount = 0;


/* Functions */

/* Load a keep to the web frontend */
function loadKeep(keep) {
    
    var cntnr = $("<div></div>", { id: "keep-" + keep["id"]});    
    
    /* Load on left or right panel? */
    if (leftContainerCount > rightContainerCount) {
        $("#keep-container-2").append(cntnr);
    } else {
        $("#keep-container-1").append(cntnr);    
    }

    /* Keep basic data */
    $(cntnr).append("<h2>" + keep["name"] + "</h2>");
    $(cntnr).append("<p>" + keep["description"] + "</p>");

    var table = $("<table />", { 
        id: "tb-keep-" + keep["id"],
        class: "table table-bordered"
    });
    table.append("<thead><tr><th>Secret</th><th>Description</th></tr></thead>");
    table.appendTo(cntnr);
        
    var tbody = $("<tbody />");
    table.append(tbody);

    /* Load secrets */
    secret_count = 0;
    $.each(keep["secrets"], function(index, secret) {
        tbody.append(
                "<tr><td><a href=\"#\" id=\"secret-" + secret["id"] + "\">"
                        + secret["name"] + "</a></td><td id=\"secret-" + secret["id"] + "-description\">"
                        + secret["description"] + "</td></tr>");

        $("#secret-" + secret["id"]).click(function() {
            $.ajax({
                type : "GET",
                dataType : "json",
                url : "secret/" + secret["id"],
                success : function(sc) {
                    $("#frm-secret-id").val(sc["id"]);
                    $("#frm-secret-name").val(sc["name"]);
                    $("#frm-secret-description").val(sc["description"]);
                    $("#frm-secret-login").val(sc["login"]);
                    $("#frm-secret-password").val(sc["password"]);
                    $("#frm-secret-password-tx").val(sc["password"]);
                    $("#dlg-secret").modal("show");
                }
            });

        });

        secret_count++;
    });
    
    
    /* Edit Keep Button */
    $(cntnr).append("<a class=\"btn\" id=\"btn-keep-edit-" + keep["id"] + "\">Edit</a> ");
    $("#btn-keep-edit-" + keep["id"]).click(function() {
        $.ajax({
            type : "GET",
            dataType : "json",
            url : "keep/" + keep["id"],
            success : function(data) {
                $("#frm-keep-id").val(data["id"]);
                $("#frm-keep-name").val(data["name"]);
                $("#frm-keep-description").val(data["description"]);
                $("#dlg-keep").modal("show");
            }
        });
    });
    
    /* New Secret Button */
    $(cntnr).append("<a class=\"btn\" id=\"btn-secret-new-" + keep["id"] + "\">New Secret</a>");
    $("#btn-secret-new-" + keep["id"]).click(function() {
        $("#frm-secret-id").val("");
        $("#frm-secret-keep-id").val(keep["id"]);
        $("#frm-secret-name").val("");
        $("#frm-secret-description").val("");
        $("#frm-secret-login").val("");
        $("#frm-secret-password").val("");
        $("#frm=secret-password-tx").val("");
        $("#dlg-secret").modal("show");
    });
    
    $(cntnr).append("<hr>");

    /* Save data to equalize next load panel */
    if (leftContainerCount > rightContainerCount) {
        rightContainerCount += secret_count + 2;
    } else {
        leftContainerCount += secret_count + 2;
    }

}

/* Load the a secret on user interface */
function loadSecret(keepId, Secret) {
    
}


