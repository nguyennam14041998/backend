package com.api.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DanhmuchanhchinhTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Danhmuchanhchinh.class);
        Danhmuchanhchinh danhmuchanhchinh1 = new Danhmuchanhchinh();
        danhmuchanhchinh1.setId(1L);
        Danhmuchanhchinh danhmuchanhchinh2 = new Danhmuchanhchinh();
        danhmuchanhchinh2.setId(danhmuchanhchinh1.getId());
        assertThat(danhmuchanhchinh1).isEqualTo(danhmuchanhchinh2);
        danhmuchanhchinh2.setId(2L);
        assertThat(danhmuchanhchinh1).isNotEqualTo(danhmuchanhchinh2);
        danhmuchanhchinh1.setId(null);
        assertThat(danhmuchanhchinh1).isNotEqualTo(danhmuchanhchinh2);
    }
}
