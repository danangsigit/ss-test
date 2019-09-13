package com.java.test.ss.commons.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StringUtils extends org.apache.commons.lang3.StringUtils{
    public static final Pattern PATTERN_NUMBER = Pattern.compile("^\\d+$");
    public static final String INDO_PHONE_NUMBER_FORMAT = "+62-%s";
    public static final String INDO_HOME_PHONE_NUMBER_FORMAT = "+62-%s";

    public static final String INDO_PHONE_CODE = "62";
    public static final String INDO_HOME_PHONE_CODE = "62";
    public static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static boolean equalsIgnoreCase(String s1, String s2) {
        return StringUtils.equals(isEmpty(s1) ? "" : s1.toLowerCase(), isEmpty(s2) ? "" : s2.toLowerCase());
    }

    public static String md5(String text) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte aByteData : byteData) {
                sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String base64Encode(String str) {
        Base64.Encoder encoder = Base64.getMimeEncoder();
        byte[] encoded = encoder.encode(str.getBytes());
        return new String(encoded);
    }

    public static String base64Decode(String str) {
        Base64.Decoder decoder = Base64.getMimeDecoder();
        byte[] decoded = decoder.decode(str.getBytes());
        return new String(decoded, StandardCharsets.UTF_8);
    }

    public static String getStackTrace(Throwable aThrowable) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }

    public static boolean validateEmail(String email) {
        String regexStr = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regexStr);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validateUUID(String uuidString) {
        if( uuidString == null) return false;
        try {
            UUID fromStringUUID = UUID.fromString(uuidString);
            String toStringUUID = fromStringUUID.toString();
            return toStringUUID.equals(uuidString);
        } catch(IllegalArgumentException e) {
            return false;
        }
    }

    public static String getTail(String s, int numberOfChar, char c) {
        if(StringUtils.isEmpty(s)) {
            return StringUtils.EMPTY;
        }
        if(numberOfChar > s.length()) {
            StringBuilder strBuilder = new StringBuilder(s);
            for(int i = 0; i < (numberOfChar - s.length()); i++) {
                strBuilder.append(c);
            }
            return strBuilder.toString();
        }
        return StringUtils.substring(s, s.length() - numberOfChar, s.length());
    }

    public static Long parseLong(String string){
        if (!StringUtils.isEmpty(string) && PATTERN_NUMBER.matcher(string).matches()) {
            return Long.parseLong(string);
        }
        return null;
    }

    public static String getFileURL(String fileId, String domain ,String fileApi) {
        return domain + fileApi + "/" + fileId;
    }

    public static String normalizePhoneNumber(String phoneNumber) {
        if (StringUtils.isEmpty(phoneNumber)) {
            return phoneNumber;
        }
        phoneNumber = phoneNumber.trim().replaceAll("[^0-9]", "");
        phoneNumber = phoneNumber.replaceAll("^[0]*", "");

        if (!phoneNumber.startsWith("+")) {
            phoneNumber = "+" + phoneNumber;
        }
        return phoneNumber;
    }

    public static String formatIndoPhoneNumber(String phoneNumber){
        if (phoneNumber == null){
            return null;
        }
        String codePart = phoneNumber.substring(0,2);
        String phonePart = phoneNumber.substring(2);
        if (StringUtils.equals(codePart, INDO_PHONE_CODE) && StringUtils.length(phonePart) >= 10){
            //phone number includes code
            return String.format(INDO_PHONE_NUMBER_FORMAT, phonePart);
        } else if (StringUtils.equals(codePart, INDO_HOME_PHONE_CODE) && StringUtils.length(phonePart) >= 10) {
            return String.format(INDO_HOME_PHONE_NUMBER_FORMAT, phonePart);
        } else{
            //phone number not include code
            return String.format(INDO_PHONE_NUMBER_FORMAT, phoneNumber);
        }
    }

    public static boolean isIndoPhoneNumber(String phoneNumber) {
        return StringUtils.isNotEmpty(phoneNumber) && phoneNumber.matches("^[\\+]*62\\d+$");
    }

    public static String removeAllSpecialCharFromString(String s) {
        if(StringUtils.isEmpty(s)) {
            return StringUtils.EMPTY;
        }
        String result = s.replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s+"," ");
        return result.trim();
    }

    public static String convertStreamToString(InputStream inputStream) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            String result = br.lines().collect(Collectors.joining("\n"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static Boolean checkDobAndKtpMatching(String dob, String ktp) {
        if(StringUtils.isEmpty(dob) || StringUtils.isEmpty(ktp) || ktp.length() < 13) {
            return Boolean.FALSE;
        }
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat yearFormat = new SimpleDateFormat("yyyy");
        Date inputDob = null;
        Date extractDob = null;
        try {
            inputDob = dateFormat1.parse(dob);
        } catch (ParseException ignore) {
            return Boolean.FALSE;
        }
        String inputDobYear = yearFormat.format(inputDob);
        if(StringUtils.isEmpty(inputDobYear) || inputDobYear.length() < 4) {
            return Boolean.FALSE;
        }
        String dobStrFromKtp = ktp.substring(6, 12);
        if(StringUtils.isEmpty(dobStrFromKtp)) {
            return Boolean.FALSE;
        }
        String inputDobYearPrefix = inputDobYear.substring(0, 2);
        String dobStrFromKtpYear = dobStrFromKtp.substring(4);
        String dobStrFromKtpDay = dobStrFromKtp.substring(0,2);
        String dobStrFromKtpMonth = dobStrFromKtp.substring(2,4);
        Integer dobIntFromKtpDay = null;
        try {
            dobIntFromKtpDay = Integer.parseInt(dobStrFromKtpDay);
        } catch (NumberFormatException ignore) {
            return Boolean.FALSE;
        }
        if(dobIntFromKtpDay.compareTo(40) > 0) {
            dobIntFromKtpDay -= 40;
        }
        dobStrFromKtp = String.format("%s-%s-%s%s",dobIntFromKtpDay, dobStrFromKtpMonth, inputDobYearPrefix, dobStrFromKtpYear);
        try {
            extractDob= dateFormat.parse(dobStrFromKtp);
        } catch (ParseException ignore) {
            return Boolean.FALSE;
        }
        return extractDob.compareTo(inputDob) == 0;
    }

    public static String hideEmail(String email){
        return email.replaceAll("(?<=.{2}).(?=[^@]*?.@)", "*");
    }

    public static String hidePhoneNumber(String phoneNumber){
        return phoneNumber.replaceAll("(?<=.{4}).(?=.*.{2}$)", "*");
    }

    public static String firstLetterCapitalWithSingleSpace(final String words) {
        return Stream.of(words.trim().split("\\s"))
                .filter(word -> word.length() > 0)
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining(" "));
    }

    public static boolean checkIsIndonesiaPhone(String phoneNumber) {
        if (isBlank(phoneNumber)) {
            return false;
        } else if (phoneNumber.trim().substring(0, 1).equals("+")) {
            return phoneNumber.trim().substring(1, 3).equals("62");
        } else {
            return phoneNumber.trim().substring(0, 2).equals("62");
        }
    }

    public static String toHex(String input){
        StringBuffer buffer = new StringBuffer();
        int intValue;
        for(int x = 0; x < input.length(); x++)
        {
            int cursor = 0;
            intValue = input.charAt(x);
            String binaryChar = new String(Integer.toBinaryString(input.charAt(x)));
            for(int i = 0; i < binaryChar.length(); i++)
            {
                if(binaryChar.charAt(i) == '1')
                {
                    cursor += 1;
                }
            }
            buffer.append(Integer.toHexString(intValue));
        }
        return buffer.toString();
    }

    public static String generateCode(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public static void main(String... args) {
        System.out.println(hidePhoneNumber("84948459484"));
        int nextDay =0;
        Calendar fromEndDate = Calendar.getInstance();
        fromEndDate.add(Calendar.DATE, nextDay);
        fromEndDate.set(Calendar.HOUR_OF_DAY, 0);
        fromEndDate.set(Calendar.MINUTE, 0);
        fromEndDate.set(Calendar.SECOND, 0);
        fromEndDate.set(Calendar.MILLISECOND, 0);
        System.out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(fromEndDate.getTime()));

        Calendar.getInstance().clear();
        Calendar toEndDate = Calendar.getInstance();
        toEndDate.add(Calendar.DATE, nextDay);
        toEndDate.set(Calendar.HOUR_OF_DAY, 23);
        toEndDate.set(Calendar.MINUTE, 59);
        toEndDate.set(Calendar.SECOND, 59);
        toEndDate.set(Calendar.MILLISECOND, 999);
        System.out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(toEndDate.getTime()));
        Calendar.getInstance().clear();
    }
}
