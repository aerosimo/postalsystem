/******************************************************************************
 * This piece of work is to enhance postalsystem project functionality.       *
 *                                                                            *
 * Author:    eomisore                                                        *
 * File:      PostalSystemSOAPService.java                                    *
 * Created:   21/10/2024, 17:15                                               *
 * Modified:  21/10/2024, 17:15                                               *
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

package com.aerosimo.ominet.api;

import com.aerosimo.ominet.models.Postmaster;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.ws.BindingType;
import jakarta.xml.ws.soap.SOAPBinding;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebService(name = "PostalSystem", serviceName = "PostalSystemService",
        portName = "PostalSystemPort", targetNamespace = "https://aerosimo.com/api/ws/postmaster")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class PostalSystemSOAPService {

    private static final Logger log;
    static String Response;

    static {
        log = LogManager.getLogger(PostalSystemSOAPService.class.getName());
    }

    @WebMethod(operationName = "sendEmail")
    @WebResult(name = "Status", partName = "sendEmailResponse")
    public String sendMail(@XmlElement(required = true) @WebParam(name = "emailAddress", partName = "sendEmailRequest") String emailAddress,
                           @XmlElement(required = true) @WebParam(name = "emailSubject", partName = "sendEmailRequest") String emailSubject,
                           @XmlElement(required = true) @WebParam(name = "emailMessage", partName = "sendEmailRequest") String emailMessage,
                           @WebParam(name = "emailFiles", partName = "sendEmailRequest") String emailFiles) {

        if (emailAddress.isEmpty()) {
            Response = "Schema Validation failed because emailAddress is a required field or not valid";
        } else if (emailSubject.isEmpty()) {
            Response = "Schema Validation failed because emailSubject is a required field";
        } else if (emailMessage.isEmpty()) {
            Response = "Schema Validation failed because emailMessage is a required field";
        } else {
            Response = Postmaster.sendEmail(emailAddress, emailSubject, emailMessage, emailFiles, "Aerosimo Support<support@aerosimo.com>", "text/html");
        }
        return Response;
    }
}