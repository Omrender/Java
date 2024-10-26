package com.epam.rd.contactbook;

public class Contact {

    private String name;
    private ContactInfo phoneNumber;
    private ContactInfo[] emails;
    private ContactInfo[] socialMedias;
    private int emailCount = 0;
    private int socialMediaCount = 0;

    public Contact(String name) {
        this.name = name;
        this.emails = new ContactInfo[3];
        this.socialMedias = new ContactInfo[5];
    }

    private class NameContactInfo implements ContactInfo {
        @Override
        public String getTitle() {
            return "Name";
        }

        @Override
        public String getValue() {
            return Contact.this.name;
        }
    }

    public static class Email implements ContactInfo {
        private final String title;
        private final String value;

        public Email(String localPart, String domain) {
            this.title = "Email";
            this.value = localPart + "@" + domain;
        }

        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public String getValue() {
            return value;
        }
    }

    public static class Social implements ContactInfo {
        private final String title;
        private final String value;

        public Social(String title, String id) {
            this.title = title;
            this.value = id;
        }

        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public String getValue() {
            return value;
        }
    }

    public void rename(String newName) {
        if (newName != null && !newName.trim().isEmpty()) {
            this.name = newName;
        }
    }

    public Email addEmail(String localPart, String domain) {
        if (emailCount < emails.length) {
            Email email = new Email(localPart, domain);
            emails[emailCount++] = email;
            return email;
        }
        return null;
    }

    public Email addEpamEmail(String firstname, String lastname) {
        if (emailCount < emails.length) {
            Email epamEmail = new Email(firstname + "_" + lastname, "epam.com") {
                @Override
                public String getTitle() {
                    return "Epam Email";
                }
            };
            emails[emailCount++] = epamEmail;
            return epamEmail;
        }
        return null;
    }

    public ContactInfo addPhoneNumber(int code, String number) {
        if (phoneNumber == null) {
            phoneNumber = new ContactInfo() {
                @Override
                public String getTitle() {
                    return "Tel";
                }

                @Override
                public String getValue() {
                    return "+" + code + " " + number;
                }
            };
            return phoneNumber;
        }
        return null;
    }

    public Social addTwitter(String twitterId) {
        return addSocialMedia("Twitter", twitterId);
    }

    public Social addInstagram(String instagramId) {
        return addSocialMedia("Instagram", instagramId);
    }

    public Social addSocialMedia(String title, String id) {
        if (socialMediaCount < socialMedias.length) {
            Social social = new Social(title, id);
            socialMedias[socialMediaCount++] = social;
            return social;
        }
        return null;
    }

    public ContactInfo[] getInfo() {
        int totalEntries = 1 + (phoneNumber != null ? 1 : 0) + emailCount + socialMediaCount;
        ContactInfo[] infoArray = new ContactInfo[totalEntries];
        int index = 0;

        // Name contact info
        infoArray[index++] = new NameContactInfo();

        // Phone number
        if (phoneNumber != null) {
            infoArray[index++] = phoneNumber;
        }

        // Email entries
        for (int i = 0; i < emailCount; i++) {
            infoArray[index++] = emails[i];
        }

        // Social media entries
        for (int i = 0; i < socialMediaCount; i++) {
            infoArray[index++] = socialMedias[i];
        }

        return infoArray;
    }
}
