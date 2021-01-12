package com.example.application.backend.security;

import com.vaadin.flow.server.HandlerHelper;
import com.vaadin.flow.server.ServletHelper;
import com.vaadin.flow.shared.ApplicationConstants;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import javax.servlet.http.HttpServletRequest;
import java.util.stream.Stream;

    /**
     * This class provides utility methods
     *
     * @author  Lea Schünemann, Marieke Menna de Boer
     * @version 1.0
     * @since   11.01.2021
     * @lastUpdated 12.01.2021
     */

public final class SecurityUtils {

    private SecurityUtils() {
        // Util methods only
    }
        /**
         * This method tests if the request is an internal framework.
         * The test consists on checking if the
         * request parameter is present and if its value is consistent
         *  with any of the request types know
         *
         * @author  Lea Schünemann, Marieke Menna de Boer
         * @version 1.0
         * @since   11.01.2021
         * @lastUpdated 12.01.2021
         */
    static boolean isFrameworkInternalRequest(HttpServletRequest request) {

        final String parameterValue = request.getParameter
                (ApplicationConstants.REQUEST_TYPE_PARAMETER);
        return parameterValue != null
                && Stream.of(HandlerHelper.RequestType.values())
                .anyMatch(r -> r.getIdentifier().equals(parameterValue));
    }

        /**
         * This method tests if some user is authenticated
         *
         * @author  Lea Schünemann, Marieke Menna de Boer
         * @version 1.0
         * @since   11.01.2021
         * @lastUpdated 12.01.2021
         */
    static boolean isUserLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        return authentication != null
                && !(authentication instanceof AnonymousAuthenticationToken)
                && authentication.isAuthenticated();
    }
}
