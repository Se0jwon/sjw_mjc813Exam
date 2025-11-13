package com.mjc813;

import com.mjc813.exception.*;
import com.mjc813.mjc_exam_lib.IContact;
import com.mjc813.mjc_exam_lib.IContactCUD;
import com.mjc813.mjc_exam_lib.IContactList;
import com.mjc813.mjc_exam_lib.IContactSearch;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestContactProject {
    @Test
    public void testContact() {
//        IContact contact = new 1번클래스(....);
//        this.testContactFunc(contact);
        IContact contact = new Contact("홍길동", "010-1111-1111", "39291", "dis2.392@gmail.com");
        this.testContactFunc(contact);
    }

    private void testContactFunc(IContact contact) {
        assertThat(contact).isNotNull();
        String name01 = contact.getName();
        assertThat(name01).isNotNull();
        assertThat(name01).isNotEqualTo("");

        String phoneNumber01 = contact.getPhoneNumber();
        assertThat(phoneNumber01).isNotNull();
        assertThat(phoneNumber01).isNotEqualTo("");
        assertThatThrownBy(() -> contact.setPhoneNumber(null))
                .isInstanceOf(NotValidDataException.class);
        assertThat(contact.getPhoneNumber()).isEqualTo(phoneNumber01);
        assertThatThrownBy(() -> contact.setPhoneNumber(""))
                .isInstanceOf(NotValidDataException.class);
        assertThat(contact.getPhoneNumber()).isEqualTo(phoneNumber01);
        assertThatThrownBy(() -> contact.setPhoneNumber("02-3ss3"))
                .isInstanceOf(NotValidDataException.class);
        assertThat(contact.getPhoneNumber()).isEqualTo(phoneNumber01);
//        assertThatThrownBy(() -> contact.setPhoneNumber("010-3ss3-33wa"))
//                .isInstanceOf(NotValidDataException.class);
        Throwable exception = assertThrows(RuntimeException.class, () -> {
            contact.setPhoneNumber("010-3ss3-33wa");
        });
        System.out.println(exception.getMessage());
        assertThat(contact.getPhoneNumber()).isEqualTo(phoneNumber01);
        contact.setPhoneNumber("010-3456-9123");
        assertThat(contact.getPhoneNumber()).isEqualTo("010-3456-9123");

        String zipNumber01 = contact.getZipNumber();
        assertThat(zipNumber01).isNotNull();
        assertThat(zipNumber01).isNotEqualTo("");
        assertThatThrownBy(() -> contact.setZipNumber(null))
                .isInstanceOf(NotValidDataException.class);
        assertThat(contact.getZipNumber()).isEqualTo(zipNumber01);
        assertThatThrownBy(() -> contact.setZipNumber(""))
                .isInstanceOf(NotValidDataException.class);
        assertThat(contact.getZipNumber()).isEqualTo(zipNumber01);
//        assertThatThrownBy(() -> contact.setZipNumber("01233"))
//                .isInstanceOf(NotValidDataException.class);
        exception = assertThrows(RuntimeException.class, () -> {
            contact.setZipNumber("01233");
        });
        System.out.println(exception.getMessage());
        assertThat(contact.getZipNumber()).isEqualTo(zipNumber01);
        assertThatThrownBy(() -> contact.setZipNumber("37s98"))
                .isInstanceOf(NotValidDataException.class);
        assertThat(contact.getZipNumber()).isEqualTo(zipNumber01);
        assertThatThrownBy(() -> contact.setZipNumber("123456"))
                .isInstanceOf(NotValidDataException.class);
        assertThat(contact.getZipNumber()).isEqualTo(zipNumber01);
        contact.setZipNumber("21532");
        assertThat(contact.getZipNumber()).isEqualTo("21532");

        String email01 = contact.getEmail();
        assertThat(email01).isNotNull();
        assertThat(email01).isNotEqualTo("");
        assertThatThrownBy(() -> contact.setEmail(null))
                .isInstanceOf(NotValidDataException.class);
        assertThat(contact.getEmail()).isEqualTo(email01);
        assertThatThrownBy(() -> contact.setEmail(""))
                .isInstanceOf(NotValidDataException.class);
        assertThat(contact.getEmail()).isEqualTo(email01);
        assertThatThrownBy(() -> contact.setEmail("abdk322@gmail"))
                .isInstanceOf(NotValidDataException.class);
        assertThat(contact.getEmail()).isEqualTo(email01);
        assertThatThrownBy(() -> contact.setEmail("naver.com"))
                .isInstanceOf(NotValidDataException.class);
        assertThat(contact.getEmail()).isEqualTo(email01);
        assertThatThrownBy(() -> contact.setEmail("392920dd@eids.com."))
                .isInstanceOf(NotValidDataException.class);
        assertThat(contact.getEmail()).isEqualTo(email01);
        contact.setEmail("dksie922@mail.korea.ac.kr");
        assertThat(contact.getEmail()).isEqualTo("dksie922@mail.korea.ac.kr");
    }

    @Test
    public void testContactList() {
//        IContact contact01 = new 1번클래스(....);
//        IContact contact02 = new 1번클래스(....);
//        IContact contact03 = new 1번클래스(....);
//        IContact contact04 = new 1번클래스(....);
//        클래스 contactlist = new 2번클래스(....);
//        IContactCUD contactCud = contactlist;
//        IContactList contactList = contactlist;
//        IContactList contactList = contactlist;
//        IContactSearch contactSearch = contactlist;
//        this.testContactCUD(contactCud, contactList, contactSearch, contact01, contact02, contact03);

        IContact contact01 = new Contact("홍길동", "010-1111-1111", "39291", "dis2.392@gmail.com");
        IContact contact02 = new Contact("이순신", "010-1111-2222", "45631", "aa2354@gmail.com");
        IContact contact03 = new Contact("을지문덕", "010-1111-3333", "75421", "sdf332@hanmail.co.kr");
        IContact contact04 = new Contact("김유신", "010-1111-4444", "84741", "ddikdg22@naver.com");
        IContact contact05 = new Contact("김유신", "010-1111-4444", "84741", "ddikdg22@naver.com");
        ContactArrayList<IContact> cal = new ContactArrayList();
        IContactCUD contactCud = cal;
        IContactList contactList = cal;
        IContactSearch contactSearch = cal;
        this.testContactCUD(contactCud, contactList, contactSearch
                , contact01, contact02, contact03, contact04);
        System.out.println("result : " + contact05);
        System.out.println("result : ".hashCode());
        System.out.println("result : ".hashCode());
    }

    private void testContactCUD(IContactCUD contactCud
            , IContactList contactList
            , IContactSearch contactSearch
            , IContact ... contacts) {
        assertThat(contactCud).isNotNull();
        assertThat(contacts).isNotNull();
        assertThat(contacts.length).isGreaterThanOrEqualTo(4);

        assertThatThrownBy(() -> contactCud.add(null))
                .isInstanceOf(ParameterNullPointException.class);
        for( int i = 0; i < contacts.length; i++ ) {
            assertThat(contacts[i]).isNotNull();
            contactCud.add(contacts[i]);
            assertThat(contactList.length()).isEqualTo(i+1);
            System.out.println(contacts[i].toString());
        }
        String name01 = contacts[2].getName();
        String phoneNumber01 = contacts[2].getPhoneNumber();
        String zipNumber01 = contacts[2].getZipNumber();
        String email01 = contacts[2].getEmail();

        IContact notFind = contactCud.getContactFromName("392910saa3@$");
        assertThat(notFind).isNull();

        IContact find = contactCud.getContactFromName(name01);
        assertThat(find).isNotNull();
        assertThat(find.getName()).isEqualTo(name01);
        assertThat(find.getPhoneNumber()).isEqualTo(phoneNumber01);
        assertThat(find.getZipNumber()).isEqualTo(zipNumber01);
        assertThat(find.getEmail()).isEqualTo(email01);

        assertThatThrownBy(() -> contactCud.update(null))
                .isInstanceOf(ParameterNullPointException.class);
        IContact update = find;
        update.setPhoneNumber("010-0000-1111");
        update.setZipNumber("98415");
        update.setEmail("d8339s-202.3992@gmail.co.kr");
        contactCud.update(update);

        IContact findUpdate = contactCud.getContactFromName(name01);
        assertThat(findUpdate).isNotNull();
        assertThat(findUpdate.getName()).isEqualTo(update.getName());
        assertThat(findUpdate.getPhoneNumber()).isEqualTo(update.getPhoneNumber());
        assertThat(findUpdate.getZipNumber()).isEqualTo(update.getZipNumber());
        assertThat(findUpdate.getEmail()).isEqualTo(update.getEmail());

        assertThatThrownBy(() -> contactCud.delete(null))
                .isInstanceOf(ParameterNullPointException.class);
        assertThat(contactList.length()).isEqualTo(4);
        contactCud.delete(findUpdate);
        assertThat(contactList.length()).isEqualTo(3);

        List<IContact> searchList = contactSearch.getListFromContainsName("신");
        for( IContact con : searchList ) {
            assertThat(con.getName()).contains("신");
        }
    }

    @Test
    public void testTrys() {
        try {
            int i = 0;
            this.testTryIn();
        } catch (LeeSunSinException e) {
            System.out.println("이순신 : " + e.getMessage());
        } catch (LeeKangSunException e) {
            System.out.println("이강순 : " + e.getMessage());
        } catch (LeeKangMalException e) {
            System.out.println("이강말 : " + e.getMessage());
        } catch (LeeKangJaException e) {
            System.out.println("이강자 : " + e.getMessage());
        } catch (LeeSunImException e) {
            System.out.println("이순임 : " + e.getMessage());
        } catch (LeeFamilyException e) {
            System.out.println("이씨가족 : " + e.getMessage());
        }
    }

    private void testTryIn() throws LeeSunSinException, LeeKangSunException, LeeKangMalException
        , LeeKangJaException, LeeSunImException, LeeFamilyException {
//        throw new LeeSunSinException("이순신");
//        throw new LeeKangSunException("이강순");
//        throw new LeeKangMalException("이강말");
//        throw new LeeKangJaException("이강자");
//        throw new LeeSunImException("이순임");
        throw new LeeFamilyException("이씨가족");
    }
}
