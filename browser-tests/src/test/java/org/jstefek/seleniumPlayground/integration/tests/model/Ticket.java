package org.jstefek.seleniumPlayground.integration.tests.model;

import java.io.File;

/**
 * Object representation of T-Mobile ticket
 */
public class Ticket {

    private final String subject;
    private final String content;
    private final String phoneNumber;
    private final String email;
    private final boolean sendCopy;
    private final File attachment;

    public Ticket(String subject, String content, String phoneNumber, String email, boolean sendCopy, File attachment) {
        this.subject = subject;
        this.content = content;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.sendCopy = sendCopy;
        this.attachment = attachment;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public boolean isSendCopy() {
        return sendCopy;
    }

    public File getAttachment() {
        return attachment;
    }

    public static class Builder {

        private String subject = "test";
        private String content = "test message";
        private String phoneNumber = "101010101";
        private String email = "test@test.mail";
        private boolean sendCopy = false;
        private File attachment = null;

        public Builder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setSendCopy(boolean sendCopy) {
            this.sendCopy = sendCopy;
            return this;
        }

        public Builder setAttachment(File attachment) {
            this.attachment = attachment;
            return this;
        }

        public Ticket createTicket() {
            return new Ticket(subject, content, phoneNumber, email, sendCopy, attachment);
        }
    }
}
