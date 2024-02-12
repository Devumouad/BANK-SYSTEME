package bank.management.system;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class PasswordHashing {
    public static String hashPassword(String password) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(password.getBytes());
                StringBuilder hexString = new StringBuilder();
                for (byte b : hash) {
                    String hex = Integer.toHexString(0xff & b);
                    if (hex.length() == 1) hexString.append('0');
                    hexString.append(hex);
                }
                return hexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null; // Return null if hashing algorithm is not available
            }
        }

        public static void main(String[] args) {
            String password = "pass1";
            String hashedPassword = hashPassword(password);
            if (hashedPassword != null) {
                System.out.println("Original Password: " + password);
                System.out.println("Hashed Password: " + hashedPassword);
            } else {
                System.out.println("Password hashing failed.");
            }
        }
    }

