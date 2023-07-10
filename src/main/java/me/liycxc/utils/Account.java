package me.liycxc.utils;

import me.liycxc.Main;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * This file is part of AutoXGP Remake project.
 * Copyright 2023 Liycxc
 * All Rights Reserved.
 *
 * @author Liycxc
 * @date: 2023-07-09
 * @time: 16:19
 */
public class Account {
    public static File locAcc = new File("locAcc.txt");

    public static String[] getLocAcc() {
        String[] account = new String[]{null, null};

        try {
            if (!locAcc.exists()) {
                locAcc.createNewFile();
            }

            BufferedReader bufferedReader = new BufferedReader(new FileReader(locAcc));
            String str = bufferedReader.readLine();
            if (str.contains(":") || str.contains("----")) {
                account = str.split("\\|+|-{4}");
            }
            bufferedReader.close();
        } catch (Exception exception) {
        }

        return account;
    }

    public static String[] getMailByApi() {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContexts.custom().loadTrustMaterial(null, (x509Certificates, s) -> true).build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            e.printStackTrace();
        }

        CloseableHttpClient client = HttpClients.custom().setSSLContext(sslContext).
                setSSLHostnameVerifier(new NoopHostnameVerifier()).build();

        HttpGet httpGet = new HttpGet(URI.create(Main.YX_API));

        RequestConfig.Builder builder = RequestConfig
                .custom()
                .setConnectTimeout(30000)
                .setSocketTimeout(30000)
                .setConnectionRequestTimeout(60000);

        RequestConfig defaultRequestConfig = builder.build();
        httpGet.setConfig(defaultRequestConfig);

        try (CloseableHttpClient httpClient = client; CloseableHttpResponse execute = httpClient.execute(httpGet);) {
            if (execute.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                System.out.println("Get mail api error status code: " + execute.getStatusLine().getStatusCode());
                return null;
            }

            HttpEntity entity = execute.getEntity();
            // like -> metelngonyar@hotmail.com----Gn37ms56</br>
            String responseString = EntityUtils.toString(entity);

            System.out.println("Response: " + responseString);

            String[] parts = responseString.split("\\|+|-{4}");

            String[] result = new String[parts.length];
            System.arraycopy(parts, 0, result, 0, parts.length);
            result[0] = result[0].replace("<br>", "").replace("</br>", "");
            result[1] = result[1].replace("<br>", "").replace("</br>", "");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
