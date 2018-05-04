package app.citta.utilities.SoapClass;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SoapServiceCall {
    private URL mUrl;
    private String serviceUrl, body;
    private String mResult, mResponse;
    private String mMethodName;
    private int cnt;

    public SoapServiceCall(String url) {
        try {
            mUrl = new URL(url);
            serviceUrl = mUrl.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    /******** METHOD TO GENERATE SOAP BODY AND CALL SERVICE WITH OBJECT AS A PARAMETER ********/

    public String CallServiceObject(String soapAction, String methodName, String objName, Object... args) throws IOException, IllegalArgumentException, IllegalAccessException {
        mMethodName = methodName;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL u = new URL(serviceUrl);
        HttpURLConnection connection;
        URLConnection uc = u.openConnection();
        connection = (HttpURLConnection) uc;

        connection.setDoOutput(true);
        //connection.setDoInput(true);
        connection.setRequestProperty("SOAPAction", soapAction);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-type", "text/xml; charset=utf-8");

        body = "";
        cnt = checkForNullArgs(args);

        if (cnt > 0) {
            // Creating SOAP Body for which allow NULL parameters
            body = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\" xmlns:bus=\"http://schemas.datacontract.org/2004/07/BUSINESS\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\">" +
                    "<soapenv:Header/>" +
                    "<soapenv:Body>" +
                    "<tem:" + methodName + ">" +
                    "<tem:" + objName + ">";

            body += buildArgs(args);

            body += "</tem:" + objName + ">" +
                    "</tem:" + methodName + ">" +
                    "</soapenv:Body>" +
                    "</soapenv:Envelope>";
        } else {
            // Creating SOAP Body which doesn't allowed NULL parameters
            body = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\" xmlns:bus=\"http://schemas.datacontract.org/2004/07/BUSINESS\">" +
                    "<soapenv:Header/>" +
                    "<soapenv:Body>" +
                    "<tem:" + methodName + ">" +
                    "<tem:" + objName + ">";

            body += buildArgs(args);

            body += "</tem:" + objName + ">" +
                    "</tem:" + methodName + ">" +
                    "</soapenv:Body>" +
                    "</soapenv:Envelope>";
        }

        OutputStream out = connection.getOutputStream();

        Writer wout = new OutputStreamWriter(out);

        connection.connect();

        wout.write(body);

        wout.flush();

        wout.close();

        // Retrieving Service Response Code
        String code = String.valueOf(connection.getResponseCode());
        InputStream in = null;

        if (Integer.parseInt(code) >= 200 && Integer.parseInt(code) < 400) {
            in = connection.getInputStream();
        } else {
            in = connection.getErrorStream();
        }

        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String result = null;
        while ((result = rd.readLine()) != null) {
            System.out.println(result);
            mResult = result;
        }

        return mResult;
    }


    /******** METHOD TO GENERATE SOAP BODY AND CALL SERVICE WITHOUT OBJECT AS A PARAMETER ********/

    public String CallServiceWithoutObject(String soapAction, String methodName, Object... args) throws IOException, IllegalArgumentException, IllegalAccessException {
        mMethodName = methodName;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL u = new URL(serviceUrl);
        HttpURLConnection connection;
        URLConnection uc = u.openConnection();
        connection = (HttpURLConnection) uc;

        connection.setDoOutput(true);
        //connection.setDoInput(true);
        connection.setRequestProperty("SOAPAction", soapAction);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-type", "text/xml; charset=utf-8");

        body = "";
        cnt = checkForNullArgs(args);

        if (cnt > 0) {
            // Creating SOAP Body for which allow NULL parameters
            body = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\" xmlns:bus=\"http://schemas.datacontract.org/2004/07/BUSINESS\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\">" +
                    "<soapenv:Header/>" +
                    "<soapenv:Body>" +
                    "<tem:" + methodName + ">";

            body += buildArgs(args);

            body += "</tem:" + methodName + ">" +
                    "</soapenv:Body>" +
                    "</soapenv:Envelope>";
        } else {
            // Creating SOAP Body which doesn't allowed NULL parameters
            body = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\" xmlns:bus=\"http://schemas.datacontract.org/2004/07/BUSINESS\">" +
                    "<soapenv:Header/>" +
                    "<soapenv:Body>" +
                    "<tem:" + methodName + ">";

            body += buildArgs(args);

            body += "</tem:" + methodName + ">" +
                    "</soapenv:Body>" +
                    "</soapenv:Envelope>";
        }

        OutputStream out = connection.getOutputStream();

        Writer wout = new OutputStreamWriter(out);

        connection.connect();

        wout.write(body);

        wout.flush();

        wout.close();

        // Retrieving Service Response Code
        String code = String.valueOf(connection.getResponseCode());
        InputStream in = null;

        if (Integer.parseInt(code) >= 200 && Integer.parseInt(code) < 400) {
            in = connection.getInputStream();
        } else {
            in = connection.getErrorStream();
        }

        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String result = null;
        while ((result = rd.readLine()) != null) {
            System.out.println(result);
            mResult = result;
        }

        return mResult;
    }


    /**** METHOD TO CHECK NULL PARAMETERS IN SOAP BODY ****/

    private int checkForNullArgs(Object... args) throws IllegalArgumentException, IllegalAccessException {
        String result = "";
        String argName = "";

        for (int i = 0; i < args.length; i++) {
            if (i % 2 == 0) {
                argName = args[i].toString();
            } else {
                if (args[i].equals("") || args[i].equals("null") || args[i] == null) {
                    cnt++;
                }
            }
        }

        return cnt;
    }


    /**** METHOD TO BUILD SOAP METHOD ARGUMENTS WITH VALUE AND NAME USING ODD/EVEN LOGIC ****/

    private String buildArgs(Object... args) throws IllegalArgumentException, IllegalAccessException {
        String result = "";
        String argName = "";

        for (int i = 0; i < args.length; i++) {
            if (i % 2 == 0) {
                argName = args[i].toString();
            } else {
                if (args[i].equals("")) {
                    result = result + "<" + argName + " " + "xsi:nil=\"true\"" + "/>";
                } else {
                    result += "<" + argName + ">";
                    result += buildArgValue(args[i]);
                    result += "</" + argName + ">";
                }
            }
        }

        return result;
    }


    private String buildArgValue(Object obj) throws IllegalArgumentException, IllegalAccessException {
        Class<?> cl = obj.getClass();
        String result = "";
        if (cl.isPrimitive()) return obj.toString();
        if (cl.getName().contains("java.lang.")) return obj.toString();
        if (cl.getName().equals("java.util.Date")) {
            DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            return dfm.format((Date) obj);
        }

        if (cl.isArray()) {
            String xmlName = cl.getName().substring(cl.getName().lastIndexOf(".") + 1);
            xmlName = xmlName.replace(";", "");
            Object[] arr = (Object[]) obj;
            for (int i = 0; i < arr.length; i++) {
                result += "<bus:" + xmlName + ">";
                result += buildArgValue(arr[i]);
                result += "</" + xmlName + ">";
            }
            return result;
        }

        Field[] fields = cl.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            result += "<" + fields[i].getName() + ">";
            result += buildArgValue(fields[i].get(obj));
            result += "</" + fields[i].getName() + ">";
        }

        return result;
    }


    /**** METHOD TO FETCH SOAP SERVICE RESPONSE ****/

    public String getResult() {
        return mResult;
    }


    /**** METHOD TO FETCH RETURN VALUE OF RESPONSE ****/

    public Object getReturnValue(Class<?> cl) throws IllegalAccessException, InstantiationException, ParseException {
        return getVariableValue(mResult, mMethodName + "Result", cl);
    }


    /**** METHOD TO GET VARIABLE VALUE WITHIN XML TAG FROM SERVICE RESPONSE ****/

    public Object getVariableValue(String name, Class<?> cl) throws IllegalAccessException, InstantiationException, ParseException {
        return getVariableValue(mResult, name, cl);
    }

    private Object getVariableValue(String body, String name, Class<?> cl) throws IllegalAccessException, InstantiationException, ParseException {
        int start = body.indexOf("<" + name + ">");
        start += name.length() + 2; //with < and > char
        int end = body.indexOf("</" + name + ">");
        if (end == -1)
            body = "";
        else
            body = body.substring(start, end);

        if (cl.getName().toLowerCase().contains("string")) return body;
        if (cl.getName().toLowerCase().contains("double")) return Double.parseDouble(body);
        if (cl.getName().toLowerCase().contains("date")) {
            DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dfm.parse(body.replace("T", " "));
        }
        if (cl.getName().toLowerCase().contains("boolean")) return Boolean.parseBoolean(body);

        if (cl.isArray()) {
            if (body == "") return Array.newInstance(cl.getComponentType(), 0); //return empty array

            String xmlName = cl.getName().substring(cl.getName().lastIndexOf(".") + 1);
            xmlName = xmlName.replace(";", "");

            String[] items = body.split("</" + xmlName + ">");
            Object arr = Array.newInstance(cl.getComponentType(), items.length);
            for (int i = 0; i < items.length; i++) {
                items[i] += "</" + xmlName + ">";
                Array.set(arr, i, getVariableValue(items[i], xmlName, cl.getComponentType()));
            }
            return arr;
        }

        Object result = cl.newInstance();
        Field[] fields = cl.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].set(result, getVariableValue(body, fields[i].getName(), fields[i].getType()));
        }
        return result;
    }
}