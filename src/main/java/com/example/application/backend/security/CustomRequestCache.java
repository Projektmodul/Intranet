package com.example.application.backend.security;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class initialize a cache to keep track of unauthenticated requests.
 * This class keeps track of the url you were trying to reach while you weren't logged in
 * It is not used yet.
 *
 * @author  Lea Schünemann, Marieke Menna de Boer
 * @version 1.0
 * @since   11.01.2021
 * @lastUpdated 12.01.2021
 */

class CustomRequestCache extends HttpSessionRequestCache {
    /**
     * This method saves unauthenticated requests so we can redirect
     * the user to the page they
     * were trying to access once they’re logged in
     *
     * @author  Lea Schünemann, Marieke Menna de Boer
     * @version 1.0
     * @since   11.01.2021
     * @lastUpdated 12.01.2021
     */
    @Override
    public void saveRequest(HttpServletRequest request, HttpServletResponse
            response) {
        if (!SecurityUtils.isFrameworkInternalRequest(request)) {
            super.saveRequest(request, response);
        }
    }
}
