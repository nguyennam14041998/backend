package com.api.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DmloaibenhlyTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dmloaibenhly.class);
        Dmloaibenhly dmloaibenhly1 = new Dmloaibenhly();
        dmloaibenhly1.setId(1L);
        Dmloaibenhly dmloaibenhly2 = new Dmloaibenhly();
        dmloaibenhly2.setId(dmloaibenhly1.getId());
        assertThat(dmloaibenhly1).isEqualTo(dmloaibenhly2);
        dmloaibenhly2.setId(2L);
        assertThat(dmloaibenhly1).isNotEqualTo(dmloaibenhly2);
        dmloaibenhly1.setId(null);
        assertThat(dmloaibenhly1).isNotEqualTo(dmloaibenhly2);
    }
}
