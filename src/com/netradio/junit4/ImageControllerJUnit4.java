package com.netradio.junit4;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.netradio.web.controller.ImageController;
import static org.junit.Assert.*;

public class ImageControllerJUnit4 extends AbstractServiceJUnit4 {

    @Autowired
    private ImageController image;

    private MockHttpServletRequest req;
    private MockHttpServletResponse res;

    @Before
    public void setup() {
        req = new MockHttpServletRequest();
        res = new MockHttpServletResponse();
    }

    /**
     * Get image with exist id
     * */
    @Test
    public void image1() {
        image.image(2L, req, res);
        assertEquals(MockHttpServletResponse.SC_OK, res.getStatus());
    }

    /**
     * Get image with not exist id
     * */
    @Test
    public void image2() {
        image.image(200L, req, res);
        assertEquals(MockHttpServletResponse.SC_OK, res.getStatus());
    }
}
