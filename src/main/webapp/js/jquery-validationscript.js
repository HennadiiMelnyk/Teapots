$(function () {
    $("#firstname_error_message").hide();
    $("#lastname_error_message").hide();
    $("#email_error_message").hide();
    $("#password_error_message").hide();
    $("#confirm_password_error_message").hide();
    $("#terms_checkbox_error_message").hide();
    var error_fname = false;
    var error_sname = false;
    var error_email = false;
    var error_password = false;
    var error_retype_password = false;
    var errot_terms = false;

    $("#registration-form").click(function () {
        error_fname = false;
        error_sname = false;
        error_email = false;
        error_password = false;
        error_retype_password = false;
        check_firstname();
        check_lastname();
        check_email();
        check_password();
        check_retype_password();
        if (error_fname == false && error_sname == false && error_email == false && error_password == false && error_retype_password == false) {
            alert("Registration Successfull");
            return true;
        } else {
            alert("Please Fill the form Correctly");
            return false;
        }
    })
    $("#user_name").focusout(function () {
        check_firstname();
    });
    $("#user_surname").focusout(function () {
        check_lastname();
    });
    $("#user_email").focusout(function () {
        check_email();
    });
    $("#user_password").focusout(function () {
        check_password();
    });
    $("#confirm_password").focusout(function () {
        check_retype_password();
    });
    $("#terms-checkbox").focusout(function () {
        validateTermsCheckbox();
    });

    function check_firstname() {
        //remove error
        $("#user_name").removeClass("is-invalid");


        //validate input

        var fname = $("#user_name").val();
        if (fname == "") {
            $("#firstname_error_message").html("**Last Name Must Be Filled");
            $("#firstname_error_message").show();
            $("#user_name").addClass("is-invalid");
            error_fname = true;
        } else if (!INPUT_PATTERN.test(fname)) {
            $("#firstname_error_message").html("**Please, fill last name correctly (only words)");
            $("#firstname_error_message").show();
            $("#user_name").addClass("is-invalid");
            error_fname = true;
        } else {
            $("#firstname_error_message").hide();
            $("#user_name").addClass("is-valid");
            error_fname = false;
        }
    }

    function check_lastname() {
        $("#user_surname").removeClass("is-invalid");


        var sname = $("#user_surname").val();
        if (sname == "") {
            $("#lastname_error_message").html("**Last Name Must Be Filled");
            $("#lastname_error_message").show();
            $("#user_surname").addClass("is-invalid");
            error_sname = true;
        } else if (!INPUT_PATTERN.test(sname)) {
            $("#lastname_error_message").html("**Please, fill last name correctly (only words)");
            $("#lastname_error_message").show();
            $("#user_surname").addClass("is-invalid");
            error_sname = true;
        } else {
            $("#lastname_error_message").hide();
            $("#user_surname").addClass("is-valid");
            error_sname = false;
        }
    }

    function check_password() {
        $("#user_password").removeClass("is-invalid");

        var password_length = $("#user_password").val().length;
        if (password_length == 0) {
            $("#password_error_message").html("**Password Must Be Filled");
            $("#password_error_message").show();
            $("#user_password").addClass("is-invalid");
            error_password = true;

        } else if (password_length < 8 ) {
            $("#password_error_message").html("**At least 8 Characters");
            $("#password_error_message").show();
            $("#user_password").addClass("is-invalid");
            error_password = true;
        } else {
            $("#password_error_message").hide();
            $("#user_password").addClass("is-valid");
            error_password = false;
        }
    }

    function check_retype_password() {
        $("#confirm_password").removeClass("is-invalid");

        var password = $("#confirm_password").val();
        var retype_password = $("#confirm_password").val();
        if (retype_password == "") {
            $("#confirm_password_error_message").html("Confirm-Passwords must be filled");
            $("#confirm_password_error_message").show();
            $("#confirm_password").addClass("is-invalid");
            error_retype_password = true;
        } else if (password !== retype_password) {
            $("#confirm_password_error_message").html("Passwords Did not Matched");
            $("#confirm_password_error_message").show();
            $("#confirm_password").addClass("is-invalid");
            error_retype_password = true;
        } else {
            $("#confirm_password_error_message").hide();
            $("#confirm_password").addClass("is-valid");
            error_retype_password = false;
        }
    }

    function validateTermsCheckbox() {
        $("#terms-checkbox").removeClass("is-invalid");
        if ($('#terms-checkbox').prop('checked')) {
            $("#terms_checkbox_error_message").hide();
            $("#terms-checkbox").addClass("is-valid");
        } else {
            $("#terms_checkbox_error_message").html("**Must checkbox before submitting");
            $("#terms_checkbox_error_message").show();
            $("#terms-checkbox").addClass("is-invalid");
        }
    }

    function check_email() {
        $("#user_email").removeClass("is-invalid");

        var email = $("#user_email").val();
        if (email == "") {
            $("#email_error_message").html("**Email Must Be Filled");
            $("#email_error_message").show();
            $("#user_email").addClass("is-invalid");
            error_email = true;
        } else if (!EMAIL_REG_EXP.test(email)) {
            $("#email_error_message").html("**Invalid Email");
            $("#email_error_message").show();
            $("#user_email").addClass("is-invalid");
            error_email = true;
        } else {
            $("#email_error_message").hide();
            $("#user_email").addClass("is-valid");
            error_email = false;
        }
    }
});