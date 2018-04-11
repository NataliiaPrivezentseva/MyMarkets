package com.example.asterisk.mymarkets;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;

public class MarketFranceFragment extends Fragment {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = MarketFranceFragment.class.getSimpleName();

    /**
     * URL for fetch information about markets in France
     */
    private static final String REQUEST_URL_FR =
            "https://api.ig.com/deal/samples/markets/ANDROID_PHONE/fr_FR/frm";

    ListView listView;

    public MarketFranceFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.market_list, container, false);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        listView = (ListView) rootView.findViewById(R.id.list);

        // Kick off an {@link AsyncTask} to perform the network request
        FragmentAsyncTask task = new FragmentAsyncTask();
        task.execute(REQUEST_URL_FR);

        return rootView;
    }

    /**
     * {@link AsyncTask} to perform the network request on a background thread, and then
     * update the UI with list of markets in the response.
     */
    private class FragmentAsyncTask extends AsyncTask<String, Void, ArrayList<Market>> {

        @Override
        protected ArrayList<Market> doInBackground(String... urls) {
            // Create URL object
            URL url = createUrl(urls[0]);

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Problem making the HTTP request.", e);
            }

            // Extract relevant fields from the JSON response,
            // create and return an {@link ArrayList<Market>} object
            return extractFeatureFromJson(jsonResponse);
        }

        @Override
        protected void onPostExecute(ArrayList<Market> markets) {
            // Create an {@link MarketAdapter}, whose data source is a list of {@link Market}s. The
            // adapter knows how to create list items for each item in the list.
            MarketAdapter adapter = new MarketAdapter(getActivity(), markets);

            // Make the {@link ListView} use the {@link MarketAdapter} we created above, so that the
            // {@link ListView} will display list items for each {@link Market} in the list.
            listView.setAdapter(adapter);
        }

        /**
         * Make an HTTP request to the given URL and return a String as the response.
         */
        private String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";

            // If the URL is null, then return early.
            if (url == null) {
                return jsonResponse;
            }

            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();

                // If the request was successful (response code 200),
                // then read the input stream and parse the response.
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                } else {
                    Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    inputStream.close();
                }
            }
            return jsonResponse;

        }

        /**
         * Return an {@link  ArrayList<Market>} object by parsing out information
         * from the input earthquakeJSON string.
         */
        private ArrayList<Market> extractFeatureFromJson(String jsonResponse) {
            // If the JSON string is empty or null, then return early.
            if (TextUtils.isEmpty(jsonResponse)) {
                return null;
            }

            // Create a new {@link ArrayList<Market>} object
            final ArrayList<Market> markets = new ArrayList<>();

            try {
                JSONObject root = new JSONObject(jsonResponse);

                JSONArray marketsArray = root.getJSONArray("markets");

                // If there are results in the features array
                if (marketsArray.length() > 0) {
                    // Extract out the list of markets and create Market objects with
                    // instrumentName and displayOffer fields.
                    // Add created objects to an ArrayList<Market> object
                    for (int i = 0; i < marketsArray.length(); i++) {
                        JSONObject marketObject = marketsArray.getJSONObject(i);
                        markets.add(new Market
                                (marketObject.getString("instrumentName"),
                                        marketObject.getString("displayOffer")));
                    }

                    Collections.sort(markets, Market.MarketInstrumentNameComparator);

                    return markets;
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
            }
            return null;
        }

        /**
         * Convert the {@link InputStream} into a String which contains the
         * whole JSON response from the server.
         */
        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader =
                        new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }

        /**
         * Returns new URL object from the given string URL.
         */
        private URL createUrl(String stringUrl) {
            URL url;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                Log.e(LOG_TAG, "Error with creating URL", exception);
                return null;
            }
            return url;
        }
    }
}