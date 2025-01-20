package org.example.mobile;

import java.util.ArrayList;
import java.util.List;

public class MobilePhone {
    private String myNumber;
    private List<Contact> myContacts;

    // Constructor sadece myNumber alıyorsa
    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<>();
    }

    // Constructor hem myNumber hem de myContacts alıyorsa
    public MobilePhone(String myNumber, List<Contact> myContacts) {
        this.myNumber = myNumber;
        this.myContacts = myContacts;
    }

    // Getter ve Setter metodları
    public String getMyNumber() {
        return myNumber;
    }

    public List<Contact> getMyContacts() {
        return myContacts;
    }
    
    // 1) Rehbere yeni kişi ekleme
    public boolean addNewContact(Contact contact) {
        if (findContact(contact.getName()) >= 0) {
            // İsim zaten listede mevcut
            return false;
        }
        myContacts.add(contact);
        return true;
    }

    // 2) Var olan kişiyi güncelleme
    public boolean updateContact(Contact oldContact, Contact newContact) {
        int foundPosition = findContact(oldContact);
        if (foundPosition < 0) {
            // Eski kişi listede yok
            return false;
        }
        // Aynı isimli başka bir kişi var mı kontrolü (İsteğe bağlı ek güvenlik)
        int existingContactIndex = findContact(newContact.getName());
        if (existingContactIndex >= 0 && existingContactIndex != foundPosition) {
            // Yeni contact ismi, başka bir kayıt ile çakışıyor
            return false;
        }
        // Güncelle
        myContacts.set(foundPosition, newContact);
        return true;
    }

    // 3) Kişi silme
    public boolean removeContact(Contact contact) {
        int foundPosition = findContact(contact);
        if (foundPosition < 0) {
            return false;
        }
        myContacts.remove(foundPosition);
        return true;
    }

    // 4) findContact(Contact contact) => index döndür
    public int findContact(Contact contact) {
        return myContacts.indexOf(contact);
    }

    // 5) findContact(String contactName) => index döndür
    public int findContact(String contactName) {
        for (int i = 0; i < myContacts.size(); i++) {
            // listedeki contact'ı sırayla al
            Contact currentContact = myContacts.get(i);
            if (currentContact.getName().equalsIgnoreCase(contactName)) {
                return i;
            }
        }
        return -1; // Bulunamadı
    }

    // 6) queryContact(String name) => Contact döndür
    public Contact queryContact(String name) {
        int position = findContact(name);
        if (position >= 0) {
            return myContacts.get(position);
        }
        return null;
    }

    // 7) Tüm Contact'ları yazdır
    public void printContacts() {
        System.out.println("Contact List:");
        if(myContacts.isEmpty()) {
            System.out.println("Rehberiniz boş...");
        } else {
            for (int i = 0; i < myContacts.size(); i++) {
                Contact contact = myContacts.get(i);
                System.out.println(contact.getName() + " -> " + contact.getPhoneNumber());
            }
        }
    }
}