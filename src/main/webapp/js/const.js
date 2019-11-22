const EMAIL_REG_EXP = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

/*Contain at least 8 characters
contain at least 1 number
contain at least 1 lowercase character (a-z)
contain at least 1 uppercase character (A-Z)
contains only 0-9a-zA-Z*/
const PASSWORD_REG_EXP = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/;
const INPUT_PATTERN = /^[a-zA-Z]*$/;
const FIELD_LENGTH_ERROR_MESSAGE = "You need at least  4 symbols ";
const FIELD_SHOULD_NOT_BE_EMPTY = "field shouldn't be empty.";
const VALID_EMAIL = "Please enter a valid email address.";
const VALID_LOGIN = "Please enter a valid login";
const PASSWORD_VALIDATION = "Password should be 0-9a-zA-Z 8 symbols length minimum.";
const PASSWORD_MISMATCH = "Passwords doesn't match.";
const CHECK_BOX_ERROR = "You must agree before submitting.";