package com.mjc813;

import com.mjc813.mjc_exam_lib.IContact;
import org.jetbrains.annotations.NotNull;

public class Contact implements IContact {
    @NotNull
    private String name;
    private String phoneNumber;
    private String zipNumber;
    private String email;
//    private MjcValidCheck mvc;
    private MjcValidCheck mvc = new MjcValidCheck();

    public Contact(String name, String phoneNumber
            , String zipNumber, String email) throws NotValidDataException {
//        this.mvc = new MjcValidCheck();
        this.setName(name);
        this.setPhoneNumber(phoneNumber);
        this.setZipNumber(zipNumber);
        this.setEmail(email);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String s) throws NotValidDataException {
        if ( s == null || "".equals(s) ) {
            String msg = String.format("name need not null data");
            throw new NotValidDataException(msg);
        }
        this.name = s;
    }

    @Override
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public void setPhoneNumber(String s) throws NotValidDataException {
        if ( this.mvc.isValidPhoneNumber(s) ) {
            this.phoneNumber = s;
        } else {
            String msg = String.format("[{%s}]This is not valid phone number. [010-NNNN-NNNN] please input this type", s);
            throw new NotValidDataException(msg);
        }
    }

    @Override
    public String getZipNumber() {
        return this.zipNumber;
    }

    @Override
    public void setZipNumber(String s) throws NotValidDataException {
        if ( this.mvc.isValidZipNumber(s) ) {
            this.zipNumber = s;
        } else {
            String msg = String.format("[{%s}]Wrong zip number. [NNNNN] please input this, first letter must not '0'.", s);
            throw new NotValidDataException(msg);
        }
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String s) throws NotValidDataException {
        if ( this.mvc.isValidEmail(s) ) {
            this.email = s;
        } else {
            String msg = String.format("[{%s}]는 유효한 이메일 아닙니다. [이메일아이디@인터넷도메인] 형식으로 입력하세요", s);
            throw new NotValidDataException(msg);
        }
    }

    @Override
    public String getContactFullInfo() {
        return String.format("{name:\"%s\", phoneNumber:\"%s\", zip:\"%s\", email:\"%s\"}"
                , this.getName()
                , this.getPhoneNumber()
                , this.getZipNumber()
                , this.getEmail()
        );
    }

    @Override
    public String toString() {
        return this.getContactFullInfo();
    }

    /**
     * 교재 500 page 의 Object 클래스의 equals 멤버메소드를 Override 했다.
     * equal 메소드는 객체의 멤버변수값이 모두 같은지 비교하는 용도로 재정의
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if ( obj instanceof Contact cont ) {
            if ( !this.getName().equals(cont.getName()) ) {
                return false;
            }
            if ( !this.getPhoneNumber().equals(cont.getPhoneNumber()) ) {
                return false;
            }
            if ( !this.getZipNumber().equals(cont.getZipNumber()) ) {
                return false;
            }
            if ( !this.getEmail().equals(cont.getEmail()) ) {
                return false;
            }
            return true;
        }
        return false;
    }
}
