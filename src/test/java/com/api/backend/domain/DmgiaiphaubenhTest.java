package com.api.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DmgiaiphaubenhTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dmgiaiphaubenh.class);
        Dmgiaiphaubenh dmgiaiphaubenh1 = new Dmgiaiphaubenh();
        dmgiaiphaubenh1.setId(1L);
        Dmgiaiphaubenh dmgiaiphaubenh2 = new Dmgiaiphaubenh();
        dmgiaiphaubenh2.setId(dmgiaiphaubenh1.getId());
        assertThat(dmgiaiphaubenh1).isEqualTo(dmgiaiphaubenh2);
        dmgiaiphaubenh2.setId(2L);
        assertThat(dmgiaiphaubenh1).isNotEqualTo(dmgiaiphaubenh2);
        dmgiaiphaubenh1.setId(null);
        assertThat(dmgiaiphaubenh1).isNotEqualTo(dmgiaiphaubenh2);
    }
}
