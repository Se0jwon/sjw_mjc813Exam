package com.mjc813.mjc_exam_lib;

import java.util.List;

public interface IContactCUD {
    public void add(IContact contact);
    public IContact getContactFromName(String name);
    public void update(IContact contact);
    public void delete(IContact contact);
}
