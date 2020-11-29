package br.ufscar.dc.dsw.utils;

import com.goterl.lazycode.lazysodium.LazySodiumJava;
import com.goterl.lazycode.lazysodium.SodiumJava;
import com.goterl.lazycode.lazysodium.exceptions.SodiumException;
import com.goterl.lazycode.lazysodium.interfaces.PwHash;
import com.sun.jna.NativeLong;

public class HashPassword {

    private static final LazySodiumJava lazySodium = new LazySodiumJava(new SodiumJava());

    public static String generateSalt() {
        byte[] salt = lazySodium.randomBytesBuf(PwHash.ARGON2ID_SALTBYTES);

        return lazySodium.toHexStr(salt);
    }

    public static String hashPassword(String password, String salt) throws SodiumException {
        String hash = lazySodium.cryptoPwHash(password, 64, lazySodium.toBinary(salt), PwHash.ARGON2ID_OPSLIMIT_INTERACTIVE, new NativeLong(PwHash.ARGON2ID_MEMLIMIT_INTERACTIVE), PwHash.Alg.PWHASH_ALG_ARGON2ID13);
        return hash;
    }
    
     public static boolean isSamePassword(String password, String salt, String hashedPassword) throws SodiumException {
        String hash = lazySodium.cryptoPwHash(password, 64, lazySodium.toBinary(salt), PwHash.ARGON2ID_OPSLIMIT_INTERACTIVE, new NativeLong(PwHash.ARGON2ID_MEMLIMIT_INTERACTIVE), PwHash.Alg.PWHASH_ALG_ARGON2ID13);
        return hashedPassword.equals(hash);
    }
}
