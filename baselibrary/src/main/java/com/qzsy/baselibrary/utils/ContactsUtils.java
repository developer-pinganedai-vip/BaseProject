package com.qzsy.baselibrary.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.qzsy.insuranceloan.ui.authprocess.bean.DirectoriesBean;

import java.util.HashSet;
import java.util.Set;

public class ContactsUtils {
    private static final String[] PHONES_PROJECTION = new String[]{
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER};

    public interface OnContactsCallBack {
        void onContactsSuccess(Set<DirectoriesBean> set);

        void onContactsFail();

        void onContactsCatch(Throwable catchMsg);
    }

    public void getContacts(Context context, OnContactsCallBack contactsCallBack) {
        Cursor cursor = null;
        try {
            Set<DirectoriesBean> set = new HashSet<>();
            cursor = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            if (cursor!=null&&cursor.getCount()>0){
                while (cursor.moveToNext()) {
                    String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    Cursor phoneCursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "="+contactId, null, null);
                    if (phoneCursor!=null){

                        while (phoneCursor.moveToNext()) {
                            String number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                            DirectoriesBean directoriesBean = new DirectoriesBean(name,number);
                            set.add(directoriesBean);
                        }
                        phoneCursor.close();
                    }
                }
                contactsCallBack.onContactsSuccess(set);
                cursor.close();
            }else{
                contactsCallBack.onContactsFail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            contactsCallBack.onContactsCatch(e);
        }finally {
            if (cursor!=null){
                cursor.close();
            }
        }
    }
}
