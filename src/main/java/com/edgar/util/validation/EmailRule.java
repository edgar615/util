package com.edgar.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;

import java.net.IDN;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

/**
 * email的校验，核心逻辑复制自hibernate validator
 *
 * @author Edgar  Date 2016/7/12
 */
class EmailRule implements Rule {

    private static String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";
    private static String DOMAIN = ATOM + "+(\\." + ATOM + "+)*";
    private static String IP_DOMAIN = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";

    /**
     * Regular expression for the local part of an email address (everything before '@')
     */
    private final Pattern localPattern = Pattern.compile(
            ATOM + "+(\\." + ATOM + "+)*", CASE_INSENSITIVE
    );

    /**
     * Regular expression for the domain part of an email address (everything after '@')
     */
    private final Pattern domainPattern = Pattern.compile(
            DOMAIN + "|" + IP_DOMAIN, CASE_INSENSITIVE
    );


    private EmailRule() {
    }

    static Rule create() {
        return new EmailRule();
    }


    @Override
    public String message() {
        return "Invalid Email";
    }

    @Override
    public boolean isValid(Object property) {

        if (property != null && (property instanceof String)) {
            String str = String.class.cast(property);
            if (Strings.isNullOrEmpty(str)) {
                return true;
            }
            // split email at '@' and consider local and domain part separately;
            // note a split limit of 3 is used as it causes all characters following to an (illegal) second @ character to
            // be put into a separate array element, avoiding the regex application in this case since the resulting array
            // has more than 2 elements
            String[] emailParts = str.toString().split("@", 3);
            if (emailParts.length != 2) {
                return false;
            }

            // if we have a trailing dot in local or domain part we have an invalid email address.
            // the regular expression match would take care of this, but IDN.toASCII drops trailing the trailing '.'
            // (imo a bug in the implementation)
            if (emailParts[0].endsWith(".") || emailParts[1].endsWith(".")) {
                return false;
            }

            if (!matchPart(emailParts[0], localPattern)) {
                return false;
            }

            return matchPart(emailParts[1], domainPattern);
        }
        return true;

    }

    private boolean matchPart(String part, Pattern pattern) {
        try {
            part = IDN.toASCII(part);
        } catch (IllegalArgumentException e) {
            // occurs when the label is too long (>63, even though it should probably be 64 - see http://www.rfc-editor.org/errata_search.php?rfc=3696,
            // practically that should not be a problem)
            return false;
        }
        Matcher matcher = pattern.matcher(part);
        return matcher.matches();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("EmailRule")
                .toString();
    }
}
