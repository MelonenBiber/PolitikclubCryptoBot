package de.melonenbiber.utils;

import java.util.Map;

public class Cryptocurrency
{
    public String id;
    public String symbol;
    public String name;
    public int market_cap_rank;
    public int coingecko_rank;
    //public List<NameValuePair> ath;
    public MarketData market_data;
    public String error;
    Description description;

    public class MarketData
    {
        public Map<String, Double> current_price;

        public Map<String, Double> ath;
        public Map<String, Double> atl;
        public Map<String, Double> ath_change_percentage;
        public Map<String, Double> atl_change_percentage;

        public Map<String, Double> market_cap;
        public int market_cap_rank;
        public Map<String, Double> total_volume;

        public Map<String, Double> high_24h;
        public Map<String, Double> low_24h;

        public double price_change_24h;
        public double price_change_percentage_24h;
        public double price_change_7d;
        public double price_change_14d;
        public double price_change_30d;
        public double price_change_60d;
        public double price_change_200d;
        public double price_change_1y;

        public double market_cap_change_24h;
        public double market_cap_change_percentage_24h;

        public Map<String, Double> price_change_24h_in_currency;
        public Map<String, Double> price_change_percentage_1h_in_currency;
        public Map<String, Double> price_change_percentage_24h_in_currency;
        public Map<String, Double> price_change_percentage_7d_in_currency;
        public Map<String, Double> price_change_percentage_14d_in_currency;
        public Map<String, Double> price_change_percentage_30d_in_currency;
        public Map<String, Double> price_change_percentage_60d_in_currency;
        public Map<String, Double> price_change_percentage_200d_in_currency;
        public Map<String, Double> price_change_percentage_1y_in_currency;
        public Map<String, Double> market_cap_change_24h_in_currency;
        public Map<String, Double> market_cap_change_percentage_24h_in_currency;

        public double total_supply;
        public double max_supply;
        public double circulating_supply;


    }

    public class Description
    {
        public String en;
    }
}
