package com.api.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class HuyenTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Huyen.class);
        Huyen huyen1 = new Huyen();
        huyen1.setId(1L);
        Huyen huyen2 = new Huyen();
        huyen2.setId(huyen1.getId());
        assertThat(huyen1).isEqualTo(huyen2);
        huyen2.setId(2L);
        assertThat(huyen1).isNotEqualTo(huyen2);
        huyen1.setId(null);
        assertThat(huyen1).isNotEqualTo(huyen2);
    }
}
