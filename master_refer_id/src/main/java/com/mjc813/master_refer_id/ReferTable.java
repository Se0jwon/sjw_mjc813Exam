package com.mjc813.master_refer_id;

import lombok.*;

@NoArgsConstructor
public class ReferTable {
    private Long id;
    private String name;
    private Long masterTableId;
    private MasterTable master;

    public ReferTable(Long id, String name, Long masterTableId, MasterTable master) {
        this.id = id;
        this.name = name;
        this.masterTableId = masterTableId;
        //this.master = master;
        if ( master == null ) {
            this.master = new MasterTable();
        } else {
            this.master = new MasterTable(master.getId(), master.getName());
        }
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public MasterTable getMaster() {
        return this.master;
    }

    public Long getMasterTableId() {
        if ( this.master == null ) {
            this.master = new MasterTable();
        }
        return this.master.getId();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMasterTableId(Long masterTableId) {
        if ( this.master == null ) {
            this.master = new MasterTable();
        }
        this.master.setId(masterTableId);
        this.masterTableId = masterTableId;
    }

    public void setMaster(MasterTable master) {
        this.master = master;
    }
}
