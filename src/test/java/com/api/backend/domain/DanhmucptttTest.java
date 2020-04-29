package com.api.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DanhmucptttTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Danhmucpttt.class);
        Danhmucpttt danhmucpttt1 = new Danhmucpttt();
        danhmucpttt1.setId(1L);
        Danhmucpttt danhmucpttt2 = new Danhmucpttt();
        danhmucpttt2.setId(danhmucpttt1.getId());
        assertThat(danhmucpttt1).isEqualTo(danhmucpttt2);
        danhmucpttt2.setId(2L);
        assertThat(danhmucpttt1).isNotEqualTo(danhmucpttt2);
        danhmucpttt1.setId(null);
        assertThat(danhmucpttt1).isNotEqualTo(danhmucpttt2);
    }
}
