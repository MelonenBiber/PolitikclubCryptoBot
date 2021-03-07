package de.melonenbiber.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class CoingeckoApiUtils
{
    private static final String coingeckoUri = "https://api.coingecko.com/api/v3/";
    private static final Gson gson = new Gson();
    private static  List<SimpleCryptocurrency> everySimpleCryptocurrency;

    static
    {
        try
        {
            everySimpleCryptocurrency = new Gson().fromJson(
                    new FileReader("coins.json"),
                    new TypeToken<List<SimpleCryptocurrency>>()
                    {
                    }.getType());
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static Cryptocurrency getCryptocurrency(String currency) throws ApiCallException
    {
        String response = makeAPICall(coingeckoUri + "coins/" + currency);

        Cryptocurrency c = gson.fromJson(response, Cryptocurrency.class);
        if (c.error != null)
            throw new ApiCallException(c.error);

        return c;
    }

    public static String makeAPICall(String uri) throws ApiCallException
    {
        String responseContent = "";

        HttpGet request;
        try
        {
            request = new HttpGet(new URI(uri));
        } catch (URISyntaxException e)
        {
            e.printStackTrace();
            throw new ApiCallException("Api Uri '" + uri + "' not available.");
        }

        request.setHeader(HttpHeaders.ACCEPT, "application/json");


        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response;
        try
        {
            response = client.execute(request);
            try
            {
                HttpEntity entity = response.getEntity();
                responseContent = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
            } finally
            {
                response.close();
            }
        } catch (IOException | ParseException e)
        {
            e.printStackTrace();
        }

        return responseContent;
    }

    public static List<SimpleCryptocurrency> getAllSimpleCryptocurrencies()
    {
        return everySimpleCryptocurrency;
    }
}
