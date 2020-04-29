package com.api.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DanhmucnhomptttTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Danhmucnhompttt.class);
        Danhmucnhompttt danhmucnhompttt1 = new Danhmucnhompttt();
        danhmucnhompttt1.setId(1L);
        Danhmucnhompttt danhmucnhompttt2 = new Danhmucnhompttt();
        danhmucnhompttt2.setId(danhmucnhompttt1.getId());
        assertThat(danhmucnhompttt1).isEqualTo(danhmucnhompttt2);
        danhmucnhompttt2.setId(2L);
        assertThat(danhmucnhompttt1).isNotEqualTo(danhmucnhompttt2);
        danhmucnhompttt1.setId(null);
        assertThat(danhmucnhompttt1).isNotEqualTo(danhmucnhompttt2);
    }
}
