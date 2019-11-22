(() => {
    const registrationForm = document.getElementById('registration-form');

    const elems = registrationForm.elements;
    //elements of form
    const userName = elems.userName;
    const userSurname = elems.userSurname;
    const login = elems.login;
    const email = elems.email;
    const password = elems.password;
    const confirmPassword = elems.confirmPassword;
    const terms = elems.terms;

    /**
     * listeners for all fields
     */
    registrationForm.addEventListener('submit', validateRegistrationForm);
    userName.addEventListener('blur', () => validateFieldLength(userName, FIELD_SHOULD_NOT_BE_EMPTY, resetError, showError, markValid));
    userSurname.addEventListener('blur', () => validateFieldLength(userSurname, FIELD_SHOULD_NOT_BE_EMPTY, resetError, showError, markValid));
    login.addEventListener('blur', () => validateFieldRegexp(login, FIELD_SHOULD_NOT_BE_EMPTY, VALID_LOGIN, resetError, showError, markValid, INPUT_PATTERN));
    email.addEventListener('blur', () => validateFieldRegexp(email, FIELD_SHOULD_NOT_BE_EMPTY, VALID_EMAIL, resetError, showError, markValid, EMAIL_REG_EXP));
    password.addEventListener('blur', () => validateFieldRegexp(password, FIELD_SHOULD_NOT_BE_EMPTY, PASSWORD_VALIDATION, resetError, showError, markValid, PASSWORD_REG_EXP));
    confirmPassword.addEventListener('blur', validateConfirmPassword);
    terms.addEventListener('change', validateTermsCheckbox);

    /**
     * function to check param length
     * @param {} field
     */
    function validateFieldLength(field, message, resetError, showError, markValid) {
        resetError(field);
        if (field.value.length == 0 && field.value.length < 4) {
            showError(field, message);
        } else {
            markValid(field);
        }
    }
    /**
     * function which check regexp validation for specific fields
     */
    function validateFieldRegexp(field, commonErrorMessage, specificErrorMessage, resetError, showError, markValid, regexp) {
        resetError(field);
        if (!field.value) {
            showError(field, commonErrorMessage);
        } else if (!regexpValidator(field.value, regexp)) {
            showError(field, specificErrorMessage);
        } else {
            markValid(field);
        }
    }

    /**
     * validator which check if password and confirmpassword are match
     */
    function validateConfirmPassword() {
        resetError(confirmPassword);
        if (confirmPassword.value && confirmPassword.value == password.value) {
            markValid(confirmPassword);
        } else {
            showError(confirmPassword, PASSWORD_MISMATCH)
        }
    }
    /**
     * validator for checkbox
     */
    function validateTermsCheckbox() {
        resetError(terms);
        if (terms.checked) {
            markValid(terms);
        } else {
            showError(terms, CHECK_BOX_ERROR);
        }
    }

    function validateRegistrationForm(event) {
        const formFields = registrationForm.querySelectorAll(':scope input');
        const formFieldsArray = Array.prototype.slice.call(formFields);
        validateFieldLength(userName, FIELD_SHOULD_NOT_BE_EMPTY, resetError, showError, markValid);
        validateFieldLength(userSurname, FIELD_SHOULD_NOT_BE_EMPTY, resetError, showError, markValid);
        validateFieldRegexp(login, FIELD_SHOULD_NOT_BE_EMPTY, resetError, showError, markValid, INPUT_PATTERN);
        validateFieldRegexp(email, FIELD_SHOULD_NOT_BE_EMPTY, VALID_EMAIL, resetError, showError, markValid, EMAIL_REG_EXP);
        validateFieldRegexp(password, FIELD_SHOULD_NOT_BE_EMPTY, PASSWORD_VALIDATION, resetError, showError, markValid, PASSWORD_REG_EXP);
        validateConfirmPassword();
        validateTermsCheckbox();
        const isValid = formFieldsArray.every(function (field) {
            return field.classList.contains('is-valid');
        });

        if (!isValid) {
            event.preventDefault();
        }
    }

    function showError(element, message) {
        const invalidFeedback = element.parentNode.querySelector('.invalid-feedback');
        invalidFeedback.textContent = message;
        element.classList.add('is-invalid');
    }

    function markValid(element) {
        element.classList.add('is-valid');
    }

    function resetError(element) {
        const invalidFeedback = element.parentNode.querySelector('.invalid-feedback');

        element.classList.remove('is-invalid');
        invalidFeedback.textContent = "";
    }
    /**
     * check condition of Regexp for certain field
     * @param {*} field
     * @param {*} regexp
     */
    function regexpValidator(field, regexp) {
        return regexp.test(field);
    }
})();