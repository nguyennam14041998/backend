package com.api.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DanTocTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanToc.class);
        DanToc danToc1 = new DanToc();
        danToc1.setId(1L);
        DanToc danToc2 = new DanToc();
        danToc2.setId(danToc1.getId());
        assertThat(danToc1).isEqualTo(danToc2);
        danToc2.setId(2L);
        assertThat(danToc1).isNotEqualTo(danToc2);
        danToc1.setId(null);
        assertThat(danToc1).isNotEqualTo(danToc2);
    }
}
