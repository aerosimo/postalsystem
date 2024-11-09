/******************************************************************************
 * This piece of work is to enhance postalsystem project functionality.       *
 *                                                                            *
 * Author:    eomisore                                                        *
 * File:      Connect.java                                                    *
 * Created:   21/10/2024, 16:25                                               *
 * Modified:  21/10/2024, 16:25                                               *
 *                                                                            *
 * Copyright (c)  2024.  Aerosimo Ltd                                         *
 *                                                                            *
 * Permission is hereby granted, free of charge, to any person obtaining a    *
 * copy of this software and associated documentation files (the "Software"), *
 * to deal in the Software without restriction, including without limitation  *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,   *
 * and/or sell copies of the Software, and to permit persons to whom the      *
 * Software is furnished to do so, subject to the following conditions:       *
 *                                                                            *
 * The above copyright notice and this permission notice shall be included    *
 * in all copies or substantial portions of the Software.                     *
 *                                                                            *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,            *
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES            *
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND                   *
 * NONINFINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT                 *
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,               *
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING               *
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE                 *
 * OR OTHER DEALINGS IN THE SOFTWARE.                                         *
 *                                                                            *
 ******************************************************************************/

package com.aerosimo.ominet.core;

import jakarta.mail.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;


public class Connect {

    private static final Logger log;

    static {
        log = LogManager.getLogger(Connect.class.getName());
    }

    static Context ctx;
    static Context env;
    static Session sess;

    public static Session email() {
        try {
            log.info("Preparing Mail Session");
            ctx = new InitialContext();
            env = (Context) ctx.lookup("java:/comp/env");
            sess = (Session) env.lookup("mail/aerosimo");
        } catch (Exception err) {
            log.error("Email session failed with the following - {}", Connect.class.getName(), err);
        }
        return sess;
    }
}