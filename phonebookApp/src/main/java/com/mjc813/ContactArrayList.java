package com.mjc813;

import java.util.ArrayList;
import java.util.List;

import com.mjc813.mjc_exam_lib.IContact;
import com.mjc813.mjc_exam_lib.IContactCUD;
import com.mjc813.mjc_exam_lib.IContactList;
import com.mjc813.mjc_exam_lib.IContactSearch;

public class ContactArrayList<T extends IContact> implements IContactCUD, IContactList, IContactSearch{
    // 배열객체를 갖고 있는 멤버변수
    private ArrayList<T> list = new ArrayList<T>();

    // 매개변수 contact 를 배열객체에 추가한다.
    @Override
    public void add(IContact contact) throws ParameterNullPointException {
        if (contact == null) {
            String msg = String.format("[add] real parameter need not null data.");
            throw new ParameterNullPointException(msg);
        }
        this.addGeneric((T)contact);
    }

    private void addGeneric(T t) {
        this.list.add(t);
    }

    //배열객체에서 매개변수 name 이랑 똑같은 이름의 IContact 를 찾아서 리턴한다. 없으면 null 을 리턴한다.
    @Override
    public IContact getContactFromName(String name) {
        if (name == null) {
            return null;
        }
        for (IContact c : list) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    //매개변수 contact 의 name 이랑 같은 배열객체를 찾아서 폰번호,우편번호,이메일을 수정한다.
    @Override
    public void update(IContact contact) throws ParameterNullPointException {
        if (contact == null) {
            String msg = String.format("[update] real parameter need not null data.");
            throw new ParameterNullPointException(msg);
        }
        this.updateGeneric((T)contact);
    }

    private void updateGeneric(T t) {
        for (IContact c : list) {
            if (c.getName().equals(t.getName())) {
                c.setPhoneNumber(t.getPhoneNumber()); // 폰번호
                c.setZipNumber(t.getZipNumber()); // 우편번호
                c.setEmail(t.getEmail()); // 이메일
                return;
            }
        }
    }

    //매개변수 contact 의 name 이랑 같은 배열객체를 찾아서 삭제한다.
    @Override
    public void delete(IContact Contact) throws ParameterNullPointException {
        if (Contact == null) {
            String msg = String.format("[delete] real parameter need not null data.");
            throw new ParameterNullPointException(msg);
        }
        for (IContact c : list) {
            if (c.getName().equals(Contact.getName())) {
                this.list.remove(c);
                return;
            }
        }
    }

    // 배열객체의 길이를 리턴한다.
    @Override
    public int length() {
        return this.list.size();
    }

    // 배열객체를 리턴한다.
    @Override
    public List<IContact> getListAll() {
        return (List<IContact>)this.getListAllGeneric();
    }

    private List<T> getListAllGeneric() {
        return this.list;
    }

    //매개변수 word 라는 단어가 포함된 이름으로 찾은 결과를 List 형태로 리턴한다.
    @Override
    public List<IContact> getListFromContainsName(String word) {
        if(word == null || "".equals(word)) {
            return new ArrayList<>();
        }

        ArrayList<IContact> result = new ArrayList<>();
        for (IContact c : list) {
            if(c.getName().contains(word)){
                result.add(c);
            }
        }
        return result;
    }
}
