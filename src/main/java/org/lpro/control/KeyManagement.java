/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.control;

import java.security.Key;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Nicolas
 */
public class KeyManagement {

    public Key generateKey() {
        String keyString = "gIpE";
        Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
        return key;
    }
}
