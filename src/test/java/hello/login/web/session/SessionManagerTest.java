package hello.login.web.session;

import static org.assertj.core.api.Assertions.assertThat;

import hello.login.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest() {
        // create session
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member();
        sessionManager.createSession(member, response);

        // save cookie
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        // select session
        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(member);

        // expire session
        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();

    }
}