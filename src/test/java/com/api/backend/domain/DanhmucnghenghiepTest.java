package com.api.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DanhmucnghenghiepTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Danhmucnghenghiep.class);
        Danhmucnghenghiep danhmucnghenghiep1 = new Danhmucnghenghiep();
        danhmucnghenghiep1.setId(1L);
        Danhmucnghenghiep danhmucnghenghiep2 = new Danhmucnghenghiep();
        danhmucnghenghiep2.setId(danhmucnghenghiep1.getId());
        assertThat(danhmucnghenghiep1).isEqualTo(danhmucnghenghiep2);
        danhmucnghenghiep2.setId(2L);
        assertThat(danhmucnghenghiep1).isNotEqualTo(danhmucnghenghiep2);
        danhmucnghenghiep1.setId(null);
        assertThat(danhmucnghenghiep1).isNotEqualTo(danhmucnghenghiep2);
    }
}
