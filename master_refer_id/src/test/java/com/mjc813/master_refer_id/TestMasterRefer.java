package com.mjc813.master_refer_id;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestMasterRefer {
    @Test
    public void referMasterIdTest01() {
        MasterTable mt = new MasterTable(10L, "master");
        ReferTable rt = new ReferTable(20L, "refer", mt.getId(), mt);

        rt.getMaster().setId(33L);
        assertThat(rt.getMaster().getId()).isEqualTo(33L);
        assertThat(rt.getMasterTableId()).isEqualTo(33L);

        rt.setMasterTableId(44L);
        assertThat(rt.getMasterTableId()).isEqualTo(44L);
        assertThat(rt.getMaster().getId()).isEqualTo(44L);

        rt.getMaster().setId(3333L);
        assertThat(rt.getMaster().getId()).isEqualTo(3333L);
        assertThat(rt.getMasterTableId()).isEqualTo(3333L);

        rt.setMasterTableId(4444L);
        assertThat(rt.getMasterTableId()).isEqualTo(4444L);
        assertThat(rt.getMaster().getId()).isEqualTo(4444L);
    }

    @Test
    public void referMasterIdTest02() {
        ReferTable rt = new ReferTable(20L, "refer", 10L, null);

        rt.setMasterTableId(44L);
        assertThat(rt.getMasterTableId()).isEqualTo(44L);
        assertThat(rt.getMaster().getId()).isEqualTo(44L);

        rt.getMaster().setId(33L);
        assertThat(rt.getMaster().getId()).isEqualTo(33L);
        assertThat(rt.getMasterTableId()).isEqualTo(33L);

        rt.setMasterTableId(7777L);
        assertThat(rt.getMasterTableId()).isEqualTo(7777L);
        assertThat(rt.getMaster().getId()).isEqualTo(7777L);

        rt.getMaster().setId(9876L);
        assertThat(rt.getMaster().getId()).isEqualTo(9876L);
        assertThat(rt.getMasterTableId()).isEqualTo(9876L);
    }
}
