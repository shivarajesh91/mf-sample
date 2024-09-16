package com.krishna.coding.mf;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

public class JwtSecretMakerTest {

    @Test
    public void generateSceretKey(){
        SecretKey key = Jwts.SIG.HS512.key().build();
        String encodedKey = DatatypeConverter.printHexBinary(key.getEncoded());
        System.out.printf("\nKey = [%s]\n", encodedKey);
    }
    // Key = [DBCD95B41D27595CE4F5AB445E1E823B9570583F4E107E32E824D5149F7BE1700ECE0C251C8259052107C698E0163BBD34085B292D33EBE1CC574F7BB1096E31]
}
